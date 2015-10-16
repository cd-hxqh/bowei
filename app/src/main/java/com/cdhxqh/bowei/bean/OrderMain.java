package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2015/8/17.
 */
@DatabaseTable(tableName = "ORDERMAIN")
public class OrderMain implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "number")
    private String number;
    @DatabaseField(columnName = "describe")
    private String describe;
    @DatabaseField(columnName = "place")
    private String place;//位置
    @DatabaseField(columnName = "property")
    private String property;//资产
    @DatabaseField(columnName = "wordtype")
    private String wordtype;//工作类型
    @DatabaseField(columnName = "reality_worktype")
    private String reality_worktype;//实际工作类型
    @DatabaseField(columnName = "applyunity")
    private String applyunity;//申请单位
    @DatabaseField(columnName = "major")
    private String major;//专业
    @DatabaseField(columnName = "reality_item")
    private String reality_item;//实际班组
    @DatabaseField(columnName = "state")
    private String state;//状态
    @DatabaseField(columnName = "date")
    private String date;//汇报时间
    @DatabaseField(columnName = "workplan")
    private String workplan;//作业计划
    @DatabaseField(columnName = "reality_starttime")
    private String reality_starttime;//实际开始时间
    @DatabaseField(columnName = "reality_stoptime")
    private String reality_stoptime;//实际完成时间
    @DatabaseField(columnName = "employee_id")
    private String employee_id;//录入人工号
    @DatabaseField(columnName = "questiontogether")
    private String questiontogether;//问题汇总
    @DatabaseField(columnName = "ratinghours")
    private String ratinghours;//额定工时
    @DatabaseField(columnName = "pm")
    private String pm;//PM
    @DatabaseField(columnName = "notinspection_device")
    private String notinspection_device;//未巡检设备
    @DatabaseField(columnName = "inspect_result")
    private String inspect_result;//检查结果

    @DatabaseField(columnName = "faultclass")
    private String faultclass;//故障类
    @DatabaseField(columnName = "error_coding")
    private String error_coding;//问题代码
    @DatabaseField(columnName = "error_coding_list")
    private String error_coding_list;//问题代码关系
    @DatabaseField(columnName = "phenomena")
    private String phenomena;//现象
    @DatabaseField(columnName = "phenomena_list")
    private String phenomena_list;//现象关系
    @DatabaseField(columnName = "cause")
    private String cause;//原因
    @DatabaseField(columnName = "cause_list")
    private String cause_list;//原因关系
    @DatabaseField(columnName = "remedy")
    private String remedy;//措施
    @DatabaseField(columnName = "remedy_list")
    private String remedy_list;//措施关系
    @DatabaseField(columnName = "fault_rank")
    private String fault_rank;//故障等级
    @DatabaseField(columnName = "reporttime")
    private String reporttime;


    @DatabaseField(columnName = "isNew")
    private boolean isNew;//是否是新建工单
    @DatabaseField(columnName = "isyuzhi")
    private boolean isyuzhi;//是否已预置
    @DatabaseField(columnName = "belong")
    private String belong;//所属账号

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getError_coding_list() {
        return error_coding_list;
    }

    public void setError_coding_list(String error_coding_list) {
        this.error_coding_list = error_coding_list;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCause_list() {
        return cause_list;
    }

    public void setCause_list(String cause_list) {
        this.cause_list = cause_list;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getRemedy_list() {
        return remedy_list;
    }

    public void setRemedy_list(String remedy_list) {
        this.remedy_list = remedy_list;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getFault_rank() {
        return fault_rank;
    }

    public void setFault_rank(String fault_rank) {
        this.fault_rank = fault_rank;
    }

    public boolean isyuzhi() {
        return isyuzhi;
    }

    public void setIsyuzhi(boolean isyuzhi) {
        this.isyuzhi = isyuzhi;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(String phenomena) {
        this.phenomena = phenomena;
    }

    public String getPhenomena_list() {
        return phenomena_list;
    }

    public void setPhenomena_list(String phenomena_list) {
        this.phenomena_list = phenomena_list;
    }
}
