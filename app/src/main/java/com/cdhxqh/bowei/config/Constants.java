package com.cdhxqh.bowei.config;

import android.content.Context;

public class Constants {
    public static String webserviceURL = "http://182.92.8.94:7001/meaweb/schema/common/meta/MXMeta.xsd";//webservice接口地址

    public static String loginURL = "http://182.92.8.94:7001/maximo/mobile/system/login";//登录接口地址

    public static String searchURL = "http://182.92.8.94:7001/maximo/mobile/common/api";//通用查询接口地址
    public static String ORDER_GETDATA = "{appid:'WO',objectname:'WORKORDER',curpage:1,showcount:20,option:'read',condition:{WORKTYPE:'PM'}}";
    //获得工单任务信息
    public static String GET_ORDER_TASK = "{appid:'WOACTIVITY',objectname:'WOACTIVITY',option:'read',condition:{WORKTYPE:'PM'}}";
    public static String getOrderTaskUrl(String num){
        return "{appid:'WOACTIVITY',objectname:'WOACTIVITY',option:'read',condition:{wonum:'"+num+"'}}";
    }
    //获得工单流程任务分配
    public static String GET_OWNER_ID = "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
            "condition:{origperson:'maxadmin',PROCESSNAME:'APPWFWO',ASSIGNSTATUS:'ACTIVE'}}";
    public static String getOwnerId(String name){
        return "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
                "condition:{origperson:'"+name+"',PROCESSNAME:'APPWFWO',ASSIGNSTATUS:'ACTIVE'}}";
    }
    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";

    public static String getWsUrl(Context context) {
        return webserviceURL;
    }


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录
    /**用户登录表识--结束**/

}
