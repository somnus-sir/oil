package com.whn.hellospring.request;

import lombok.Data;

@Data
public class DeleteCustomerRequest extends BaseRequest {
    private Long id;
}
