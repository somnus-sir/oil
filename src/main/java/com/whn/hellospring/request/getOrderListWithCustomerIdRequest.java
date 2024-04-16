package com.whn.hellospring.request;

import lombok.Data;

@Data
public class getOrderListWithCustomerIdRequest extends BaseRequest {
    private String customerId;
}
