package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/7.
 */
@DatabaseTable(tableName = "FAILURECODE")
public class Failurecode {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "FAILURECODE")
    private String FAILURECODE;
    @DatabaseField(columnName = "FAILURECODEID")
    private String FAILURECODEID;

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

    public String getFAILURECODE() {
        return FAILURECODE;
    }

    public void setFAILURECODE(String FAILURECODE) {
        this.FAILURECODE = FAILURECODE;
    }

    public String getFAILURECODEID() {
        return FAILURECODEID;
    }

    public void setFAILURECODEID(String FAILURECODEID) {
        this.FAILURECODEID = FAILURECODEID;
    }
}
