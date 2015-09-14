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
    private int number;//员工编号
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
    private int worktime;//工时

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }
}
