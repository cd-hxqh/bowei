package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.cdhxqh.bowei.ui.widget.CumTimePickerDialog;
import com.cdhxqh.bowei.ui.widget.OrderMorePopuowindow;
import com.cdhxqh.bowei.utils.WebserviceDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by think on 2015/8/18.
 */
public class ServeDetailActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView moreimg;
    private OrderMain orderMain;
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
    private TextView faultclass;
    private RelativeLayout faultclasslayout;
    private TextView error_coding;
    private RelativeLayout error_codinglayout;
    private TextView phenomena;//现象
    private RelativeLayout phenomenalayout;
    private TextView cause;
    private RelativeLayout causelayout;
    private TextView remedy;
    private RelativeLayout remedylayout;
    private TextView fault_rank;
    private RelativeLayout fault_ranklayout;
//    private TextView reporttime;
//    private RelativeLayout reporttimelayout;

    private String parent;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;
    private ProgressDialog mProgressDialog;

    private Button save;
    private Button yuzhi;
    private Button inputbtn;

    protected static final int S = 0;
    protected static final int F = 1;
    protected static final int YUZHI_S = 2;
    protected static final int YUZHI_F = 3;
    private String result;
    private String yuzhi_result;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case S:
                    mProgressDialog.dismiss();
                    Toast.makeText(ServeDetailActivity.this, "工单" + orderMain.getNumber() + "提交成功", Toast.LENGTH_SHORT).show();
                    new OrderMainDao(ServeDetailActivity.this).deleteById(orderMain.getId());
                    ServeDetailActivity.this.finish();
                    break;
                case F:
                    mProgressDialog.dismiss();
                    Toast.makeText(ServeDetailActivity.this, "提交失败" + result, Toast.LENGTH_SHORT).show();
                    break;
                case YUZHI_S:
                    mProgressDialog.dismiss();
                    number.setText(yuzhi_result);
                    yuzhi.setVisibility(View.GONE);
                    orderMain.setIsyuzhi(true);
                    new OrderMainDao(ServeDetailActivity.this).update(SaveData());
                    Toast.makeText(ServeDetailActivity.this, "获取工单编号成功", Toast.LENGTH_SHORT).show();
                    break;
                case YUZHI_F:
                    mProgressDialog.dismiss();
                    Toast.makeText(ServeDetailActivity.this, "获取工单编号失败," + yuzhi_result, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

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
        save = (Button) findViewById(R.id.order_detail_save);
        yuzhi = (Button) findViewById(R.id.order_detail_yuzhi);
        inputbtn = (Button) findViewById(R.id.order_detail_input);
    }

    @Override
    protected void initView() {
        getData();
        setview();
        if(orderMain.getNumber().equals("")||orderMain.getNumber()==null){
            yuzhi.setVisibility(View.VISIBLE);
        }
        titlename.setText(getResources().getString(R.string.serve));
        moreimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderMorePopuowindow orderMorePopuowindow = new OrderMorePopuowindow(ServeDetailActivity.this, getResources().getString(R.string.serve),
                        orderMain);
                orderMorePopuowindow.showPopupWindow(moreimg);
            }
        });

        setDataListener();

        if(!orderMain.isByserch()){
            placelayout.setOnClickListener(new MylayoutListener(1));
            propertylayout.setOnClickListener(new MylayoutListener(2));
            worktypelayout.setOnClickListener(new MylayoutListener(3));
            reality_worktypelayout.setOnClickListener(new MylayoutListener(4));
            applyunitylayout.setOnClickListener(new MylayoutListener(5));
            majorlayout.setOnClickListener(new MylayoutListener(6));
            employee_idlayout.setOnClickListener(new MylayoutListener(7));
            datelayout.setOnClickListener(new MydateListener());
//        workplanlayout.setOnClickListener(new MylayoutListener(9));
            faultclasslayout.setOnClickListener(new MylayoutListener(11));
            error_codinglayout.setOnClickListener(new MylayoutListener(12));
            phenomenalayout.setOnClickListener(new MylayoutListener(16));
            causelayout.setOnClickListener(new MylayoutListener(13));
            remedylayout.setOnClickListener(new MylayoutListener(14));
            fault_ranklayout.setOnClickListener(new MylayoutListener(15));
            reality_starttimelayout.setOnClickListener(new MydateListener());
            reality_stoptimelayout.setOnClickListener(new MydateListener());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OrderMainDao(ServeDetailActivity.this).update(SaveData());
                finish();
            }
        });
        inputbtn.setOnClickListener(inputlistener);

        yuzhi.setOnClickListener(yuzhilistener);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!orderMain.isByserch()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ServeDetailActivity.this);
                    builder.setMessage("是否保存工单详情?").setTitle("提示")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            save.performClick();
                        }
                    }).create().show();
                }else {
                    finish();
                }
            }
        });
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


    private View.OnClickListener inputlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ServeDetailActivity.this);
            builder.setMessage("请确认已填写员工信息").setTitle("提示")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    String isok = isOK();
                    if (isok.equals("OK")) {
                        final OrderMain orderMain = SaveData();
                        final String data = WebserviceDataUtils.updateData(getBaseApplication().getUsername(), ServeDetailActivity.this, orderMain);
                        mProgressDialog = ProgressDialog.show(ServeDetailActivity.this, null,
                                getString(R.string.inputing), true, true);
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.setCancelable(false);
                        new AsyncTask<String, String, String>() {
                            @Override
                            protected String doInBackground(String... strings) {
                                if ((orderMain.isNew() && !orderMain.isyuzhi()) || (orderMain.isNew() && orderMain.getNumber().equals(""))) {
                                    result = getBaseApplication().getWsService().InsertWO(data);
                                } else if (orderMain.isNew() && orderMain.isyuzhi()) {
                                    result = getBaseApplication().getWsService().UpdataWOyz(data);
                                } else if (!orderMain.isNew()) {
                                    result = getBaseApplication().getWsService().UpdataWO(data);
                                } else {
                                    result = "false";
                                }
                                return result;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                if (s.equals("false")) {
                                    mHandler.sendEmptyMessage(F);
                                    return;
                                }
                                try {
                                    JSONObject object = new JSONObject(s);
                                    if (object.getString("errorMsg").equals("成功")) {
                                        mHandler.sendEmptyMessage(S);
                                    } else {
                                        mHandler.sendEmptyMessage(F);
                                    }
                                    result = object.getString("errorMsg");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.execute();
                    } else if (isok.equals("请完善信息")) {
                        Toast.makeText(ServeDetailActivity.this, isok, Toast.LENGTH_SHORT).show();
                    }
                }
            }).create().show();
        }
    };
    private View.OnClickListener yuzhilistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String isok = isOK();
            if (isok.equals("OK")) {
                SaveData();
                mProgressDialog = ProgressDialog.show(ServeDetailActivity.this, null,
                        getString(R.string.requesting), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = WebserviceDataUtils.updateData(getBaseApplication().getUsername(), ServeDetailActivity.this, orderMain);
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
                                    mHandler.sendEmptyMessage(YUZHI_S);
                                    yuzhi_result = object.getString("woNum");
                                } else {
                                    mHandler.sendEmptyMessage(YUZHI_F);
                                    yuzhi_result = object.getString("errorMsg");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            mHandler.sendEmptyMessage(YUZHI_F);
                        }
                        mProgressDialog.dismiss();
                    }
                }.execute();
            } else if (isok.equals("请完善信息")) {
                Toast.makeText(ServeDetailActivity.this, isok, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void getData() {
        orderMain = (OrderMain) getIntent().getSerializableExtra("ordermain");
    }

    private void setview() {
        if(orderMain.isByserch()){
            save.setVisibility(View.GONE);
            yuzhi.setVisibility(View.GONE);
            inputbtn.setVisibility(View.GONE);
        }
        number.setText(orderMain.getNumber());
        describe.setText(orderMain.getDescribe());
        place.setText(orderMain.getPlace());
        property.setText(orderMain.getProperty());
        worktype.setText(orderMain.getWordtype());
        reality_worktype.setText(orderMain.getReality_worktype());
        applyunity.setText(orderMain.getApplyunity());
        major.setText(orderMain.getMajor());
        state.setText(orderMain.getState());
        date.setText(orderMain.getDate());
//        workplan.setText(orderMain.getWorkplan());
        reality_starttime.setText(orderMain.getReality_starttime());
        reality_stoptime.setText(orderMain.getReality_stoptime());
        employee_id.setText(orderMain.getEmployee_id());
        questiontogether.setText(orderMain.getQuestiontogether());
        faultclass.setText(orderMain.getFaultclass());
        error_coding.setText(orderMain.getError_coding());
        phenomena.setText(orderMain.getPhenomena());
        cause.setText(orderMain.getCause());
        remedy.setText(orderMain.getRemedy());
        fault_rank.setText(orderMain.getFault_rank());
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

    public class MylayoutListener implements View.OnClickListener {
        int requestCode;
        String parent;

        public MylayoutListener(int requestcode) {
            this.requestCode = requestcode;
        }

        @Override
        public void onClick(View view) {
            if (faultclass.getText().equals("") && requestCode == 12) {
                Toast.makeText(ServeDetailActivity.this, "请选择故障类", Toast.LENGTH_SHORT).show();
                return;
            } else if (error_coding.getText().equals("") && requestCode == 16) {
                Toast.makeText(ServeDetailActivity.this, "请选择问题代码", Toast.LENGTH_SHORT).show();
                return;
            } else if (phenomena.getText().equals("") && requestCode == 13) {
                Toast.makeText(ServeDetailActivity.this, "请选择现象", Toast.LENGTH_SHORT).show();
                return;
            }else if (cause.getText().equals("") && requestCode == 14) {
                Toast.makeText(ServeDetailActivity.this, "请选择原因", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(ServeDetailActivity.this, ItemChooseListActivity.class);
            intent.putExtra("requestCode", requestCode);
            this.parent = ServeDetailActivity.this.parent;
            if (faultclass.getText() != null && requestCode == 12) {
                intent.putExtra("parent", new FailureListDao(ServeDetailActivity.this).queryForClassByCode(faultclass.getText().toString()));
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
                faultclass.setText(new AssetDao(ServeDetailActivity.this).queryClassByAsset(content));
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
            case  16:
                phenomena.setText(content);
                orderMain.setPhenomena_list(number);
                cause.setText("");
                orderMain.setCause_list("");
                remedy.setText("");
                orderMain.setRemedy_list("");
                break;
            case 13:
                cause.setText(content);
                orderMain.setCause_list(number);
                break;
            case 14:
                remedy.setText(content);
                orderMain.setRemedy_list(number);
                break;
            case 15:
                fault_rank.setText(content);
                break;
            default:
                break;
        }
    }

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

    private OrderMain SaveData() {
        OrderMain orderMain = new OrderMain();
        orderMain = this.orderMain;
        orderMain.setNumber(number.getText().toString());
        orderMain.setDescribe(describe.getText().toString());
        orderMain.setPlace(place.getText().toString());
        orderMain.setProperty(property.getText().toString());
        orderMain.setWordtype(worktype.getText().toString());
        orderMain.setReality_worktype(reality_worktype.getText().toString());
        orderMain.setApplyunity(applyunity.getText().toString());
        orderMain.setMajor(major.getText().toString());
//            orderMain.setReality_item(reality_item.getText().toString());
        orderMain.setState(state.getText().toString());
        orderMain.setDate(date.getText().toString());
//        orderMain.setWorkplan(workplan.getText().toString());
        orderMain.setReality_starttime(reality_starttime.getText().toString());
        orderMain.setReality_stoptime(reality_stoptime.getText().toString());
        orderMain.setEmployee_id(employee_id.getText().toString());
        orderMain.setQuestiontogether(questiontogether.getText().toString());
        orderMain.setFaultclass(faultclass.getText().toString());
        orderMain.setPhenomena(phenomena.getText().toString());
        orderMain.setError_coding(error_coding.getText().toString());
        orderMain.setCause(cause.getText().toString());
        orderMain.setRemedy(remedy.getText().toString());
        orderMain.setFault_rank(fault_rank.getText().toString());

        return orderMain;
    }
}
