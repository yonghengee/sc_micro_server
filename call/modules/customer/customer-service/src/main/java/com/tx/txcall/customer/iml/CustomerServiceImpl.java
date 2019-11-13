package com.tx.txcall.customer.iml;/**
 * Created by wyh in 2019/10/29 10:12
 **/

import com.tx.txcall.modules.customer.api.CustomerService;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-29 10:12
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerVo getCustomerById(
        Integer customerId) {
        Assert.notNull(customerId,"customerId is null");
        CustomerVo customerVo = new CustomerVo();
        customerVo.setCustomerId(customerId);
        customerVo.setCustomerMobile("12345678901");
        customerVo.setCustomerName("Jordan");
        return customerVo;
    }
}
