package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2015/8/25.
 */
@DatabaseTable(tableName = "WORKERINFO")
public class WorkerInfo implements Serializable {
    /**
     * 员工信息
     */
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "number")
    private String number;//员工编号
    @DatabaseField(columnName = "name")
    private String name;//员工名称
    @DatabaseField(columnName = "startdate")
    private String startdate;//开始日期
    @DatabaseField(columnName = "stopdate")
    private String stopdate;//结束日期
    @DatabaseField(columnName = "starttime")
    private String starttime;//开始时间
    @DatabaseField(columnName = "stoptime")
    private String stoptime;//结束时间
    @DatabaseField(columnName = "worktime")
    private String worktime;//工时
    @DatabaseField(columnName = "LabtransId")
    private String LabtransId;
    @DatabaseField(columnName = "belongorderid")
    private int belongorderid;//所属工单id

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStopdate() {
        return stopdate;
    }

    public void setStopdate(String stopdate) {
        this.stopdate = stopdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelongorderid() {
        return belongorderid;
    }

    public void setBelongorderid(int belongorderid) {
        this.belongorderid = belongorderid;
    }

    public String getLabtransId() {
        return LabtransId;
    }

    public void setLabtransId(String labtransId) {
        LabtransId = labtransId;
    }
}
