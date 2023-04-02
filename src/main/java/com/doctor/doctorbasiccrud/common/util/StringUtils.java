package com.doctor.doctorbasiccrud.common.util;

public class StringUtils {
    public static boolean isBlank(String val) {
        return val == null || "".equals(val);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
