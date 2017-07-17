package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 负责日期字符串与Date的互相转化
 * Created by qianzhihao on 2017/7/17.
 */
public class DateTransformer {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");

    public static Date stringToDate(String str) throws ParseException {
        return sdf.parse(str.replace("null","1").replace("\t","1"));
    }

    public static String dateToString(Date date){
        return sdf.format(date);
    }
}
