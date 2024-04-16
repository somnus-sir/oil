package com.whn.hellospring.controller;

import com.whn.hellospring.common.StateMessage;
import com.whn.hellospring.common.Status;
import com.whn.hellospring.model.CustomerDO;
import com.whn.hellospring.request.CustomerDetailRequest;
import com.whn.hellospring.request.CustomerLoginRequest;
import com.whn.hellospring.request.DeleteCustomerRequest;
import com.whn.hellospring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController  //处理http请求，默认返回json格式数据
@RequestMapping(value = "/customer") //配置映射在/customer
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * 创建客户
     */
    @PostMapping(value = "/create")
    public Status postCustomer(@Valid @RequestBody CustomerDO customerDO) {
        try {
            CustomerDO customer = service.createCustomer(customerDO);
            Status status = new Status(StateMessage.SUCCESS, customer);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 编辑客户
     */
    @PostMapping(value = "/update")
    public Status updateCustomer(@Valid @RequestBody CustomerDO customerDO) {
        try {
            CustomerDO customer =   service.updateCustomer(customerDO);
            Status status = new Status(StateMessage.SUCCESS, customer);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 获取顾客列表
     */
    @GetMapping(value = "/list")
    public Status getCustomerList() {
        try {
            List<CustomerDO> customerList = service.getCustomerList();
            Status status = new Status(StateMessage.SUCCESS, customerList);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 删除顾客1
     */
    @DeleteMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (service.deleteCustomer(id) == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 删除顾客2
     * @return
     */
    @PostMapping(value = "/delete")
    public Status postDeleteCustomer(@RequestBody DeleteCustomerRequest request) {
        try {
            if (service.deleteCustomer(request.getId()) == 1) {
                return new Status(StateMessage.SUCCESS, "success");
            }else{
                return new Status(StateMessage.SUCCESS,  "error");
            }
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 客户详情
     */
    @PostMapping(value = "/detail")
    public Status detail(@RequestBody CustomerDetailRequest request) {
        try {
            CustomerDO customerDetail = service.getCustomerDetail(request.getCustomer_id());
            return new Status(StateMessage.SUCCESS, customerDetail);
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 客户登录
     */
    @PostMapping(value = "/login")
    public Status login(@RequestBody CustomerLoginRequest request) {
        try {
            String phone = request.getPhone();
            String password = request.getPassword();
            String loginMsg = service.login(phone, password);
            Status status = new Status(StateMessage.SUCCESS, loginMsg);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    @GetMapping(value = "/test")
    public String test() {
        return "hello";
    }
}
