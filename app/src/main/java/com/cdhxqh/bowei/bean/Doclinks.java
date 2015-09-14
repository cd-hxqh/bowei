package com.cdhxqh.bowei.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by apple on 15/9/10.
 * 知识库附件
 */

@DatabaseTable(tableName = "Doclinks")
public class Doclinks implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    /**附件编号**/
    @DatabaseField(columnName = "DOCUMENT")
    private int document;
    /**附件名称**/
    @DatabaseField(columnName = "DESCRIPTION")
    private String description;
    /**附件路径**/
    @DatabaseField(columnName = "URLNAME")
    private String urlname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }
}
