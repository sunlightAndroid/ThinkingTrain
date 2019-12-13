package me.sunlight.sdk.common.network.api;

/**
 * <pre>
 *     author : eric
 *     e-mail : 1944633835@qq.com
 * </pre>
 */

public enum ErrorEnum {

    SUCCESS(1, "success"),
    NEED_LOGIN(-10001, "请先登陆"),
    ;

    private int code;
    private String message;

    ErrorEnum(int status, String message) {
        this.code = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
