package com.tx.txcall.modules.customer.api;/**
 * Created by wyh in 2019/10/29 10:05
 **/

import com.tx.txcall.modules.customer.entity.CustomerVo;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-29 10:05
 **/
public interface CustomerService {


    CustomerVo getCustomerById(Integer customerId);

}
