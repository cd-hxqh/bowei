package com.cdhxqh.bowei.Dao;

import android.content.Context;

import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.OrderMain;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class OrderMainDao {
    private Context context;
    private Dao<OrderMain, Integer> OrderMainDaoOpe;
    private DatabaseHelper helper;

    public OrderMainDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            OrderMainDaoOpe = helper.getDao(OrderMain.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新工单
     * @param orderMain
     */
    public void update(OrderMain orderMain) {
        try
        {
            OrderMainDaoOpe.createOrUpdate(orderMain);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有工单
     * @return
     */
    public List<OrderMain> queryForAll(){
        try {
            return OrderMainDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询维保工单
     * @return
     */
    public List<OrderMain> queryForPMAndCM(String username){
        try {
            return OrderMainDaoOpe.queryBuilder().orderBy("date",false)
                    .where().eq("wordtype","PM").and().eq("belong",username)
                    .and().eq("state","APPR").or().eq("wordtype","CM")
                    .and().eq("belong",username).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询维修工单
     * @return
     */
    public List<OrderMain> queryForEM(String username){
        try {

            return OrderMainDaoOpe.queryBuilder().orderBy("number", true)
                    .where().eq("wordtype", "EM").and().eq("belong",username).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询服务工单
     * @return
     */
    public List<OrderMain> queryForSVR(String username){
        try {

            return OrderMainDaoOpe.queryBuilder().orderBy("number", true)
                    .where().eq("wordtype","SVR").and().eq("belong",username).query();
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
            OrderMainDaoOpe.delete(OrderMainDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单id删除信息
     */
    public void deleteById(int id){
        try {
            OrderMainDaoOpe.delete(OrderMainDaoOpe.queryBuilder().where().eq("id",id).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照工单id查询工单
     * @param id
     * @return
     */
    public OrderMain SearchByNum(int id){
        try {
            return OrderMainDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照工单编号查询本地是否存在此工单
     * @param num
     * @return
     */
    public boolean isexitByNum(String num,String username){
        try {
            List<OrderMain>orderMainList = OrderMainDaoOpe.queryBuilder().where().eq("number",num).and().eq("belong",username).query();
            if(orderMainList.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
