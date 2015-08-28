package com.cdhxqh.bowei.bean;

import java.io.Serializable;

/**
 * Created by think on 2015/8/17.
 */
public class OrderMain implements Serializable {
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
    private String ratinghours;//额定工时
    private String pm;//PM
    private String notinspection_device;//未巡检设备
    private String inspect_result;//检查结果


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

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getReality_stoptime() {
        return reality_stoptime;
    }

    public void setReality_stoptime(String reality_stoptime) {
        this.reality_stoptime = reality_stoptime;
    }

    public String getQuestiontogether() {
        return questiontogether;
    }

    public void setQuestiontogether(String questiontogether) {
        this.questiontogether = questiontogether;
    }

    public String getRatinghours() {
        return ratinghours;
    }

    public void setRatinghours(String ratinghours) {
        this.ratinghours = ratinghours;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getNotinspection_device() {
        return notinspection_device;
    }

    public void setNotinspection_device(String notinspection_device) {
        this.notinspection_device = notinspection_device;
    }

    public String getInspect_result() {
        return inspect_result;
    }

    public void setInspect_result(String inspect_result) {
        this.inspect_result = inspect_result;
    }
}
