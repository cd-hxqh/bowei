package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "ASSET")
public class Asset {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "ASSETNUM")
    private String ASSETNUM;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "LOCATION")
    private String LOCATION;
    @DatabaseField(columnName = "LOCATIONDESC")
    private String LOCATIONDESC;
    @DatabaseField(columnName = "FAILURECODE")
    private String FAILURECODE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getASSETNUM() {
        return ASSETNUM;
    }

    public void setASSETNUM(String ASSETNUM) {
        this.ASSETNUM = ASSETNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getLOCATIONDESC() {
        return LOCATIONDESC;
    }

    public void setLOCATIONDESC(String LOCATIONDESC) {
        this.LOCATIONDESC = LOCATIONDESC;
    }

    public String getFAILURECODE() {
        return FAILURECODE;
    }

    public void setFAILURECODE(String FAILURECODE) {
        this.FAILURECODE = FAILURECODE;
    }
}
