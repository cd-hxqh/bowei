package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.OrderTask;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class OrderTaskDao {
    private Context context;
    private Dao<OrderTask, Integer> OrderTaskDaoOpe;
    private DatabaseHelper helper;

    public OrderTaskDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            OrderTaskDaoOpe = helper.getDao(OrderTask.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新任务信息
     * @param orderTask
     */
    public void update(OrderTask orderTask) {
        try
        {
            OrderTaskDaoOpe.create(orderTask);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有任务
     * @return
     */
    public List<OrderTask> queryForAll(){
        try {
            return OrderTaskDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有任务
     */
    public void deleteall(){
        try {
            OrderTaskDaoOpe.delete(OrderTaskDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
