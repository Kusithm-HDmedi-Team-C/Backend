package com.example.kusithms_hdmedi_project.global.error.dto;

import com.example.kusithms_hdmedi_project.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorBaseResponse {
    private int status;
    private String message;

    @Builder
    public ErrorBaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorBaseResponse of(ErrorCode errorCode) {
        return ErrorBaseResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .build();
    }
}