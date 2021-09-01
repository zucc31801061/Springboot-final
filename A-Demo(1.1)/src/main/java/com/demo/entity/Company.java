package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Company implements Serializable {
    private String companyId;
    private String companyName;
    private String companyPwd;
    private String companyEmail;
    private String companyTel;
}
