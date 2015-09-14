package com.cdhxqh.bowei.utils;

import android.content.Context;

import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;

import java.util.List;

/**
 * webservice提交参数生成类
 * Created by think on 2015/9/11.
 */
public class WebserviceDataUtils {

    public static String updateData(Context context,OrderMain orderMain){
        String number = orderMain.getNumber();
        List<OrderTask> orderTask = new OrderTaskDao(context).queryByNum(number);
        if (orderTask.size()>0){

        }
        return null;
    }
}
