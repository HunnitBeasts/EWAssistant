package com.hunnit_beasts.EWAssistant.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    TEMPORARY_SERVER_ERROR(500,"A000","세부타입이 정해지지 않은 일반 에러입니다. 곧 수정하겠습니다."),
    NO_DATA_ERROR(500,"E001","[ERROR] 요청 데이터가 존재하지 않습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
