package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderServe;
import com.cdhxqh.bowei.ui.adapter.OrderServeAdapter;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/14.缁翠慨宸ュ崟
 */
public class ServeActivity extends BaseActivity {
    private ImageView backimg;
    private ImageView addimg;
    private TextView titlename;
    RecyclerView recyclerView;
    private OrderServeAdapter orderServeAdapter;
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
        recyclerView = (RecyclerView) findViewById(R.id.maintenance_list);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.serve));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderServeAdapter = new OrderServeAdapter(this);
        recyclerView.setAdapter(orderServeAdapter);
        addData();
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServeActivity.this, AddOrderServeActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ServeActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        OrderServe orderServe;
        switch (resultCode) {
            case 0:
                break;
            case 1:
                orderServe = (OrderServe) data.getSerializableExtra("orderServe");
                adddata(orderServe);
                break;
        }
    }

    private void addData() {
        ArrayList<OrderServe> list = new ArrayList<OrderServe>();
        for (int i = 0; i < 3; i++) {
            OrderServe orderServe = new OrderServe();
            orderServe.setNumber(103882549);
            orderServe.setDescribe("TT2分拣机"+ (i + 1) + "日检");
            list.add(i,orderServe);
        }
        orderServeAdapter.update(list, true);
    }

    private void adddata(OrderServe orderServe){
        ArrayList<OrderServe> list = new ArrayList<OrderServe>();
        list.add(0,orderServe);
        orderServeAdapter.update(list,true);
    }
}
