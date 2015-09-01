package com.cdhxqh.bowei.ui.activity;

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

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.ui.adapter.OrderMaintenanceAdapter;

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
        addData();

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
    private void addData() {
        ArrayList<OrderMain> list = new ArrayList<OrderMain>();
        for (int i = 0; i < 3; i++) {
            OrderMain orderMain = new OrderMain();
            orderMain.setNumber(103882549+i);
            orderMain.setDescribe("TT2分拣机" + (i + 1) + "日检");
            orderMain.setPlace("SQWES");
            orderMain.setProperty("01002");
            list.add(i, orderMain);
        }
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
