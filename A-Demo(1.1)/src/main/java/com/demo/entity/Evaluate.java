package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Evaluate {
    private Integer evalId;
    private String evalContent;
    private Integer resuId;
    private String companyId;
    private String companyName;
    private int emplId;
}
