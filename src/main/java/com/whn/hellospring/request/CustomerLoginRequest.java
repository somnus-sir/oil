package com.whn.hellospring.request;

import lombok.Data;

@Data
public class CustomerLoginRequest extends BaseRequest {
    private String phone;
    private String password;
}
