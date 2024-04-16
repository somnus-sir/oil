package com.whn.hellospring.request;

import lombok.Data;

@Data
public class GetOrderRequest extends BaseRequest {
    private Long id;
}
