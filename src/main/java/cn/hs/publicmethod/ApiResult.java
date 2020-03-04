package cn.hs.publicmethod;

import java.util.Date;

public class ApiResult {
    public static final int SUCCESS_CODE = 10000;
    public static final int COMMON_FAILED_CODE = 10001;

    private int code;
    private String msg;
    private Object data;
    private Long stamp;

    public static ApiResult success() {
        return success("", "");
    }

    public static ApiResult success(Object data) {
        return success("", data);
    }

    public static ApiResult success(String message, Object data) {
        ApiResult result = new ApiResult();
        result.setCode(SUCCESS_CODE);
        result.setMsg(message);
        result.setStamp(new Date().getTime());
        result.setData(data);
        return result;
    }

    public static ApiResult failed(String message) {
        return failed(COMMON_FAILED_CODE, message);
    }

    public static ApiResult failed(int code, String message) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(message);
        result.setStamp(new Date().getTime());
        result.setData("");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getStamp() {
        return stamp;
    }

    public void setStamp(Long stamp) {
        this.stamp = stamp;
    }
}
