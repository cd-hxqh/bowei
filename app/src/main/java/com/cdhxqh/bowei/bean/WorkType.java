package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "WORKTYPE")
public class WorkType {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "WORKTYPE")
    private String WORKTYPE;
    @DatabaseField(columnName = "WTYPEDESC")
    private String WTYPEDESC;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWORKTYPE() {
        return WORKTYPE;
    }

    public void setWORKTYPE(String WORKTYPE) {
        this.WORKTYPE = WORKTYPE;
    }

    public String getWTYPEDESC() {
        return WTYPEDESC;
    }

    public void setWTYPEDESC(String WTYPEDESC) {
        this.WTYPEDESC = WTYPEDESC;
    }
}
