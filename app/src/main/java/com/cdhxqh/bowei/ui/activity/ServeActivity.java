package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.cdhxqh.bowei.R;

/**
 * Created by think on 2015/8/14.
 */
public class ServeActivity extends BaseActivity {
    private ImageView backimg;
    private ImageView addimg;
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
        chooseitembtn = (Button) findViewById(R.id.activity_chooser_view_content);
        recyclerView = (RecyclerView) findViewById(R.id.maintenance_list);
    }

    @Override
    protected void initView() {

    }
}
