package fun.imcoder.cloud.base.enums;

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    BAD_REQUEST(400,"Bad Request"),
    AUTH_ERROR(400,"用户名或密码错误"),
    UNAUTHORIZED(401,"访问此资源需要完全的身份验证"),
    ACCESS_TOKEN_INVALID(401,"access_token无效"),
    REFRESH_TOKEN_INVALID(401,"refresh_token无效"),
    INSUFFICIENT_PERMISSIONS(403,"该用户权限不足以访问该资源接口"),
    INCORRECT_PARAMS(400, "参数不正确"),
    SERVER_ERROR(500, "服务器错误"),
    ;
    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
