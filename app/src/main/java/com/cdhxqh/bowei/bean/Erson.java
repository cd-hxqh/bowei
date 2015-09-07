package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "ERSON")
public class Erson {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DISPLAYNAME")
    private String DISPLAYNAME;
    @DatabaseField(columnName = "PERSONID")
    private String PERSONID;
    @DatabaseField(columnName = "YWBZ")
    private String YWBZ;
    @DatabaseField(columnName = "YWFL")
    private String YWFL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public String getYWBZ() {
        return YWBZ;
    }

    public void setYWBZ(String YWBZ) {
        this.YWBZ = YWBZ;
    }

    public String getYWFL() {
        return YWFL;
    }

    public void setYWFL(String YWFL) {
        this.YWFL = YWFL;
    }
}
