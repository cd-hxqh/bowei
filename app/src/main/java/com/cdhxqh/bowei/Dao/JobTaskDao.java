package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.JobTask;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class JobTaskDao {
    private Context context;
    private Dao<JobTask, Integer> JobTaskDaoOpe;
    private DatabaseHelper helper;

    public JobTaskDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobTaskDaoOpe = helper.getDao(JobTask.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新计划任务
     * @param list
     */
    public void update(final List<JobTask> list) {
        try
        {
//            JobTaskDaoOpe.createOrUpdate(jobTask);
            JobTaskDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (JobTask jobtask : list) {
                        JobTaskDaoOpe.createOrUpdate(jobtask);
                    }
                    return null; 			}
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有计划任务
     * @return
     */
    public List<JobTask> queryForAll(){
        try {
            return JobTaskDaoOpe.queryBuilder().orderBy("JOBTASKID",true).query();
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
            JobTaskDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (JobTask jobtask : JobTaskDaoOpe.queryForAll()) {
                        JobTaskDaoOpe.delete(jobtask);
                    }
                    return null; 			}
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<JobTask> QueryByJobTaskId(String  value){
        try {
            return JobTaskDaoOpe.queryBuilder().where().eq("JPNUM",value).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
