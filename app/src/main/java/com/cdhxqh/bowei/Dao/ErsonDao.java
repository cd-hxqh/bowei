package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Erson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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
     * @param erson
     */
    public void update(Erson erson) {
        try
        {
            ErsonDaoOpe.create(erson);
        } catch (SQLException e)
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
            return ErsonDaoOpe.queryForAll();
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
            ErsonDaoOpe.delete(ErsonDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
