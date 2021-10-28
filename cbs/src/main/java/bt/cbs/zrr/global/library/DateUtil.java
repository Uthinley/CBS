package bt.cbs.zrr.global.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dechen Wangdi on 12/19/2019.
 */
public class DateUtil {

    private static SimpleDateFormat dateDMYFormat = new SimpleDateFormat("dd-MMM-yyyy");
    private static SimpleDateFormat dateMDYFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat formatDateForDB = new SimpleDateFormat("yyyy-MM-dd");
    //region public method.

    /**
     * To get date format like 19-Dec-2019
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return dateDMYFormat.format(date);
    }

    public static String formatDateForDB(Date date) {
        return formatDateForDB.format(date);
    }


    /**
     * To convert string date to date.
     *
     * @param date String
     * @return Date
     * @throws java.text.ParseException
     */
    public static Date parseStringDate(String date) throws ParseException {
        return dateDMYFormat.parse(date);
    }

    /**
     * To get year part from the given date. ex. 2019
     *
     * @param systemOpenDate Date
     * @return String
     */
    public static String getYear(Date systemOpenDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(systemOpenDate);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /**
     * To get month part from the given date.
     *
     * @param systemOpenDate Date
     * @return String
     */
    public static String getMonth(Date systemOpenDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(systemOpenDate);
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * To get month like Jan, Feb, Mar,...
     *
     * @param systemOpenDate Date
     * @return String
     */
    public static String getMonthString(Date systemOpenDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(systemOpenDate);
        String[] strMonths = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};
        return strMonths[calendar.get(Calendar.MONTH)];
    }

    public static Date plusDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date minusDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return calendar.getTime();
    }

    public static boolean isDate(Date date) {
        try {
            String parseDate = dateDMYFormat.format(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * To check date1 is before date2.
     *
     * @param date1 Date
     * @param date2 Date
     * @return boolean
     */
    public static boolean before(Date date1, Date date2) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        if (date1.before(date2)) {
            return true;
        }
        return false;
    }

    public boolean isThisDateValid(String dateToValidate, String dateFromat) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    //endregion
}
