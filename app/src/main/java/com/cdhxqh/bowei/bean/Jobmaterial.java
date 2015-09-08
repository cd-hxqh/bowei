package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "JOBMATERIAL")
public class Jobmaterial {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "ITEMDESC")
    private String ITEMDESC;
    @DatabaseField(columnName = "ITEMNUM")
    private String ITEMNUM;
    @DatabaseField(columnName = "ITEMQTY")
    private String ITEMQTY;
    @DatabaseField(columnName = "JOBITEMID")
    private String JOBITEMID;
    @DatabaseField(columnName = "JOBPLANID")
    private String JOBPLANID;
    @DatabaseField(columnName = "JPNUM")
    private String JPNUM;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setITEMDESC(String ITEMDESC) {
        this.ITEMDESC = ITEMDESC;
    }

    public String getITEMQTY() {
        return ITEMQTY;
    }

    public void setITEMQTY(String ITEMQTY) {
        this.ITEMQTY = ITEMQTY;
    }

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getJOBITEMID() {
        return JOBITEMID;
    }

    public void setJOBITEMID(String JOBITEMID) {
        this.JOBITEMID = JOBITEMID;
    }

    public String getJOBPLANID() {
        return JOBPLANID;
    }

    public void setJOBPLANID(String JOBPLANID) {
        this.JOBPLANID = JOBPLANID;
    }

    public String getJPNUM() {
        return JPNUM;
    }

    public void setJPNUM(String JPNUM) {
        this.JPNUM = JPNUM;
    }
}
