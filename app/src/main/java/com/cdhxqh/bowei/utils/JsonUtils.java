package com.cdhxqh.bowei.utils;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.Dao.AcWorkTypeDao;
import com.cdhxqh.bowei.Dao.AssetDao;
import com.cdhxqh.bowei.Dao.ErsonDao;
import com.cdhxqh.bowei.Dao.FailureListDao;
import com.cdhxqh.bowei.Dao.FailurecodeDao;
import com.cdhxqh.bowei.Dao.JobMaterialDao;
import com.cdhxqh.bowei.Dao.JobPlanDao;
import com.cdhxqh.bowei.Dao.JobTaskDao;
import com.cdhxqh.bowei.Dao.LocationsDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.Dao.WorkTypeDao;
import com.cdhxqh.bowei.Dao.WorkdwDao;
import com.cdhxqh.bowei.Dao.WorkzyDao;
import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.Erson;
import com.cdhxqh.bowei.bean.FailureList1;
import com.cdhxqh.bowei.bean.Failurecode;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.bean.Jobplan;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.Locations;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.bean.WorkType;
import com.cdhxqh.bowei.bean.Workdw;
import com.cdhxqh.bowei.bean.Workzy;
import com.cdhxqh.bowei.config.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";
    static DatabaseHelper helper;

    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        String isSuccess = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.LOGINSUCCESS) || jsonString.equals(Constants.CHANGEIMEI)) {
                Constants.UserName = json.getJSONObject("result").getString("displayName");
            }

            return jsonString;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

    /**
     * 解析通用查询接口返回的工单jsonArray信息*
     */
    public static void parsingOrderArr(String data, Context ctx) {
        try {

            JSONArray jsonArray = new JSONArray(data);
            OrderMain orderMain;
            JSONObject jsonObject;
            new OrderMainDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                orderMain = new OrderMain();
                jsonObject = jsonArray.getJSONObject(i);
                orderMain.setNumber(Integer.parseInt(jsonObject.get("WONUM").toString()));
                orderMain.setDescribe(jsonObject.get("DESCRIPTION").toString());
                orderMain.setPlace(jsonObject.get("LOCATION") == null ? "" : jsonObject.get("LOCATION").toString());
                    orderMain.setProperty(jsonObject.get("ASSETNUM").toString());
                orderMain.setWordtype(jsonObject.get("WORKTYPE").toString());
                    orderMain.setReality_worktype(jsonObject.get("ACWORKTYPE").toString());
                    orderMain.setApplyunity(jsonObject.get("WORKDW").toString());
                    orderMain.setMajor(jsonObject.get("WORKZY").toString());
                    orderMain.setState(jsonObject.get("WOSTATUS").toString());
                    orderMain.setDate(jsonObject.get("STATUSDATE").toString());
                    orderMain.setWorkplan(jsonObject.get("JPNUM").toString());
//                orderMain.setReality_starttime(jsonObject.get("ACTSTART").toString());
//                    orderMain.setReality_stoptime(jsonObject.get("ACTFINISH").toString());
                    orderMain.setEmployee_id(jsonObject.get("ONBEHALFOF").toString());
                    orderMain.setQuestiontogether(jsonObject.get("BZ").toString());
                    orderMain.setRatinghours(jsonObject.get("ESTDUR").toString());
                    orderMain.setPm(jsonObject.get("PMNUM").toString());
//                    orderMain.setNotinspection_device(jsonObject.get("ASSETNUMLIST").toString());
//                    orderMain.setInspect_result(jsonObject.get(""));
                new OrderMainDao(ctx).update(orderMain);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<OrderTask> parsingOrderTask(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            OrderTask orderTask;
            JSONObject jsonObject = new JSONObject();
            ArrayList<OrderTask> list = new ArrayList<OrderTask>();
            for (int i = 0; i < jsonArray.length(); i++) {
                orderTask = new OrderTask();
                jsonObject = jsonArray.getJSONObject(i);
                orderTask.setNum(jsonObject.get("WONUM").toString());
                orderTask.setTask(jsonObject.get("TASKID").toString());
                orderTask.setDigest(jsonObject.get("DESCRIPTION").toString());
                orderTask.setJcr(jsonObject.get("JCR").toString());
                orderTask.setWosequence(jsonObject.get("WOSEQUENCE").toString());
                orderTask.setZxr(jsonObject.get("ZXR").toString());
                orderTask.setWorkorderid(jsonObject.get("WORKORDERID").toString());
                list.add(i, orderTask);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void parsingWenerId(String str) {
        try {
            JSONArray fromarr = new JSONArray(str);
            JSONObject object = new JSONObject(Constants.ORDER_GETDATA);
//            JSONArray toweneridarr = new JSONArray();
            JSONArray towenertablearr = new JSONArray();
            if (fromarr.length() == 1) {
                Constants.serOrderUrl(fromarr.getJSONObject(0).get("OWNERID").toString());
            } else {
                String s = "";
                for (int i = 0; i < fromarr.length(); i++) {
                    if (i == 0) {
                        s = fromarr.getJSONObject(0).get("OWNERID").toString();
                    } else {
                        s = s + "," + fromarr.getJSONObject(i).get("OWNERID").toString();
                    }
                }
                Constants.serOrderUrl(s);
            }
            Log.i(TAG, Constants.ORDER_GETDATA);
            Log.i(TAG, fromarr.length() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析并存储位置信息
     *
     * @param ctx
     * @param str
     */
    public static void parsingLocations(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Locations locations;
            new LocationsDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                locations = new Locations();
                jsonObject = jsonArray.getJSONObject(i);
                locations.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                locations.setLOCATION(jsonObject.getString("LOCATION"));
                locations.setLOCATIONSID(jsonObject.getString("LOCATIONSID"));
                new LocationsDao(ctx).update(locations);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingAsset(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Asset asset;
            new AssetDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                asset = new Asset();
                jsonObject = jsonArray.getJSONObject(i);
                asset.setASSETNUM(jsonObject.getString("ASSETNUM"));
                asset.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                asset.setLOCATION(jsonObject.getString("LOCATION"));
                asset.setLOCATIONDESC(jsonObject.getString("LOCATIONDESC"));
                new AssetDao(ctx).update(asset);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkdw(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Workdw workdw;
            new WorkdwDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                workdw = new Workdw();
                jsonObject = jsonArray.getJSONObject(i);
                workdw.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                workdw.setVALUE(jsonObject.getString("VALUE"));
                new WorkdwDao(ctx).update(workdw);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkzy(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Workzy workzy;
            new WorkzyDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                workzy = new Workzy();
                jsonObject = jsonArray.getJSONObject(i);
                workzy.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                workzy.setVALUE(jsonObject.getString("VALUE"));
                new WorkzyDao(ctx).update(workzy);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkType(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            WorkType workType;
            new WorkTypeDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                workType = new WorkType();
                jsonObject = jsonArray.getJSONObject(i);
                workType.setWORKTYPE(jsonObject.getString("WORKTYPE"));
                workType.setWTYPEDESC(jsonObject.getString("WTYPEDESC"));
                new WorkTypeDao(ctx).update(workType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingAcWorkType(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            AcWorkType acWorkType;
            new AcWorkTypeDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                acWorkType = new AcWorkType();
                jsonObject = jsonArray.getJSONObject(i);
                acWorkType.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                acWorkType.setVALUE(jsonObject.getString("VALUE"));
                new AcWorkTypeDao(ctx).update(acWorkType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingFailurecode(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Failurecode failurecode;
            new FailurecodeDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                failurecode = new Failurecode();
                jsonObject = jsonArray.getJSONObject(i);
                failurecode.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                failurecode.setFAILURECODE(jsonObject.getString("FAILURECODE"));
                failurecode.setFAILURECODEID(jsonObject.getString("FAILURECODEID"));
                new FailurecodeDao(ctx).update(failurecode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingFailureList(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            FailureList1 failureList;
            new FailureListDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                failureList = new FailureList1();
                jsonObject = jsonArray.getJSONObject(i);
                failureList.setFAILURELIST(jsonObject.getString("FAILURELIST"));
                failureList.setFAILURECODE(jsonObject.getString("FAILURECODE"));
                failureList.setPARENT(jsonObject.getString("PARENT"));
                failureList.setTYPE(jsonObject.getString("TYPE"));
                new FailureListDao(ctx).update(failureList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobPlan(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Jobplan jobPlan;
            new JobPlanDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new Jobplan();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                jobPlan.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobPlan.setJPNUM(jsonObject.getString("JPNUM"));
                new JobPlanDao(ctx).update(jobPlan);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobTask(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            JobTask jobPlan;
            new JobTaskDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new JobTask();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                jobPlan.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobPlan.setJPNUM(jsonObject.getString("JPNUM"));
                jobPlan.setJPTASK(jsonObject.getString("JPTASK"));
                new JobTaskDao(ctx).update(jobPlan);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobMaterial(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Jobmaterial jobPlan;
            new JobMaterialDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new Jobmaterial();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.setITEMDESC(jsonObject.getString("ITEMDESC"));
                jobPlan.setITEMNUM(jsonObject.getString("ITEMNUM"));
                jobPlan.setITEMQTY(jsonObject.getString("ITEMQTY"));
                jobPlan.setJOBITEMID(jsonObject.getString("JOBITEMID"));
                jobPlan.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobPlan.setJPNUM(jsonObject.getString("JPNUM"));
                new JobMaterialDao(ctx).update(jobPlan);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingErson(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Erson erson;
            new ErsonDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                erson = new Erson();
                jsonObject = jsonArray.getJSONObject(i);
                erson.setDISPLAYNAME(jsonObject.getString("DISPLAYNAME"));
                erson.setPERSONID(jsonObject.getString("PERSONID"));
                erson.setYWBZ(jsonObject.getString("YWBZ"));
                erson.setYWFL(jsonObject.getString("YWFL"));
                new ErsonDao(ctx).update(erson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
