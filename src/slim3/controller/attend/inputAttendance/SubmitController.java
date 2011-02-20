package slim3.controller.attend.inputAttendance;

import java.util.Calendar;
import java.util.Date;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.DateUtil;

import slim3.model.Attendance;
import slim3.service.AttendanceService;
import slim3.service.PracticeService;

public class SubmitController extends Controller {
    
    private AttendanceService attendanceSvc = new AttendanceService();
    private PracticeService practiceSvc = new PracticeService();

    @Override
    public Navigation run() throws Exception {
        
        if(asString("regist") != null) {
            // 決定ボタンが押されたときの処理
            attendanceSvc.regist(ContstructAttendance());
        } else if (asString("update") != null) {
            // 更新ボタンが押されたときの処理
            if(customValidate()){
                attendanceSvc.updateInputFlgToFalse(asKey("key"));
            } else {
                errors.put("cannotUpdate", "本日以前の出欠は更新できません。更新したい場合はマネージャーに連絡して下さい。");
            }
        } else if (asString("currentDate") == null) {
            // フォームの情報が存在しない場合はTopPageに遷移する事でエラー回避（F5を想定）
            return forward("/attend/");
        }
        
        return forward(basePath + "?date=" + asString("currentDate"));
    }

    /**
     * Request情報から練習情報を生成します
     * 
     * @return
     */
    private Attendance ContstructAttendance() {
        Attendance attendance = new Attendance();
        attendance.setAttendance(asInteger("attendance"));
        attendance.setRacital(asString("racital"));
        attendance.setMemberKey(asKey("memberKey"));
        attendance.setPracticeKey(asKey("practiceKey"));
        return attendance;
    }
    
    private boolean customValidate() {
        Date currentDay = DateUtil.clearTimePart(getCurrentDate());
        // 更新対象となる出席情報にひもづく練習日が本日より後日付なら更新可能。
        System.out.println(currentDay.compareTo(practiceSvc.searchFromKey(asKey("practiceKey")).getStartDate()));
        System.out.println("currentDay :" + currentDay);
        System.out.println("practiceDay : " + DateUtil.clearTimePart(practiceSvc.searchFromKey(asKey("practiceKey")).getStartDate()));
        if(currentDay.compareTo(DateUtil.clearTimePart(practiceSvc.searchFromKey(asKey("practiceKey")).getStartDate())) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * requestParameters から現在の年月を取得します
     * requestParameters に存在しない場合はシステム日付から取得します。（JST）
     * 
     * @return
     */
    private Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 9);
        return cal.getTime();
    }
}
