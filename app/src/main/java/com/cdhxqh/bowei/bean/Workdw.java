package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "WORKDW")
public class Workdw {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "VALUE")
    private String VALUE;

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

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }
}
