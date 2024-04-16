package com.whn.hellospring.request;

import lombok.Data;

@Data
public class DeleteCoffeeRequest extends BaseRequest {
    private Long id;
}
