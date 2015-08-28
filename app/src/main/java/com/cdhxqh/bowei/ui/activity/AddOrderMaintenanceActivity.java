package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;

/**
 * Created by think on 2015/8/26.新增维保工单
 */
public class AddOrderMaintenanceActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_detail_add_new_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.maintenance_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrderMaintenanceActivity.this.setResult(0);
                finish();
            }
        });
    }
}
