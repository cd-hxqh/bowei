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

/**
 * Created by think on 2015/8/14.
 */
public class ServiceActivity extends BaseActivity {
    private ImageView backimg;
    private ImageView addimg;
    private TextView titlename;
    private Button chooseitembtn;
    RecyclerView recyclerView;
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
        titlename.setText(getResources().getString(R.string.service));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ServiceActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
