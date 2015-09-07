package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.JobPlan;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class JobPlanDao {
    private Context context;
    private Dao<JobPlan, Integer> JobPlanDaoOpe;
    private DatabaseHelper helper;

    public JobPlanDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobPlanDaoOpe = helper.getDao(JobPlan.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新工作计划
     * @param jobPlan
     */
    public void update(JobPlan jobPlan) {
        try
        {
            JobPlanDaoOpe.create(jobPlan);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有工作计划
     * @return
     */
    public List<JobPlan> queryForAll(){
        try {
            return JobPlanDaoOpe.queryForAll();
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
            JobPlanDaoOpe.delete(JobPlanDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
