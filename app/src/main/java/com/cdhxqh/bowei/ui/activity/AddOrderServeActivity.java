package com.cdhxqh.bowei.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.OrderMainDao;
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
    private TextView worktype;//工作类型
    private RelativeLayout worktypelayout;
    private TextView reality_worktype;//实际工作类型
    private RelativeLayout reality_worktypelayout;
    private TextView applyunity;//申请单位
    private RelativeLayout applyunitylayout;
    private TextView major;//专业
    private RelativeLayout majorlayout;
//    private TextView reality_item;//实际班组
//    private RelativeLayout reality_itemlayout;
    private EditText state;//状态
    private TextView date;//汇报时间
    private RelativeLayout datelayout;
    private TextView workplan;//作业计划
    private RelativeLayout workplanlayout;
    private TextView reality_starttime;//实际开始时间
    private RelativeLayout reality_starttimelayout;
    private TextView reality_stoptime;//实际完成时间
    private RelativeLayout reality_stoptimelayout;
    private EditText employee_id;//录入人工号
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

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

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

        worktype = (TextView) findViewById(R.id.oder_detail_wordtype);
        worktypelayout = (RelativeLayout) findViewById(R.id.oder_detail_wordtype_layout);

        reality_worktype = (TextView) findViewById(R.id.oder_detail_reality_worktype);
        reality_worktypelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_worktype_layout);

        applyunity = (TextView) findViewById(R.id.oder_detail_applyunity);
        applyunitylayout = (RelativeLayout) findViewById(R.id.oder_detail_applyunity_layout);

        major = (TextView) findViewById(R.id.oder_detail_major);
        majorlayout = (RelativeLayout) findViewById(R.id.oder_detail_major_layout);

//        reality_item = (TextView) findViewById(R.id.oder_detail_reality_item);
//        reality_itemlayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_item_layout);

        state = (EditText) findViewById(R.id.order_detail_state);

        date = (TextView) findViewById(R.id.oder_detail_date);
        datelayout = (RelativeLayout) findViewById(R.id.oder_detail_date_layout);

        workplan = (TextView) findViewById(R.id.oder_detail_workplan);
        workplanlayout = (RelativeLayout) findViewById(R.id.oder_detail_workplan_layout);

        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_starttimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_starttime_layout);

        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        reality_stoptimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_stoptime_layout);

        employee_id = (EditText) findViewById(R.id.oder_detail_employee_id);

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
        titlename.setText(getResources().getString(R.string.serve_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrderServeActivity.this.setResult(0);
                finish();
            }
        });
        number.setText("");
        worktype.setText("EM");
        datePickerDialog = new DatePickerDialog(this, new datelistener(), 2015, 0, 1);
        timePickerDialog = new TimePickerDialog(this, new timelistener(), 0, 0, true);
        placelayout.setOnClickListener(new MylayoutListener(1));
        propertylayout.setOnClickListener(new MylayoutListener(2));
        worktypelayout.setOnClickListener(new MylayoutListener(3));
        reality_worktypelayout.setOnClickListener(new MylayoutListener(4));
        applyunitylayout.setOnClickListener(new MylayoutListener(5));
        majorlayout.setOnClickListener(new MylayoutListener(6));
        datelayout.setOnClickListener(new MydateListener());
        workplanlayout.setOnClickListener(new MylayoutListener(9));
        reality_starttimelayout.setOnClickListener(new MydateListener());
        reality_stoptimelayout.setOnClickListener(new MydateListener());
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

    }
    public class MylayoutListener implements View.OnClickListener {
        int requestCode;
        public MylayoutListener(int requestcode){
            this.requestCode = requestcode;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddOrderServeActivity.this,ItemChooseListActivity.class);
            intent.putExtra("requestCode",requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String content = null;
        if(resultCode!=0){
            content = data.getCharSequenceExtra("result").toString();
        }
        switch (resultCode) {
            case 0:
                break;
            case 1:
                place.setText(content);
                break;
            case 2:
                property.setText(content);
                break;
            case 3:
                worktype.setText(content);
                break;
            case 4:
                reality_worktype.setText(content);
                break;
            case 5:
                applyunity.setText(content);
                break;
            case 6:
                major.setText(content);
                break;
            case 9:
                workplan.setText(content);
                break;
            default:
                break;
        }
    }
    public class MydateListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }
    private View.OnClickListener inputlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String isok = isOK();
            if(isok.equals("OK")) {
                Intent intent = new Intent();
                OrderMain orderMain = new OrderMain();
//            orderMain.setNumber(Integer.parseInt(number.getText().toString()));
                orderMain.setDescribe(describe.getText().toString());
                orderMain.setPlace(place.getText().toString());
                orderMain.setProperty(property.getText().toString());
                orderMain.setWordtype(worktype.getText().toString());
                orderMain.setReality_worktype(reality_worktype.getText().toString());
                orderMain.setApplyunity(applyunity.getText().toString());
                orderMain.setMajor(major.getText().toString());
//            orderServe.setReality_item(reality_item.getText().toString());
                orderMain.setState(state.getText().toString());
                orderMain.setDate(date.getText().toString());
                if (workplan.getText() != null) {
                    orderMain.setWorkplan(workplan.getText().toString());
                }
                orderMain.setReality_starttime(reality_starttime.getText().toString());
                orderMain.setReality_stoptime(reality_stoptime.getText().toString());
//            orderMain.setEmployee_id(employee_id.getText().toString());
//            orderMain.setQuestiontogether(questiontogether.getText().toString());
//            orderMain.setFaultclass(faultclass.getText().toString());
//            orderMain.setError_coding(error_coding.getText().toString());
//            orderMain.setFault_rank(fault_rank.getText().toString());
//            orderMain.setReporttime(reporttime.getText().toString());
                intent.putExtra("orderMain", orderMain);
                AddOrderServeActivity.this.setResult(1, intent);
                finish();
            }else if(isok.equals("请完善信息")){
                Toast.makeText(AddOrderServeActivity.this, isok, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                sb.append(sb.append(String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)));
            if(dayOfMonth<10){
                sb.append(year+"-"+monthOfYear+1+"-"+"0"+dayOfMonth);
            }else {
                sb.append(year + "-" + monthOfYear + 1 + "-" + dayOfMonth);
            }
            timePickerDialog.show();
        }
    }
    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if(i1<10){
                sb.append(i+":"+"0"+i1+":00");
            }else {
                sb.append(i+":"+i1+":00");
            }
            if(layoutnum == datelayout.getId()){
                date.setText(sb);
            }else if(layoutnum == reality_starttimelayout.getId()){
                reality_starttime.setText(sb);
            }else if(layoutnum ==reality_stoptimelayout.getId()){
                reality_stoptime.setText(sb);
            }

        }
    }
    /**
     * 提交时判断填写是否合格
     * @return
     */
    private String isOK(){
        if (describe.getText().equals("")||place.equals("")
                ||property.getText().equals("")||worktype.getText().equals("")
                ||reality_worktype.getText().equals("")||applyunity.getText().equals("")
                ||major.getText().equals("")||date.getText().equals("")){
            return "请完善信息";
        }else{
            return "OK";
        }
    }
}
