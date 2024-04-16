package com.whn.hellospring.request;

import lombok.Data;

@Data
public class DeleteOilRecordRequest extends BaseRequest {
    private Long id;
}
