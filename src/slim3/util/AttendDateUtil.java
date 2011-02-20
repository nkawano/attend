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
            // 対象日の年度が過去の場合
            return true;
        } else if (practiceYear > sysYear) {
            // 対象日の年度が未来の場合
            return false;
        }

        // 以下、対象日の年度がシステム日付の年度と同じ場合
        if (practiceMonth < sysMonth) {
            // 対象日の月が過去の場合
            return true;
        } else if (practiceMonth > sysMonth) {
            // 対象日の月が未来の場合
            return false;
        }

        // 以下、対象日の月がシステム日付の月と同じ場合
        if (practiceDay <= sysDay) {
            // 当日の場合も過去に含む
            return true;
        }

        return false;
    }

}
