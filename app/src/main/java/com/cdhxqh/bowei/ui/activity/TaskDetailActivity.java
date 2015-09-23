package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.ui.widget.BadgeView;

/**
 * Created by think on 2015/9/5.
 */
public class TaskDetailActivity extends BaseActivity{
    private static final String TAG = "TaskDetailActivity";
    private ImageView backimg;
    private TextView titlename;
    private EditText tasknum;
    private EditText taskdigest;
//    private EditText taskzxr;
    private GridLayout zxrlayout;
//    private EditText taskjcr;
    private GridLayout jcrlayout;
    OrderTask orderTask;
    private Button okbtn;
    BadgeView badgeView;

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
//        taskzxr = (EditText) findViewById(R.id.task_zxr);
        zxrlayout = (GridLayout) findViewById(R.id.order_zxr_layout);
//        taskjcr = (EditText) findViewById(R.id.task_jcr);
        jcrlayout = (GridLayout) findViewById(R.id.order_jcr_layout);
        okbtn = (Button) findViewById(R.id.task_ok);
    }

    @Override
    protected void initView() {
        orderTask = (OrderTask) getIntent().getSerializableExtra("orderTask");
        titlename.setText(getResources().getString(R.string.task_title));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tasknum.setText(orderTask.getNum());
        taskdigest.setText(orderTask.getDigest());
//        taskzxr.setText(orderTask.getZxr());
        String zxr = orderTask.getZxr();
//        BadgeView badgeView = new BadgeView(this);
//        badgeView.setText("ss");
//        zxrlayout.addView(badgeView);
        if(zxr!=null&&zxr.contains(",")){
            String[] zxrlist = zxr.split(",");
            for (int i = 0; i < zxrlist.length; i++) {
                RelativeLayout rl = new RelativeLayout(this);
                RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                relLayoutParams.setMargins(0,0,10,0);
                rl.setLayoutParams(relLayoutParams);
                Button view = new Button(this);
                view.setText(zxrlist[i]);
                view.setBackgroundResource(R.color.light_gray);
                view.setTextColor(getResources().getColor(R.color.black));

                BadgeView badgeView = new BadgeView(this);
                badgeView.setText("X");
                badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                badgeView.toggle();
                badgeView.show();
                rl.addView(view);
                rl.addView(badgeView);
                zxrlayout.addView(rl);
            }
        }else if(zxr!=null&&!zxr.contains(",")){
            Button view = new Button(this);
            view.setText(zxr);
            view.setBackgroundResource(R.color.light_gray);
            view.setTextColor(getResources().getColor(R.color.black));
            zxrlayout.addView(view);
        }else if(zxr==null){
        }
//        Button view1 = new Button(this);
//        view1.setText("123");
//        zxrlayout.addView(view1);
        String jcr = orderTask.getJcr();
        if(jcr!=null&&jcr.contains(",")){
            String[] jcrlist = jcr.split(",");
            for (int i = 0; i < jcrlist.length; i++) {
                Button view = new Button(this);
                view.setText(jcrlist[i]);
                view.setBackgroundResource(R.color.light_gray);
                view.setTextColor(getResources().getColor(R.color.black));
                jcrlayout.addView(view);
            }
        }else if(jcr!=null&&!jcr.contains(",")){
            Button view = new Button(this);
            view.setText(jcr);
            view.setBackgroundResource(R.color.light_gray);
            view.setTextColor(getResources().getColor(R.color.black));
            jcrlayout.addView(view);
        }else if(jcr==null){
        }

        okbtn.setOnClickListener(oklistener);
    }

    private View.OnClickListener oklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            orderTask.setJcr(taskjcr.getText().toString());
//            orderTask.setZxr(taskzxr.getText().toString());
            orderTask.setIschange(true);
            new OrderTaskDao(TaskDetailActivity.this).update(orderTask);
            finish();
        }
    };
}
