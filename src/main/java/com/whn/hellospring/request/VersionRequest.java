package com.whn.hellospring.request;

import lombok.Data;

@Data
public class VersionRequest extends BaseRequest {
    private int equipment;
}
