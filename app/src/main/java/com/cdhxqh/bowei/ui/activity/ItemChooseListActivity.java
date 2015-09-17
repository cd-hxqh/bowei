package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.AcWorkTypeDao;
import com.cdhxqh.bowei.Dao.AlndomainDao;
import com.cdhxqh.bowei.Dao.AssetDao;
import com.cdhxqh.bowei.Dao.ErsonDao;
import com.cdhxqh.bowei.Dao.FailureListDao;
import com.cdhxqh.bowei.Dao.FailurecodeDao;
import com.cdhxqh.bowei.Dao.JobMaterialDao;
import com.cdhxqh.bowei.Dao.JobPlanDao;
import com.cdhxqh.bowei.Dao.JobTaskDao;
import com.cdhxqh.bowei.Dao.LocationsDao;
import com.cdhxqh.bowei.Dao.WorkTypeDao;
import com.cdhxqh.bowei.Dao.WorkdwDao;
import com.cdhxqh.bowei.Dao.WorkzyDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.AcWorkType;
import com.cdhxqh.bowei.bean.Alndomain;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.ChooseItem;
import com.cdhxqh.bowei.bean.Erson;
import com.cdhxqh.bowei.bean.FailureList1;
import com.cdhxqh.bowei.bean.Failurecode;
import com.cdhxqh.bowei.bean.JobTask;
import com.cdhxqh.bowei.bean.Jobmaterial;
import com.cdhxqh.bowei.bean.Jobplan;
import com.cdhxqh.bowei.bean.Locations;
import com.cdhxqh.bowei.bean.WorkType;
import com.cdhxqh.bowei.bean.Workdw;
import com.cdhxqh.bowei.bean.Workzy;
import com.cdhxqh.bowei.ui.adapter.ItemListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class ItemChooseListActivity extends BaseActivity{
    private static final String TAG = "ItemChooseListActivity";

    private ImageView backimg;
    private TextView titlename;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private ItemListAdapter itemListAdapter;
    ArrayList<ChooseItem> list;
    ChooseItem chooseItem;
    Intent intent;
    public int requestCode;
    public String OrderType;
    private String parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_choose_list);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);
        recyclerView = (RecyclerView) findViewById(R.id.item_choose_list);
    }

    @Override
    protected void initView() {
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemChooseListActivity.this.setResult(0);
                finish();
            }
        });
        requestCode = (int) getIntent().getExtras().get("requestCode");
        OrderType = getIntent().getExtras().getString("OrderType");
        parent = getIntent().getExtras().getString("parent");
        titlename.setText(getResources().getString(R.string.item_list));
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        itemListAdapter = new ItemListAdapter(this,this);
        recyclerView.setAdapter(itemListAdapter);
        addData();
    }

    private void addData(){
        list = new ArrayList<ChooseItem>();
        switch (requestCode){
            case 1:
                List<Locations> locationsList = new LocationsDao(this).queryForAll();
                for(int i = 0;i<locationsList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(locationsList.get(i).getDESCRIPTION());
                    chooseItem.setValue(locationsList.get(i).getLOCATION());
                    list.add(i,chooseItem);
                }
                break;
            case 2:
                List<Asset> assetList = new AssetDao(this).queryForAll();
                for(int i = 0;i < assetList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(assetList.get(i).getDESCRIPTION());
                    chooseItem.setValue(assetList.get(i).getASSETNUM());
                    list.add(i,chooseItem);
                }
                break;
            case 3:
                List<WorkType>workTypeList = new WorkTypeDao(this).queryForAll();
                for(int i = 0;i < workTypeList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(workTypeList.get(i).getWTYPEDESC());
                    chooseItem.setValue(workTypeList.get(i).getWORKTYPE());
                    list.add(i,chooseItem);
                }

                break;
            case 4:
                List<AcWorkType>acWorkTypeList;
                if(OrderType.equals(getResources().getString(R.string.maintenance))){
                    acWorkTypeList = new AcWorkTypeDao(this).queryForAll();
                }else if(OrderType.equals(getResources().getString(R.string.serve))){
                    acWorkTypeList = new AcWorkTypeDao(this).queryForEM();
                }else if(OrderType.equals(getResources().getString(R.string.service))){
                    acWorkTypeList = new AcWorkTypeDao(this).queryForSVR();
                }else {
                    acWorkTypeList = null;
                }
                for(int i = 0;i < acWorkTypeList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(acWorkTypeList.get(i).getDESCRIPTION());
                    chooseItem.setValue(acWorkTypeList.get(i).getVALUE());
                    list.add(i,chooseItem);
                }
                break;
            case 5:
                List<Workdw>workdwList = new WorkdwDao(this).queryForAll();
                for (int i = 0;i < workdwList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(workdwList.get(i).getDESCRIPTION());
                    chooseItem.setValue(workdwList.get(i).getVALUE());
                    list.add(i,chooseItem);
                }
                break;
            case 6:
                List<Workzy>workzyList = new WorkzyDao(this).queryForAll();
                Log.i(TAG,workzyList.size()+"");
                for(int i = 0;i < workzyList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(workzyList.get(i).getDESCRIPTION());
                    chooseItem.setValue(workzyList.get(i).getVALUE());
                    list.add(i,chooseItem);
                }
                break;
            case 7:
                List<Erson>ersonList = new ErsonDao(this).queryForAll();
//                if(ersonList!=null){
                    for(int i = 0;i < ersonList.size();i++){
                        chooseItem = new ChooseItem();
                        chooseItem.setName(ersonList.get(i).getDISPLAYNAME());
                        chooseItem.setValue(ersonList.get(i).getYWFL());
                        list.add(i,chooseItem);
                    }
//                }

                break;
            case 8://服务工单实际工作类型
                List<AcWorkType>acWorkTypeList1 = new AcWorkTypeDao(this).queryForSVR();
                for(int i = 0;i < acWorkTypeList1.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(acWorkTypeList1.get(i).getDESCRIPTION());
                    chooseItem.setValue(acWorkTypeList1.get(i).getVALUE());
                    list.add(i,chooseItem);
                }
                break;
            case 9:
                List<Jobplan>jobplanList = new JobPlanDao(this).queryForAll();
                for(int i = 0;i < jobplanList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(jobplanList.get(i).getDESCRIPTION());
                    chooseItem.setValue(jobplanList.get(i).getJPNUM());
                    list.add(i,chooseItem);
                }
                break;
            case 10:
                List<JobTask>jobTaskList = new JobTaskDao(this).queryForAll();
                for(int i = 0;i < jobTaskList.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(jobTaskList.get(i).getDESCRIPTION());
                    chooseItem.setValue(String.valueOf(jobTaskList.get(i).getJOBTASKID()));
                    list.add(i,chooseItem);
                }
                break;
            case 11://故障类
                List<FailureList1> failureList1List = new FailureListDao(this).queryForClass();
                for (int i = 0;i < failureList1List.size();i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(new FailurecodeDao(this).queryByCode(failureList1List.get(i).getFAILURECODE()).getDESCRIPTION());
                    chooseItem.setValue(failureList1List.get(i).getFAILURECODE());
                    chooseItem.setParent(failureList1List.get(i).getFAILURELIST());
                    list.add(i,chooseItem);
                }
                break;
            case 12://现象
                if(parent!=null){
                    List<FailureList1> failureList1List1 = new FailureListDao(this).queryForProblem(parent);
                    for (int i = 0;i < failureList1List1.size();i++){
                        chooseItem = new ChooseItem();
                        chooseItem.setName(failureList1List1.get(i).getFAILURELIST());
                        chooseItem.setValue(failureList1List1.get(i).getFAILURECODE());
                        list.add(i, chooseItem);
                    }
                }
                break;
            case 13://原因
                if(parent!=null){
                    List<FailureList1> failureList1List2 = new FailureListDao(this).queryForCauseByPatent(parent);
                    for (int i = 0;i < failureList1List2.size();i++){
                        chooseItem = new ChooseItem();
                        chooseItem.setName(failureList1List2.get(i).getFAILURELIST());
                        chooseItem.setValue(failureList1List2.get(i).getFAILURECODE());
                        list.add(i, chooseItem);
                    }
                }
                break;
            case 14://措施
                if(parent!=null) {
                    List<FailureList1> failureList1List3 = new FailureListDao(this).queryForRemedyByPatent(parent);
                    for (int i = 0; i < failureList1List3.size(); i++) {
                        chooseItem = new ChooseItem();
                        chooseItem.setName(failureList1List3.get(i).getFAILURELIST());
                        chooseItem.setValue(failureList1List3.get(i).getFAILURECODE());
                        list.add(i, chooseItem);
                    }
                }
                break;
            case 15:
                List<Alndomain> alndomainList = new AlndomainDao(this).queryForAll();
                for (int i = 0; i < alndomainList.size(); i++){
                    chooseItem = new ChooseItem();
                    chooseItem.setName(alndomainList.get(i).getDESCRIPTION());
                    chooseItem.setValue(alndomainList.get(i).getVALUE());
                    list.add(i, chooseItem);
                }
                break;
        }
        itemListAdapter.update(list, true);
        if(itemListAdapter.getItemCount()==0){

        }
    }

    public void responseData(String data){
        intent = new Intent();
        intent.putExtra("result",data);
        Log.i(TAG,data);
        ItemChooseListActivity.this.setResult(requestCode, intent);
        finish();
    }
    public void responseData(String data,String num){
        intent = new Intent();
        intent.putExtra("result",data);
        intent.putExtra("number",num);
        Log.i(TAG,data);
        ItemChooseListActivity.this.setResult(requestCode, intent);
        finish();
    }
}
