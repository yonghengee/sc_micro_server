package com.tx.txcall.modules.customer.entity;/**
 * Created by wyh in 2019/10/29 10:08
 **/

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-29 10:08
 **/
@Data
@Accessors(chain = true)
public class CustomerVo {

    //id
    private Integer customerId;


    //name
    private String customerName;


    //mobile
    private String customerMobile;
}
