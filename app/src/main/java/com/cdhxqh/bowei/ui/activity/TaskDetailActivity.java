package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderTask;

/**
 * Created by think on 2015/9/5.
 */
public class TaskDetailActivity extends BaseActivity{
    private static final String TAG = "TaskDetailActivity";
    private ImageView backimg;
    private TextView titlename;
    private EditText tasknum;
    private EditText taskdigest;
    private EditText taskzxr;
    private EditText taskjcr;
    OrderTask orderTask;
    private Button okbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);
        tasknum = (EditText) findViewById(R.id.task_num);
        taskdigest = (EditText) findViewById(R.id.task_digest);
        taskzxr = (EditText) findViewById(R.id.task_zxr);
        taskjcr = (EditText) findViewById(R.id.task_jcr);
        okbtn = (Button) findViewById(R.id.task_ok);
    }

    @Override
    protected void initView() {
        orderTask = (OrderTask) getIntent().getSerializableExtra("orderTask");
        Log.i(TAG,orderTask.getDigest());
        titlename.setText(getResources().getString(R.string.task_title));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tasknum.setText(orderTask.getNum());
        taskdigest.setText(orderTask.getDigest());
        taskzxr.setText(orderTask.getZxr());
        taskjcr.setText(orderTask.getJcr());
        okbtn.setOnClickListener(oklistener);
    }

    private View.OnClickListener oklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            orderTask.setJcr(taskjcr.getText().toString());
            orderTask.setZxr(taskzxr.getText().toString());
            orderTask.setIschange(true);
            new OrderTaskDao(TaskDetailActivity.this).update(orderTask);
            finish();
        }
    };
}
