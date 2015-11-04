package com.cdhxqh.bowei.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.ErsonDao;
import com.cdhxqh.bowei.Dao.JobPlanDao;
import com.cdhxqh.bowei.Dao.JobTaskDao;
import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.Dao.OrderTaskDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.ChooseItem;
import com.cdhxqh.bowei.bean.Erson;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.ItemListAdapter;
import com.cdhxqh.bowei.ui.adapter.OrderTaskAdapter;
import com.cdhxqh.bowei.ui.widget.SwipeRefreshLayout;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by think on 2015/8/20.
 */
public class OrderTaskActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backimg;
    private TextView titlename;
    private ImageView addimg;
    private Button choosebtn;
    public RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout = null;
    private OrderTaskAdapter orderTaskAdapter;
    LinearLayoutManager layoutManager;
    private ProgressDialog mProgressDialog;
    private RelativeLayout Multiplelayout;
    private LinearLayout nodatalayout;
    String name;
    int id;
    public OrderMain orderMain;
    public boolean isMultiple = false;
    AlertDialog.Builder builder1;
    AlertDialog.Builder builder2;
    private String[] mItems1 = {"执行人", "检查人"};
    private String[] mItems2;//人员列表
    private String[] mItems3;//班组列表
    ArrayList<String> MultiChoiceID = new ArrayList<String>();
    ArrayList<String> MultiChoiceID2 = new ArrayList<String>();
    private int chooseitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_task_activity);

        findViewById();
        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        choosebtn = (Button) findViewById(R.id.choose_btn);
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        Multiplelayout = (RelativeLayout) findViewById(R.id.chooseitem_relativelayout);
    }

    @Override
    protected void initView() {
        name = (String) getIntent().getExtras().get("fromname");
        orderMain = (OrderMain) getIntent().getSerializableExtra("orderMain");
        addimg.setVisibility(View.GONE);
        titlename.setText(getResources().getString(R.string.task_list));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        orderTaskAdapter = new OrderTaskAdapter(this, this);
        recyclerView.setAdapter(orderTaskAdapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadListener(this);
        if(!orderMain.isByserch()&&!orderMain.getWorkplan().equals("")) {
            addLocationTask(orderMain.getId());
        }else if(orderMain.isByserch()){
            getData();
        }



        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OrderTaskActivity.this,)
            }
        });
        choosebtn.setOnClickListener(chooselistener);
    }

    private View.OnClickListener chooselistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (orderTaskAdapter.checkedlist.size() == 0) {
                Toast.makeText(OrderTaskActivity.this,
                        getResources().getString(R.string.please_choose_task), Toast.LENGTH_SHORT).show();
            } else {
                builder1 = new AlertDialog.Builder(OrderTaskActivity.this);
                builder1.setTitle("工作选择");
                builder1.setItems(mItems1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        chooseitem = which;
                        showItemdialog();
//                        showErsondialog();
                    }
                });
                builder1.create().show();
            }
        }
    };

    /**
     * 班组选择复选对话框
     */
    private void showItemdialog() {
        List<Erson> ersonList = new ErsonDao(this).queryForAll();
        final List<String> itemlist = new ArrayList<String>();//选项列表
        for (int i = 0; i < ersonList.size(); i++) {
            if (!itemlist.contains(ersonList.get(i).getYWBZ()+"-"+(ersonList.get(i).getYWFL().equals("")?"无":ersonList.get(i).getYWFL()))) {
                itemlist.add(ersonList.get(i).getYWBZ()+"-"+(ersonList.get(i).getYWFL().equals("")?"无":ersonList.get(i).getYWFL()));
            }
        }
        boolean[] mItems4 = new boolean[itemlist.size()];
        for(int j = 0;j < itemlist.size();j ++){
            mItems4[j] =false;
        }
        mItems3 = (String[]) itemlist.toArray(new String[itemlist.size()]);
        MultiChoiceID2.clear();
        new AlertDialog.Builder(this).setTitle("班组选择").setMultiChoiceItems(
                mItems3, mItems4, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton,
                                        boolean isChecked) {
                        if (isChecked) {
                            MultiChoiceID2.add(whichButton + "");
                        } else {
                            if (MultiChoiceID2.contains(whichButton + "")) {
                                MultiChoiceID2.remove(whichButton + "");
                            }
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MultiChoiceID2.size() > 0) {
                            List<String> list = new ArrayList<String>();
                            for (int m = 0; m < MultiChoiceID2.size(); m++) {
                                list.add(m, itemlist.get(Integer.parseInt(MultiChoiceID2.get(m))));
                            }
                            showErsondialog(list);
                        } else {
                            Toast.makeText(OrderTaskActivity.this,"请选择班组",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    /**
     * 显示员工列表
     */
    private void showErsondialog(List<String> list) {
        List<Erson> ersonList = new ArrayList<>();
        for(int i = 0;i < list.size();i ++){
            ersonList.addAll(new ErsonDao(this).queryByItem(list.get(i)));
        }
//        List<Erson> ersonList = new ErsonDao(this).queryBylist(list);
        List<String> itemlist = new ArrayList<String>();//选项列表
        final List<String> itemlist1 = new ArrayList<String>();//对应员工列表
        boolean[] mItems3 = new boolean[ersonList.size()];
        for (int i = 0; i < ersonList.size(); i++) {
            itemlist.add(i, ersonList.get(i).getDISPLAYNAME() + "       " +
                    getResources().getString(R.string.item) + ":" + (ersonList.get(i).getYWBZ().equals("") ? "无" : ersonList.get(i).getYWBZ()));
            mItems3[i] = false;
            itemlist1.add(i, ersonList.get(i).getDISPLAYNAME());
        }
        mItems2 = (String[]) itemlist.toArray(new String[itemlist.size()]);
//        Boolean[] mItems4 = (Boolean[]) initlist.toArray(new Boolean[initlist.size()]);
        builder2 = new AlertDialog.Builder(OrderTaskActivity.this);
        MultiChoiceID.clear();
        builder2.setTitle("员工选择");
        builder2.setMultiChoiceItems(mItems2,
                mItems3,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton,
                                        boolean isChecked) {
                        if (isChecked) {
                            MultiChoiceID.add(whichButton + "");
                        } else {
                            if (MultiChoiceID.contains(whichButton + "")) {
                                MultiChoiceID.remove(whichButton + "");
                            }
                        }

                    }
                });
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String str = "";
                int size = MultiChoiceID.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        if (i == 0) {
//                            str = mItems2[Integer.parseInt(MultiChoiceID.get(i))];
                            str = itemlist1.get(Integer.parseInt(MultiChoiceID.get(i)));
                        } else {
//                            str += "," + mItems2[Integer.parseInt(MultiChoiceID.get(i))];
                            str += "," + itemlist1.get(Integer.parseInt(MultiChoiceID.get(i)));
                        }
                    }
                }
                changeTask(str);
                changeitenback();
            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        builder2.create().show();
    }

    /**
     * 按照选择的员工改变任务执行人/检查人
     *
     * @param str
     */
    private void changeTask(String str) {
        Iterator iter = orderTaskAdapter.checkedlist.entrySet().iterator();
        OrderTask val;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            val = (OrderTask) entry.getValue();
            if (chooseitem == 0) {
                val.setZxr(str);
            } else if (chooseitem == 1) {
                val.setJcr(str);
            }
            new OrderTaskDao(this).update(val);
        }
    }

    private void getData() {
        mProgressDialog = ProgressDialog.show(this, null,
                getString(R.string.requesting), true, true);
        HttpManager.getData(this, Constants.getOrderTaskUrl(orderMain.getNumber()), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        List<OrderTask> orderTaskList = JsonUtils.parsingOrderTask(OrderTaskActivity.this, jsonObject.getString("result"));
                        addOrderTaskList(orderTaskList);
                        mProgressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                Toast.makeText(OrderTaskActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addData(int id) {
        ArrayList<OrderTask> list = new ArrayList<OrderTask>();
        List<OrderTask> orderTaskList = new OrderTaskDao(OrderTaskActivity.this).queryByOrderId(id);
        for (int i = 0; i < orderTaskList.size(); i++) {
            list.add(i, orderTaskList.get(i));
        }
        orderTaskAdapter.update(list, true);
        if (orderTaskList.size() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else if(nodatalayout.getVisibility()==View.VISIBLE){
            nodatalayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        refreshLayout.setRefreshing(false);
    }

    private void addOrderTaskList(List<OrderTask> orderTaskList){
        orderTaskAdapter = new OrderTaskAdapter(this, this);
        recyclerView.setAdapter(orderTaskAdapter);
        ArrayList<OrderTask> list = new ArrayList<OrderTask>();
        for (int i = 0; i < orderTaskList.size(); i++) {
            list.add(i, orderTaskList.get(i));
        }
        orderTaskAdapter.update(list, true);
        if (orderTaskList.size() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else if(nodatalayout.getVisibility()==View.VISIBLE){
            nodatalayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        refreshLayout.setRefreshing(false);
    }

    private void addLocationTask(int id) {
        List<OrderTask>orderTaskList = new OrderTaskDao(OrderTaskActivity.this).queryByOrderId(id);
        if (new OrderTaskDao(OrderTaskActivity.this).queryByOrderId(id).size() > 0&&!orderMain.isByserch()) {
            addData(id);
        } else {
            if (orderMain.getWorkplan() != null&&!orderMain.getWorkplan().equals("")) {
                ArrayList<OrderTask> list = new ArrayList<OrderTask>();
                OrderTask orderTask;
                List<String> jpnumList = new JobPlanDao(this).queryForparent(orderMain.getWorkplan());
                List<JobTask> task = new JobTaskDao(this).QueryByJobTaskId(orderMain.getWorkplan(),jpnumList);
                for (int i = 0; i < task.size(); i++) {
                    orderTask = new OrderTask();
                    orderTask.setBelongordermain(id);
                    orderTask.setNum(orderMain.getNumber());
                    orderTask.setDigest(task.get(i).getDESCRIPTION());
                    orderTask.setOrdermainid(String.valueOf(task.get(i).getJOBTASKID()));
                    orderTask.setTask(String.valueOf(task.get(i).getJPTASK()));
//            orderTask.setWosequence();
                    new OrderTaskDao(OrderTaskActivity.this).update(orderTask);
                    list.add(i, orderTask);
                }
                orderTaskAdapter.update(list, true);
            }else {
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }
        refreshLayout.setRefreshing(false);
    }

    public void changeitem() {
        isMultiple = true;
        Multiplelayout.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(orderTaskAdapter);
    }

    public void changeitenback() {
        isMultiple = false;
        Multiplelayout.setVisibility(View.GONE);
        recyclerView.setAdapter(orderTaskAdapter);
    }


    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        orderTaskAdapter = new OrderTaskAdapter(this, this);
        recyclerView.setAdapter(orderTaskAdapter);
        if(!orderMain.isByserch()&&!orderMain.getWorkplan().equals("")) {
            addLocationTask(orderMain.getId());
        }else if(orderMain.isByserch()){
            getData();
        }
        refreshLayout.setRefreshing(false);
    }

    //上拉加载更多触发事件
    @Override
    public void onLoad() {
        refreshLayout.setLoading(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && isMultiple == true) {
            changeitenback();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
