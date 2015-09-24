package com.cdhxqh.bowei.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.activity.About_us_Activity;
import com.cdhxqh.bowei.ui.activity.MipcaActivityCapture;

/**
 * 设置fragment
 */
public class SettingFragment extends Fragment {
    /**
     * 数据下载*
     */
    RelativeLayout dataRelativeLayout;
    /**
     * 清除缓存*
     */
    RelativeLayout clearRelativeLayout;
    /**
     * 网络设置*
     */
    RelativeLayout workRelativeLayout;
    /**
     * 版本更新*
     */
    RelativeLayout updateRelativeLayout;
    /**
     * 关于我们*
     */
    RelativeLayout usRelativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setting_layout, container, false);
        findById(view);
        return view;
    }


    /**
     * 初始化界面控件*
     */
    private void findById(View view) {
        dataRelativeLayout = (RelativeLayout) view.findViewById(R.id.upload_data_id);
        clearRelativeLayout = (RelativeLayout) view.findViewById(R.id.clear_cache_id);
        workRelativeLayout = (RelativeLayout) view.findViewById(R.id.network_id);
        updateRelativeLayout = (RelativeLayout) view.findViewById(R.id.update_id);
        usRelativeLayout = (RelativeLayout) view.findViewById(R.id.about_us_id);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {
        usRelativeLayout.setOnClickListener(usRelativeLayoutOnClickListener);
    }


    private View.OnClickListener usRelativeLayoutOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),About_us_Activity.class);
            startActivity(intent);
        }
    };
}
