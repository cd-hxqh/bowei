package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Asset;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class AssetDao {
    private Context context;
    private Dao<Asset, Integer> AssetDaoOpe;
    private DatabaseHelper helper;

    public AssetDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            AssetDaoOpe = helper.getDao(Asset.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新资产信息
     * @param asset
     */
    public void update(Asset asset) {
        try
        {
            AssetDaoOpe.create(asset);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有资产
     * @return
     */
    public List<Asset> queryForAll(){
        try {
            return AssetDaoOpe.queryForAll();
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
            AssetDaoOpe.delete(AssetDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
