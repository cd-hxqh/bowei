package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Alndomain;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class AlndomainDao {
    private Context context;
    private Dao<Alndomain, Integer> AlndomainDaoOpe;
    private DatabaseHelper helper;

    public AlndomainDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            AlndomainDaoOpe = helper.getDao(Alndomain.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新实际工作类型信息
     * @param list
     */
    public void update(final List<Alndomain> list) {
        try
        {
            AlndomainDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Alndomain alndomain : list) {
                        AlndomainDaoOpe.createOrUpdate(alndomain);
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
     * 查询所有实际工作类型
     * @return
     */
    public List<Alndomain> queryForAll(){
        try {
            return AlndomainDaoOpe.queryForAll();
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
            AlndomainDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Alndomain alndomain : AlndomainDaoOpe.queryForAll()) {
                        AlndomainDaoOpe.delete(alndomain);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
