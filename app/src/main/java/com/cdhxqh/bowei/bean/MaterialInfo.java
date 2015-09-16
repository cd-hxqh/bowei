package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2015/8/25.
 */
@DatabaseTable(tableName = "WPMATERIAL")
public class MaterialInfo implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "number")
    private String number;//编码
    @DatabaseField(columnName = "name")
    private String name;//名称
    @DatabaseField(columnName = "size")
    private int size;//数量
    @DatabaseField(columnName = "warehouse")
    private String warehouse;//仓库
    @DatabaseField(columnName = "belongorderid")
    private int belongorderid;//所属工单id
    @DatabaseField(columnName = "isPlan")
    private boolean isPlan;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
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

    public boolean isPlan() {
        return isPlan;
    }

    public void setIsPlan(boolean isPlan) {
        this.isPlan = isPlan;
    }
}
