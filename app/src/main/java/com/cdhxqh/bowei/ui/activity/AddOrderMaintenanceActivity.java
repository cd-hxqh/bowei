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
    private EditText placeedit;
    private TextView property;//资产
    private RelativeLayout propertylayout;
    private EditText propertyedit;
    private TextView wordtype;//工作类型
    private RelativeLayout wordtypelayout;
    private EditText wordtypeedit;
    private TextView reality_worktype;//实际工作类型
    private RelativeLayout reality_worktypelayout;
    private EditText reality_worktypeedit;
    private TextView applyunity;//申请单位
    private RelativeLayout applyunitylayout;
    private EditText applyunityedit;
    private TextView major;//专业
    private RelativeLayout majorlayout;
    private EditText majoredit;
    private TextView reality_item;//实际班组
    private RelativeLayout reality_itemlayout;
    private EditText reality_itemedit;
    private EditText state;//状态
    private TextView date;//汇报时间
    private RelativeLayout datelayout;
    private EditText dateedit;
    private TextView workplan;//作业计划
    private RelativeLayout workplanlayout;
    private EditText workplanedit;
    private TextView reality_starttime;//实际开始时间
    private RelativeLayout reality_starttimelayout;
    private EditText reality_starttimeedit;
    private TextView reality_stoptime;//实际完成时间
    private RelativeLayout reality_stoptimelayout;
    private EditText reality_stoptimeedit;
    private TextView employee_id;//录入人工号
    private RelativeLayout employee_idlayout;
    private EditText employee_idedit;
    private EditText questiontogether;//问题汇总
    private EditText ratinghours;//额定工时
    private EditText pm;//PM
    private EditText notinspection_device;//未巡检设备
    private TextView inspect_result;//检查结果
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
        placeedit = (EditText) findViewById(R.id.oder_detail_place_edit);

        property = (TextView) findViewById(R.id.oder_detail_property);
        propertylayout = (RelativeLayout) findViewById(R.id.oder_detail_property_layout);
        propertyedit = (EditText) findViewById(R.id.oder_detail_property_edit);

        wordtype = (TextView) findViewById(R.id.oder_detail_wordtype);
        wordtypelayout = (RelativeLayout) findViewById(R.id.oder_detail_wordtype_layout);
        wordtypeedit = (EditText) findViewById(R.id.oder_detail_wordtype_edit);

        reality_worktype = (TextView) findViewById(R.id.oder_detail_reality_worktype);
        reality_worktypelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_worktype_layout);
        reality_worktypeedit = (EditText) findViewById(R.id.oder_detail_reality_worktype_edit);

        applyunity = (TextView) findViewById(R.id.oder_detail_applyunity);
        applyunitylayout = (RelativeLayout) findViewById(R.id.oder_detail_applyunity_layout);
        applyunityedit = (EditText) findViewById(R.id.oder_detail_applyunity_edit);

        major = (TextView) findViewById(R.id.oder_detail_major);
        majorlayout = (RelativeLayout) findViewById(R.id.oder_detail_major_layout);
        majoredit = (EditText) findViewById(R.id.oder_detail_major_edit);

        reality_item = (TextView) findViewById(R.id.oder_detail_reality_item);
        reality_itemlayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_item_layout);
        reality_itemedit = (EditText) findViewById(R.id.oder_detail_reality_item_edit);

        state = (EditText) findViewById(R.id.order_detail_state);

        date = (TextView) findViewById(R.id.oder_detail_date);
        datelayout = (RelativeLayout) findViewById(R.id.oder_detail_date_layout);
        dateedit = (EditText) findViewById(R.id.oder_detail_date_edit);

        workplan = (TextView) findViewById(R.id.oder_detail_workplan);
        workplanlayout = (RelativeLayout) findViewById(R.id.oder_detail_workplan_layout);
        workplanedit = (EditText) findViewById(R.id.oder_detail_workplan_edit);

        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_starttimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_starttime_layout);
        reality_starttimeedit = (EditText) findViewById(R.id.oder_detail_reality_starttime_edit);

        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        reality_stoptimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_stoptime_layout);
        reality_stoptimeedit = (EditText) findViewById(R.id.oder_detail_reality_stoptime_edit);

        employee_id = (TextView) findViewById(R.id.oder_detail_employee_id);
        employee_idlayout = (RelativeLayout) findViewById(R.id.oder_detail_employee_id_layout);
        employee_idedit = (EditText) findViewById(R.id.oder_detail_employee_id_edit);

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
        placelayout.setOnClickListener(new MylayoutListener(placeedit, place));
        propertylayout.setOnClickListener(new MylayoutListener(propertyedit,property));
        wordtypelayout.setOnClickListener(new MylayoutListener(wordtypeedit,wordtype));


        inputbtn.setOnClickListener(inputlistener);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (showingedit != null && showingedit.getVisibility() == View.VISIBLE &&
                        (motionEvent.getX() < showingedit.getLeft() || motionEvent.getX() > showingedit.getRight()
                                || motionEvent.getY() < showingedit.getBottom() || motionEvent.getY() > showingedit.getTop())) {
                    showingtext.setText(showingedit.getText());
                    showingedit.setVisibility(View.GONE);
                    showingtext.setVisibility(View.VISIBLE);
                    showingedit = null;
                    showingtext = null;
                }
                return true;
            }
        });
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

    public class MylayoutListener implements View.OnClickListener {
        private EditText editText;
        private TextView textView;
        public MylayoutListener(EditText editText,TextView textView){
            this.editText = editText;
            this.textView = textView;
        }
        @Override
        public void onClick(View view) {
            editText.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            showingedit = editText;
            showingtext = textView;
        }
    }
}
