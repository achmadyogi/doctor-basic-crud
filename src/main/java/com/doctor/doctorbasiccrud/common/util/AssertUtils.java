package com.doctor.doctorbasiccrud.common.util;

import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;
import com.doctor.doctorbasiccrud.biz.exception.TaskException;

public class AssertUtils {
    public static void notBlank(String attributeName, String val) {
        String attribute = StringUtils.isBlank(attributeName) ? "attribute" : attributeName;
        if (StringUtils.isBlank(val)) {
            throw new TaskException(ErrorCodeEnum.PARAM_ILLEGAL, attribute + " cannot be blank");
        }
    }

    public static void notNull(String attributeName, Object object) {
        String attribute = StringUtils.isBlank(attributeName) ? "attribute" : attributeName;
        if (object == null) {
            throw new TaskException(ErrorCodeEnum.PARAM_ILLEGAL, attribute + " cannot be null");
        }
    }
}
