package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.Dao.OrderMainDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.ui.fragment.ConsumeMaterialFragment;
import com.cdhxqh.bowei.ui.fragment.GiveMaterialFragment;
import com.cdhxqh.bowei.ui.fragment.JieyunFragment;
import com.cdhxqh.bowei.ui.fragment.RealMaterialConsunmeFragment;
import com.cdhxqh.bowei.ui.fragment.WorkerFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/8/20.
 */
public class ServeRealInfoActivity extends BaseActivity {
    private TextView titlename;
    private ImageView backimg;
    private ImageView addimg;

    private TextView worker_info, real_material;
    private ImageView mTabLineIv;
    private int currentIndex = 0;
    private int screenWidth;
    private ImageView mImageView;
    private ViewPager mViewPager;    //下方的可横向拖动的控件
    private List<Fragment> fragmentlist = new ArrayList<Fragment>();
    private WorkerFragment workerFragment;
    private GiveMaterialFragment giveMaterialFragment = new GiveMaterialFragment();
//    private ConsumeMaterialFragment consumeMaterialFragment = new ConsumeMaterialFragment();
    private RealMaterialConsunmeFragment realMaterialConsunmeFragment;
    private JieyunFragment jieyunFragment = new JieyunFragment();
    private Intent intent;
    private int requestCode;
    public int id;
    public String num;
    OrderMain orderMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serve_real_info);

        findViewById();
        initView();
        initTabLineWidth();
        iniListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
        mViewPager.setCurrentItem(currentIndex);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);
        worker_info = (TextView) this.findViewById(R.id.worker_info);
        real_material = (TextView) this.findViewById(R.id.real_material);
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        titlename.setText(getResources().getString(R.string.real_info));
        orderMain = (OrderMain) getIntent().getSerializableExtra("orderMain");
        backimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(orderMain.isByserch()){
            addimg.setVisibility(View.GONE);
        }
        addimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode = 1;
                intent = new Intent(ServeRealInfoActivity.this,AddWorkerInfoActivity.class);
                intent.putExtra("orderid",orderMain.getId());
//                startActivityForResult(intent, requestCode);
                startActivity(intent);
            }
        });
        worker_info.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
        real_material.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
        workerFragment = new WorkerFragment(orderMain);
        realMaterialConsunmeFragment = new RealMaterialConsunmeFragment(orderMain);
        fragmentlist = new ArrayList<Fragment>();
        fragmentlist.add(workerFragment);
//        fragmentlist.add(giveMaterialFragment);
//        fragmentlist.add(consumeMaterialFragment);
//        fragmentlist.add(jieyunFragment);
        fragmentlist.add(realMaterialConsunmeFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        MaterialInfo materialInfo;
//        switch (resultCode) {
//            case 0:
//                break;
//            case 1:
//                WorkerInfo workerInfo = (WorkerInfo) data.getSerializableExtra("workinfo");
//                workerFragment.adddata(workerInfo);
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            default:
//                break;
//        }
//    }

    private void iniListener() {
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }


    /**
     * ViewPager的适配器
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     */
    private class MyPagerOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                    .getLayoutParams();

            /**
             * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
             * 设置mTabLineIv的左边距 滑动场景：
             * 记3个页面,
             * 从左到右分别为0,1,2
             * 0->1; 1->2; 2->1; 1->0
             */

            if (currentIndex == 0 && position == 0)// 0->1
            {
                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                        * (screenWidth / 2));

            } else if (currentIndex == 1 && position == 0) // 1->0
            {
                lp.leftMargin = (int) (-(1 - offset)
                        * (screenWidth * 1.0 / 2) + currentIndex
                        * (screenWidth / 2));

            }
//            else if (currentIndex == 1 && position == 1) // 1->2
//            {
//                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
//                        * (screenWidth / 3));
//            } else if (currentIndex == 2 && position == 1) // 2->1
//            {
//                lp.leftMargin = (int) (-(1 - offset)
//                        * (screenWidth * 1.0 / 3) + currentIndex
//                        * (screenWidth / 3));
//            }
            mTabLineIv.setLayoutParams(lp);
        }

        /**
         * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    worker_info.setTextColor(Color.WHITE);
                    addimg.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    real_material.setTextColor(Color.WHITE);
                    addimg.setVisibility(View.GONE);
                    break;
            }
            currentIndex = position;
        }
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        real_material.setTextColor(getResources().getColor(R.color.gray));
        worker_info.setTextColor(getResources().getColor(R.color.gray));
    }
}
