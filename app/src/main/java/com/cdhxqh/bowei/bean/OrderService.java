package com.cdhxqh.bowei.bean;

import java.io.Serializable;

/**
 * Created by think on 2015/8/17.
 */
public class OrderService implements Serializable {
    private int number;
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
