package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Admin {
    private String adId;
    private String adPwd;
}
