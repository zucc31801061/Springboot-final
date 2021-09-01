package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Resume {
    private int resuId;
    private int emplId;
    private String content;
}
