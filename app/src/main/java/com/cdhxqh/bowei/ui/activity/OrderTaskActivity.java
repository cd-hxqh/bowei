package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.ui.adapter.OrderTaskAdapter;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/20.
 */
public class OrderTaskActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView addimg;
    public RecyclerView recyclerView;
    private OrderTaskAdapter orderTaskAdapter;
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
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
    }

    @Override
    protected void initView() {
        String name = (String) getIntent().getExtras().get("fromname");
        if(name.equals(getResources().getString(R.string.maintenance))){
            addimg.setVisibility(View.GONE);
        }
        titlename.setText(getResources().getString(R.string.task_list));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderTaskAdapter = new OrderTaskAdapter(this);
        recyclerView.setAdapter(orderTaskAdapter);
        addData();

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

    private void addData() {
        ArrayList<OrderTask> list = new ArrayList<OrderTask>();
        for (int i = 0; i < 3; i++) {
            OrderTask orderTask = new OrderTask();
            orderTask.setTask(100+i*10);
            orderTask.setDigest("导入装置");
            list.add(i,orderTask);
        }
        orderTaskAdapter.update(list, true);
    }
}
