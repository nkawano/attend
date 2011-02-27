package slim3.controller.attend.inputAttendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.DateUtil;

import slim3.constants.Constants;
import slim3.model.Attendance;
import slim3.model.Member;
import slim3.model.Practice;
import slim3.service.AttendanceService;
import slim3.service.PracticeService;

public class IndexController extends Controller {

    private final String DATE_PATTERN = "yyyyMM";
    private AttendanceService attendanceSvc = new AttendanceService();
    private PracticeService practiceSvc = new PracticeService();

    @Override
    public Navigation run() throws Exception {

        if(sessionScope(Constants.SESSION_KEY_LOGIN_USER) == null) {
            // TODO ログインしていないページに飛ばす必要がある
            return forward("/attend/error/");
        }

        String currentDateStr = getCurrentYearAndMonth();

        if(currentDateStr == null || currentDateStr.isEmpty() || currentDateStr.equals("null")) {
            return forward("index.jsp");
        }

        int year = Integer.valueOf(currentDateStr.substring(0, 4));
        int month = Integer.valueOf(currentDateStr.substring(4, 6));

        request.setAttribute("currentYear", year);
        request.setAttribute("currentMonth", month);
        request.setAttribute("nextDate", getNextYearAndMonth(currentDateStr));
        request.setAttribute("currentDate", currentDateStr);
        request.setAttribute("beforeDate", getBeforeYearAndMonth(currentDateStr));
        request.setAttribute("attendanceList", ConstructAttendanceList(year, month));

        return forward("index.jsp");
    }

    /**
     * requestParameters から現在の年月を取得します
     * requestParameters に存在しない場合はシステム日付から取得します。（JST）
     *
     * @return
     */
    private String getCurrentYearAndMonth() {

        String yearAndDateStr = asString("date");

        if(yearAndDateStr == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 9);
            yearAndDateStr = DateUtil.toString(cal.getTime(), DATE_PATTERN);
        }
        return yearAndDateStr;

    }

    /**
     * 現在の年月から翌月の年月を取得します。
     *
     * @param currentDateStr
     * @return
     */
    private String getNextYearAndMonth(String currentDateStr) {
        if(currentDateStr == null) {
            return null;
        }

        Date currentDate = DateUtil.toDate(currentDateStr, DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MONTH, 1);
        return DateUtil.toString(cal.getTime(), DATE_PATTERN);
    }

    /**
     * 現在の年月から先月の年月を取得します。
     *
     * @param currentDateStr
     * @return
     */
    private String getBeforeYearAndMonth(String currentDateStr) {
        if(currentDateStr == null) {
            return null;
        }

        Date currentDate = DateUtil.toDate(currentDateStr, DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MONTH, -1);
        return DateUtil.toString(cal.getTime(), DATE_PATTERN);
    }

    private List<Attendance> ConstructAttendanceList(int year, int month){
        Member loginUser = sessionScope(Constants.SESSION_KEY_LOGIN_USER);
        if(loginUser == null) {
            // TODO ログインしていない例外を発生させる。
        }

        // ログインユーザーと練習日のリストから、画面に表示する出欠情報を取得する。
        List<Attendance> attendanceList = null;// 練習日程が登録されていない場合の画面表示のために、ここではnull
        List<Practice> practiceList = practiceSvc.searchFromYearAndMonth(year, month);

        if(practiceList != null && practiceList.size() > 0){
            attendanceList = new ArrayList<Attendance>();
            for(Practice practice : practiceList) {
                Attendance attendance = attendanceSvc.searchFromMemberIdAndPracticeDate(loginUser.getId(), practice.getStartDate());
                if(attendance == null) {
                    attendance = attendanceSvc.getInitalizedAttendance(loginUser, practice);
                }
                attendanceList.add(attendance);
            }
        }

        return attendanceList;
    }
}
