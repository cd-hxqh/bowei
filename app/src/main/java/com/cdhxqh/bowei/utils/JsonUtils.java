package com.cdhxqh.bowei.utils;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.Dao.AcWorkTypeDao;
import com.cdhxqh.bowei.Dao.AlndomainDao;
import com.cdhxqh.bowei.Dao.AssetDao;
import com.cdhxqh.bowei.Dao.ErsonDao;
import com.cdhxqh.bowei.Dao.FailureListDao;
import com.cdhxqh.bowei.Dao.FailurecodeDao;
import com.cdhxqh.bowei.Dao.JobMaterialDao;
import com.cdhxqh.bowei.Dao.JobPlanDao;
import com.cdhxqh.bowei.Dao.JobTaskDao;
import com.cdhxqh.bowei.Dao.LocationsDao;
import com.cdhxqh.bowei.Dao.MaterialInfoDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.Dao.WorkTypeDao;
import com.cdhxqh.bowei.Dao.WorkdwDao;
import com.cdhxqh.bowei.Dao.WorkerInfoDao;
import com.cdhxqh.bowei.Dao.WorkzyDao;
import com.cdhxqh.bowei.OrmLiteOpenHelper.DatabaseHelper;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.Alndomain;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.Deptinventory;
import com.cdhxqh.bowei.bean.Doclinks;
import com.cdhxqh.bowei.bean.Erson;
import com.cdhxqh.bowei.bean.FailureList1;
import com.cdhxqh.bowei.bean.Failurecode;
import com.cdhxqh.bowei.bean.Inventory;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.bean.Jobplan;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.bean.Locations;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.bean.Results;
import com.cdhxqh.bowei.bean.WorkType;
import com.cdhxqh.bowei.bean.Workdw;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.bean.Workzy;
import com.cdhxqh.bowei.config.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Log.i(TAG,"login   data="+data);
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
    public static void parsingOrderArr(String data, Context ctx,String username) {
        try {
            JSONObject jsonObj = new JSONObject(data);
            JSONArray jsonArray = jsonObj.getJSONArray("resultlist");
            OrderMain orderMain;
            JSONObject jsonObject;
//            new OrderMainDao(ctx).deleteall();
            for (int i = 0; i < jsonArray.length(); i++) {
                orderMain = new OrderMain();
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("WONUM")) {//工单号
                    orderMain.setNumber(jsonObject.get("WONUM").toString());
                }
                if (jsonObject.has("DESCRIPTION")) {//描述
                    orderMain.setDescribe(jsonObject.get("DESCRIPTION").toString());
                }
                if (jsonObject.has("LOCATION")) {//位置
                    orderMain.setPlace(jsonObject.get("LOCATION").toString());
                }
                if (jsonObject.has("ASSETNUM")) {//资产
                    orderMain.setProperty(jsonObject.get("ASSETNUM").toString());
                }
                if (jsonObject.has("WORKTYPE")) {//工单类型
                    orderMain.setWordtype(jsonObject.get("WORKTYPE").toString());
                }
                if (jsonObject.has("ACWORKTYPE")) {//实际工单类型
                    orderMain.setReality_worktype(jsonObject.get("ACWORKTYPE").toString());
                }
                if (jsonObject.has("WORKDW")) {//单位
                    orderMain.setApplyunity(jsonObject.get("WORKDW").toString());
                }
                if (jsonObject.has("WORKZY")) {//专业
                    orderMain.setMajor(jsonObject.get("WORKZY").toString());
                }
                if (jsonObject.has("STATUS")) {//状态
                    orderMain.setState(jsonObject.get("STATUS").toString());
                }
                if (jsonObject.has("STATUSDATE")) {//状态日期
                    orderMain.setDate(jsonObject.get("STATUSDATE").toString());
                }
                if (jsonObject.has("JPNUM")) {//作业计划
                    orderMain.setWorkplan(jsonObject.getString("JPNUM"));
                }
                if (jsonObject.has("ONBEHALFOF")) {//录入人
                    orderMain.setEmployee_id(jsonObject.get("ONBEHALFOF").toString());
                }
                if (jsonObject.has("ACTSTART")) {//实际开始时间
                    orderMain.setReality_starttime(jsonObject.get("ACTSTART").toString());
                }
                if (jsonObject.has("ACTFINISH")) {//实际结束时间
                    orderMain.setReality_stoptime(jsonObject.get("ACTFINISH").toString());
                }
                if (jsonObject.has("BZ")) {//备注
                    orderMain.setQuestiontogether(jsonObject.get("BZ").toString());
                }
                if (jsonObject.has("ESTDUR")) {//额定工时
                    orderMain.setRatinghours(jsonObject.get("ESTDUR").toString());
                }
                if (jsonObject.has("PMNUM")) {//PM值
                    orderMain.setPm(jsonObject.get("PMNUM").toString());
                }
                if (jsonObject.has("ASSETNUMLIST")) {//未巡检列表
                    orderMain.setNotinspection_device(jsonObject.get("ASSETNUMLIST").toString());
                }
                if (jsonObject.has("JCJG")) {//检查结果
                    orderMain.setInspect_result(jsonObject.get("JCJG").toString());
                }
                if (jsonObject.has("FAILURECODE")) {//故障类
                    orderMain.setFaultclass(jsonObject.get("FAILURECODE").toString());
                }
                if (jsonObject.has("PROBLEMCODE")) {//问题代码
                    orderMain.setError_coding(jsonObject.get("PROBLEMCODE").toString());
                }
                if (jsonObject.has("GZDJ")) {//工作等级
                    orderMain.setFault_rank(jsonObject.get("GZDJ").toString());
                }
                if (jsonObject.has("PROBLEM")) {//现象
                    orderMain.setPhenomena(jsonObject.get("PROBLEM").toString());
                }
                if (jsonObject.has("CAUSE")) {//原因
                    orderMain.setCause(jsonObject.get("CAUSE").toString());
                }
                if (jsonObject.has("REMEDY")) {//措施
                    orderMain.setRemedy(jsonObject.get("REMEDY").toString());
                }
                orderMain.setIsNew(false);
                orderMain.setBelong(username);
                orderMain.setIsByserch(false);
                if (!new OrderMainDao(ctx).isexitByNum(orderMain.getNumber(),username)) {//如果本地不存在此工单则添加
                    new OrderMainDao(ctx).update(orderMain);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<OrderTask> parsingOrderTask(Context cxt, String data) {
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
                list.add(orderTask);
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
            if(fromarr.length() == 0){
                Constants.ORDER_GETDATA = "";
            }else if (fromarr.length() == 1) {
                Constants.setOrderUrl(StringNumToIntUtils.parsingnum(fromarr.getJSONObject(0).getString("OWNERID")));
            } else {
                String s = "";
                for (int i = 0; i < fromarr.length(); i++) {
                    if (i == 0) {
                        s = StringNumToIntUtils.parsingnum(fromarr.getJSONObject(0).getString("OWNERID"));
                    } else {
                        s = s + "," + StringNumToIntUtils.parsingnum(fromarr.getJSONObject(i).getString("OWNERID"));
                    }
                }
                Constants.setOrderUrl(s);
            }
            Log.i(TAG, Constants.ORDER_GETDATA);
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
            LocationsDao locationsDao = new LocationsDao(ctx);
            locationsDao.deleteall();
            List<Locations>locationsList = new ArrayList<Locations>();
            for (int i = 0; i < jsonArray.length(); i++) {
                locations = new Locations();
                jsonObject = jsonArray.getJSONObject(i);
                locations.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                locations.setLOCATION(jsonObject.getString("LOCATION"));
                locations.setLOCATIONSID(jsonObject.getString("LOCATIONSID"));
                locationsList.add(locations);
            }
            locationsDao.update(locationsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingAsset(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Asset asset;
            AssetDao assetDao = new AssetDao(ctx);
            assetDao.deleteall();
            List<Asset>assetList = new ArrayList<Asset>();
            for (int i = 0; i < jsonArray.length(); i++) {
                asset = new Asset();
                jsonObject = jsonArray.getJSONObject(i);
                asset.setASSETNUM(jsonObject.getString("ASSETNUM"));
                asset.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                asset.setLOCATION(jsonObject.getString("LOCATION"));
                asset.setLOCATIONDESC(jsonObject.getString("LOCATIONDESC"));
                asset.setFAILURECODE(jsonObject.getString("FAILURECODE"));
                assetList.add(asset);
            }
            assetDao.update(assetList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkdw(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Workdw workdw;
            WorkdwDao workdwDao = new WorkdwDao(ctx);
            workdwDao.deleteall();
            List<Workdw>workdwList = new ArrayList<Workdw>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workdw = new Workdw();
                jsonObject = jsonArray.getJSONObject(i);
                workdw.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                workdw.setVALUE(jsonObject.getString("VALUE"));
                workdwList.add(workdw);
            }
            workdwDao.update(workdwList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkzy(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Workzy workzy;
            WorkzyDao workzyDao = new WorkzyDao(ctx);
            workzyDao.deleteall();
            List<Workzy>workzyList = new ArrayList<Workzy>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workzy = new Workzy();
                jsonObject = jsonArray.getJSONObject(i);
                workzy.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                workzy.setVALUE(jsonObject.getString("VALUE"));
                workzyList.add(workzy);
            }
            workzyDao.update(workzyList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingWorkType(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            WorkType workType;
            WorkTypeDao workTypeDao = new WorkTypeDao(ctx);
            workTypeDao.deleteall();
            List<WorkType>workTypeList = new ArrayList<WorkType>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workType = new WorkType();
                jsonObject = jsonArray.getJSONObject(i);
                workType.setWORKTYPE(jsonObject.getString("WORKTYPE"));
                workType.setWTYPEDESC(jsonObject.getString("WTYPEDESC"));
                workTypeList.add(workType);
            }
            workTypeDao.update(workTypeList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingAcWorkType(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            AcWorkType acWorkType;
            AcWorkTypeDao acWorkTypeDao = new AcWorkTypeDao(ctx);
            acWorkTypeDao.deleteall();
            List<AcWorkType>acWorkTypeList = new ArrayList<AcWorkType>();
            for (int i = 0; i < jsonArray.length(); i++) {
                acWorkType = new AcWorkType();
                jsonObject = jsonArray.getJSONObject(i);
                acWorkType.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                acWorkType.setVALUE(jsonObject.getString("VALUE"));
                acWorkTypeList.add(acWorkType);
            }
            acWorkTypeDao.update(acWorkTypeList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingFailurecode(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Failurecode failurecode;
            FailurecodeDao failurecodeDao = new FailurecodeDao(ctx);
            failurecodeDao.deleteall();
            List<Failurecode>failurecodeList = new ArrayList<Failurecode>();
            for (int i = 0; i < jsonArray.length(); i++) {
                failurecode = new Failurecode();
                jsonObject = jsonArray.getJSONObject(i);
                failurecode.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                failurecode.setFAILURECODE(jsonObject.getString("FAILURECODE"));
                failurecode.setFAILURECODEID(jsonObject.getString("FAILURECODEID"));
                failurecodeList.add(failurecode);
            }
            failurecodeDao.update(failurecodeList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingFailureList(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            FailureList1 failureList;
            FailureListDao failureListDao = new FailureListDao(ctx);
            failureListDao.deleteall();
            List<FailureList1>failureList1List = new ArrayList<FailureList1>();
            for (int i = 0; i < jsonArray.length(); i++) {
                failureList = new FailureList1();
                jsonObject = jsonArray.getJSONObject(i);
                failureList.setFAILURELIST(jsonObject.getString("FAILURELIST"));
                failureList.setFAILURECODE(jsonObject.getString("FAILURECODE"));
                failureList.setPARENT(jsonObject.getString("PARENT"));
                failureList.setTYPE(jsonObject.getString("TYPE"));
                failureList1List.add(failureList);
            }
            failureListDao.update(failureList1List);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobPlan(Context ctx, String str) {
        try {
//            JSONArray jsonArray = new JSONObject(str).getJSONArray("resultlist");
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Jobplan jobPlan;
            JobPlanDao jobPlanDao = new JobPlanDao(ctx);
            jobPlanDao.deleteall();
            List<Jobplan>jobplanList = new ArrayList<Jobplan>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new Jobplan();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                jobPlan.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobPlan.setJPNUM(jsonObject.getString("JPNUM"));
                jobPlan.setJPDURATION(jsonObject.getString("JPDURATION"));
                jobPlan.setPARENT(jsonObject.getString("PARENT"));
                jobplanList.add(jobPlan);
            }
            jobPlanDao.update(jobplanList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobTask(Context ctx, String str) {
        try {
//            JSONArray jsonArray = new JSONObject(str).getJSONArray("resultlist");
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            JobTask jobTask;
            JobTaskDao jobTaskDao = new JobTaskDao(ctx);
            jobTaskDao.deleteall();
            List<JobTask>list = new ArrayList<JobTask>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobTask = new JobTask();
                jsonObject = jsonArray.getJSONObject(i);
                jobTask.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                jobTask.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobTask.setJOBTASKID(jsonObject.getInt("JOBTASKID"));
                jobTask.setJPNUM(jsonObject.getString("JPNUM"));
                jobTask.setJPTASK(jsonObject.getInt("JPTASK"));//序号
                list.add(jobTask);
//                jobTaskDao.update(jobTask);
//                List<JobTask>list = jobTaskDao.queryForAll();
            }
            jobTaskDao.update(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingJobMaterial(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Jobmaterial jobPlan;
            JobMaterialDao jobMaterialDao = new JobMaterialDao(ctx);
            jobMaterialDao.deleteall();
            List<Jobmaterial>jobmaterialList = new ArrayList<Jobmaterial>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new Jobmaterial();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.setITEMDESC(jsonObject.getString("ITEMDESC"));
                jobPlan.setITEMNUM(jsonObject.getString("ITEMNUM"));
                jobPlan.setITEMQTY(jsonObject.getString("ITEMQTY"));
                jobPlan.setJOBITEMID(jsonObject.getString("JOBITEMID"));
                jobPlan.setJOBPLANID(jsonObject.getString("JOBPLANID"));
                jobPlan.setJPNUM(jsonObject.getString("JPNUM"));
                jobPlan.setWarehouse(jsonObject.getString("LOCATION"));
                jobmaterialList.add(jobPlan);
            }
            jobMaterialDao.update(jobmaterialList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingErson(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Erson erson;
            ErsonDao ersonDao = new ErsonDao(ctx);
            ersonDao.deleteall();
            List<Erson>ersonList = new ArrayList<Erson>();
            for (int i = 0; i < jsonArray.length(); i++) {
                erson = new Erson();
                jsonObject = jsonArray.getJSONObject(i);
                erson.setDISPLAYNAME(jsonObject.getString("DISPLAYNAME"));
                erson.setPERSONID(jsonObject.getString("PERSONID"));
                erson.setYWBZ(jsonObject.getString("YWBZ"));
                erson.setYWFL(jsonObject.getString("YWFL"));
                ersonList.add(erson);
            }
            ersonDao.update(ersonList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsingAlndomain(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Alndomain alndomain;
            AlndomainDao alndomainDao = new AlndomainDao(ctx);
            alndomainDao.deleteall();
            List<Alndomain>alndomainList = new ArrayList<Alndomain>();
            for (int i = 0; i < jsonArray.length(); i++) {
                alndomain = new Alndomain();
                jsonObject = jsonArray.getJSONObject(i);
                alndomain.setDESCRIPTION(jsonObject.getString("DESCRIPTION"));
                alndomain.setVALUE(jsonObject.getString("VALUE"));
                alndomainList.add(alndomain);
            }
            alndomainDao.update(alndomainList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析员工信息数据
     *
     * @param ctx
     * @param str
     * @param id
     */
    public static void parsingWorkInfo(Context ctx, String str, int id) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            WorkerInfo workerInfo;
            SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d1=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat d2=new SimpleDateFormat("HH:mm:ss");
            Date startdate;
            Date stopdate;
            Date starttime;
            Date stoptime;
            List<WorkerInfo>workerInfos = new ArrayList<>();
            WorkerInfoDao workerInfoDao = new WorkerInfoDao(ctx);
            for (int i = 0; i < jsonArray.length(); i++) {
                workerInfo = new WorkerInfo();
                jsonObject = jsonArray.getJSONObject(i);
                workerInfo.setLabtransId(jsonObject.getString("LABTRANSID"));
                workerInfo.setNumber(jsonObject.getString("LABORCODE"));
                workerInfo.setBelongorderid(id);
                if(!jsonObject.getString("STARTDATE").equals("")||jsonObject.getString("STARTDATE")!=null){
                    startdate = dd.parse(jsonObject.getString("STARTDATE"));
                    workerInfo.setStartdate(d1.format(startdate));
                }
                if(!jsonObject.getString("FINISHDATE").equals("")||jsonObject.getString("FINISHDATE")!=null){
                    stopdate = dd.parse(jsonObject.getString("FINISHDATE"));
                    workerInfo.setStopdate(d1.format(stopdate));
                }
                if(!jsonObject.getString("STARTTIME").equals("")||jsonObject.getString("STARTTIME")!=null){
                    starttime = dd.parse(jsonObject.getString("STARTTIME"));
                    workerInfo.setStarttime(d2.format(starttime));
                }
                if(!jsonObject.getString("FINISHTIME").equals("")||jsonObject.getString("FINISHTIME")!=null){
                    stoptime = dd.parse(jsonObject.getString("FINISHTIME"));
                    workerInfo.setStoptime(d2.format(stoptime));
                }
                    workerInfo.setWorktime(jsonObject.getString("REGULARHRS"));
                if(!workerInfoDao.queryByLabtransId(jsonObject.getString("LABTRANSID"), id)) {
                    workerInfoDao.update(workerInfo);
                }
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析计划物料消耗数据
     *
     * @param ctx
     * @param str
     * @param id
     */
    public static void parsingWpMaterial(Context ctx, String str, int id) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            MaterialInfo materialInfo;
            for (int i = 0; i < jsonArray.length(); i++) {
                materialInfo = new MaterialInfo();
                jsonObject = jsonArray.getJSONObject(i);
                if (!new MaterialInfoDao(ctx).queryByLabtransId(jsonObject.getString("ITEMNUM"), id)) {//
                    materialInfo.setBelongorderid(id);
                    materialInfo.setName(jsonObject.getString("DESCRIPTION"));
                    materialInfo.setNumber(jsonObject.getString("ITEMNUM"));
                    materialInfo.setSize(jsonObject.getInt("ITEMQTY"));
                    materialInfo.setWarehouse(jsonObject.getString("LOCATION"));
                    materialInfo.setIsPlan(true);
                    new MaterialInfoDao(ctx).update(materialInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //    DEPTMATUSETRANS
    public static void parsingDeptmatusetrans(Context ctx, String str, int id) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            MaterialInfo materialInfo;
            for (int i = 0; i < jsonArray.length(); i++) {
                materialInfo = new MaterialInfo();
                jsonObject = jsonArray.getJSONObject(i);
//                if (!new MaterialInfoDao(ctx).queryByLabtransId(jsonObject.getString("ITEMNUM"), id)) {//
                    materialInfo.setBelongorderid(id);
                    materialInfo.setName(jsonObject.getString("BJMC"));
                    materialInfo.setNumber(jsonObject.getString("ITEMNUM"));
                    materialInfo.setSize(jsonObject.getInt("COUNT"));
                    materialInfo.setWarehouse(jsonObject.getString("LOCATION"));
                    materialInfo.setIsPlan(false);
                    new MaterialInfoDao(ctx).update(materialInfo);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析知识库数据*
     */
    public static ArrayList<Knowledge> parsingKnowKedge(Context ctx, String data) {
        ArrayList<Knowledge> list = null;
        try {
            JSONArray jsonArray = new JSONObject(data).getJSONArray("resultlist");
            JSONObject jsonObject;
            list = new ArrayList<Knowledge>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Knowledge knowledge = new Knowledge();
                jsonObject = jsonArray.getJSONObject(i);
                knowledge.setKnowdesc(jsonObject.getString("KNOWDESC")); //名称
                knowledge.setKnowledgeid(Integer.valueOf(jsonObject.getString("KNOWLEDGEID"))); //编号
                knowledge.setKnowdl(jsonObject.getString("KNOWDL")); //大类
                knowledge.setKnowxl(jsonObject.getString("KNOWXL")); //小类
                knowledge.setKnowbz(jsonObject.getString("KNOWBZ")); //备注
                knowledge.setKnowbh(jsonObject.getString("KNOWBH"));
                list.add(knowledge);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 解析知识库附件*
     */
    public static ArrayList<Doclinks> parsingDoclinks(Context ctx, String data) {
        ArrayList<Doclinks> list = null;
        Doclinks doclinks = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Doclinks>();
            for (int i = 0; i < jsonArray.length(); i++) {
                doclinks = new Doclinks();
                jsonObject = jsonArray.getJSONObject(i);
                doclinks.setDocument(jsonObject.getString("DOCUMENT")); //编号
                doclinks.setDescription(jsonObject.getString("DESCRIPTION")); //名称
                doclinks.setUrlname(Constants.SERVER_URL + replace(jsonObject.getString("URLNAME"))); //路径
                list.add(doclinks);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析返回的数据结果*
     */

    public static String parsing(Context ctx, String data) {
        String result = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
            }

            return result;


        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
    }


    /**
     * 替换数据格式*
     */
    private static String replace(String data) {
        String newData = data.replaceAll("\\\\", "/");
        Log.i(TAG, "newData=" + newData);


        return newData;
    }


    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                JSONObject rJson = new JSONObject(result);
                String curpage = rJson.getString("curpage");
                String totalresult = rJson.getString("totalresult");
                String resultlist = rJson.getString("resultlist");
                String totalpage = rJson.getString("totalpage");
                String showcount = rJson.getString("showcount");
                results = new Results();
                results.setCurpage(Integer.valueOf(curpage));
                results.setTotalresult(totalresult);
                results.setResultlist(resultlist);
                results.setTotalpage(totalpage);
                results.setShowcount(Integer.valueOf(showcount));
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }
    }


    /**
     * 解析公司库存信息*
     */
    public static ArrayList<Inventory> parsingInventory(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        ArrayList<Inventory> list = null;
        Inventory inventory = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Inventory>();
            for (int i = 0; i < jsonArray.length(); i++) {
                inventory = new Inventory();
                jsonObject = jsonArray.getJSONObject(i);
                inventory.setItemnum(jsonObject.getString("ITEMNUM")); //物料编号
                inventory.setDescription(jsonObject.getString("ITEMDESC")); //名称
                inventory.setLocation(jsonObject.getString("LOCATION")); //库房
                inventory.setCurbaltotal(jsonObject.getString("CURBALTOTAL")); //当前余量

                Log.i(TAG, "ITEMNUM=" + jsonObject.getString("ITEMNUM"));
                list.add(inventory);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析捷运库存信息*
     */
    public static ArrayList<Deptinventory> parsingDeptinventory(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        ArrayList<Deptinventory> list = null;
        Deptinventory inventory = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Deptinventory>();
            for (int i = 0; i < jsonArray.length(); i++) {
                inventory = new Deptinventory();
                jsonObject = jsonArray.getJSONObject(i);

                Log.i(TAG, "ITEMNUM=" + jsonObject.getString("ITEMNUM"));
                inventory.setItemnum(jsonObject.getString("ITEMNUM")); //物料编号
                inventory.setBjmc(jsonObject.getString("BJMC")); //名称
                inventory.setLocation(jsonObject.getString("LOCATION")); //库房
                inventory.setCurbaltotal(jsonObject.getString("CURBALTOTAL")); //当前余量
                inventory.setLocdesc(jsonObject.getString("LOCDESC")); //库房名称

                Log.i(TAG, "ITEMNUM=" + jsonObject.getString("ITEMNUM"));
                list.add(inventory);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 解析资产信息*
     */

    public static Asset parsingAssets(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        boolean isexit = new AssetDao(ctx).isexitByNum(data);
        if(isexit) {
            Asset asset = null;
            asset = new Asset();
            asset.setASSETNUM(data); //编号
            asset.setDESCRIPTION(new AssetDao(ctx).queryFordescriptionBynum(data)); //名称
            asset.setLOCATION(new AssetDao(ctx).queryForLoucationBynum(data));//位置
            return asset;
        }else {
            return null;
        }
    }


    /**
     * 解析工单任务*
     */
    public static ArrayList<OrderMain> parsingOrderMain(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        ArrayList<OrderMain> list = null;
        OrderMain orderMain = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<OrderMain>();
            for (int i = 0; i < jsonArray.length(); i++) {
                orderMain = new OrderMain();
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("WONUM")) {
                    orderMain.setNumber(jsonObject.get("WONUM").toString());
                }
                if (jsonObject.has("DESCRIPTION")) {
                    orderMain.setDescribe(jsonObject.get("DESCRIPTION").toString());
                }
                if (jsonObject.has("LOCATION")) {
                    orderMain.setPlace(jsonObject.get("LOCATION").toString());
                }
                if (jsonObject.has("ASSETNUM")) {
                    orderMain.setProperty(jsonObject.get("ASSETNUM").toString());
                }
                if (jsonObject.has("WORKTYPE")) {
                    orderMain.setWordtype(jsonObject.get("WORKTYPE").toString());
                }
                if (jsonObject.has("ACWORKTYPE")) {
                    orderMain.setReality_worktype(jsonObject.get("ACWORKTYPE").toString());
                }
                if (jsonObject.has("WORKDW")) {
                    orderMain.setApplyunity(jsonObject.get("WORKDW").toString());
                }
                if (jsonObject.has("WORKZY")) {
                    orderMain.setMajor(jsonObject.get("WORKZY").toString());
                }
                if (jsonObject.has("STATUS")) {
                    orderMain.setState(jsonObject.get("STATUS").toString());
                }
                if (jsonObject.has("STATUSDATE")) {
                    orderMain.setDate(jsonObject.get("STATUSDATE").toString());
                }
                if (jsonObject.has("JPNUM")) {
                    orderMain.setWorkplan(jsonObject.getString("JPNUM"));
                }
                if (jsonObject.has("ONBEHALFOF")) {
                    orderMain.setEmployee_id(jsonObject.get("ONBEHALFOF").toString());
                }
                if (jsonObject.has("ACTSTART")) {
                    orderMain.setReality_starttime(jsonObject.get("ACTSTART").toString());
                }
                if (jsonObject.has("ACTFINISH")) {
                    orderMain.setReality_stoptime(jsonObject.get("ACTFINISH").toString());
                }
                if (jsonObject.has("BZ")) {
                    orderMain.setQuestiontogether(jsonObject.get("BZ").toString());
                }
                if (jsonObject.has("ESTDUR")) {
                    orderMain.setRatinghours(jsonObject.get("ESTDUR").toString());
                }
                if (jsonObject.has("PMNUM")) {
                    orderMain.setPm(jsonObject.get("PMNUM").toString());
                }
                if (jsonObject.has("ASSETNUMLIST")) {
                    orderMain.setNotinspection_device(jsonObject.get("ASSETNUMLIST").toString());
                }
                if (jsonObject.has("JCJG")) {//检查结果
                    orderMain.setInspect_result(jsonObject.get("JCJG").toString());
                }
                if (jsonObject.has("FAILURECODE")) {//故障类
                    orderMain.setFaultclass(jsonObject.get("FAILURECODE").toString());
                }
                if (jsonObject.has("PROBLEMCODE")) {//问题代码
                    orderMain.setError_coding(jsonObject.get("PROBLEMCODE").toString());
                }
                if (jsonObject.has("GZDJ")) {//工作等级
                    orderMain.setFault_rank(jsonObject.get("GZDJ").toString());
                }
                if (jsonObject.has("PROBLEM")) {//现象
                    orderMain.setPhenomena(jsonObject.get("PROBLEM").toString());
                }
                if (jsonObject.has("CAUSE")) {//措施
                    orderMain.setCause(jsonObject.get("CAUSE").toString());
                }
                if (jsonObject.has("REMEDY")) {//原因
                    orderMain.setRemedy(jsonObject.get("REMEDY").toString());
                }
                orderMain.setIsNew(false);
                orderMain.setIsByserch(true);
                list.add(orderMain);
            }

            return list;
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析员工信息数据
     *
     * @param ctx
     * @param str
     */
    public static List<WorkerInfo> parsingWorkInfo(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            WorkerInfo workerInfo;
            SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d1=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat d2=new SimpleDateFormat("HH:mm:ss");
            Date startdate;
            Date stopdate;
            Date starttime;
            Date stoptime;
            List<WorkerInfo>workerInfos = new ArrayList<>();
            WorkerInfoDao workerInfoDao = new WorkerInfoDao(ctx);
            for (int i = 0; i < jsonArray.length(); i++) {
                workerInfo = new WorkerInfo();
                jsonObject = jsonArray.getJSONObject(i);
                workerInfo.setLabtransId(jsonObject.getString("LABTRANSID"));
                workerInfo.setNumber(jsonObject.getString("LABORCODE"));
                if(!jsonObject.getString("STARTDATE").equals("")||jsonObject.getString("STARTDATE")!=null){
                    startdate = dd.parse(jsonObject.getString("STARTDATE"));
                    workerInfo.setStartdate(d1.format(startdate));
                }
                if(!jsonObject.getString("FINISHDATE").equals("")||jsonObject.getString("FINISHDATE")!=null){
                    stopdate = dd.parse(jsonObject.getString("FINISHDATE"));
                    workerInfo.setStopdate(d1.format(stopdate));
                }
                if(!jsonObject.getString("STARTTIME").equals("")||jsonObject.getString("STARTTIME")!=null){
                    starttime = dd.parse(jsonObject.getString("STARTTIME"));
                    workerInfo.setStarttime(d2.format(starttime));
                }
                if(!jsonObject.getString("FINISHTIME").equals("")||jsonObject.getString("FINISHTIME")!=null){
                    stoptime = dd.parse(jsonObject.getString("FINISHTIME"));
                    workerInfo.setStoptime(d2.format(stoptime));
                }
                workerInfo.setWorktime(jsonObject.getString("REGULARHRS"));
                workerInfos.add(workerInfo);
//                }
            }
            return workerInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析计划物料消耗数据
     *
     * @param ctx
     * @param str
     */
    public static List<MaterialInfo> parsingWpMaterial(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            MaterialInfo materialInfo;
            List<MaterialInfo>materialInfoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                materialInfo = new MaterialInfo();
                jsonObject = jsonArray.getJSONObject(i);
                    materialInfo.setName(jsonObject.getString("DESCRIPTION"));
                    materialInfo.setNumber(jsonObject.getString("ITEMNUM"));
                    materialInfo.setSize(jsonObject.getInt("ITEMQTY"));
                    materialInfo.setWarehouse(jsonObject.getString("LOCATION"));
                    materialInfo.setIsPlan(true);
                    materialInfoList.add(materialInfo);
            }
            return materialInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    DEPTMATUSETRANS
    public static List<MaterialInfo> parsingDeptmatusetrans(Context ctx, String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            MaterialInfo materialInfo;
            List<MaterialInfo>materialInfoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                materialInfo = new MaterialInfo();
                jsonObject = jsonArray.getJSONObject(i);
                materialInfo.setName(jsonObject.getString("BJMC"));
                materialInfo.setNumber(jsonObject.getString("ITEMNUM"));
                materialInfo.setSize(jsonObject.getInt("COUNT"));
                materialInfo.setWarehouse(jsonObject.getString("LOCATION"));
                materialInfo.setIsPlan(false);
                materialInfoList.add(materialInfo);
            }
            return materialInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<MaterialInfo>();
    }
}
