package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.application.BaseApplication;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.OrderMaintenanceAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/13.
 */
public class MaintenanceActivity extends BaseActivity{

    public static final String TAG = "MaintenanceActivity";

    private ImageView backimg;
    private ImageView addimg;
    private TextView titlename;
    private Button chooseitembtn;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private OrderMaintenanceAdapter orderMainAdapter;
    private ArrayList<OrderMain> list;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        titlename = (TextView) findViewById(R.id.title_name);
        chooseitembtn = (Button) findViewById(R.id.activity_chooser_view_content);
        recyclerView = (RecyclerView) findViewById(R.id.maintenance_list);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.maintenance));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderMainAdapter = new OrderMaintenanceAdapter(this,this);
        recyclerView.setAdapter(orderMainAdapter);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceActivity.this,AddOrderMaintenanceActivity.class);
                startActivityForResult(intent,1);
            }
        });
        getData();

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        OrderMain orderMain;
        switch (resultCode) {
            case 0:
                break;
            case 1:
                orderMain = (OrderMain) data.getSerializableExtra("orderMain");
                adddata(orderMain);
                break;
        }
    }

    private void getData(){
        mProgressDialog = ProgressDialog.show(this, null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(this, Constants.ORDER_GETDATA, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if(jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))){
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
        list = JsonUtils.parsingOrderArr(str);
        orderMainAdapter.update(list, true);
    }

    private void adddata(OrderMain orderMain){
        ArrayList<OrderMain> list = new ArrayList<OrderMain>();
        list.add(0,orderMain);
        orderMainAdapter.update(list,true);
    }

//    public void changeitem(){
//        for(int i = 0;i<orderMainAdapter.getItemCount();i++){
//            layoutManager.findViewByPosition(i).findViewById(R.id.order_main_in).setVisibility(View.GONE);
//            layoutManager.findViewByPosition(i).findViewById(R.id.order_checkbox).setVisibility(View.VISIBLE);
//        }
//    }

}
