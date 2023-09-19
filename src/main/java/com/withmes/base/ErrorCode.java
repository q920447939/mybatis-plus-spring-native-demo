package com.withmes.base;


public class ErrorCode {
    private ErrorCode() {
    }

    public static final String USER_ACCOUNT_EXPIRED = "user.account_expired";
    public static final String USER_CREDENTIALS_ERROR = "user.credentials_error";
    public static final String USER_CREDENTIALS_EXPIRED = "user.credentials_expired";
    public static final String USER_DISABLE = "user.disable";
    public static final String USER_NOT_EXIST = "user.not_exist";
    public static final String USER_LOCKED = "user.locked";
    public static final String USER_NOT_LOGIN = "user.not_login";
    public static final String USER_JWT_EXPIRED = "user.jwt_expired";
    public static final String USER_JWT_UNSUPPORTED = "user.jwt_unsupported";
    public static final String USER_JWT_SIGNATURE_ERROR = "user.jwt_signature_error";

    public static final String USER_PERMISSION_DENIED = "user.permission_denied";
    public static final String USER_ORGANIZATION_MISMATCH = "user.organization_mismatch";
    public static final String USER_ORGANIZATION_INVALID = "user.organization_invalid";

    public static final String USER_INVALID_INPUT = "user.invalid_input";

    public static final String SYS_INTERNAL_ERROR = "sys.internal_error";
    public static final String SYS_AUTHENTICATION_ERROR = "sys.authentication_error";

    public static final String SYS_CONNECTION_TIMED_OUT = "sys.connection_timed_out";
    public static final String SYS_CONNECTION_REFUSED = "sys.connection_refused";
    public static final String SYS_QUERY_FAILED = "sys.query_failed";
    public static final String SYS_PAGE_FAILED = "sys.page_failed";
    public static final String SYS_ILLEGAL_ARGUMENT = "sys.illegal_argument";

    public static final String SYS_IO_EXCEPTION = "sys.io_exception";
    public static final String SYS_JWT_EXCEPTION = "sys.jwt_exception";
    public static final String SYS_SQL_EXCEPTION = "sys.sql_exception";

}
