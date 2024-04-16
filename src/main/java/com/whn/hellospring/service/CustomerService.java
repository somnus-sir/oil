package com.whn.hellospring.service;


import com.whn.hellospring.model.CustomerDO;
import com.whn.hellospring.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    /**
     * 获取一个顾客详情
     */
    public CustomerDO getCustomerDetail(long id){
        return customerRepository.getOne(id);
    }


    /**
     * 新增一个顾客
     */
    public CustomerDO createCustomer(CustomerDO customerDO) {
        customerRepository.save(customerDO);
        return customerDO;
    }

    /**
     * 删除一个顾客
     */
    public int deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return 1;
    }

    /**
     * 编辑客户
     */
    public CustomerDO updateCustomer(CustomerDO customerDO) {
        customerRepository.save(customerDO);
        return customerDO;
    }

    /**
     * 获取顾客列表
     */
    public List<CustomerDO> getCustomerList() {
        return customerRepository.findAll();
    }

    /**
     * 登录
     */
    public String login(String phone, String password) {
        //判断是否存在该手机号
        List<CustomerDO> list = customerRepository.isHaveThisPhone(phone);
        if (list == null || list.size() == 0) {
            return "该手机号不存在";
        } else {
            String msg = "请检查密码";
            for (CustomerDO customerDo : list) {
                if (password.equals(customerDo.getPass_word())) {
                    msg = customerDo.getId().toString();
                    break;
                }
            }
            return msg;
        }
    }
}
