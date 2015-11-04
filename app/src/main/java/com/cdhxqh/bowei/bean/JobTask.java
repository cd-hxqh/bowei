package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "JOBTASK")
public class JobTask {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "JOBPLANID")
    private String JOBPLANID;
    @DatabaseField(columnName = "JOBTASKID")
    private int JOBTASKID;
    @DatabaseField(columnName = "JPNUM")
    private String JPNUM;
    @DatabaseField(columnName = "JPTASK")
    private int JPTASK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getJOBTASKID() {
        return JOBTASKID;
    }

    public void setJOBTASKID(int JOBTASKID) {
        this.JOBTASKID = JOBTASKID;
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

    public int getJPTASK() {
        return JPTASK;
    }

    public void setJPTASK(int JPTASK) {
        this.JPTASK = JPTASK;
    }
}
