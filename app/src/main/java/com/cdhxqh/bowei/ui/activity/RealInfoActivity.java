package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.ui.fragment.ConsumeMaterialFragment;
import com.cdhxqh.bowei.ui.fragment.GiveMaterialFragment;
import com.cdhxqh.bowei.ui.fragment.JieyunFragment;
import com.cdhxqh.bowei.ui.fragment.MaterialConsunmeFragment;
import com.cdhxqh.bowei.ui.fragment.WorkerFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/8/20.
 */
public class RealInfoActivity extends BaseActivity implements OnCheckedChangeListener{
    private TextView titlename;
    private ImageView backimg;
    private ImageView addimg;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private ImageView mImageView;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private HorizontalScrollView mHorizontalScrollView;//上面的水平滚动控件
    private ViewPager mViewPager;	//下方的可横向拖动的控件
    private List<Fragment> fragmentlist = new ArrayList<Fragment>();
    private WorkerFragment workerFragment = new WorkerFragment();
    private GiveMaterialFragment giveMaterialFragment = new GiveMaterialFragment();
    private ConsumeMaterialFragment consumeMaterialFragment = new ConsumeMaterialFragment();
    private MaterialConsunmeFragment materialConsunmeFragment = new MaterialConsunmeFragment();
    private JieyunFragment jieyunFragment = new JieyunFragment();
    private Intent intent;
    private int requestCode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_info_activity);

        findViewById();
        initView();
        iniListener();

        mRadioButton1.setChecked(true);
        mViewPager.setCurrentItem(0);
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();

    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backimg = (ImageView) findViewById(R.id.maintenance_title_back);
        addimg = (ImageView) findViewById(R.id.maintenance_title_add);

        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        mRadioButton1 = (RadioButton)findViewById(R.id.btn1);
        mRadioButton2 = (RadioButton)findViewById(R.id.btn2);
        mRadioButton3 = (RadioButton)findViewById(R.id.btn3);
        mRadioButton4 = (RadioButton)findViewById(R.id.btn4);
        mRadioButton5 = (RadioButton)findViewById(R.id.btn5);

        mImageView = (ImageView)findViewById(R.id.img1);

        mHorizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);

        mViewPager = (ViewPager)findViewById(R.id.pager);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.real_info));
        backimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R.id.btn1 == mRadioGroup.getCheckedRadioButtonId()) {//添加员工信息
                    requestCode = 1;
                    intent = new Intent(RealInfoActivity.this,AddWorkerInfoActivity.class);
                    startActivityForResult(intent,requestCode);
                } else if (R.id.btn2 == mRadioGroup.getCheckedRadioButtonId()) {//添加发放物料信息
                    requestCode = 2;
                    intent = new Intent(RealInfoActivity.this,AddMaterialInfoActivity.class);
                    intent.putExtra("requestCode",requestCode);
                    startActivityForResult(intent, requestCode);
                }else if (R.id.btn3 == mRadioGroup.getCheckedRadioButtonId()) {//
                    requestCode = 3;
                    intent = new Intent(RealInfoActivity.this,AddMaterialInfoActivity.class);
                    intent.putExtra("requestCode",requestCode);
                    startActivityForResult(intent, requestCode);
                }else if (R.id.btn4 == mRadioGroup.getCheckedRadioButtonId()) {//
                    requestCode = 4;
                    intent = new Intent(RealInfoActivity.this,AddMaterialInfoActivity.class);
                    intent.putExtra("requestCode",requestCode);
                    startActivityForResult(intent, requestCode);
                }else if (R.id.btn5 == mRadioGroup.getCheckedRadioButtonId()) {//
                    requestCode = 5;
                    intent = new Intent(RealInfoActivity.this,AddMaterialInfoActivity.class);
                    intent.putExtra("requestCode",requestCode);
                    startActivityForResult(intent, requestCode);
                }
            }
        });

        fragmentlist.add(workerFragment);
        fragmentlist.add(giveMaterialFragment);
        fragmentlist.add(consumeMaterialFragment);
        fragmentlist.add(jieyunFragment);
        fragmentlist.add(materialConsunmeFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MaterialInfo materialInfo;
        switch (resultCode) {
            case 0:

                break;
            case 1:
                WorkerInfo workerInfo = (WorkerInfo) data.getSerializableExtra("workinfo");
                workerFragment.adddata(workerInfo);
                break;
            case 2:
                materialInfo = (MaterialInfo) data.getSerializableExtra("materialInfo");
                giveMaterialFragment.adddata(materialInfo);
                break;
            case 3:
                materialInfo = (MaterialInfo) data.getSerializableExtra("materialInfo");
                consumeMaterialFragment.adddata(materialInfo);
                break;
            case 4:
                materialInfo = (MaterialInfo) data.getSerializableExtra("materialInfo");
                jieyunFragment.adddata(materialInfo);
                break;
            case 5:
                materialInfo = (MaterialInfo) data.getSerializableExtra("materialInfo");
                materialConsunmeFragment.adddata(materialInfo);
                break;
            default:
                break;
        }
    }

    /**
     * RadioGroup点击CheckedChanged监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        AnimationSet _AnimationSet = new AnimationSet(true);
        TranslateAnimation _TranslateAnimation;

        if (checkedId == R.id.btn1) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            mImageView.startAnimation(_AnimationSet);//开始上面蓝色横条图片的动画切换
            mViewPager.setCurrentItem(0);//让下方ViewPager跟随上面的HorizontalScrollView切换
        }else if (checkedId == R.id.btn2) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo2), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(1);
        }else if (checkedId == R.id.btn3) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo3), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(2);
        }else if (checkedId == R.id.btn4) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo4), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(3);
        }else if (checkedId == R.id.btn5) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo5), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(4);
        }
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();//更新当前横条距离左边的距离
        mHorizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)getResources().getDimension(R.dimen.rdo2), 0);
    }

    /**
     * 获得当前被选中的RadioButton距离左侧的距离
     */
    private float getCurrentCheckedRadioLeft() {
        if (mRadioButton1.isChecked()) {
            return getResources().getDimension(R.dimen.rdo1);
        }else if (mRadioButton2.isChecked()) {
            return getResources().getDimension(R.dimen.rdo2);
        }else if (mRadioButton3.isChecked()) {
            return getResources().getDimension(R.dimen.rdo3);
        }else if (mRadioButton4.isChecked()) {
            return getResources().getDimension(R.dimen.rdo4);
        }else if (mRadioButton5.isChecked()) {
            return getResources().getDimension(R.dimen.rdo5);
        }
        return 0f;
    }

    private void iniListener() {
        mRadioGroup.setOnCheckedChangeListener(this);
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
    private class MyPagerOnPageChangeListener implements OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        /**
         * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                mViewPager.setCurrentItem(0);
                mRadioButton1.performClick();
            }else if (position == 1) {
                mRadioButton2.performClick();
            }else if (position == 2) {
                mRadioButton3.performClick();
            }else if (position == 3) {
                mRadioButton4.performClick();
            }else if (position == 4) {
                mRadioButton5.performClick();
            }
        }

    }

}
