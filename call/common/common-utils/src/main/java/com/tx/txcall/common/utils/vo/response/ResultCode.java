package com.tx.txcall.common.utils.vo.response;

/**
 * Created by mrt on 2018/3/5.
 * 10000-- 通用错误代码
 */
public interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作代码
    int code();
    //提示信息
    String message();



}
