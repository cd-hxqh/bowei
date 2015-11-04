package com.cdhxqh.bowei.Dao;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Erson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class ErsonDao {
    private Context context;
    private Dao<Erson, Integer> ErsonDaoOpe;
    private DatabaseHelper helper;

    public ErsonDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            ErsonDaoOpe = helper.getDao(Erson.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新实际班组信息
     * @param list
     */
    public void update(final List<Erson> list) {
        try
        {
            ErsonDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Erson erson : list) {
                        ErsonDaoOpe.createOrUpdate(erson);
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
     * 查询所有实际班组信息
     * @return
     */
    public List<Erson> queryForAll(){
        try {
            return ErsonDaoOpe.queryBuilder().orderBy("YWBZ",true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据班组列表查询人员信息
     * @return
     */
    public List<Erson> queryBylist(List<String> list){
        try {
            Where<Erson, Integer> where = ErsonDaoOpe.queryBuilder().orderBy("YWBZ", true).where();
            List<Erson>ersonList = new ArrayList<>();
            for(int i = 0;i < list.size();i ++){
                if(i<list.size()-1) {
                    if(list.get(i).split("-")[1].equals("无")){
                        where = where.eq("YWBZ", list.get(i).split("-")[0]).and().
                                eq("YWFL", "").or();
                    }else {
                        where = where.eq("YWBZ", list.get(i).split("-")[0]).and().
                                eq("YWFL", list.get(i).split("-")[1]).or();
                    }
                }else {
                    if (list.get(i).split("-")[1].equals("无")){
                        where = where.eq("YWBZ", list.get(i).split("-")[0]).and().eq("YWFL", "");
                    }else {
                        where = where.eq("YWBZ", list.get(i).split("-")[0]).and().eq("YWFL", list.get(i).split("-")[1]);
                    }
                }
                ersonList=where.query();
            }
            Log.i("s",ersonList.toString());
            return where.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据班组查询人员信息
     * @return
     */
    public List<Erson> queryByItem(String item){
        try {
            if(item.split("-")[1].equals("无")){
                return ErsonDaoOpe.queryBuilder().where().eq("YWBZ", item.split("-")[0]).query();
            }else {
                return ErsonDaoOpe.queryBuilder().where().eq("YWBZ", item.split("-")[0])
                        .and().eq("YWFL",item.split("-")[1]).query();
            }
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
            ErsonDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Erson erson : ErsonDaoOpe.queryForAll()) {
                        ErsonDaoOpe.delete(erson);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
