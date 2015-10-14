package com.cdhxqh.bowei.OrmLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.Alndomain;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.Erson;
import com.cdhxqh.bowei.bean.FailureList1;
import com.cdhxqh.bowei.bean.Failurecode;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.bean.Jobplan;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.Locations;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.bean.WorkType;
import com.cdhxqh.bowei.bean.Workdw;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.bean.Workzy;
import com.cdhxqh.bowei.utils.Utils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2015/9/7.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {

        super(context, Utils.getFilePath(context), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Locations.class);
            TableUtils.createTable(connectionSource, Asset.class);
            TableUtils.createTable(connectionSource, Workdw.class);
            TableUtils.createTable(connectionSource, Workzy.class);
            TableUtils.createTable(connectionSource, WorkType.class);
            TableUtils.createTable(connectionSource, AcWorkType.class);
            TableUtils.createTable(connectionSource, Failurecode.class);
            TableUtils.createTable(connectionSource, FailureList1.class);
            TableUtils.createTable(connectionSource, Jobplan.class);
            TableUtils.createTable(connectionSource, JobTask.class);
            TableUtils.createTable(connectionSource, Jobmaterial.class);
            TableUtils.createTable(connectionSource, Erson.class);
            TableUtils.createTable(connectionSource, OrderMain.class);
            TableUtils.createTable(connectionSource, OrderTask.class);
            TableUtils.createTable(connectionSource, WorkerInfo.class);
            TableUtils.createTable(connectionSource, Alndomain.class);
            TableUtils.createTable(connectionSource, MaterialInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Locations.class, true);
            TableUtils.dropTable(connectionSource, Asset.class, true);
            TableUtils.dropTable(connectionSource, Workdw.class, true);
            TableUtils.dropTable(connectionSource, Workzy.class, true);
            TableUtils.dropTable(connectionSource, WorkType.class, true);
            TableUtils.dropTable(connectionSource, AcWorkType.class, true);
            TableUtils.dropTable(connectionSource, Failurecode.class, true);
            TableUtils.dropTable(connectionSource, FailureList1.class, true);
            TableUtils.dropTable(connectionSource, Jobplan.class, true);
            TableUtils.dropTable(connectionSource, JobTask.class, true);
            TableUtils.dropTable(connectionSource, Jobmaterial.class, true);
            TableUtils.dropTable(connectionSource, Erson.class, true);
            TableUtils.dropTable(connectionSource, OrderMain.class, true);
            TableUtils.dropTable(connectionSource, OrderTask.class, true);
            TableUtils.dropTable(connectionSource, WorkerInfo.class, true);
            TableUtils.dropTable(connectionSource, Alndomain.class, true);
            TableUtils.dropTable(connectionSource, MaterialInfo.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
