package slim3.util;

import static junit.framework.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class AttendDateUtilTest {

    @Test
    public void testIsPracticeDateIsPast() {

        // �����`�F�b�N
        Calendar calendar = Calendar.getInstance();
        doAndCheckIsPracticeDateIsPast(calendar, true);

        // ����
        calendar = Calendar.getInstance();
        int tommorowDay = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        calendar.set(Calendar.DAY_OF_MONTH, tommorowDay);
        doAndCheckIsPracticeDateIsPast(calendar, false);

        // ���
        calendar = Calendar.getInstance();
        int yesterday = calendar.get(Calendar.DATE) - 1;
        calendar.set(Calendar.DAY_OF_MONTH, yesterday);
        doAndCheckIsPracticeDateIsPast(calendar, true);

        // ����
        calendar = Calendar.getInstance();
        int nextMonth = calendar.get(Calendar.MONTH) + 1;
        calendar.set(Calendar.MONTH, nextMonth);
        doAndCheckIsPracticeDateIsPast(calendar, false);

        // �挎
        calendar = Calendar.getInstance();
        int lastMonth = calendar.get(Calendar.MONTH) - 1;
        calendar.set(Calendar.MONTH, lastMonth);
        doAndCheckIsPracticeDateIsPast(calendar, true);

        // ���N
        calendar = Calendar.getInstance();
        int nextYear = calendar.get(Calendar.YEAR) + 1;
        calendar.set(Calendar.YEAR, nextYear);
        doAndCheckIsPracticeDateIsPast(calendar, false);

        // ��N
        calendar = Calendar.getInstance();
        int lastYear = calendar.get(Calendar.YEAR) - 1;
        calendar.set(Calendar.YEAR, lastYear);
        doAndCheckIsPracticeDateIsPast(calendar, true);

    }

    public void doAndCheckIsPracticeDateIsPast(final Calendar checkDateCal,
            boolean expected) {

        Date checkDate = checkDateCal.getTime();

        assertEquals(expected,   AttendDateUtil.checkPast(checkDate));

    }

}
