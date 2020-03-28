package cn.hs.publicclass.method;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    //格式化起始日期(接收String，返回String)
    public static String formatstartDate(String startDate) {
        if (StringUtils.isNotEmpty(startDate) && startDate.length() >= 10) {
            startDate = startDate.substring(0, 10) + " 00:00:00";
        }
        return startDate;
    }

    //格式化结束日期(接收String，返回String)
    public static String formatEndDay(String endDate) {
        if (StringUtils.isNotEmpty(endDate) && endDate.length() >= 10) {
            endDate = endDate.substring(0, 10) + " 23:59:59";
        }
        return endDate;
    }

    //格式化时间(接收Date，返回String)
    public static String formatDay(Date date){
        if (date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
