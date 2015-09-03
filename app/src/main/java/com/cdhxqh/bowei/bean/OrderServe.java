package com.cdhxqh.bowei.bean;

import java.io.Serializable;

/**
 * Created by think on 2015/8/17.
 */
public class OrderServe implements Serializable {
    private int number;
    private String describe;
    private String place;//位置
    private String property;//资产
    private String wordtype;//工作类型
    private String reality_worktype;//实际工作类型
    private String applyunity;//申请单位
    private String major;//专业
    private String reality_item;//实际班组
    private String state;//状态
    private String date;//汇报时间
    private String workplan;//作业计划
    private String reality_starttime;//实际开始时间
    private String reality_stoptime;//实际完成时间
    private String employee_id;//录入人工号
    private String questiontogether;//问题汇总
    private String faultclass;//故障类
    private String error_coding;//问题代码
    private String fault_rank;//故障等级
    private String reporttime;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }

    public String getReality_worktype() {
        return reality_worktype;
    }

    public void setReality_worktype(String reality_worktype) {
        this.reality_worktype = reality_worktype;
    }

    public String getApplyunity() {
        return applyunity;
    }

    public void setApplyunity(String applyunity) {
        this.applyunity = applyunity;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getReality_item() {
        return reality_item;
    }

    public void setReality_item(String reality_item) {
        this.reality_item = reality_item;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkplan() {
        return workplan;
    }

    public void setWorkplan(String workplan) {
        this.workplan = workplan;
    }

    public String getReality_starttime() {
        return reality_starttime;
    }

    public void setReality_starttime(String reality_starttime) {
        this.reality_starttime = reality_starttime;
    }

    public String getReality_stoptime() {
        return reality_stoptime;
    }

    public void setReality_stoptime(String reality_stoptime) {
        this.reality_stoptime = reality_stoptime;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getQuestiontogether() {
        return questiontogether;
    }

    public void setQuestiontogether(String questiontogether) {
        this.questiontogether = questiontogether;
    }

    public String getFaultclass() {
        return faultclass;
    }

    public void setFaultclass(String faultclass) {
        this.faultclass = faultclass;
    }

    public String getError_coding() {
        return error_coding;
    }

    public void setError_coding(String error_coding) {
        this.error_coding = error_coding;
    }

    public String getFault_rank() {
        return fault_rank;
    }

    public void setFault_rank(String fault_rank) {
        this.fault_rank = fault_rank;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }
}
