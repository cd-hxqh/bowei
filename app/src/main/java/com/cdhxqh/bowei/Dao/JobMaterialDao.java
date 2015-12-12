package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.utils.MyComparator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Jobmaterial> queryByJobPlanId(String value, List<String> list){
        try {
            List<Jobmaterial> jobmaterialList = new ArrayList<>();
            List<Jobmaterial> jobmaterialList1;
            jobmaterialList1 = JobMaterialDaoOpe.queryBuilder().where().eq("JOBPLANID",value).query();
//            Comparator comparator = new MyComparator();
//            if (jobmaterialList1.size()>0){
//                comparator = new MyComparator();
//                Collections.sort(jobmaterialList1, comparator);
                jobmaterialList.addAll(jobmaterialList1);
//            }
            if(list != null && list.size() > 0){
                for(int i = 0;i < list.size();i ++){
                    jobmaterialList1 = new ArrayList<>();
                    jobmaterialList1 = JobMaterialDaoOpe.queryBuilder().where().eq("JOBPLANID",list.get(i)).query();
//                    if (jobmaterialList1.size()>0){
//                        comparator = new MyComparator();
//                        Collections.sort(jobmaterialList1,comparator);
                        jobmaterialList.addAll(jobmaterialList1);
//                    }
                }
            }
            return jobmaterialList;
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
