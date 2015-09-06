package com.cdhxqh.bowei.utils;

import android.content.Context;
import android.util.Log;

import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.config.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";

    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        String isSuccess = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            Constants.UserName = json.getJSONObject("result").getString("displayName");
            return jsonString;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
        }
    }

    /**
     * 解析通用查询接口返回的工单jsonArray信息*
     */
    public static ArrayList<OrderMain> parsingOrderArr(String data) {
        try {

            JSONArray jsonArray = new JSONArray(data);
            OrderMain orderMain;
            JSONObject jsonObject;
            ArrayList<OrderMain> list = new ArrayList<OrderMain>();
            for (int i = 0; i < jsonArray.length(); i++) {
                orderMain = new OrderMain();
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.get("WORKTYPE").toString().equals("PM")
                        ||jsonObject.get("WORKTYPE").toString().equals("CM")) {
                    orderMain.setNumber(Integer.parseInt(jsonObject.get("WONUM").toString()));
                    orderMain.setDescribe(jsonObject.get("DESCRIPTION").toString());
//                    orderMain.setPlace(jsonObject.get("LOCATION") == null ? "" : jsonObject.get("LOCATION").toString());
//                    orderMain.setProperty(jsonObject.get("ASSETNUM").toString());
//                    orderMain.setWordtype(jsonObject.get("WORKTYPE").toString());
//                    orderMain.setReality_worktype(jsonObject.get("ACWORKTYPE").toString());
//                    orderMain.setApplyunity(jsonObject.get("WORKDW").toString());
//                    orderMain.setMajor(jsonObject.get("WORKZY").toString());
//                    orderMain.setState(jsonObject.get("Status").toString());
//                    orderMain.setDate(jsonObject.get("REPORTDATE").toString());
//                    orderMain.setWorkplan(jsonObject.get("JPNUM").toString());
//                    orderMain.setReality_starttime(jsonObject.get("ACTSTART").toString());
//                    orderMain.setReality_stoptime(jsonObject.get("ACTFINISH").toString());
//                    orderMain.setEmployee_id(jsonObject.get("ONBEHALFOF").toString());
//                    orderMain.setQuestiontogether(jsonObject.get("Bz").toString());
//                    orderMain.setRatinghours(jsonObject.get("ESTDUR").toString());
//                    orderMain.setPm(jsonObject.get("PMNUM").toString());
//                    orderMain.setNotinspection_device(jsonObject.get("ASSETNUMLIST").toString());
//                    orderMain.setInspect_result(jsonObject.get(""));

                    list.add(i, orderMain);
                }
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
            if(fromarr.length() ==1){
                Constants.serOrderUrl(fromarr.getJSONObject(0).get("OWNERID").toString());
            }else {
                String s = "";
                for (int i = 0; i < fromarr.length(); i++) {
                    if(i ==0){
                        s = fromarr.getJSONObject(0).get("OWNERID").toString();
                    }else {
                        s = s + "," + fromarr.getJSONObject(i).get("OWNERID").toString();
                    }
                }
                Constants.serOrderUrl(s);
            }
            Log.i(TAG, Constants.ORDER_GETDATA);
            Log.i(TAG,fromarr.length()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
