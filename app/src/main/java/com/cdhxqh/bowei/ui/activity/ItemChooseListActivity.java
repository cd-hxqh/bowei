package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.Dao.AssetDao;
import com.cdhxqh.bowei.Dao.LocationsDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Asset;
import com.cdhxqh.bowei.bean.Locations;
import com.cdhxqh.bowei.ui.adapter.ItemListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/9/7.
 */
public class ItemChooseListActivity extends BaseActivity{
    private ImageView backimg;
    private TextView titlename;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private ItemListAdapter itemListAdapter;
    ArrayList<String> list;
    private int requestCode;

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
                finish();
            }
        });
        requestCode = (int) getIntent().getExtras().get("requestCode");
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
        list = new ArrayList<String>();
        switch (requestCode){
            case 1:
                List<Locations> locationsListnew = new LocationsDao(this).queryForAll();
                for(int i = 0;i<locationsListnew.size();i++){
                    list.add(i,locationsListnew.get(i).getDESCRIPTION());
                }
                break;
            case 2:
                List<Asset> assetList = new AssetDao(this).queryForAll();
                for(int i = 0;i < assetList.size();i++){
                    list.add(i,assetList.get(i).getDESCRIPTION());
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
        }
        itemListAdapter.update(list,true);
    }
}
