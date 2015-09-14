package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class WorkerInfoDao {
    private Context context;
    private Dao<WorkerInfo, Integer> WorkerInfoDaoOpe;
    private DatabaseHelper helper;

    public WorkerInfoDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WorkerInfoDaoOpe = helper.getDao(AcWorkType.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新员工信息
     * @param workerInfo
     */
    public void update(WorkerInfo workerInfo) {
        try
        {
            WorkerInfoDaoOpe.create(workerInfo);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有员工信息
     * @return
     */
    public List<WorkerInfo> queryForAll(){
        try {
            return WorkerInfoDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有员工信息
     */
    public void deleteall(){
        try {
            WorkerInfoDaoOpe.delete(WorkerInfoDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
