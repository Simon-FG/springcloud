package com.doubleskyline.core.validator;

import com.doubleskyline.core.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @Auther:
 * @Date 2017-03-23 15:50
 */
public abstract class Assert extends org.springframework.util.Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
}
