package com.tx.txcall.modules.calltask.api.natives;/**
 * Created by wyh in 2019/10/31 10:27
 **/

import com.tx.txcall.modules.customer.entity.CustomerVo;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-31 10:27
 **/
public interface CallTaskService {

    CustomerVo callCustomer(Integer customerId);

}
