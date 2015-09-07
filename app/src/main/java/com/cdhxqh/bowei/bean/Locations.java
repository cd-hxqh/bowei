package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by think on 2015/9/6.
 */
@DatabaseTable(tableName = "LOCATIONS")
public class Locations {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION;
    @DatabaseField(columnName = "LOCATION")
    private String LOCATION;
    @DatabaseField(columnName = "LOCATIONSID")
    private String LOCATIONSID;

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

    public String getLOCATIONSID() {
        return LOCATIONSID;
    }

    public void setLOCATIONSID(String LOCATIONSID) {
        this.LOCATIONSID = LOCATIONSID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
