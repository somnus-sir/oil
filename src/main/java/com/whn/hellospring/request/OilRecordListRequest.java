package com.whn.hellospring.request;

import lombok.Data;

@Data
public class OilRecordListRequest extends BaseRequest {
    private Long customer_id;
}
