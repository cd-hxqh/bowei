package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.WorkType;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class WorkTypeDao {
    private Context context;
    private Dao<WorkType, Integer> WorkTypeDaoOpe;
    private DatabaseHelper helper;

    public WorkTypeDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WorkTypeDaoOpe = helper.getDao(WorkType.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新工作类型信息
     * @param list
     */
    public void update(final List<WorkType> list) {
        try
        {
            WorkTypeDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkType workType : list) {
                        WorkTypeDaoOpe.createOrUpdate(workType);
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
     * 查询所有工作类型
     * @return
     */
    public List<WorkType> queryForAll(){
        try {
            return WorkTypeDaoOpe.queryForAll();
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
            WorkTypeDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkType workType : WorkTypeDaoOpe.queryForAll()) {
                        WorkTypeDaoOpe.delete(workType);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
