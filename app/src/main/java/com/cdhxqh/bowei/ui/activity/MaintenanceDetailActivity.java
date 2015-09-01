package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.ui.widget.OrderMorePopuowindow;

/**
 * Created by think on 2015/8/18.
 */
public class MaintenanceDetailActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView moreimg;
    private OrderMain orderMain;
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
        setContentView(R.layout.maintenance_detail_activity);

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
        questiontogether = (EditText) findViewById(R.id.questiontogether);
        ratinghours = (EditText) findViewById(R.id.detail_ratinghours);
        pm = (EditText) findViewById(R.id.pm);
        notinspection_device = (EditText) findViewById(R.id.notinspection_device);
        inspect_result = (TextView) findViewById(R.id.inspect_result);
        inputbtn = (Button) findViewById(R.id.order_detail_input);
    }

    @Override
    protected void initView() {
        getData();
        setview();
        titlename.setText(getResources().getString(R.string.maintenance));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moreimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderMorePopuowindow orderMorePopuowindow = new OrderMorePopuowindow(MaintenanceDetailActivity.this,getResources().getString(R.string.maintenance));
                orderMorePopuowindow.showPopupWindow(moreimg);
            }
        });
    }

    private void getData(){
        orderMain = (OrderMain) getIntent().getSerializableExtra("ordermain");
    }

    private void setview(){
        number.setText(orderMain.getNumber() + "");
        describe.setText(orderMain.getDescribe());
        place.setText(orderMain.getPlace());
        property.setText(orderMain.getProperty());
        wordtype.setText(orderMain.getWordtype());
        reality_worktype.setText(orderMain.getReality_worktype());
        applyunity.setText(orderMain.getApplyunity());
        major.setText(orderMain.getMajor());
        reality_item.setText(orderMain.getReality_item());
        state.setText(orderMain.getState());
        date.setText(orderMain.getDate());
        workplan.setText(orderMain.getWorkplan());
        reality_starttime.setText(orderMain.getReality_starttime());
        reality_stoptime.setText(orderMain.getReality_stoptime());
        employee_id.setText(orderMain.getEmployee_id());
        questiontogether.setText(orderMain.getQuestiontogether());
        ratinghours.setText(orderMain.getRatinghours());
        pm.setText(orderMain.getPm());
        notinspection_device.setText(orderMain.getNotinspection_device());
        inspect_result.setText(orderMain.getInspect_result());
    }
}
