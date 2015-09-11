package com.cdhxqh.bowei.config;

import android.content.Context;

public class Constants {
    public static String webserviceURL = "http://182.92.8.94:7001/maximo/services/WOService";//webservice接口地址

    public static String loginURL = "http://182.92.8.94:7001/maximo/mobile/system/login";//登录接口地址

    public static String SEARCHURL = "http://182.92.8.94:7001/maximo/mobile/common/api";//通用查询接口地址
    /**工单测试接口**/
    public static String ORDER_GETDATA = "{appid:'WO',objectname:'WORKORDER',curpage:1,showcount:20,option:'read',condition:{WORKTYPE:'PM'}}";
    /**工单接口**/
    public static void serOrderUrl(String num){
        ORDER_GETDATA = "{appid:'WO',objectname:'WORKORDER',option:'read',condition:{workorderid:'"+num+"'}}";
    }
    //获得工单任务信息
    public static String GET_ORDER_TASK = "{appid:'WOACTIVITY',objectname:'WOACTIVITY',option:'read',condition:{WORKTYPE:'PM'}}";
    public static String getOrderTaskUrl(String num){
        return "{appid:'WOACTIVITY',objectname:'WOACTIVITY',option:'read',condition:{wonum:'"+num+"'}}";
    }
    //获得实际员工信息
    public static String getRealWorkerInfoUrl(String num){
        return "{appid:'LABTRANS',objectname:'LABTRANS',option:'read',condition:{refwo:'"+num+"'}}";
    }
    //获得计划物料消耗
    public static String getMeterialConsumePlanUrl(String num){
        return "{appid:'WPMATERIAL',objectname:'WPMATERIAL',option:'read',condition:{wonum:'"+num+"'}}";
    }
    //获得实际物料消耗
    public static String getMeterialConsumeRealUrl(String num){
        return "{appid:'DEPTMATUSETRANS',objectname:'DEPTMATUSETRANS',option:'read',condition:{wonum:'"+num+"',issuetype:'ISSUETYPE'}}";
    }
    //获得工单流程任务分配
    public static String GET_OWNER_ID = "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
            "condition:{origperson:'maxadmin',PROCESSNAME:'APPWFWO',ASSIGNSTATUS:'ACTIVE'}}";
    public static String getOwnerId(String name){
        return "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
                "condition:{origperson:'"+name+"',PROCESSNAME:'APPWFWO',ASSIGNSTATUS:'ACTIVE'}}";
    }

    //知识库列表信息
    public static String KNOW_LEDGE_LIST = "{appid:'KNOWLEDGE',objectname:'KNOWLEDGE',option:'read'}";









    //位置信息
    public static String LOCATIONS = "{appid:'LOCATIONS',objectname:'LOCATIONS',option:'read',condition:{TYPE:'OPERATING'}}";
    //资产
    public static String ASSET = "{appid:'ASSET',objectname:'ASSET',option:'read'}";
    //单位
    public static String WORKDW = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'WORKDW'}}";
    //专业
    public static String WORKZY = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'WORKZY'}}";
    //工作类型
    public static String WORKTYPE = "{appid:'WORKTYPE',objectname:'WORKTYPE',option:'read',condition:{WOCLASS:'WORKORDER'}}";
    //实际工作类型
    public static String ACWORKTYPE = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'ACWORKTYPE'}}";
    //故障代码
    public static String FAILURECODE = "{appid:'FAILURECODE',objectname:'FAILURECODE',option:'read'}";
    //故障代码关系
    public static String FAILURELIST = "{appid:'FAILURELIST',objectname:'FAILURELIST',option:'read'}";
    //所有工作计划
    public static String JOBPLAN = "{appid:'JOBPLAN',objectname:'JOBPLAN',option:'read',condition:{status:'ACTIVE'}}";
    //所有工作计划任务
    public static String JOBTASK = "{appid:'JOBTASK',objectname:'JOBTASK',option:'read'}";
    //所有工作计划物料
    public static String JOBMATERIAL = "{appid:'JOBMATERIAL',objectname:'JOBMATERIAL',option:'read'}";
    //工作班组
    public static String PERSON = "{appid:'PERSON',objectname:'PERSON',option:'read'}";

    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";
    public static String UserName = "用户";

    public static String getWsUrl(Context context) {
        return webserviceURL;
    }


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功
    /**用户登录表识--结束**/

}
