package com.whn.hellospring.request;

import lombok.Data;

@Data
public class GetCoffeeListByOrderIdRequest extends BaseRequest {
    private Long orderId;
}
