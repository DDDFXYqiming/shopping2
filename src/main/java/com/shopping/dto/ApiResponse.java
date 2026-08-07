package com.shopping.dto;

/** 统一 API 响应结构（与旧版 code=1 成功约定一致） */
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data, String message) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.code = 1;
        resp.message = message;
        resp.data = data;
        return resp;
    }

    public static <T> ApiResponse<T> fail(String message) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.code = 0;
        resp.message = message;
        resp.data = null;
        return resp;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}
