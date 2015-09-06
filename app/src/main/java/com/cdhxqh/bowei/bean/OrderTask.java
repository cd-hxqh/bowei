package com.cdhxqh.bowei.bean;

import java.io.Serializable;

/**
 * Created by think on 2015/8/20.
 */
public class OrderTask implements Serializable {
    private String num;
    private String task;//任务名
    private String digest;//摘要
    private String wosequence;//序号
    private String zxr;//执行人
    private String jcr;//检查人
    private String workorderid;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
}
