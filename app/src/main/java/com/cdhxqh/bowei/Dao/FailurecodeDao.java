package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Failurecode;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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
     * @param failurecode
     */
    public void update(Failurecode failurecode) {
        try
        {
            FailurecodeDaoOpe.create(failurecode);
        } catch (SQLException e)
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
     * 删除所有信息
     */
    public void deleteall(){
        try {
            FailurecodeDaoOpe.delete(FailurecodeDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
