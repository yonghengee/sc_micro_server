package com.tx.txcall.common.utils.vo.enums;


import com.tx.txcall.common.utils.vo.response.ResultCode;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonResponseCode implements ResultCode {
    SUCCESS(true,200,"操作成功！"),
    FAIL(false,400,"操作失败！"),
    UNAUTHENTICATED(false,401,"此操作需要登陆系统！"),
    UNAUTHORISE(false,403,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    REQUEST_PARAM_NULL(false,5012,"参数为null！"),

    WS_CONNECT_SUCCESS(true,201,"websocket连接成功"),
    WS_CONNECT_CLOSE(true,202,"websocket断开成功"),
    WS_CONNECT_ERROR(true,501,"websocket出错");
//    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    CommonResponseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
