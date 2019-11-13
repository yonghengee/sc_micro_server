package com.tx.txcall.common.utils.vo.response;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}
