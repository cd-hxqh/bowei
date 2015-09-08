package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.OrderMain;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class OrderMainDao {
    private Context context;
    private Dao<OrderMain, Integer> OrderMainDaoOpe;
    private DatabaseHelper helper;

    public OrderMainDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            OrderMainDaoOpe = helper.getDao(OrderMain.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新实际工作类型信息
     * @param orderMain
     */
    public void update(OrderMain orderMain) {
        try
        {
            OrderMainDaoOpe.create(orderMain);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有实际工作类型
     * @return
     */
    public List<OrderMain> queryForAll(){
        try {
            return OrderMainDaoOpe.queryForAll();
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
            OrderMainDaoOpe.delete(OrderMainDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
