package com.cdhxqh.bowei.ui.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.cdhxqh.bowei.Dao.AssetDao;
import com.cdhxqh.bowei.Dao.FailureListDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderServe;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.ui.widget.CumTimePickerDialog;
import com.cdhxqh.bowei.utils.WebserviceDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by think on 2015/9/2.
 * 新增维修
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
    //    private TextView workplan;//作业计划
//    private RelativeLayout workplanlayout;
    private TextView reality_starttime;//实际开始时间
    private RelativeLayout reality_starttimelayout;
    private TextView reality_stoptime;//实际完成时间
    private RelativeLayout reality_stoptimelayout;
    private TextView employee_id;//录入人工号
    private RelativeLayout employee_idlayout;
    private EditText questiontogether;//问题汇总
    private TextView faultclass;//故障类
    private RelativeLayout faultclasslayout;
    private TextView error_coding;//问题代码
    private RelativeLayout error_codinglayout;
    private TextView phenomena;//现象
    private RelativeLayout phenomenalayout;
    private TextView cause;//原因
    private RelativeLayout causelayout;
    private TextView remedy;//措施
    private RelativeLayout remedylayout;
    private TextView fault_rank;
    private RelativeLayout fault_ranklayout;
//    private TextView reporttime;
//    private RelativeLayout reporttimelayout;
//    private EditText reporttimeedit;

    private String parent;

    private Button yuzhi;
    private Button save;

    private EditText showingedit;
    private TextView showingtext;

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

    private OrderMain orderMain = new OrderMain();
    private ProgressDialog mProgressDialog;
    protected static final int S = 0;
    protected static final int F = 1;
    private String result;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case S:
                    number.setText(result);
                    Toast.makeText(AddOrderServeActivity.this, "获取工单编号成功", Toast.LENGTH_SHORT).show();
                    orderMain.setIsyuzhi(true);
                    save.performClick();
                    break;
                case F:
                    Toast.makeText(AddOrderServeActivity.this, "获取工单编号失败," + result, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


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
        setContentView(R.layout.serve_detail_add_activity);
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

        state = (EditText) findViewById(R.id.order_detail_state);

        date = (TextView) findViewById(R.id.oder_detail_date);
        datelayout = (RelativeLayout) findViewById(R.id.oder_detail_date_layout);

//        workplan = (TextView) findViewById(R.id.oder_detail_workplan);
//        workplanlayout = (RelativeLayout) findViewById(R.id.oder_detail_workplan_layout);

        reality_starttime = (TextView) findViewById(R.id.oder_detail_reality_starttime);
        reality_starttimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_starttime_layout);

        reality_stoptime = (TextView) findViewById(R.id.oder_detail_reality_stoptime);
        reality_stoptimelayout = (RelativeLayout) findViewById(R.id.oder_detail_reality_stoptime_layout);

        employee_id = (TextView) findViewById(R.id.oder_detail_employee_id);
        employee_idlayout = (RelativeLayout) findViewById(R.id.oder_detail_employee_id_layout);

        questiontogether = (EditText) findViewById(R.id.questiontogether);

        faultclass = (TextView) findViewById(R.id.oder_detail_faultclass);
        faultclasslayout = (RelativeLayout) findViewById(R.id.oder_detail_faultclass_layout);

        error_coding = (TextView) findViewById(R.id.oder_detail_error_coding);
        error_codinglayout = (RelativeLayout) findViewById(R.id.oder_detail_error_coding_layout);

        phenomena = (TextView) findViewById(R.id.oder_detail_phenomena);
        phenomenalayout = (RelativeLayout) findViewById(R.id.oder_detail_phenomena_layout);

        cause = (TextView) findViewById(R.id.oder_detail_cause);
        causelayout = (RelativeLayout) findViewById(R.id.oder_detail_cause_layout);

        remedy = (TextView) findViewById(R.id.oder_detail_remedy);
        remedylayout = (RelativeLayout) findViewById(R.id.oder_detail_remedy_layout);

        fault_rank = (TextView) findViewById(R.id.oder_detail_fault_rank);
        fault_ranklayout = (RelativeLayout) findViewById(R.id.oder_detail_fault_rank_layout);

