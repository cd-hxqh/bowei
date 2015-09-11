package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by apple on 15/9/10.
 * 知识库
 */

@DatabaseTable(tableName = "Knowledge")
public class Knowledge implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    /**知识编号**/
    @DatabaseField(columnName = "KNOWLEDGEID")
    private int knowledgeid;
    /**知识名称**/
    @DatabaseField(columnName = "KNOWDESC")
    private String knowdesc;
    /**知识大类**/
    @DatabaseField(columnName = "KNOWDL")
    private String knowdl;
    /**知识小类**/
    @DatabaseField(columnName = "KNOWXL")
    private String knowxl;
    /**备注**/
    @DatabaseField(columnName = "KNOWBZ")
    private String knowbz;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(int knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    public String getKnowdesc() {
        return knowdesc;
    }

    public void setKnowdesc(String knowdesc) {
        this.knowdesc = knowdesc;
    }

    public String getKnowdl() {
        return knowdl;
    }

    public void setKnowdl(String knowdl) {
        this.knowdl = knowdl;
    }

    public String getKnowxl() {
        return knowxl;
    }

    public void setKnowxl(String knowxl) {
        this.knowxl = knowxl;
    }

    public String getKnowbz() {
        return knowbz;
    }

    public void setKnowbz(String knowbz) {
        this.knowbz = knowbz;
    }
}
