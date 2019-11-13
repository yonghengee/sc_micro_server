package com.tx.txcall.common.utils.vo.response;

import com.tx.txcall.common.utils.vo.enums.CommonResponseCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {

    //操作是否成功
    boolean success = SUCCESS;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String message;

    Object obj;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ResponseResult(ResultCode resultCode,Object obj){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.obj = obj;
    }


    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonResponseCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonResponseCode.FAIL);
    }

}
