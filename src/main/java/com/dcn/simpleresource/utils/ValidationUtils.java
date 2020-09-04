package com.dcn.simpleresource.utils;

import com.dcn.simpleresource.exception.BadRequestException;

import java.util.Optional;

public class ValidationUtils {

    public static void isNull(Optional optional, String entity, String parameter, Object value) {
        if (!optional.isPresent()) {
            String msg = entity + "-" + parameter + " { " + value.toString() + " } does not exist";
            throw new BadRequestException(msg);
        }
    }
}
