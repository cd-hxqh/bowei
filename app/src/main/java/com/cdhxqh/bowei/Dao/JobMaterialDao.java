package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class JobMaterialDao {
    private Context context;
    private Dao<Jobmaterial, Integer> JobMaterialDaoOpe;
    private DatabaseHelper helper;

    public JobMaterialDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobMaterialDaoOpe = helper.getDao(Jobmaterial.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新资产信息
     * @param list
     */
    public void update(final List<Jobmaterial> list) {
        try
        {
            JobMaterialDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Jobmaterial jobMaterial : list) {
                        JobMaterialDaoOpe.createOrUpdate(jobMaterial);
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
     * 查询所有资产
     * @return
     */
    public List<Jobmaterial> queryForAll(){
        try {
            return JobMaterialDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Jobmaterial> queryByJobPlanId(String id){
        try {
            return JobMaterialDaoOpe.queryBuilder().where().eq("JOBPLANID",id).query();
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
            JobMaterialDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Jobmaterial jobMaterial : JobMaterialDaoOpe.queryForAll()) {
                        JobMaterialDaoOpe.delete(jobMaterial);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
