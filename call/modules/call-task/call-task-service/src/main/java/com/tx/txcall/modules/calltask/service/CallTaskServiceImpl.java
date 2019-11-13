package com.tx.txcall.modules.calltask.service;/**
 * Created by wyh in 2019/10/30 17:11
 **/

import com.tx.txcall.modules.calltask.api.natives.CallTaskService;
import com.tx.txcall.modules.calltask.api.remote.CallTaskFeignService;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-30 17:11
 **/
@Service
public class CallTaskServiceImpl implements CallTaskService {

    @Autowired
    private CallTaskFeignService callTaskFeignService;

    @Override
    public CustomerVo callCustomer(Integer customerId) {
        Assert.notNull(customerId,"customerId is null");
        CustomerVo customer = callTaskFeignService.getCustomer(customerId);
        System.out.println("call - customer: " + customer.toString());

        return customer;
    }
}
