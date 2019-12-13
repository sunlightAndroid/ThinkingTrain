package me.sunlight.sdk.common.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.sunlight.sdk.common.app.BaseApplication;


/**
 * Created by eric on 2019/12/12.
 * 邮箱：1944633835@qq.com
 */
public class Utils {

    /**
     * 获得栈中最顶层的Activity
     *
     * @param context
     * @return
     */
    public static String getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity).toString();
        } else
            return null;
    }

    /**
     * 验证是否是手机号
     *
     * @param number 手机号
     * @return
     */
    public static boolean isMobilePhone(String number) {
        return RxRegUtils.isMobile(number);
    }

    /**
     * 验证是否为邮箱号
     *
     * @param number 邮箱号
     * @return
     */
    public static boolean isEmail(String number) {
        return RxRegUtils.isEmail(number);
    }

    /**
     * 时间戳转化成日期
     *
     * @param seconds 秒数  时间戳
     * @param format  yyyy-MM-dd HH:mm:ss
     * @return 返回一个日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 从时间(毫秒)中提取出时间(时:分)
     * 时间格式:  时:分
     *
     * @param millisecond 毫秒数  时间戳
     * @return 返回一个日期
     */
    public static String milliTimeStamp2Date(String millisecond, String format) {
        if (TextUtils.isEmpty(millisecond))
            return "";
        if (TextUtils.isEmpty(format))
            format = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(Long.parseLong(millisecond));
        return simpleDateFormat.format(date);
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return 返回一个字符串秒数
     */
    @SuppressLint("SimpleDateFormat")
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前时间的时间戳
     */
    public static String getCurrentTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return date2TimeStamp(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
     *
     * @param time String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date stringToDate(String time) {
        SimpleDateFormat formatter;
        int tempPos = time.indexOf("AD");
        time = time.trim();
        formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        if (tempPos > -1) {
            time = time.substring(0, tempPos) +
                    "公元" + time.substring(tempPos + "AD".length());//china
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos = time.indexOf("-");
        if (tempPos > -1 && (time.indexOf(" ") < 0)) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
        } else if ((time.indexOf("/") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if ((time.indexOf("-") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if ((time.indexOf("/") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        } else if ((time.indexOf("-") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(time, pos);

        return ctime;
    }

    /**
     * 计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     */
    public static String getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;
        String flag;
        if (time1 < time2) {
            diff = time2 - time1;
            flag = "前";
        } else {
            diff = time1 - time2;
            flag = "后";
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) return day + "天" + flag;
        if (hour != 0) return hour + "小时" + flag;
        if (min != 0) return min + "分钟" + flag;
        return "刚刚";
    }

    /**
     * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     * String timeStr1="2011-06-10 08:17:15";
     * String timeStr2="2000-10-11 10:20:51";
     * long date1=sdf.parse(timeStr1).getTime();
     * long date2=sdf.parse(timeStr2).getTime();
     *
     * @param timeStr1
     * @param timeStr2
     * @return
     */
    public static long getSecondBy2Time(String timeStr1, String timeStr2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long date1 = 0, date2 = 0;
        try {
            date1 = sdf.parse(timeStr1).getTime();
            date2 = sdf.parse(timeStr2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (date2 - date1) / 1000;
    }


    /**
     * url 编码
     *
     * @param input 输入的参数字符串
     * @return "UTF-8" 编码之后的字符串
     */
    public static String urlEncode(String input) {
        return RxEncodeUtils.urlEncode(input);
    }

    /**
     * 保留5位有效数字 (最后以为四舍五入)
     */
    public static String transNumbers(String number) {
        DecimalFormat df = new DecimalFormat("###.00000");
        return df.format(Double.parseDouble(number));
    }


    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    // 获取百度地图所需的SHA1码
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将View转化成bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public static Bitmap convertViewToBitmap(View view, int size) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = size * 40;
        view.layout(0, 0, width, view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    //-----------------------app 保存一些常用数据-----------------------//

    // 保存用户名称
    public static void saveAccount(String account) {
        RxSPUtils.putString(BaseApplication.getInstance(), "ACCOUNT", account);
    }

    public static String getAccount() {
        return RxSPUtils.getString(BaseApplication.getInstance(), "ACCOUNT");
    }

    // 保存用户密码
    public static void savePassword(String password) {
        RxSPUtils.putString(BaseApplication.getInstance(), "PASSWORD", password);
    }

    public static String getPassword() {
        return RxSPUtils.getString(BaseApplication.getInstance(), "PASSWORD");
    }



}
