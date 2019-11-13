package com.tx.txcall.modules.customer.pro;/**
 * Created by wyh in 2019/10/29 10:17
 **/

import com.tx.txcall.modules.customer.api.CustomerService;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-29 10:17
 **/
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer/{id}")
    public CustomerVo getCustomer(@PathVariable("id") Integer customerId){
        Assert.notNull(customerId,"customerId is null");
        System.out.println("this is port 8762");
        return customerService.getCustomerById(customerId);
    }

}
