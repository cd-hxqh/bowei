package com.cdhxqh.bowei.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.WorkerInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 员工信息页面
 * Created by think on 2015/8/31.
 */
public class WorkerInfoActivity extends BaseActivity{
    private ImageView backimg;
    private TextView titlename;
    private TextView workernum;
    private RelativeLayout workernumlayout;
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
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int layoutnum = 0;
    private double start;
    private double stop;

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

        workernum = (TextView) findViewById(R.id.worker_info_number);
        workernumlayout = (RelativeLayout) findViewById(R.id.worker_info_number_layout);
        startdate = (TextView) findViewById(R.id.worker_info_start_date);
        stopdate = (TextView) findViewById(R.id.worker_info_stop_date);
        starttime = (TextView) findViewById(R.id.worker_info_start_time);
        stoptime = (TextView) findViewById(R.id.worker_info_stop_time);
        worktime = (TextView) findViewById(R.id.worker_info_work_time);
        startdate_layout = (RelativeLayout) findViewById(R.id.worker_info_start_date_layout);
        stopdate_layout = (RelativeLayout) findViewById(R.id.worker_info_stop_date_layout);
        starttime_layout = (RelativeLayout) findViewById(R.id.worker_info_start_time_layout);
        stoptime_layout = (RelativeLayout) findViewById(R.id.worker_info_stop_time_layout);
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
        datePickerDialog = new DatePickerDialog(this, new datelistener(), 2015, 0, 1);
        timePickerDialog = new TimePickerDialog(this, new timelistener(), 0, 0, true);
        workernum.setText(workerInfo.getNumber());
        startdate.setText(workerInfo.getStartdate());
        stopdate.setText(workerInfo.getStopdate());
        starttime.setText(workerInfo.getStarttime());
        stoptime.setText(workerInfo.getStoptime());
        worktime.setText(workerInfo.getWorktime());

        workernumlayout.setOnClickListener(new MylayoutListener(7));
        startdate_layout.setOnClickListener(dateOnClicklistener);
        stopdate_layout.setOnClickListener(dateOnClicklistener);
        starttime_layout.setOnClickListener(timeOnClicklistener);
        stoptime_layout.setOnClickListener(timeOnClicklistener);
    }

    public class MylayoutListener implements View.OnClickListener {
        int requestCode;
        public MylayoutListener(int requestcode){
            this.requestCode = requestcode;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WorkerInfoActivity.this,ItemChooseListActivity.class);
            intent.putExtra("requestCode",requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    private View.OnClickListener dateOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layoutnum = v.getId();
            datePickerDialog.show();
        }
    };
    private View.OnClickListener timeOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            layoutnum = view.getId();
            timePickerDialog.show();
        }
    };
    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (layoutnum == startdate_layout.getId()) {
                startdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            } else if (layoutnum == stopdate_layout.getId()) {
                stopdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
            getWorkHours();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            if (layoutnum == starttime_layout.getId()) {
                start = i * 60 * 60 * 1000 + i1 * 60 * 1000;
                starttime.setText(setTimeText(i, i1));
            } else if (layoutnum == stoptime_layout.getId()) {
                stop = i * 60 * 60 * 1000 + i1 * 60 * 1000;
                stoptime.setText(setTimeText(i, i1));
            }
            getWorkHours();
        }
    }

    /**
     * 得到00:00格式的时间
     *
     * @param hour
     * @param minute
     * @return
     */
    private String setTimeText(int hour, int minute) {
        String h;
        String m;
        if (hour < 10) {
            h = "0" + hour;
        } else {
            h = hour + "";
        }
        if (minute < 10) {
            m = "0" + minute;
        } else {
            m = minute + "";
        }
        return h + ":" + m;
    }

    /**
     * 计算工时
     */
    private void getWorkHours() {
        if (starttime.getText() != "" && stoptime.getText() != ""
                && startdate.getText() != null && stopdate.getText() != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            double to = 0;
            double from = 0;
            try {
                to = df.parse(stopdate.getText().toString()).getTime();
                from = df.parse(startdate.getText().toString()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start > stop) {
                worktime.setText(new java.text.DecimalFormat("#.00").format((to - from) / (1000 * 60 * 60) - (start - stop) / (1000 * 60 * 60)) + "");
            } else {
                worktime.setText(new java.text.DecimalFormat("#.00").format((to - from) / (1000 * 60 * 60) + (stop - start) / (1000 * 60 * 60)) + "");
            }
//                Toast.makeText(AddWorkerInfoActivity.this,(to - from) / (1000 * 60 * 60 * 24)+"",Toast.LENGTH_SHORT).show();
//            worktime.setText((to - from) / (1000 * 60 * 60 * 24)+"");
//                System.out.println((to - from) / (1000 * 60 * 60 * 24));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String content = null;
        if (resultCode != 0) {
            content = data.getCharSequenceExtra("result").toString();
        }
        switch (resultCode) {
            case 7:
                workernum.setText(content);
                break;
        }
    }
}
