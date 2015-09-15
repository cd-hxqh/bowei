package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by apple on 15/9/10.
 * 公司库存表
 */

@DatabaseTable(tableName = "Inventory")
public class Inventory implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    /**物料编号**/
    @DatabaseField(columnName = "ITEMNUM")
    private String itemnum;
    /**名称**/
    @DatabaseField(columnName = "DESCRIPTION")
    private String description;
    /**库房**/
    @DatabaseField(columnName = "LOCATION")
    private String location;
    /**库房**/
    @DatabaseField(columnName = "CURBALTOTAL")
    private String curbaltotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurbaltotal() {
        return curbaltotal;
    }

    public void setCurbaltotal(String curbaltotal) {
        this.curbaltotal = curbaltotal;
    }
}
