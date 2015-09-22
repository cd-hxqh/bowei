package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.JobTaskDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.OrderTaskAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/8/20.
 */
public class OrderTaskActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView addimg;
    private Button choosebtn;
    public RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout = null;
    private OrderTaskAdapter orderTaskAdapter;
    LinearLayoutManager layoutManager;
    private ProgressDialog mProgressDialog;
    private RelativeLayout Multiplelayout;
    private LinearLayout nodatalayout;
    String name;
    int id;
    OrderMain orderMain;
    public boolean isMultiple = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_task_activity);

        findViewById();
        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        choosebtn = (Button) findViewById(R.id.choose_btn);
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        Multiplelayout = (RelativeLayout) findViewById(R.id.chooseitem_relativelayout);
    }

    @Override
    protected void initView() {
        name = (String) getIntent().getExtras().get("fromname");
        id = getIntent().getExtras().getInt("orderid");
        orderMain = new OrderMainDao(this).SearchByNum(id);
        addimg.setVisibility(View.GONE);
        titlename.setText(getResources().getString(R.string.task_list));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderTaskAdapter = new OrderTaskAdapter(this, this);
        recyclerView.setAdapter(orderTaskAdapter);
        if(orderMain.isNew()){//本地新建工单
            addLocationTask(orderMain.getId());
        }else{//接收的工单
            getData();
        }



        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OrderTaskActivity.this,)
            }
        });
        choosebtn.setOnClickListener(chooselistener);
//        refreshLayout.setOnRefreshListener(this);
    }

    private View.OnClickListener chooselistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Toast.makeText(OrderTaskActivity.this,orderTaskAdapter.checkedlist.size()+"",Toast.LENGTH_SHORT).show();

        }
    };

    private void getData() {
        mProgressDialog = ProgressDialog.show(this, null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(this, Constants.getOrderTaskUrl(orderMain.getNumber()), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
//                        ((BaseApplication)getActivity().getApplication()).setOrderResult(jsonObject.getString("result"));
                        JsonUtils.parsingOrderTask(OrderTaskActivity.this, jsonObject.getString("result"),orderMain.getId());
                        mProgressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {
            }
        });
        addData(orderMain.getId());
    }

    private void addData(int id) {
        ArrayList<OrderTask> list = new ArrayList<OrderTask>();
        List<OrderTask>orderTaskList = new OrderTaskDao(OrderTaskActivity.this).queryByOrderId(id);
        for(int i = 0;i < orderTaskList.size();i++){
            list.add(i,orderTaskList.get(i));
        }
        orderTaskAdapter.update(list, true);
        if(orderTaskList.size()==0){
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void addLocationTask(int id) {
        if(new OrderTaskDao(OrderTaskActivity.this).queryByOrderId(id).size()>0){
            addData(id);
        }else {
            if(!orderMain.getWorkplan().equals("")) {
                ArrayList<OrderTask> list = new ArrayList<OrderTask>();
                OrderTask orderTask;
                List<JobTask> task = new JobTaskDao(this).QueryByJobTaskId(orderMain.getWorkplan());
                for (int i = 0; i < task.size(); i++) {
                    orderTask = new OrderTask();
                    orderTask.setBelongordermain(id);
                    orderTask.setNum(orderMain.getNumber());
                    orderTask.setDigest(task.get(i).getDESCRIPTION());
                    orderTask.setOrdermainid(String.valueOf(task.get(i).getJOBTASKID()));
                    orderTask.setTask(task.get(i).getJPTASK());
//            orderTask.setWosequence();
                    new OrderTaskDao(OrderTaskActivity.this).update(orderTask);
                    list.add(i, orderTask);
                }
                orderTaskAdapter.update(list, true);
            }
        }
    }

    public void changeitem() {
        isMultiple = true;
        Multiplelayout.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(orderTaskAdapter);
//        for (int i = 0; i < orderTaskAdapter.getItemCount()-1; i++) {
//            layoutManager.findViewByPosition(i).findViewById(R.id.task_main_in).setVisibility(View.GONE);
//            layoutManager.findViewByPosition(i).findViewById(R.id.task_checkbox).setVisibility(View.VISIBLE);
//        }
    }

    public void changeitenback() {
        isMultiple = false;
        Multiplelayout.setVisibility(View.GONE);
        recyclerView.setAdapter(orderTaskAdapter);
//        for (int i = 0; i < orderTaskAdapter.getItemCount()-1; i++) {
//            layoutManager.findViewByPosition(i).findViewById(R.id.task_main_in).setVisibility(View.VISIBLE);
//            layoutManager.findViewByPosition(i).findViewById(R.id.task_checkbox).setVisibility(View.GONE);
//        }
    }


    //下拉刷新触发事件
//    @Override
//    public void onRefresh() {
//        if (orderTaskAdapter.getItemCount() > 0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(OrderTaskActivity.this);
//            builder.setMessage("刷新将清除本地数据，确定吗？").setTitle("提示")
//                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                            getData();
//                        }
//                    }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    refreshLayout.setRefreshing(false);
//                    dialogInterface.dismiss();
//                }
//            }).create().show();
//        } else {
//            getData();
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && isMultiple == true) {
            changeitenback();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
