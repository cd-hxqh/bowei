package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.JobMaterial;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class JobMaterialDao {
    private Context context;
    private Dao<JobMaterial, Integer> JobMaterialDaoOpe;
    private DatabaseHelper helper;

    public JobMaterialDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobMaterialDaoOpe = helper.getDao(JobMaterial.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新资产信息
     * @param jobMaterial
     */
    public void update(JobMaterial jobMaterial) {
        try
        {
            JobMaterialDaoOpe.create(jobMaterial);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有资产
     * @return
     */
    public List<JobMaterial> queryForAll(){
        try {
            return JobMaterialDaoOpe.queryForAll();
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
            JobMaterialDaoOpe.delete(JobMaterialDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
