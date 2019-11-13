package com.tx.txcall.modules.calltask.api.remote;/**
 * Created by wyh in 2019/10/30 17:10
 **/

import com.tx.txcall.modules.calltask.api.fallback.CustomerFallback;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-30 17:10
 **/
@FeignClient(value = "customer-no", fallback = CustomerFallback.class)
public interface CallTaskFeignService {
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    CustomerVo getCustomer(@PathVariable("id") Integer customerId);

}
