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
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.WorkerInfoDao;
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
    private TextView workernum;
    private RelativeLayout workernumlayout;
//    private EditText workername;
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
    private TimePickerDialog timePickerDialog;
    private int layoutnum = 0;
    private double start;
    private double stop;

    int id;

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
        inputbtn = (Button) findViewById(R.id.info_input);
    }

    @Override
    protected void initView() {
        id = getIntent().getExtras().getInt("orderid");
        datePickerDialog = new DatePickerDialog(this, new datelistener(), 2015, 0, 1);
        timePickerDialog = new TimePickerDialog(this, new timelistener(), 0, 0, true);
        titlename.setText(getResources().getString(R.string.worker_info_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWorkerInfoActivity.this.setResult(0);
                finish();
            }
        });

        workernumlayout.setOnClickListener(new MylayoutListener(7));

        inputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workernum.getText().equals("")
                        || startdate.getText().equals("") || stopdate.getText().equals("")
                        || starttime.getText().equals("") || stoptime.getText().equals("")
                        || worktime.getText().equals("")) {
                    Toast.makeText(AddWorkerInfoActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    if (Double.parseDouble(worktime.getText().toString()) < 0) {
                        Toast.makeText(AddWorkerInfoActivity.this, "请输入正确起止时间", Toast.LENGTH_SHORT).show();
                    } else {
                        intent = new Intent();
                        WorkerInfo workerInfo = new WorkerInfo();
                        workerInfo.setNumber(workernum.getText().toString());
                        workerInfo.setStartdate(startdate.getText().toString());
                        workerInfo.setStopdate(stopdate.getText().toString());
                        workerInfo.setStarttime(starttime.getText().toString());
                        workerInfo.setStoptime(stoptime.getText().toString());
                        workerInfo.setWorktime(worktime.getText().toString());
                        workerInfo.setLabtransId("-1");
                        workerInfo.setBelongorderid(id);
                        intent.putExtra("workinfo", workerInfo);
                        new WorkerInfoDao(AddWorkerInfoActivity.this).update(workerInfo);
//                        AddWorkerInfoActivity.this.setResult(1, intent);
                        finish();
                    }
                }
            }
        });

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
            Intent intent = new Intent(AddWorkerInfoActivity.this,ItemChooseListActivity.class);
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
}
