package com.whn.hellospring.request;

import lombok.Data;

@Data
public class CustomerDetailRequest extends BaseRequest {
    private long customer_id;
}
