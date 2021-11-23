package com.example.zookeeper.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static Boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static Boolean isValidEmail(String str) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
