package com.cdhxqh.bowei.utils;

import android.app.Activity;
import android.content.Context;

import com.cdhxqh.bowei.Dao.MaterialInfoDao;
import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.Dao.WorkerInfoDao;
import com.cdhxqh.bowei.application.BaseApplication;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.bean.WorkerInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * webservice提交参数生成类
 * Created by think on 2015/9/11.
 */
public class WebserviceDataUtils {

    public static String updateData(String usename,Context context, OrderMain orderMain) {
        JSONObject jsonObject = new JSONObject();
        int id = orderMain.getId();

        try {
            jsonObject.put("woNum", orderMain.getNumber()!=null ? orderMain.getNumber() : "");
            jsonObject.put("description", orderMain.getDescribe()!=null ? orderMain.getDescribe() : "");
            jsonObject.put("location", orderMain.getPlace()!=null ? orderMain.getPlace() : "");
            jsonObject.put("assetnum", orderMain.getProperty()!=null ? orderMain.getProperty() : "");
            jsonObject.put("worktype", orderMain.getWordtype()!=null ? orderMain.getWordtype() : "");
            jsonObject.put("acworktype", orderMain.getReality_worktype()!=null ? orderMain.getReality_worktype() : "");
            jsonObject.put("workdw", orderMain.getApplyunity()!=null ? orderMain.getApplyunity() : "");
            jsonObject.put("workzy", orderMain.getMajor()!=null ? orderMain.getMajor() : "");
            jsonObject.put("actstart", orderMain.getReality_starttime()!=null ? orderMain.getReality_starttime() : "");
            jsonObject.put("actfinish", orderMain.getReality_stoptime()!=null ? orderMain.getReality_stoptime() : "");
            jsonObject.put("problem", orderMain.getError_coding()!=null ? orderMain.getError_coding() : "");
            jsonObject.put("remedy", orderMain.getRemedy()!=null ? orderMain.getRemedy() : "");
            jsonObject.put("cause", orderMain.getCause()!=null ? orderMain.getCause() : "");
            jsonObject.put("reportedby", usename);
            jsonObject.put("failurecode", orderMain.getFaultclass()!=null ? orderMain.getFaultclass() : "");
            jsonObject.put("failurelist1", orderMain.getError_coding_list()!=null ? orderMain.getError_coding_list() : "");
            jsonObject.put("failurelist2", orderMain.getCause_list()!=null ? orderMain.getCause_list() : "");
            jsonObject.put("failurelist3", orderMain.getRemedy_list()!=null ? orderMain.getRemedy_list() : "");
            jsonObject.put("reportdate", orderMain.getDate()!=null ? orderMain.getDate() : "");
            jsonObject.put("onbehalfof", orderMain.getEmployee_id()!=null ? orderMain.getEmployee_id() : "");
            jsonObject.put("bz", orderMain.getQuestiontogether()!=null ? orderMain.getQuestiontogether() : "");
            jsonObject.put("assetnumlist", orderMain.getNotinspection_device()!=null ? orderMain.getNotinspection_device() : "");
            jsonObject.put("jpnum", orderMain.getWorkplan()!=null ? orderMain.getWorkplan() : "");
            jsonObject.put("gzdj", orderMain.getFault_rank()!=null ? orderMain.getFault_rank() : "");
            jsonObject.put("jcjg", orderMain.getInspect_result()!=null ? orderMain.getInspect_result() : "");

            List<OrderTask> orderTask = new OrderTaskDao(context).queryByOrderId(id);
            if (orderTask.size() > 0) {
                JSONArray taskAry = new JSONArray();
                JSONObject taskObj;
                for(int i = 0;i < orderTask.size();i++){
                    taskObj = new JSONObject();
                    taskObj.put("wosequence",orderTask.get(i).getWosequence()!=null ? orderTask.get(i).getWosequence() : "");
                    taskObj.put("taskid",orderTask.get(i).getTask()!=null ? orderTask.get(i).getTask() : "");
                    taskObj.put("description",orderTask.get(i).getDigest()!=null ? orderTask.get(i).getDigest() : "");
                    taskObj.put("zxr",orderTask.get(i).getZxr()!=null ? orderTask.get(i).getZxr() : "");
                    taskObj.put("jcr",orderTask.get(i).getJcr()!=null ? orderTask.get(i).getJcr() : "");
                    taskObj.put("workorderid",orderTask.get(i).getWorkorderid()!=null ? orderTask.get(i).getWorkorderid() : "");
                    taskAry.put(i,taskObj);
                }
                jsonObject.put("wotasks", taskAry);
            }else {
                jsonObject.put("wotasks", new JSONArray());
            }
            List<WorkerInfo> workerInfoList = new WorkerInfoDao(context).queryByOrdermainId(orderMain.getId());
            if(workerInfoList.size()>0){
                JSONArray workAry = new JSONArray();
                JSONObject workObj;
                for(int i = 0;i < workerInfoList.size();i++){
                    workObj = new JSONObject();
                    workObj.put("laborcode",workerInfoList.get(i).getNumber()!=null ? workerInfoList.get(i).getNumber() : "");
                    workObj.put("starttime",(workerInfoList.get(i).getStartdate()!=null&&workerInfoList.get(i).getStarttime()!=null)
                            ?workerInfoList.get(i).getStartdate()+" "+workerInfoList.get(i).getStarttime():"");
                    workObj.put("finishtime",(workerInfoList.get(i).getStopdate()!=null&&workerInfoList.get(i).getStoptime()!=null)?
                            workerInfoList.get(i).getStopdate()+" "+workerInfoList.get(i).getStoptime():"");
                    workObj.put("regularhrs",workerInfoList.get(i).getWorktime()!=null ? workerInfoList.get(i).getWorktime() : "");
                    workAry.put(i,workObj);
                }
                jsonObject.put("labtrans",workAry);
            }else {
                jsonObject.put("labtrans", new JSONArray());
            }
            List<MaterialInfo>materialInfoList = new MaterialInfoDao(context).queryByLabtransId(orderMain.getId(),true);
            if(materialInfoList.size()>0){
                JSONArray materialAry = new JSONArray();
                JSONObject materialObj;
                for(int i = 0;i < materialInfoList.size();i++){
                    materialObj = new JSONObject();
                    materialObj.put("itemnum",materialInfoList.get(i).getNumber()!=null ? materialInfoList.get(i).getNumber() : "");
                    materialObj.put("itemqty",materialInfoList.get(i).getSize() > 0 ? materialInfoList.get(i).getSize() : 0);
                    materialObj.put("location",materialInfoList.get(i).getWarehouse()!=null ? materialInfoList.get(i).getWarehouse() : "");
                    materialAry.put(i,materialObj);
                }
                jsonObject.put("wpmaterials",materialAry);
            }else {
                jsonObject.put("wpmaterials",new JSONArray());
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
