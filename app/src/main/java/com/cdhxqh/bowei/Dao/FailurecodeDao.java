package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Failurecode;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class FailurecodeDao {
    private Context context;
    private Dao<Failurecode, Integer> FailurecodeDaoOpe;
    private DatabaseHelper helper;

    public FailurecodeDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            FailurecodeDaoOpe = helper.getDao(Failurecode.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新故障代码信息
     * @param list
     */
    public void update(final List<Failurecode> list) {
        try
        {
            FailurecodeDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Failurecode failurecode : list) {
                        FailurecodeDaoOpe.createOrUpdate(failurecode);
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
     * 查询所有故障代码信息
     * @return
     */
    public List<Failurecode> queryForAll(){
        try {
            return FailurecodeDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据code查询故障代码信息
     * @return
     */
    public Failurecode queryByCode(String code){
        try {
            return FailurecodeDaoOpe.queryBuilder().where().eq("FAILURECODE",code).queryForFirst();
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
            FailurecodeDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Failurecode failurecode : FailurecodeDaoOpe.queryForAll()) {
                        FailurecodeDaoOpe.delete(failurecode);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
