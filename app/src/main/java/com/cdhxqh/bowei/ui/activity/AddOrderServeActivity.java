package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderServe;

/**
 * Created by think on 2015/9/2.
 */
public class AddOrderServeActivity extends BaseActivity {
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
    private EditText faultclass;
    private EditText error_coding;
    private EditText fault_rank;
    private TextView reporttime;
    private RelativeLayout reporttimelayout;
    private EditText reporttimeedit;

    private Button inputbtn;

    private EditText showingedit;
    private TextView showingtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serve_detail_add_activity);

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
        faultclass = (EditText) findViewById(R.id.order_detail_faultclass);
        error_coding = (EditText) findViewById(R.id.order_detail_error_coding);
        fault_rank = (EditText) findViewById(R.id.order_detail_fault_rank);
        reporttime = (TextView) findViewById(R.id.order_detail_reporttime);
        reporttimelayout = (RelativeLayout) findViewById(R.id.order_detail_reporttime_layout);
        reporttimeedit = (EditText) findViewById(R.id.order_detail_reporttime_edit);

        inputbtn = (Button) findViewById(R.id.order_detail_input);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.maintenance_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrderServeActivity.this.setResult(0);
                finish();
            }
        });
        number.setText("123");
//        placelayout.setOnClickListener(new MylayoutListener(placeedit, place));
//        propertylayout.setOnClickListener(new MylayoutListener(propertyedit,property));
//        wordtypelayout.setOnClickListener(new MylayoutListener(wordtypeedit,wordtype));
//        reality_worktypelayout.setOnClickListener(new MylayoutListener(reality_worktypeedit,reality_worktype));
//        applyunitylayout.setOnClickListener(new MylayoutListener(applyunityedit,applyunity));
//        majorlayout.setOnClickListener(new MylayoutListener(majoredit,major));
//        reality_itemlayout.setOnClickListener(new MylayoutListener(reality_itemedit,reality_item));
//        datelayout.setOnClickListener(new MylayoutListener(dateedit,date));
//        workplanlayout.setOnClickListener(new MylayoutListener(workplanedit,workplan));
//        reality_starttimelayout.setOnClickListener(new MylayoutListener(reality_starttimeedit,reality_starttime));
//        reality_stoptimelayout.setOnClickListener(new MylayoutListener(reality_stoptimeedit,reality_stoptime));
//        employee_idlayout.setOnClickListener(new MylayoutListener(employee_idedit,employee_id));
//        reporttimelayout.setOnClickListener(new MylayoutListener(reporttimeedit,reporttime));

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
    public class MylayoutListener implements View.OnClickListener {
        private EditText editText;
        private TextView textView;
        public MylayoutListener(EditText editText,TextView textView){
            this.editText = editText;
            this.textView = textView;
        }
        @Override
        public void onClick(View view) {
            if(showingedit!=null){
                showingtext.setText(showingedit.getText());
                showingedit.setVisibility(View.GONE);
                showingtext.setVisibility(View.VISIBLE);
            }
            editText.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            showingedit = editText;
            showingtext = textView;
        }
    }

    private View.OnClickListener inputlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            OrderServe orderServe = new OrderServe();
            orderServe.setNumber(Integer.parseInt(number.getText().toString()));
            orderServe.setDescribe(describe.getText().toString());
            orderServe.setPlace(place.getText().toString());
            orderServe.setProperty(property.getText().toString());
            orderServe.setWordtype(wordtype.getText().toString());
            orderServe.setReality_worktype(reality_worktype.getText().toString());
            orderServe.setApplyunity(applyunity.getText().toString());
            orderServe.setMajor(major.getText().toString());
            orderServe.setReality_item(reality_item.getText().toString());
            orderServe.setState(state.getText().toString());
            orderServe.setDate(date.getText().toString());
            orderServe.setWorkplan(workplan.getText().toString());
            orderServe.setReality_starttime(reality_starttime.getText().toString());
            orderServe.setReality_stoptime(reality_stoptime.getText().toString());
            orderServe.setEmployee_id(employee_id.getText().toString());
            orderServe.setQuestiontogether(questiontogether.getText().toString());
            orderServe.setFaultclass(faultclass.getText().toString());
            orderServe.setError_coding(error_coding.getText().toString());
            orderServe.setFault_rank(fault_rank.getText().toString());
            orderServe.setReporttime(reporttime.getText().toString());
            intent.putExtra("orderServe", orderServe);
            AddOrderServeActivity.this.setResult(1, intent);
            finish();
        }
    };
}
