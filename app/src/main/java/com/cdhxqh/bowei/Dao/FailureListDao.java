package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.FailureList;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class FailureListDao {
    private Context context;
    private Dao<FailureList, Integer> FailureListDaoOpe;
    private DatabaseHelper helper;

    public FailureListDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            FailureListDaoOpe = helper.getDao(FailureList.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新故障代码关系信息
     * @param failureList
     */
    public void update(FailureList failureList) {
        try
        {
            FailureListDaoOpe.create(failureList);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有故障代码关系
     * @return
     */
    public List<FailureList> queryForAll(){
        try {
            return FailureListDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有信息
     */
    public void deleteall(){
        try {
            FailureListDaoOpe.delete(FailureListDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
