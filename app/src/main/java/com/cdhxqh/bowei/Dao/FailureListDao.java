package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.FailureList1;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class FailureListDao {
    private Context context;
    private Dao<FailureList1, Integer> FailureListDaoOpe;
    private DatabaseHelper helper;

    public FailureListDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            FailureListDaoOpe = helper.getDao(FailureList1.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新故障代码关系信息
     * @param list
     */
    public void update(final List<FailureList1> list) {
        try
        {
            FailureListDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (FailureList1 failureList : list) {
                        FailureListDaoOpe.createOrUpdate(failureList);
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
     * 查询所有故障代码关系
     * @return
     */
    public List<FailureList1> queryForAll(){
        try {
            return FailureListDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询故障类别
     * @return
     */
    public List<FailureList1> queryForClass(){
        try {
            return FailureListDaoOpe.queryBuilder().where().eq("TYPE", "").and().eq("PARENT","0").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询问题代码
     * @return
     */
    public List<FailureList1> queryForCode(String parent){
        try {
            return FailureListDaoOpe.queryBuilder().where().eq("TYPE", "").and().eq("parent", parent).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据选择的故障类别查询对应的list值
     * @return
     */
    public String queryForClassByCode(String code){
        try {
            return FailureListDaoOpe.queryBuilder()
                    .where().eq("TYPE","").and().eq("FAILURECODE", code).queryForFirst().getFAILURELIST();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据patent查询故障现象
     * @return
     */
    public List<FailureList1> queryForProblem(String parent){
        try {
            return FailureListDaoOpe.queryBuilder().where().eq("TYPE", "PROBLEM").and().eq("PARENT", parent).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询故障原因
     * @return
     */
    public List<FailureList1> queryForCause(){
        try {
            return FailureListDaoOpe.queryBuilder().where().eq("TYPE","PROBLEM").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据选择的故障现象查询对应的原因
     * @return
     */
    public List<FailureList1> queryForCauseByParent(String parent){
        try {
            return FailureListDaoOpe.queryBuilder()
                    .where().eq("TYPE", "CAUSE").and().eq("PARENT", parent).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询故障措施
     * @return
     */
    public List<FailureList1> queryForRemedy(){
        try {
            return FailureListDaoOpe.queryBuilder().where().eq("TYPE","REMEDY").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据选择的故障现象查询对应的措施
     * @return
     */
    public List<FailureList1> queryForRemedyByParent(String parent){
        try {
            return FailureListDaoOpe.queryBuilder()
                    .where().eq("TYPE", "REMEDY").and().eq("PARENT", parent).query();
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
            FailureListDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (FailureList1 failureList : FailureListDaoOpe.queryForAll()) {
                        FailureListDaoOpe.delete(failureList);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
