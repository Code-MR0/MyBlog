package com.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Description TODO
 * @Author MR
 * @Date 2021/4/24 19:41
 */
public class CommentDateUtil {
    public static String getCommentDate(Date date) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        long diff = now.getTime() - date.getTime();
        long seconds = diff / 1000 % 60;
        long minutes = diff / (60 * 1000) % 60;
        long hours = diff / (60 * 60 * 1000) % 24;
        long days = diff / (24 * 60 * 60 * 1000);
        if (days > 3)
            return format;
        if (days > 0)
            return days + "days ago";
        if (hours > 0)
            return hours + "hours ago";
        if (minutes > 0)
            return minutes + "minutes ago";
        return seconds + "seconds ago";
    }
}
