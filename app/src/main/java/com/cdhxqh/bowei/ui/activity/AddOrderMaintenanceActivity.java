package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;

/**
 * Created by think on 2015/8/26.新增维保工单
 */
public class AddOrderMaintenanceActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private LinearLayout linearLayout;
    private EditText number;//工单编号
    private EditText describe;//工单描述
    private TextView place;//位置
    private RelativeLayout placelayout;
    private TextView property;//资产
    private RelativeLayout propertylayout;
    private TextView wordtype;//工作类型
    private RelativeLayout wordtypelayout;
    private TextView reality_worktype;//实际工作类型
    private RelativeLayout reality_worktypelayout;
    private TextView applyunity;//申请单位
    private RelativeLayout applyunitylayout;
    private TextView major;//专业
    private RelativeLayout majorlayout;
    private TextView reality_item;//实际班组
    private RelativeLayout reality_itemlayout;
    private EditText state;//状态
    private TextView date;//汇报时间
    private RelativeLayout datelayout;
    private TextView workplan;//作业计划
    private RelativeLayout workplanlayout;
    private TextView reality_starttime;//实际开始时间
    private RelativeLayout reality_starttimelayout;
    private TextView reality_stoptime;//实际完成时间
    private RelativeLayout reality_stoptimelayout;
    private TextView employee_id;//录入人工号
    private RelativeLayout employee_idlayout;
    private EditText questiontogether;//问题汇总
    private EditText ratinghours;//额定工时
    private EditText pm;//PM
    private EditText notinspection_device;//未巡检设备
    private TextView inspect_result;//检查结果
    private RelativeLayout inspect_resultlayout;
    private Button inputbtn;

    private EditText showingedit;
    private TextView showingtext;

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
        linearLayout = (LinearLayout) findViewById(R.id.order_detail_layout);
        number = (EditText) findViewById(R.id.order_detail_number);
        describe = (EditText) findViewById(R.id.order_detail_describe);

        place = (TextView) findViewById(R.id.oder_detail_place);
        placelayout = (RelativeLayout) findViewById(R.id.oder_detail_place_layout);

        property = (TextView) findViewById(R.id.oder_detail_property);
        propertylayout = (RelativeLayout) findViewById(R.id.oder_detail_property_layout);

        wordtype = (TextView) findViewById(R.id.oder_detail_wordtype);
        wordtypelayout = (RelativeLayout) findViewById(R.id.oder_detail_wordtype_layout);

        reality_worktype = (TextView) findViewById(R.id.oder_detail_reality_worktype);
        reality_worktypelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_worktype_layout);

        applyunity = (TextView) findViewById(R.id.oder_detail_applyunity);
        applyunitylayout = (RelativeLayout) findViewById(R.id.oder_detail_applyunity_layout);

        major = (TextView) findViewById(R.id.oder_detail_major);
        majorlayout = (RelativeLayout) findViewById(R.id.oder_detail_major_layout);

        reality_item = (TextView) findViewById(R.id.oder_detail_reality_item);
        reality_itemlayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_item_layout);

        state = (EditText) findViewById(R.id.order_detail_state);

        date = (TextView) findViewById(R.id.oder_detail_date);
        datelayout = (RelativeLayout) findViewById(R.id.oder_detail_date_layout);

        workplan = (TextView) findViewById(R.id.oder_detail_workplan);
        workplanlayout = (RelativeLayout) findViewById(R.id.oder_detail_workplan_layout);

        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_starttimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_starttime_layout);

        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        reality_stoptimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_stoptime_layout);

        employee_id = (TextView) findViewById(R.id.oder_detail_employee_id);
        employee_idlayout = (RelativeLayout) findViewById(R.id.oder_detail_employee_id_layout);

        questiontogether = (EditText) findViewById(R.id.questiontogether);
        ratinghours = (EditText) findViewById(R.id.detail_ratinghours);
        pm = (EditText) findViewById(R.id.pm);
        notinspection_device = (EditText) findViewById(R.id.notinspection_device);

        inspect_result = (TextView) findViewById(R.id.inspect_result);
        inspect_resultlayout = (RelativeLayout) findViewById(R.id.inspect_result_layout);

        inputbtn = (Button) findViewById(R.id.order_detail_input);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.Serve_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrderMaintenanceActivity.this.setResult(0);
                finish();
            }
        });
        number.setText("123");
        placelayout.setOnClickListener(new MylayoutListener(1));
        propertylayout.setOnClickListener(new MylayoutListener(2));
        wordtypelayout.setOnClickListener(new MylayoutListener(3));
        reality_worktypelayout.setOnClickListener(new MylayoutListener(4));
        applyunitylayout.setOnClickListener(new MylayoutListener(5));
        majorlayout.setOnClickListener(new MylayoutListener(6));
        reality_itemlayout.setOnClickListener(new MylayoutListener(7));
        datelayout.setOnClickListener(new MylayoutListener(8));
        workplanlayout.setOnClickListener(new MylayoutListener(9));
        reality_starttimelayout.setOnClickListener(new MylayoutListener(10));
        reality_stoptimelayout.setOnClickListener(new MylayoutListener(11));
        employee_idlayout.setOnClickListener(new MylayoutListener(12));
        inspect_resultlayout.setOnClickListener(new MylayoutListener(13));

        inputbtn.setOnClickListener(inputlistener);
    }

    private View.OnClickListener inputlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            OrderMain orderMain = new OrderMain();
            orderMain.setNumber(Integer.parseInt(number.getText().toString()));
            orderMain.setDescribe(describe.getText().toString());
            orderMain.setPlace(place.getText().toString());
            orderMain.setProperty(property.getText().toString());
            orderMain.setWordtype(wordtype.getText().toString());
            orderMain.setReality_worktype(reality_worktype.getText().toString());
            orderMain.setApplyunity(applyunity.getText().toString());
            orderMain.setMajor(major.getText().toString());
            orderMain.setReality_item(reality_item.getText().toString());
            orderMain.setState(state.getText().toString());
            orderMain.setDate(date.getText().toString());
            orderMain.setWorkplan(workplan.getText().toString());
            orderMain.setReality_starttime(reality_starttime.getText().toString());
            orderMain.setReality_stoptime(reality_stoptime.getText().toString());
            orderMain.setEmployee_id(employee_id.getText().toString());
            orderMain.setQuestiontogether(questiontogether.getText().toString());
            orderMain.setRatinghours(ratinghours.getText().toString());
            orderMain.setPm(pm.getText().toString());
            orderMain.setNotinspection_device(notinspection_device.getText().toString());
            orderMain.setInspect_result(inspect_result.getText().toString());
            intent.putExtra("orderMain", orderMain);
            AddOrderMaintenanceActivity.this.setResult(1, intent);
            finish();
        }
    };

    public class MylayoutListener implements View.OnClickListener {
        int requestCode;
        public MylayoutListener(int requestcode){
            this.requestCode = requestcode;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddOrderMaintenanceActivity.this,ItemChooseListActivity.class);
            intent.putExtra("requestCode",requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
