package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Workdw;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class WorkdwDao {
    private Context context;
    private Dao<Workdw, Integer> WorkdwDaoOpe;
    private DatabaseHelper helper;

    public WorkdwDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WorkdwDaoOpe = helper.getDao(Workdw.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新单位信息
     * @param workdw
     */
    public void update(Workdw workdw) {
        try
        {
            WorkdwDaoOpe.create(workdw);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有单位
     * @return
     */
    public List<Workdw> queryForAll(){
        try {
            return WorkdwDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有位置信息
     */
    public void deleteall(){
        try {
            WorkdwDaoOpe.delete(WorkdwDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
