package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.WorkerInfo;

/**
 * Created by think on 2015/8/31.
 */
public class WorkerInfoActivity extends BaseActivity{
    private ImageView backimg;
    private TextView titlename;
    private EditText workernum;
    private EditText workername;
    private TextView startdate;
    private RelativeLayout startdate_layout;
    private TextView stopdate;
    private RelativeLayout stopdate_layout;
    private TextView starttime;
    private RelativeLayout starttime_layout;
    private TextView stoptime;
    private RelativeLayout stoptime_layout;
    private TextView worktime;
    private Button ok;
    private WorkerInfo workerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerinfo);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);

        workernum = (EditText) findViewById(R.id.worker_info_number);
        workername = (EditText) findViewById(R.id.worker_info_name);
        startdate = (TextView) findViewById(R.id.worker_info_start_date);
        stopdate = (TextView) findViewById(R.id.worker_info_stop_date);
        starttime = (TextView) findViewById(R.id.worker_info_start_time);
        stoptime = (TextView) findViewById(R.id.worker_info_stop_time);
        worktime = (TextView) findViewById(R.id.worker_info_work_time);
        ok = (Button) findViewById(R.id.info_ok);
    }

    @Override
    protected void initView() {
        workerInfo = (WorkerInfo) getIntent().getExtras().getSerializable("workinfo");
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlename.setText(getResources().getString(R.string.worker_info));
        workernum.setText(workerInfo.getNumber()+"");
        workername.setText(workerInfo.getName());
        startdate.setText(workerInfo.getStartdate());
        stopdate.setText(workerInfo.getStopdate());
        starttime.setText(workerInfo.getStarttime());
        stoptime.setText(workerInfo.getStoptime());
        worktime.setText(workerInfo.getWorktime() + "");
    }
}
