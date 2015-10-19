package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
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
            WorkerInfoDaoOpe = helper.getDao(WorkerInfo.class);
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
            WorkerInfoDaoOpe.createOrUpdate(workerInfo);
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
     * 根据工单id查询员工信息
     * @return
     */
    public List<WorkerInfo> queryByOrdermainId(int id){
        try {
            return WorkerInfoDaoOpe.queryBuilder().where().eq("belongorderid",id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据LABTRANSID查询存在员工信息
     * @return
     */
    public boolean queryByLabtransId(String LabtransId,int id){
        try {
            List<WorkerInfo> workerInfos = WorkerInfoDaoOpe.
                    queryBuilder().where().eq("LabtransId", LabtransId).and().eq("belongorderid",id).query();
            if (workerInfos.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加新的员工信息
     * @param workerInfo
     */
    public void add(WorkerInfo workerInfo){
        try
        {
            List<WorkerInfo> workerInfos = WorkerInfoDaoOpe.
                    queryBuilder().where().eq("LabtransId", workerInfo.getLabtransId()).
                    and().eq("belongorderid",workerInfo.getBelongorderid()).query();
            if (workerInfos.size()==0){
                WorkerInfoDaoOpe.createOrUpdate(workerInfo);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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
