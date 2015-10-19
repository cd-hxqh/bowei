package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Erson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class ErsonDao {
    private Context context;
    private Dao<Erson, Integer> ErsonDaoOpe;
    private DatabaseHelper helper;

    public ErsonDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            ErsonDaoOpe = helper.getDao(Erson.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新实际班组信息
     * @param list
     */
    public void update(final List<Erson> list) {
        try
        {
            ErsonDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Erson erson : list) {
                        ErsonDaoOpe.createOrUpdate(erson);
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
     * 查询所有实际班组信息
     * @return
     */
    public List<Erson> queryForAll(){
        try {
            return ErsonDaoOpe.queryBuilder().orderBy("YWBZ",true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据班组查询人员信息
     * @return
     */
    public List<Erson> queryByItem(String item){
        try {
            return ErsonDaoOpe.queryBuilder().where().eq("YWBZ",item).query();
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
            ErsonDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Erson erson : ErsonDaoOpe.queryForAll()) {
                        ErsonDaoOpe.delete(erson);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
