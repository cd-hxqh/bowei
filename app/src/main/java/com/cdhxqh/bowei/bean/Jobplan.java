package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "JOBPLAN")
public class Jobplan {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "JOBPLANID")
    private String JOBPLANID;
    @DatabaseField(columnName = "JPNUM")
    private String JPNUM;
    @DatabaseField(columnName = "JPDURATION")
    private String JPDURATION;
    @DatabaseField(columnName = "PARENT")
    private String PARENT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJOBPLANID() {
        return JOBPLANID;
    }

    public void setJOBPLANID(String JOBPLANID) {
        this.JOBPLANID = JOBPLANID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getJPNUM() {
        return JPNUM;
    }

    public void setJPNUM(String JPNUM) {
        this.JPNUM = JPNUM;
    }

    public String getJPDURATION() {
        return JPDURATION;
    }

    public void setJPDURATION(String JPDURATION) {
        this.JPDURATION = JPDURATION;
    }

    public String getPARENT() {
        return PARENT;
    }

    public void setPARENT(String PARENT) {
        this.PARENT = PARENT;
    }
}
