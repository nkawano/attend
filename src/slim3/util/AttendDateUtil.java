package slim3.util;

import java.util.Calendar;
import java.util.Date;

public class AttendDateUtil {

    public static boolean checkPast(Date targetDate){

        Date practiceDate = targetDate;
        Calendar practiceCalendar = Calendar.getInstance();
        practiceCalendar.setTimeInMillis(practiceDate.getTime());

        int practiceYear = practiceCalendar.get(Calendar.YEAR);
        int practiceMonth = practiceCalendar.get(Calendar.MONTH);
        int practiceDay = practiceCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();

        int sysYear = calendar.get(Calendar.YEAR);
        int sysMonth = calendar.get(Calendar.MONTH);
        int sysDay = calendar.get(Calendar.DAY_OF_MONTH);

        if (practiceYear < sysYear) {
            // �Ώۓ��̔N�x���ߋ��̏ꍇ
            return true;
        } else if (practiceYear > sysYear) {
            // �Ώۓ��̔N�x�������̏ꍇ
            return false;
        }

        // �ȉ��A�Ώۓ��̔N�x���V�X�e�����t�̔N�x�Ɠ����ꍇ
        if (practiceMonth < sysMonth) {
            // �Ώۓ��̌����ߋ��̏ꍇ
            return true;
        } else if (practiceMonth > sysMonth) {
            // �Ώۓ��̌��������̏ꍇ
            return false;
        }

        // �ȉ��A�Ώۓ��̌����V�X�e�����t�̌��Ɠ����ꍇ
        if (practiceDay <= sysDay) {
            // �����̏ꍇ���ߋ��Ɋ܂�
            return true;
        }

        return false;
    }

}