//        reporttime = (TextView) findViewById(R.id.order_detail_reporttime);
//        reporttimelayout = (RelativeLayout) findViewById(R.id.order_detail_reporttime_layout);
//        reporttimeedit = (EditText) findViewById(R.id.order_detail_reporttime_edit);

        yuzhi = (Button) findViewById(R.id.order_detail_yuzhi);
        save = (Button) findViewById(R.id.order_detail_save);
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
        applyunity.setText("T3");
        major.setText("JY");
        date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        if (!assetNum.equals("")) {
            property.setText(assetNum);
            faultclass.setText(new AssetDao(AddOrderServeActivity.this).queryClassByAsset(assetNum));
        }
        if(!loucations.equals("")){
            place.setText(loucations);
        }
        orderMain.setIsNew(true);
        setDataListener();
        placelayout.setOnClickListener(new MylayoutListener(1));
        propertylayout.setOnClickListener(new MylayoutListener(2));
//        worktypelayout.setOnClickListener(new MylayoutListener(3));
        reality_worktypelayout.setOnClickListener(new MylayoutListener(4));
        applyunitylayout.setOnClickListener(new MylayoutListener(5));
        majorlayout.setOnClickListener(new MylayoutListener(6));
        datelayout.setOnClickListener(new MydateListener());
        employee_idlayout.setOnClickListener(new MylayoutListener(7));
