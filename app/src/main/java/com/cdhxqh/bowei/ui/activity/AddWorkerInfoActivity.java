package com.cdhxqh.bowei.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.WorkerInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by think on 2015/8/26.
 */
public class AddWorkerInfoActivity extends BaseActivity {
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
    private Button inputbtn;
    private Intent intent;
    private DatePickerDialog datePickerDialog;
    private int layoutnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workerinfo);

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

        startdate_layout = (RelativeLayout) findViewById(R.id.worker_info_start_date_layout);
        stopdate_layout = (RelativeLayout) findViewById(R.id.worker_info_stop_date_layout);
        starttime_layout = (RelativeLayout) findViewById(R.id.worker_info_start_time_layout);
        stoptime_layout = (RelativeLayout) findViewById(R.id.worker_info_stop_time_layout);
        inputbtn = (Button) findViewById(R.id.info_input);
    }

    @Override
    protected void initView() {
        datePickerDialog = new DatePickerDialog(this, new datelistener(), 2015, 0, 1);
        titlename.setText(getResources().getString(R.string.worker_info_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWorkerInfoActivity.this.setResult(0);
                finish();
            }
        });
        inputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                WorkerInfo workerInfo = new WorkerInfo();
                workerInfo.setNumber(Integer.parseInt(workernum.getText().toString()));
                workerInfo.setName(workername.getText().toString());
                workerInfo.setStartdate(startdate.getText().toString());
                workerInfo.setStopdate(stopdate.getText().toString());
                workerInfo.setStarttime(starttime.getText().toString());
                workerInfo.setStoptime(stoptime.getText().toString());
                intent.putExtra("workinfo", workerInfo);
                AddWorkerInfoActivity.this.setResult(1, intent);
                finish();
            }
        });

        startdate_layout.setOnClickListener(dateOnClicklistener);
        stopdate_layout.setOnClickListener(dateOnClicklistener);
        starttime_layout.setOnClickListener(dateOnClicklistener);
        stoptime_layout.setOnClickListener(dateOnClicklistener);
    }

    private View.OnClickListener dateOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutnum = v.getId();
            datePickerDialog.show();
        }
    };

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (layoutnum == startdate_layout.getId()) {
                startdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            } else if (layoutnum == stopdate_layout.getId()) {
                stopdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            } else if (layoutnum == starttime_layout.getId()) {
                starttime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            } else if (layoutnum == stoptime_layout.getId()) {
                stoptime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
            if(starttime.getText()!=""&&stoptime.getText()!=""){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                long to = 0;
                long from = 0;
                try {
                    to = df.parse(stoptime.getText().toString()).getTime();
                    from = df.parse(starttime.getText().toString()).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(AddWorkerInfoActivity.this,(to - from) / (1000 * 60 * 60 * 24)+"",Toast.LENGTH_SHORT).show();
                worktime.setText((to - from) / (1000 * 60 * 60 * 24)+"");
//                System.out.println((to - from) / (1000 * 60 * 60 * 24));
            }
        }
    }
}
