package com.whn.hellospring.request;

import lombok.Data;

@Data
public class DeleteOrderRequest extends BaseRequest {
    private Long id;
}
