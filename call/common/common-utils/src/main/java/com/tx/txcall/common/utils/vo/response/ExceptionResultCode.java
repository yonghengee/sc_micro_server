package com.tx.txcall.common.utils.vo.response;/**
 * Created by wyh in 2019/4/16 17:44
 **/

import lombok.Data;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-04-16 17:44
 **/
@Data
public class ExceptionResultCode implements ResultCode {
    private boolean success;

    private int code;

    private String message;

    public ExceptionResultCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean success() {
        return this.success;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
