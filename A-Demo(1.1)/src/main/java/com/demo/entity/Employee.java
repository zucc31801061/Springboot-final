package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Calendar;

@Data
@Accessors(chain = true)
public class Employee implements Serializable {
    private int emplId;
    private int resuId;
    private String companyId;
    private String companyName;
    private String emplName;
    private String emplSex;
    private String emplEmail;
    private String emplTel;
    private int age;
    private int nowAge;
    private double salary;
    private String position;
    private String content;

    public void setNowAge() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        this.nowAge = year-age;
    }

    public void setAge(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        this.age = year-nowAge;
    }
}
