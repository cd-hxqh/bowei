package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2015/8/20.
 */
@DatabaseTable(tableName = "ORDERTASK")
public class OrderTask implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "num")
    private String num;
    @DatabaseField(columnName = "ordermainid")
    private String ordermainid;
    @DatabaseField(columnName = "task")
    private String task;//任务名
    @DatabaseField(columnName = "digest")
    private String digest;//摘要
    @DatabaseField(columnName = "wosequence")
    private String wosequence;//序号
    @DatabaseField(columnName = "zxr")
    private String zxr;//执行人
    @DatabaseField(columnName = "jcr")
    private String jcr;//检查人
    @DatabaseField(columnName = "workorderid")
    private String workorderid;
    @DatabaseField(columnName = "belongordermain")
    private int belongordermain;//所属工单id
    @DatabaseField(columnName = "ischange")
    private boolean ischange;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdermainid() {
        return ordermainid;
    }

    public void setOrdermainid(String ordermainid) {
        this.ordermainid = ordermainid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getWosequence() {
        return wosequence;
    }

    public void setWosequence(String wosequence) {
        this.wosequence = wosequence;
    }

    public String getZxr() {
        return zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    public String getJcr() {
        return jcr;
    }

    public void setJcr(String jcr) {
        this.jcr = jcr;
    }

    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid;
    }

    public int getBelongordermain() {
        return belongordermain;
    }

    public void setBelongordermain(int belongordermain) {
        this.belongordermain = belongordermain;
    }

    public boolean ischange() {
        return ischange;
    }

    public void setIschange(boolean ischange) {
        this.ischange = ischange;
    }
}
