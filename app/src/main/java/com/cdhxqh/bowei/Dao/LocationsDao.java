package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Locations;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/9/7.
 */
public class LocationsDao {
    private Context context;
    private Dao<Locations, Integer> LocationsDaoOpe;
    private DatabaseHelper helper;

    public LocationsDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            LocationsDaoOpe = helper.getDao(Locations.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个位置
     * @param locations
     */
    public void add(Locations locations)
    {
        try
        {
            LocationsDaoOpe.create(locations);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 更新位置信息
     * @param list
     */
    public void update(final List<Locations> list){
        try {
//            LocationsDaoOpe.createOrUpdate(locations);
            LocationsDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Locations locations1 : list) {
                        LocationsDaoOpe.createOrUpdate(locations1);
                    }
                    return null; 			}
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有位置信息
     * @return
     */
    public List<Locations> queryForAll(){
        try {
            return LocationsDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据描述查询位置信息
     * @return
     */
    public List<Locations> queryByDescription(String str){
        try {
            return LocationsDaoOpe.queryBuilder().where().like("DESCRIPTION", "%" +str+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据Loucation查询位置信息
     * @return
     */
    public List<Locations> queryByLoucation(String Loucation){
        try {
            return LocationsDaoOpe.queryBuilder().where().eq("LOCATION",Loucation).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有位置信息
     */
    public void deleteall(){
        try {
            LocationsDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Locations locations1 : LocationsDaoOpe.queryForAll()) {
                        LocationsDaoOpe.delete(locations1);
                    }
                    return null; 			}
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
