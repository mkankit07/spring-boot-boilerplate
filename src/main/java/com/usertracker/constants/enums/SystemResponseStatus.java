package com.usertracker.constants.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SystemResponseStatus {

    INVALID_REQUEST_BODY(-1001),

    INVALID_FIELD(-1002),

    INVALID_DATA(-10003),

    MISSING_PARAM(-1004),

    DATA_ALREADY_EXIST(-1005),

    ENUM_VALUE_NOT_FOUND(-1006),

    INVALID_CREDENTIALS(-1007),

    INTERNAL_SERVER_ERROR(-1008),

    FORBIDDEN(-1009),

    NOT_FOUND(-1010),

    ERROR_LIST(-1011),

    REQUEST_PARAMETER_ERROR_LIST(-1012);

    private final int code;
}
