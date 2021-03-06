package com.cdhxqh.bowei.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.ui.widget.CumTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by think on 2015/9/2.
 * 新增服务
 */
public class AddOrderServiceActivity extends BaseActivity {
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
    private EditText state;//状态
    private TextView date;//汇报时间
    private RelativeLayout datelayout;
    private TextView reality_starttime;//实际开始时间
    private RelativeLayout reality_starttimelayout;
    private TextView reality_stoptime;//实际完成时间
    private RelativeLayout reality_stoptimelayout;
    private TextView employee_id;//录入人工号
    private RelativeLayout employee_idlayout;
    private EditText questiontogether;//问题汇总

    private Button save;
    OrderMain orderMain = new OrderMain();
    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

    /**
     * 资产编号*
     */
    private String assetNum = "";
    /**
     * 资产对应位置
     */
    private String loucations = "";
    /**
     * 跳转标识*
     */
    private int JUMP_MARK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_detail_add_activity);
        getInitData();
        findViewById();
        initView();
    }


    /**
     * 获取资产*
     */
    private void getInitData() {

        JUMP_MARK = getIntent().getExtras().getInt("jump_mark");
        if (JUMP_MARK == Constants.RESULTS_MARK) {
            assetNum = getIntent().getExtras().getString("assetnum");
            loucations = getIntent().getExtras().getString("location");
        }
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

        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_starttimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_starttime_layout);

        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        reality_stoptimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_stoptime_layout);

        employee_id = (TextView) findViewById(R.id.oder_detail_employee_id);
        employee_idlayout = (RelativeLayout) findViewById(R.id.oder_detail_employee_id_layout);

        questiontogether = (EditText) findViewById(R.id.questiontogether);

        save = (Button) findViewById(R.id.order_detail_save);
    }


    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(this, new timelistener(), hour, minute, true);
    }


    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.service_add_new));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrderServiceActivity.this.setResult(0);
                finish();
            }
        });
        number.setText("");
        worktype.setText("SVR");
        applyunity.setText("T3");
        major.setText("JY");

        if (!assetNum.equals("")) {
            property.setText(assetNum);
        }
        if(!loucations.equals("")){
            place.setText(loucations);
        }
        date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        setDataListener();

        placelayout.setOnClickListener(new MylayoutListener(1));
        propertylayout.setOnClickListener(new MylayoutListener(2));
//        worktypelayout.setOnClickListener(new MylayoutListener(3));
        reality_worktypelayout.setOnClickListener(new MylayoutListener(8));
        applyunitylayout.setOnClickListener(new MylayoutListener(5));
        majorlayout.setOnClickListener(new MylayoutListener(6));
        employee_idlayout.setOnClickListener(new MylayoutListener(7));
        datelayout.setOnClickListener(new MydateListener());
        reality_starttimelayout.setOnClickListener(new MydateListener());
        reality_stoptimelayout.setOnClickListener(new MydateListener());

        save.setOnClickListener(savelistener);

    }

    public class MylayoutListener implements View.OnClickListener {
        int requestCode;

        public MylayoutListener(int requestcode) {
            this.requestCode = requestcode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddOrderServiceActivity.this, ItemChooseListActivity.class);
            intent.putExtra("requestCode", requestCode);
            intent.putExtra("OrderType", getResources().getString(R.string.service));
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String content = null;
        String loucation = null;
        if (resultCode != 0) {
            content = data.getCharSequenceExtra("result").toString();
            if (data.hasExtra("number")) {
                loucation = data.getCharSequenceExtra("number").toString();
            }
        }
        switch (resultCode) {
            case 0:
                break;
            case 1:
                place.setText(content);
                property.setText("");
                break;
            case 2:
                property.setText(content);
                place.setText(loucation);
                break;
            case 3:
                worktype.setText(content);
                break;
            case 4:
//                reality_worktype.setText(content);
                break;
            case 5:
                applyunity.setText(content);
                break;
            case 6:
                major.setText(content);
                break;
            case 7:
                employee_id.setText(content);
                break;
            case 8:
                reality_worktype.setText(content);
                break;
            case 9:
//                workplan.setText(content);
                break;
            default:
                break;
        }
    }

    public class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }

    private View.OnClickListener savelistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String isok = isOK();
            if (isok.equals("OK")) {
                SaveData();
                Intent intent = new Intent();
                intent.putExtra("orderMain", orderMain);
                AddOrderServiceActivity.this.setResult(1, intent);
                finish();
            } else if (isok.equals("请完善信息")) {
                Toast.makeText(AddOrderServiceActivity.this, isok, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            if (dayOfMonth < 10) {
                sb.append(year + "-" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
            }
            if (layoutnum == datelayout.getId()) {
                date.setText(sb);
            } else if (layoutnum == reality_starttimelayout.getId()) {
                reality_starttime.setText(sb);
            } else if (layoutnum == reality_stoptimelayout.getId()) {
                reality_stoptime.setText(sb);
            }

        }
    }

    /**
     * 提交时判断填写是否合格
     *
     * @return
     */
    private String isOK() {
        if (describe.getText().equals("")
                || worktype.getText().equals("")
                || reality_worktype.getText().equals("") || applyunity.getText().equals("")
                || major.getText().equals("") || date.getText().equals("")
                || employee_id.getText().equals("")) {
            return "请完善信息";
        }else if(place.getText().equals("")&&property.getText().equals("")){
            return "请完善信息";
        } else {
            return "OK";
        }
    }

    /**
     * 保存填写的工单信息
     */
    private void SaveData() {
        orderMain.setNumber(number.getText().toString());
        orderMain.setDescribe(describe.getText().toString());
        orderMain.setPlace(place.getText().toString());
        orderMain.setProperty(property.getText().toString());
        orderMain.setWordtype(worktype.getText().toString());
        orderMain.setReality_worktype(reality_worktype.getText().toString());
        orderMain.setApplyunity(applyunity.getText().toString());
        orderMain.setMajor(major.getText().toString());
        orderMain.setState(state.getText().toString());
        orderMain.setDate(date.getText().toString());
        orderMain.setReality_starttime(reality_starttime.getText().toString());
        orderMain.setReality_stoptime(reality_stoptime.getText().toString());
        orderMain.setEmployee_id(employee_id.getText().toString());
        orderMain.setQuestiontogether(questiontogether.getText().toString());
        orderMain.setIsNew(true);
        orderMain.setBelong(getBaseApplication().getUsername());
        orderMain.setIsyuzhi(false);
        new OrderMainDao(this).update(orderMain);
//        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }
}
