package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 *
 */
@DatabaseTable(tableName = "FAILURELIST")
public class FailureList1 {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "FAILURECODE")
    private String FAILURECODE;
    @DatabaseField(columnName = "FAILURELIST")
    private String FAILURELIST;
    @DatabaseField(columnName = "PARENT")
    private String PARENT;
    @DatabaseField(columnName = "TYPE")
    private String TYPE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFAILURECODE() {
        return FAILURECODE;
    }

    public void setFAILURECODE(String FAILURECODE) {
        this.FAILURECODE = FAILURECODE;
    }

    public String getFAILURELIST() {
        return FAILURELIST;
    }

    public void setFAILURELIST(String FAILURELIST) {
        this.FAILURELIST = FAILURELIST;
    }

    public String getPARENT() {
        return PARENT;
    }

    public void setPARENT(String PARENT) {
        this.PARENT = PARENT;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