//        workplanlayout.setOnClickListener(new MylayoutListener(9));
        faultclasslayout.setOnClickListener(new MylayoutListener(11));
        error_codinglayout.setOnClickListener(new MylayoutListener(12));
        phenomenalayout.setOnClickListener(new MylayoutListener(16));
        causelayout.setOnClickListener(new MylayoutListener(13));
        remedylayout.setOnClickListener(new MylayoutListener(14));
        fault_ranklayout.setOnClickListener(new MylayoutListener(15));
        reality_starttimelayout.setOnClickListener(new MydateListener());
        reality_stoptimelayout.setOnClickListener(new MydateListener());

        yuzhi.setOnClickListener(yuzhilistener);
        save.setOnClickListener(savelistener);

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

    private View.OnClickListener yuzhilistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String isok = isOK();
            if (isok.equals("OK")) {
                SaveData();
                mProgressDialog = ProgressDialog.show(AddOrderServeActivity.this, null,
                        getString(R.string.requesting), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = WebserviceDataUtils.updateData(getBaseApplication().getUsername(), AddOrderServeActivity.this, orderMain);
                        String S = getBaseApplication().getWsService().InsertWOyz(data);
                        if (S == null) {
                            return "false";
                        } else {
                            return S;
                        }
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if (!s.equals("false")) {
                            try {
                                JSONObject object = new JSONObject(s);
                                if (object.getString("errorMsg").equals("成功")) {
                                    mHandler.sendEmptyMessage(S);
                                    result = object.getString("woNum");
                                } else {
                                    mHandler.sendEmptyMessage(F);
                                    result = object.getString("errorMsg");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            mHandler.sendEmptyMessage(F);
                        }
                        mProgressDialog.dismiss();
                    }
                }.execute();
            } else if (isok.equals("请完善信息")) {
                Toast.makeText(AddOrderServeActivity.this, isok, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public class MylayoutListener implements View.OnClickListener {
        int requestCode;
        String parent;

        public MylayoutListener(int requestcode) {
            this.requestCode = requestcode;

        }

        @Override
        public void onClick(View view) {
            if (faultclass.getText().equals("") && requestCode == 12) {
                Toast.makeText(AddOrderServeActivity.this, "请选择故障类", Toast.LENGTH_SHORT).show();
                return;
            } else if (error_coding.getText().equals("") && requestCode == 16) {
                Toast.makeText(AddOrderServeActivity.this, "请选择问题代码", Toast.LENGTH_SHORT).show();
                return;
            } else if (phenomena.getText().equals("") && requestCode == 13) {
                Toast.makeText(AddOrderServeActivity.this, "请选择现象", Toast.LENGTH_SHORT).show();
                return;
            }else if (cause.getText().equals("") && requestCode == 14) {
                Toast.makeText(AddOrderServeActivity.this, "请选择原因", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(AddOrderServeActivity.this, ItemChooseListActivity.class);
            intent.putExtra("requestCode", requestCode);
            this.parent = AddOrderServeActivity.this.parent;
            if (faultclass.getText() != null && requestCode == 12) {
                intent.putExtra("parent", new FailureListDao(AddOrderServeActivity.this).queryForClassByCode(faultclass.getText().toString()));
            } else if (error_coding.getText() != null && requestCode == 16) {
                intent.putExtra("parent", orderMain.getError_coding_list());
            }else if (phenomena.getText() != null && requestCode == 13) {
                intent.putExtra("parent", orderMain.getPhenomena_list());
            } else if (faultclass.getText() != null && cause.getText() != null && requestCode == 14) {
                intent.putExtra("parent", orderMain.getCause_list());
            }
            intent.putExtra("OrderType", getResources().getString(R.string.serve));
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String content = null;
        String number = null;
        if (resultCode != 0) {
            content = data.getCharSequenceExtra("result").toString();
            if (data.hasExtra("number")) {
                number = data.getCharSequenceExtra("number").toString();
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
                place.setText(number);
                faultclass.setText(new AssetDao(AddOrderServeActivity.this).queryClassByAsset(content));
                error_coding.setText("");
                orderMain.setError_coding_list("");
                phenomena.setText("");
                orderMain.setPhenomena_list("");
                cause.setText("");
                orderMain.setCause_list("");
                remedy.setText("");
                orderMain.setRemedy_list("");
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
            case 7:
                employee_id.setText(content);
                break;
            case 9:
//                workplan.setText(content);
                break;
            case 11:
                faultclass.setText(content);
                error_coding.setText("");
                orderMain.setError_coding_list("");
                phenomena.setText("");
                orderMain.setPhenomena_list("");
                cause.setText("");
                orderMain.setCause_list("");
                remedy.setText("");
                orderMain.setRemedy_list("");
                break;
            case 12:
                error_coding.setText(content);
//                parent = number;
                orderMain.setError_coding_list(number);
                phenomena.setText("");
                orderMain.setPhenomena_list("");
                cause.setText("");
                orderMain.setCause_list("");
                remedy.setText("");
                orderMain.setRemedy_list("");
                break;
            case 13:
                cause.setText(content);
                orderMain.setCause_list(number);
                remedy.setText("");
                orderMain.setRemedy("");
                break;
            case 14:
                remedy.setText(content);
                orderMain.setRemedy_list(number);
                break;
            case 15:
                fault_rank.setText(content);
                break;
            case  16:
                phenomena.setText(content);
                orderMain.setPhenomena_list(number);
                cause.setText("");
                orderMain.setCause_list("");
                remedy.setText("");
                orderMain.setRemedy_list("");
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
//            orderMain.setReporttime(reporttime.getText().toString());
                intent.putExtra("orderMain", orderMain);
                AddOrderServeActivity.this.setResult(1, intent);
                finish();
            } else if (isok.equals("请完善信息")) {
                Toast.makeText(AddOrderServeActivity.this, isok, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
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
                ||property.getText().equals("")
                || worktype.getText().equals("")
                || reality_worktype.getText().equals("") || applyunity.getText().equals("")
                || major.getText().equals("") || date.getText().equals("")
                || employee_id.getText().equals("")) {
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
        orderMain.setFaultclass(faultclass.getText().toString());
        orderMain.setError_coding(error_coding.getText().toString());
        orderMain.setPhenomena(phenomena.getText().toString());
        orderMain.setCause(cause.getText().toString());
        orderMain.setRemedy(remedy.getText().toString());
        orderMain.setFault_rank(fault_rank.getText().toString());
        orderMain.setBelong(getBaseApplication().getUsername());
        if (number.getText() != null) {
            orderMain.setIsyuzhi(true);
        } else {
            orderMain.setIsyuzhi(false);
        }
        new OrderMainDao(this).update(orderMain);
//        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }
}
