package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class AcWorkTypeDao {
    private Context context;
    private Dao<AcWorkType, Integer> AcWorkTypeDaoOpe;
    private DatabaseHelper helper;

    public AcWorkTypeDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            AcWorkTypeDaoOpe = helper.getDao(AcWorkType.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新实际工作类型信息
     * @param acworkType
     */
    public void update(AcWorkType acworkType) {
        try
        {
            AcWorkTypeDaoOpe.create(acworkType);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有实际工作类型
     * @return
     */
    public List<AcWorkType> queryForAll(){
        try {
            return AcWorkTypeDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询维保工单实际工作类型
     * @return
     */
    public List<AcWorkType> queryForCMorPM(){
        try {
            return AcWorkTypeDaoOpe.queryBuilder().where()
                    .eq("VALUE", "ZXJ").or().eq("VALUE","ZWB").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询维修工单实际工作类型
     * @return
     */
    public List<AcWorkType> queryForEM(){
        try {
            return AcWorkTypeDaoOpe.queryBuilder().where()
                    .eq("VALUE", "EM").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询服务工单实际工作类型
     * @return
     */
    public List<AcWorkType> queryForSVR(){
        try {
            return AcWorkTypeDaoOpe.queryBuilder().where()
                    .eq("VALUE", "FGZ").or().eq("VALUE","LFW")
                    .or().eq("VALUE","RDFW").or().eq("VALUE","ZJFW").query();
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
            AcWorkTypeDaoOpe.delete(AcWorkTypeDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
