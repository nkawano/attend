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
            // ����{�^���������ꂽ�Ƃ��̏���
            attendanceSvc.regist(ContstructAttendance());
        } else if (asString("update") != null) {
            // �X�V�{�^���������ꂽ�Ƃ��̏���
            if(customValidate()){
                attendanceSvc.updateInputFlgToFalse(asKey("key"));
            } else {
                errors.put("cannotUpdate", "�{���ȑO�̏o���͍X�V�ł��܂���B�X�V�������ꍇ�̓}�l�[�W���[�ɘA�����ĉ������B");
            }
        } else if (asString("currentDate") == null) {
            // �t�H�[���̏�񂪑��݂��Ȃ��ꍇ��TopPage�ɑJ�ڂ��鎖�ŃG���[����iF5��z��j
            return forward("/attend/");
        }
        
        return forward(basePath + "?date=" + asString("currentDate"));
    }

    /**
     * Request��񂩂���K���𐶐����܂�
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
        // �X�V�ΏۂƂȂ�o�ȏ��ɂЂ��Â����K�����{��������t�Ȃ�X�V�\�B
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
     * requestParameters ���猻�݂̔N�����擾���܂�
     * requestParameters �ɑ��݂��Ȃ��ꍇ�̓V�X�e�����t����擾���܂��B�iJST�j
     * 
     * @return
     */
    private Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 9);
        return cal.getTime();
    }
}
