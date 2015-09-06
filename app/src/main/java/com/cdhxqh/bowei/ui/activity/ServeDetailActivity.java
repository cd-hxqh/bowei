package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderServe;
import com.cdhxqh.bowei.ui.widget.OrderMorePopuowindow;

/**
 * Created by think on 2015/8/18.
 */
public class ServeDetailActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView moreimg;
    private OrderServe orderServe;
    private EditText number;//工单编号
    private EditText describe;//工单描述
    private TextView place;//位置
    private TextView property;//资产
    private TextView wordtype;//工作类型
    private TextView reality_worktype;//实际工作类型
    private TextView applyunity;//申请单位
    private TextView major;//专业
    private TextView reality_item;//实际班组
    private EditText state;//状态
    private TextView date;//汇报时间
    private TextView workplan;//作业计划
    private TextView reality_starttime;//实际开始时间
    private TextView reality_stoptime;//实际完成时间
    private TextView employee_id;//录入人工号
    private EditText questiontogether;//问题汇总
    private EditText faultclass;
    private EditText error_coding;
    private EditText fault_rank;
    private TextView reporttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serve_detail_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.order_title_img_left);
        titlename = (TextView) findViewById(R.id.order_title_name);
        moreimg = (ImageView) findViewById(R.id.order_title_img_right);
        number = (EditText) findViewById(R.id.order_detail_number);
        describe = (EditText) findViewById(R.id.order_detail_describe);
        place = (TextView) findViewById(R.id.oder_detail_place);
        property = (TextView) findViewById(R.id.oder_detail_property);
        wordtype = (TextView) findViewById(R.id.oder_detail_wordtype);
        reality_worktype = (TextView) findViewById(R.id.oder_detail_reality_worktype);
        applyunity = (TextView) findViewById(R.id.oder_detail_applyunity);
        major = (TextView) findViewById(R.id.oder_detail_major);
        reality_item = (TextView) findViewById(R.id.oder_detail_reality_item);
        state = (EditText) findViewById(R.id.order_detail_state);
        date = (TextView) findViewById(R.id.oder_detail_date);
        workplan = (TextView) findViewById(R.id.oder_detail_workplan);
        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        employee_id = (TextView) findViewById(R.id.oder_detail_employee_id);
        questiontogether = (EditText) findViewById(R.id.order_detail_questiontogether);
        faultclass = (EditText) findViewById(R.id.order_detail_faultclass);
        error_coding = (EditText) findViewById(R.id.order_detail_error_coding);
    }

    @Override
    protected void initView() {
        getData();
        number.setText(orderServe.getNumber() + "");
        describe.setText(orderServe.getDescribe());
        titlename.setText(getResources().getString(R.string.serve));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moreimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderMorePopuowindow orderMorePopuowindow = new OrderMorePopuowindow(ServeDetailActivity.this,getResources().getString(R.string.serve),
                        orderServe.getNumber() + "");
                orderMorePopuowindow.showPopupWindow(moreimg);
            }
        });
    }

    private void getData(){
        orderServe = (OrderServe) getIntent().getSerializableExtra("orderserve");
    }
}
