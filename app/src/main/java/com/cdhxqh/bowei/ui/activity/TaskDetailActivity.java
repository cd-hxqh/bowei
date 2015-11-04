package com.cdhxqh.bowei.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.ui.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

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
    private LinearLayout zxrlayout;
//    private EditText taskjcr;
    private LinearLayout jcrlayout;
    OrderTask orderTask;
    boolean isByserch;
    private Button okbtn;
    private Button button3;
    private Button lastitem;
    List<Button> zxr_btnlist = new ArrayList<Button>();
    List<Button> jcr_btnlist = new ArrayList<Button>();
    List<BadgeView>list = new ArrayList<BadgeView>();

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
        zxrlayout = (LinearLayout) findViewById(R.id.order_zxr_layout);
//        taskjcr = (EditText) findViewById(R.id.task_jcr);
        jcrlayout = (LinearLayout) findViewById(R.id.order_jcr_layout);
        okbtn = (Button) findViewById(R.id.task_ok);
    }

    @Override
    protected void initView() {
        orderTask = (OrderTask) getIntent().getSerializableExtra("orderTask");
        isByserch = getIntent().getBooleanExtra("isByserch",false);
        if(isByserch){
            okbtn.setVisibility(View.GONE);
        }
        titlename.setText(getResources().getString(R.string.task_title));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tasknum.setText(orderTask.getTask());
        taskdigest.setText(orderTask.getDigest());
//        taskzxr.setText(orderTask.getZxr());
        String zxr = orderTask.getZxr();
//        BadgeView badgeView = new BadgeView(this);
//        badgeView.setText("ss");
//        zxrlayout.addView(badgeView);
        if(zxr!=null&&zxr.contains(",")){
            String[] zxrlist = zxr.split(",");
            for (int i = 0; i < zxrlist.length; i++) {
                Button view = new Button(this);
                view.setText(zxrlist[i]);
                view.setBackgroundResource(R.color.light_gray);
                view.setTextColor(getResources().getColor(R.color.black));
                view.setOnClickListener(new itemlistener(zxrlayout));
                zxrlayout.addView(view);
                zxr_btnlist.add(i,view);
            }
        }else if(zxr!=null&&!zxr.contains(",")){
            Button view = new Button(this);
            view.setText(zxr);
//            view.setId();
            view.setBackgroundResource(R.color.light_gray);
            view.setTextColor(getResources().getColor(R.color.black));
            view.setOnClickListener(new itemlistener(zxrlayout));
            zxrlayout.addView(view);
            zxr_btnlist.add(0,view);
        }else if(zxr==null){
            zxrlayout.removeAllViews();
        }
        String jcr = orderTask.getJcr();
        if(jcr!=null&&jcr.contains(",")){
            String[] jcrlist = jcr.split(",");
            for (int i = 0; i < jcrlist.length; i++) {
                Button view = new Button(this);
                view.setText(jcrlist[i]);
                view.setBackgroundResource(R.color.light_gray);
                view.setTextColor(getResources().getColor(R.color.black));
                view.setOnClickListener(new itemlistener(jcrlayout));
                jcrlayout.addView(view);
                jcr_btnlist.add(i,view);
            }
        }else if(jcr!=null&&!jcr.contains(",")){
            Button view = new Button(this);
            view.setText(jcr);
            view.setBackgroundResource(R.color.light_gray);
            view.setTextColor(getResources().getColor(R.color.black));
            view.setOnClickListener(new itemlistener(jcrlayout));
            jcrlayout.addView(view);
            jcr_btnlist.add(0,view);
        }else if(jcr==null){
        }

        okbtn.setOnClickListener(oklistener);
    }
    private View.OnClickListener oklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            orderTask.setJcr(getjcrData());
            orderTask.setZxr(getzxrData());
            orderTask.setIschange(true);
            new OrderTaskDao(TaskDetailActivity.this).update(orderTask);
            finish();
        }
    };

    private class itemlistener implements View.OnClickListener{
        LinearLayout layout;
        int i=0;
        public itemlistener(LinearLayout layout){
            this.layout = layout;
        }
        @Override
        public void onClick(View view) {
            lastitem = (Button) view;
            i++;
            if(i==1){
                view.setBackgroundResource(R.color.gray);
            }else
            if(i==2){
                if(layout.getId()==zxrlayout.getId()){
                    zxr_btnlist.remove(view);
                }else if(layout.getId()==jcrlayout.getId()){
                    jcr_btnlist.remove(view);
                }
                layout.removeView(view);
//                view.setVisibility(View.GONE);
//                layout.removeAllViews();
//                for (int i = 0;i < zxr_btnlist.size();i++){
//                    layout.addView(zxr_btnlist.get(i));
//                }
            }
        }
    }

    private String getzxrData(){
        int count = zxrlayout.getChildCount();
        String zxr = null;
        if(count==0){
            return null;
        }else if(count==1){
            zxr = ((Button)zxrlayout.getChildAt(0)).getText().toString();
        }else {
            for(int i = 0;i < count;i++){
                if(i==0){
                    zxr = ((Button)zxrlayout.getChildAt(0)).getText().toString();
                }else {
                    zxr = zxr + "," + ((Button) zxrlayout.getChildAt(i)).getText().toString();
                }
            }
        }
        return zxr;
    }

    private String getjcrData(){
        int count = jcrlayout.getChildCount();
        String jcr = null;
        if(count==0){
            return null;
        }else if(count==1){
            jcr = ((Button)jcrlayout.getChildAt(0)).getText().toString();
        }else {
            for(int i = 0;i < count;i++){
                if(i==0){
                    jcr = ((Button)jcrlayout.getChildAt(0)).getText().toString();
                }else {
                    jcr = jcr + "," + ((Button) jcrlayout.getChildAt(i)).getText().toString();
                }
            }
        }
        return jcr;
    }
}
