package com.tx.txcall.modules.calltask.web;/**
 * Created by wyh in 2019/10/31 10:30
 **/

import com.tx.txcall.modules.calltask.api.natives.CallTaskService;
import com.tx.txcall.modules.customer.entity.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-31 10:30
 **/
@RequestMapping("/call-task")
@RestController
public class CallTaskController {

    @Autowired
    private CallTaskService callTaskService;

    @GetMapping("/call/{id}")
    public CustomerVo callCustomer(@PathVariable("id")Integer customerId){
        System.out.println("I'm call_task_1");
        return callTaskService.callCustomer(customerId);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
