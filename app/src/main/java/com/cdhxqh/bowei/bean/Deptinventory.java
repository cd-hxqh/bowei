package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by apple on 15/9/10.
 * 捷运库存表
 */

@DatabaseTable(tableName = "Deptinventory")
public class Deptinventory implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    /**物料编号**/
    @DatabaseField(columnName = "ITEMNUM")
    private String itemnum;
    /**仓库**/
    @DatabaseField(columnName = "LOCATION")
    private String location;
    /**数量**/
    @DatabaseField(columnName = "CURBALTOTAL")
    private String curbaltotal;
    /**名称**/
    @DatabaseField(columnName = "bjmc")
    private String bjmc;
    /**仓库名称**/
    @DatabaseField(columnName = "locdesc")
    private String locdesc;

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

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getLocdesc() {
        return locdesc;
    }

    public void setLocdesc(String locdesc) {
        this.locdesc = locdesc;
    }
}
