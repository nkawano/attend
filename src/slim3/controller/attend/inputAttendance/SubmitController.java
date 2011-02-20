package slim3.controller.attend.inputAttendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Attendance;
import slim3.service.AttendanceService;
import slim3.service.PracticeService;
import slim3.util.AttendDateUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SubmitController extends Controller {

    private AttendanceService attendanceSvc = new AttendanceService();
    private PracticeService practiceSvc = new PracticeService();

    @Override
    public Navigation run() throws Exception {

        if (asString("currentDate") == null) {
            // �t�H�[���̏�񂪑��݂��Ȃ��ꍇ��TopPage�ɑJ�ڂ��鎖�ŃG���[����iF5��z��j
            return forward("/attend/");
        }

        String[] keyArray = requestScope("keyArray");
        Integer index = asInteger("index");
        boolean isAll = asBoolean("allFlg");

        if (!isAll) {
            if (isFuture(index)) {// �������̂ݍX�V(�P�s�݂̂̍X�V�ł��ꉞ�`�F�b�N)
                attendanceSvc.regist(ContstructAttendance(index));
            } else {
                errors.put("cannotUpdate", "���̓��͂����ߋ��̓�������X�V�ł��Ȃ���I");
            }
        } else {

            int recordCount = keyArray.length;// �֋X��keyArray���烌�R�[�h�̃T�C�Y���擾
            List<Attendance> attendanceList = new ArrayList<Attendance>();
            for (int i = 0; i < recordCount; i++) {
                if (isFuture(i)) {
                    // �������̂ݍX�V���X�g�ɒǉ�
                    attendanceList.add(ContstructAttendance(i));
                }
            }
            try {
                // TODO �T�[�r�X�̐݌v�ɂ��Ė₢���킹 kawano
                attendanceSvc.registList(attendanceList);
            } catch (IllegalArgumentException e) {
                errors.put("cannotUpdate", "�o�^�Ɏ��s���܂����B�ēx�o�^���s���Ă݂Ă��������B");
            }
        }

        return forward(basePath + "?date=" + asString("currentDate"));
    }

    /**
     * Request��񂩂���K���𐶐����܂�
     *
     * @return
     */
    private Attendance ContstructAttendance(int index) {

        String[] memberKeyArray = requestScope("memberKeyArray");
        String[] practiceKeyArray = requestScope("practiceKeyArray");
        String[] attendanceArray = requestScope("attendanceArray");
        String[] racitalArray = requestScope("racitalArray");

        Attendance attendance = new Attendance();
        attendance.setAttendance(Integer.parseInt(attendanceArray[index]));
        attendance.setRacital(racitalArray[index]);
        attendance.setMemberKey(KeyFactory.stringToKey(memberKeyArray[index]));
        attendance.setPracticeKey(KeyFactory
            .stringToKey(practiceKeyArray[index]));
        return attendance;
    }

    private boolean isFuture(int index) {

        String[] practiceKeyArray = requestScope("practiceKeyArray");
        Key practiceKey = KeyFactory.stringToKey(practiceKeyArray[index]);

        Date startDate = practiceSvc.searchFromKey(practiceKey).getStartDate();

        if (AttendDateUtil.checkPast(startDate)) {
            return false;
        } else {
            return true;
        }
    }

}