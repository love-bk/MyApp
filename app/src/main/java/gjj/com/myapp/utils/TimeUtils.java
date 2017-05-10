package gjj.com.myapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yyz on 2017/5/10.
 */

public class TimeUtils {

    /**
     * 返回的字符串形式是形如：2013-10-20 20:58
     * */
    public static String formatTimeInMillis(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy年MM月dd日 HH时mm分");
        String fmt = dateFormat.format(date);

        return fmt;
    }
}
