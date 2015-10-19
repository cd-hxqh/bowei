package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Workdw;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

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
     * @param list
     */
    public void update(final List<Workdw> list) {
        try
        {
            WorkdwDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Workdw workdw : list) {
                        WorkdwDaoOpe.createOrUpdate(workdw);
                    }
                    return null;
                }
            });
        } catch (Exception e)
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
            WorkdwDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Workdw workdw : WorkdwDaoOpe.queryForAll()) {
                        WorkdwDaoOpe.delete(workdw);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
