package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Workzy;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class WorkzyDao {
    private Context context;
    private Dao<Workzy, Integer> WorkzyDaoOpe;
    private DatabaseHelper helper;

    public WorkzyDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WorkzyDaoOpe = helper.getDao(Workzy.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新专业信息
     * @param list
     */
    public void update(final List<Workzy> list) {
        try
        {
            WorkzyDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Workzy workzy : list) {
                        WorkzyDaoOpe.createOrUpdate(workzy);
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
     * 查询所有专业
     * @return
     */
    public List<Workzy> queryForAll(){
        try {
            return WorkzyDaoOpe.queryForAll();
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
            WorkzyDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Workzy workzy : WorkzyDaoOpe.queryForAll()) {
                        WorkzyDaoOpe.delete(workzy);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
