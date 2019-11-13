package com.tx.txcall.modules.calltask.api.fallback;

import com.tx.txcall.modules.calltask.api.remote.CallTaskFeignService;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.stereotype.Component;

/**
 * 错误回调类
 */
@Component
public class CustomerFallback implements CallTaskFeignService {


    @Override
    public CustomerVo getCustomer(Integer customerId) {
        //service degradation
        CustomerVo customerVo = new CustomerVo();
        customerVo.setCustomerName("张三").setCustomerMobile("1234567890").setCustomerId(0);
        return customerVo;
    }
}