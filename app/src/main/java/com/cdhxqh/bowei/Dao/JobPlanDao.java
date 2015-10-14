package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Jobplan;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class JobPlanDao {
    private Context context;
    private Dao<Jobplan, Integer> JobPlanDaoOpe;
    private DatabaseHelper helper;

    public JobPlanDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobPlanDaoOpe = helper.getDao(Jobplan.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新工作计划
     * @param jobPlan
     */
    public void update(Jobplan jobPlan) {
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
    public List<Jobplan> queryForAll(){
        try {
            return JobPlanDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Jobplan> queryByDescription(String str){
        try {
            return JobPlanDaoOpe.queryBuilder().where().like("DESCRIPTION", "%" +str+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jobplan queryByJobNum(String num){
        try {
            return JobPlanDaoOpe.queryBuilder().where().eq("JPNUM",num).queryForFirst();
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
