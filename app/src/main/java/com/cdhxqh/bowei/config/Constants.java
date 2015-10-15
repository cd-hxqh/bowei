package com.cdhxqh.bowei.config;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class Constants {
    //10.40.8.47:7013 测试 WOSUB-PM
    //182.92.8.94:7001 开发  APPWFWO
    public static String webserviceURL = "http://10.40.8.47:7013/maximo/services/WOService";//webservice接口地址

    public static String loginURL = "http://10.40.8.47:7013/maximo/mobile/system/login";//登录接口地址

    public static String SEARCHURL = "http://10.40.8.47:7013/maximo/mobile/common/api";//通用查询接口地址

    public static String SERVER_URL = "http://10.40.8.47:7013";//服务器地址

    /**工单测试接口**/
    public static String ORDER_GETDATA_TEST = "{appid:'WO',objectname:'WORKORDER',curpage:1,showcount:20,option:'read',condition:{WORKTYPE:'PM'}}";
    /**工单接口**/
    public static String ORDER_GETDATA = "";
    /**根据工单类型得到工单接口**/
    public static String getOrderUrl(int page,String type){
        if(!ORDER_GETDATA.equals("")){
            try {
                JSONObject object = new JSONObject(ORDER_GETDATA);
                object.put("curpage",page);
                object.getJSONObject("condition").put("WORKTYPE",type);
                return object.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    /**设置工单接口**/
    public static void serOrderUrl(String num){
        ORDER_GETDATA = "{appid:'WO',objectname:'WORKORDER',curpage:1,showcount:20,option:'read',condition:{workorderid:'"+num+"'}}";
    }

    /**根据资产编号查询工单数据**/

    public static String getNumByOrder(String assetnum,int curpage,int showcount ){
        return "{'appid':'WO','objectname':'WORKORDER','option':'read','condition':{'assetnum':'"+assetnum+"'},'curpage':'"+curpage+"','showcount':'"+showcount+"'}";
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
        return "{appid:'DEPTMATUSETRANS',objectname:'DEPTMATUSETRANS',option:'read',condition:{wonum:'"+num+"',issuetype:'发放'}}";
    }
    //获得工单流程任务分配
    public static String GET_OWNER_ID = "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
            "condition:{origperson:'maxadmin',PROCESSNAME:'WOSUB-PM',ASSIGNSTATUS:'ACTIVE'}}";
    public static String getOwnerId(String name){
        return "{appid:'WFASSIGNMENT',objectname:'WFASSIGNMENT',option:'read'," +
                "condition:{origperson:'"+name+"',PROCESSNAME:'WOSUB-PM',ASSIGNSTATUS:'ACTIVE'}}";
    }

    //知识库列表信息
    public static String KNOW_LEDGE_LIST = "{appid:'KNOWLEDGE',objectname:'KNOWLEDGE',curpage:1,showcount:20,option:'read'}";
    public static String getKnow_ledge_list(int page){
        return "{appid:'KNOWLEDGE',objectname:'KNOWLEDGE',curpage:"+page+",showcount:20,option:'read'}";
    }

    //知识库文件

    public static String GETKNOW_PATH(String number){
        return "{appid:'DOCLINKS',objectname:'DOCLINKS',option:'read',condition:{OWNERID:'"+number+"'}}";
    }

    //知识库搜索
    public static String search_Knowledge(int curpage,int showcount,String knowdesc){
        return "{appid:'KNOWLEDGE',objectname:'KNOWLEDGE',curpage:"+curpage+",showcount:"+showcount+",option:'read',condition:{knowdesc:'"+knowdesc+"'}}";
//        return "{appid:'KNOWLEDGE',objectname:'KNOWLEDGE',option:'read',curpage:'"+curpage+"',showcount:'"+showcount+"',condition:{knowdesc:'"+knowdesc+"'}}";
    }


    /**公司库存列表**/
    public static String get_commany_inv(int curpage,int showcount){
        return "{appid:'INV',objectname:'INVENTORY',option:'read',curpage:'"+curpage+"',showcount:'"+showcount+"'}";

    }

    /**公司库存搜索**/
    public static String search_commany_inv(String description,int curpage,int showcount){
        return "{'appid':'INV','objectname':'INVENTORY','option':'read','condition':{'item.description':'"+description+"'},'curpage':'"+curpage+"','showcount':'"+showcount+"'}";

    }


    /**捷运库存列表**/

    public static String get_deptinventory(int curpage,int showcount){
        return "{appid:'DEPTINVENTORY',objectname:'DEPTINVENTORY',option:'read',curpage:'"+curpage+"',showcount:'"+showcount+"'}";

    }

    /**捷运库存搜索**/
    public static String search_deptinventory(String description,int curpage,int showcount){
        return "{'appid':'DEPTINVENTORY','objectname':'DEPTINVENTORY','option':'read','condition':{'item.bjmc':'"+description+"'},'curpage':'"+curpage+"','showcount':'"+showcount+"'}";

    }


    //位置信息
    public static String LOCATIONS = "{appid:'LOCATIONS',objectname:'LOCATIONS',option:'read',condition:{TYPE:'OPERATING'}}";
    //资产
    public static String ASSET = "{appid:'ASSET',objectname:'ASSET',option:'read',condition:{ASSETNUM:'JY%'}}";
    //单位
    public static String WORKDW = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'WORKDW'}}";
    //专业
    public static String WORKZY = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'WORKZY'}}";
    //工作类型
    public static String WORKTYPE = "{appid:'WORKTYPE',objectname:'WORKTYPE',option:'read',condition:{WOCLASS:'WORKORDER'}}";
    //实际工作类型
    public static String ACWORKTYPE = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'ACWORKTYPE'}}";
    //故障等级
    public static String ALNDOMAIN = "{appid:'ALNDOMAIN',objectname:'ALNDOMAIN',option:'read',condition:{DOMAINID:'GZLX'}}";
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

    /**工单类型**/
    public static final String MAINTENANCE_TYPE = "PM";//维保工单1
    public static final String MAINTENANCE_TYPE1 = "CM";//维保工单2
    public static final String SERVE_TYPE = "EM";//维修
    public static final String SERVICE_TYPE = "SVR";//服务

    /**搜索标识**/
    public static final int KNOWKEDGE_SEARCH=1000; //知识库搜索
    public static final int CINVENTORY_SEARCH=1001; //公司库存搜索
    public static final int DINVENTORY_SEARCH=1002; //捷运库存搜索


    /**跳转标识**/
    public static final int ORDER_MARK=10000; //工单新建
    public static final int RESULTS_MARK=10001; //资产扫描结果



    /**设置数据库参数-开始**/
    /**
     * 数据库路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/";
    /**
     * 无SD卡的数据库路径
     */
    public static final String NOT_SDCARD_PATH_DB = "/data/data/";
    /** 数据库名称 **/
    public static final String TB_NAME = "sqlite-bowei.db";

}
