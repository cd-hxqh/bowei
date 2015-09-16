package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class MaterialInfoDao {
    private Context context;
    private Dao<MaterialInfo, Integer> MaterialInfoDaoOpe;
    private DatabaseHelper helper;

    public MaterialInfoDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            MaterialInfoDaoOpe = helper.getDao(MaterialInfo.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新物料信息
     * @param materialInfo
     */
    public void update(MaterialInfo materialInfo) {
        try
        {
            MaterialInfoDaoOpe.createOrUpdate(materialInfo);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有物料信息
     * @return
     */
    public List<MaterialInfo> queryForAll(){
        try {
            return MaterialInfoDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据LABTRANSID查询是否存在员工信息
     * @return
     */
    public boolean queryByLabtransId(String LabtransId,int id){
        try {
            List<MaterialInfo> workerInfos = MaterialInfoDaoOpe.
                    queryBuilder().where().eq("ITEMNUM", LabtransId).and().eq("belongorderid",id).and().query();
            if (workerInfos.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MaterialInfo> queryByLabtransId(int id,boolean isPlan){
        try {
            return MaterialInfoDaoOpe.queryBuilder()
                    .where().eq("belongorderid",id).and().eq("isPlan",isPlan).query();
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
            MaterialInfoDaoOpe.delete(MaterialInfoDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
