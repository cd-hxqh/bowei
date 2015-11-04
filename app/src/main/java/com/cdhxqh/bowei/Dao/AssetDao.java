package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.Asset;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

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
     * @param list
     */
    public void update(final List<Asset> list) {
        try
        {
            AssetDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Asset asset : list) {
                        AssetDaoOpe.createOrUpdate(asset);
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
    public List<Asset> queryForAll(){
        try {
            return AssetDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isexitByNum(String string){
        try {
            if(AssetDaoOpe.queryBuilder().where().eq("ASSETNUM",string).query().size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 根据描述查询位置
     * @return
     */
    public List<Asset> queryByDescription(String str){
        try {
            return AssetDaoOpe.queryBuilder().where().like("DESCRIPTION","%"+str+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据关键字查询位置
     * @return
     */
    public List<Asset> queryByStr(String str){
        try {
            return AssetDaoOpe.queryBuilder().where().like("DESCRIPTION","%"+str+"%").or().like("ASSETNUM","%"+str + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据资产查询位置
     * @return
     */
    public List<Asset> queryByAsset(String asset){
        try {
            return AssetDaoOpe.queryBuilder().where().eq("ASSETNUM", asset).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据资产查询故障类
     * @return
     */
    public String queryClassByAsset(String asset){
        try {
            return AssetDaoOpe.queryBuilder().where().eq("ASSETNUM", asset).queryForFirst().getFAILURECODE();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据资产查询描述
     * @return
     */
    public String queryFordescriptionBynum(String num){
        try {
            return AssetDaoOpe.queryBuilder().where().eq("ASSETNUM",num).queryForFirst().getDESCRIPTION();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据资产查询位置
     * @return
     */
    public String queryForLoucationBynum(String num){
        try {
            return AssetDaoOpe.queryBuilder().where().eq("ASSETNUM",num).queryForFirst().getLOCATION();
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
            AssetDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Asset asset : AssetDaoOpe.queryForAll()) {
                        AssetDaoOpe.delete(asset);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
