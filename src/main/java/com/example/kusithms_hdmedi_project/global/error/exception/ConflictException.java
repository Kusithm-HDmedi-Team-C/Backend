package com.example.kusithms_hdmedi_project.global.error.exception;

public class ConflictException extends BusinessException {
    public ConflictException(){
        super(ErrorCode.CONFLICT);
    }

    public ConflictException(ErrorCode errorCode){
        super(errorCode);
    }
}
