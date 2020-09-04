package com.dcn.simpleresource.exception;

import com.dcn.simpleresource.utils.Constants;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    private int status = Constants.HTTP_CODE.BAD_REQUEST;

    public BadRequestException(String msg){
        super(msg);
    }
}
