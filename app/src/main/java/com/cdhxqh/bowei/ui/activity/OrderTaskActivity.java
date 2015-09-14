package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private OrderTaskAdapter orderTaskAdapter;
    LinearLayoutManager layoutManager;
    private ProgressDialog mProgressDialog;
    String name;
    String num;
    boolean isMultiple = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_task_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        choosebtn = (Button) findViewById(R.id.choose_btn);
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
    }

    @Override
    protected void initView() {
        name = (String) getIntent().getExtras().get("fromname");
        num = (String) getIntent().getExtras().get("ordernum");
        OrderMain orderMain = new OrderMainDao(this).SearchByNum(num);

//        if (name.equals(getResources().getString(R.string.maintenance))) {
            addimg.setVisibility(View.GONE);
//        }
        titlename.setText(getResources().getString(R.string.task_list));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderTaskAdapter = new OrderTaskAdapter(this, this);
        recyclerView.setAdapter(orderTaskAdapter);
        if(!num.equals("0")&&!orderMain.isyuzhi()){
            getData();
        }else if(orderMain.isyuzhi()){
            List<JobTask> task = new JobTaskDao(this).QueryByJobTaskId(Integer.parseInt(orderMain.getWorkplan()));
            OrderTask orderTask = new OrderTask();
            orderTask.setNum(num);
//            orderTask.setTask(task.getJPNUM());
//            orderTask.setDigest(task.getDESCRIPTION());
//            new OrderTaskDao(this).update(orderTask);
//            ArrayList<OrderTask> list = new ArrayList<OrderTask>();
//            list.add(0,orderTask);
//            orderTaskAdapter.update(list,true);
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
    }

    private void getData() {
        mProgressDialog = ProgressDialog.show(this, null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(this, Constants.getOrderTaskUrl(num), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
//                        ((BaseApplication)getActivity().getApplication()).setOrderResult(jsonObject.getString("result"));
                        addData(jsonObject.getString("result"));
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
    }

    private void addData(String str) {
        ArrayList<OrderTask> list = new ArrayList<OrderTask>();
        list = JsonUtils.parsingOrderTask(str);
//        for (int i = 0; i < 3; i++) {
//            OrderTask orderTask = new OrderTask();
//            orderTask.setTask(100+i*10);
//            orderTask.setDigest("导入装置");
//            list.add(i,orderTask);
//        }
        orderTaskAdapter.update(list, true);
    }

    public void changeitem() {
        isMultiple = true;
        choosebtn.setVisibility(View.VISIBLE);
        for (int i = 0; i < orderTaskAdapter.getItemCount(); i++) {
            layoutManager.findViewByPosition(i).findViewById(R.id.task_main_in).setVisibility(View.GONE);
            layoutManager.findViewByPosition(i).findViewById(R.id.task_checkbox).setVisibility(View.VISIBLE);
        }
    }

    public void changeitenback(){
        isMultiple = false;
        choosebtn.setVisibility(View.GONE);
        for (int i = 0; i < orderTaskAdapter.getItemCount(); i++) {
            layoutManager.findViewByPosition(i).findViewById(R.id.task_main_in).setVisibility(View.VISIBLE);
            layoutManager.findViewByPosition(i).findViewById(R.id.task_checkbox).setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                &&isMultiple == true) {
            changeitenback();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
