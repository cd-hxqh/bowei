package com.cdhxqh.bowei.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.activity.OrderListActivity;
import com.cdhxqh.bowei.ui.activity.ServeActivity;
import com.cdhxqh.bowei.ui.activity.ServiceActivity;

/**
 * Created by think on 2015/8/17.
 */
public class OrderFragment extends Fragment {
    private LinearLayout maintenance;
    private LinearLayout serve;
    private LinearLayout service;
    private Intent intent;//工单跳转

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_frament, container, false);

        maintenance = (LinearLayout) view.findViewById(R.id.maintenance);
        serve = (LinearLayout) view.findViewById(R.id.serve);
        service = (LinearLayout) view.findViewById(R.id.service);
        maintenance.setOnClickListener(buttonclick);
        serve.setOnClickListener(buttonclick);
        service.setOnClickListener(buttonclick);
        return view;
    }

    private View.OnClickListener buttonclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent();
            intent.setClass(getActivity(),OrderListActivity.class);
            switch (v.getId()){
                case R.id.maintenance://维保工单
                    intent.putExtra("ordername",getResources().getString(R.string.maintenance));
                    startActivity(intent);
                    break;
                case R.id.serve://维护工单
                    intent.putExtra("ordername", getResources().getString(R.string.serve));
                    startActivity(intent);
                    break;
                case R.id.service://服务工单
                    intent.putExtra("ordername", getResources().getString(R.string.service));
                    startActivity(intent);
                    break;
            }
        }
    };
}
