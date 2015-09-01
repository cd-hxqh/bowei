package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;

/**
 * Created by think on 2015/8/26.新增维保工单
 */
public class AddOrderMaintenanceActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
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
    private EditText ratinghours;//额定工时
    private EditText pm;//PM
    private EditText notinspection_device;//未巡检设备
    private TextView inspect_result;//检查结果
    private Button inputbtn;

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
        questiontogether = (EditText) findViewById(R.id.questiontogether);
        ratinghours = (EditText) findViewById(R.id.detail_ratinghours);
        pm = (EditText) findViewById(R.id.pm);
        notinspection_device = (EditText) findViewById(R.id.notinspection_device);
        inspect_result = (TextView) findViewById(R.id.inspect_result);
        inputbtn = (Button) findViewById(R.id.order_detail_input);
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
        number.setText("123");
        inputbtn.setOnClickListener(inputlistener);
    }

    private View.OnClickListener inputlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            OrderMain orderMain = new OrderMain();
            orderMain.setNumber(Integer.parseInt(number.getText().toString()));
            orderMain.setDescribe(describe.getText().toString());
            orderMain.setState(state.getText().toString());
            orderMain.setQuestiontogether(questiontogether.getText().toString());
            orderMain.setRatinghours(ratinghours.getText().toString());
            orderMain.setPm(pm.getText().toString());
            orderMain.setNotinspection_device(notinspection_device.getText().toString());
            intent.putExtra("orderMain", orderMain);
            AddOrderMaintenanceActivity.this.setResult(1, intent);
            finish();
        }
    };
}
