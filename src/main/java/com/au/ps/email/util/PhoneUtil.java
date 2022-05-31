package com.au.ps.email.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
    public static boolean isValidPhone(String phone){
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
