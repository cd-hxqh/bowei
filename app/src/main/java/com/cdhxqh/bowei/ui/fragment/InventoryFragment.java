package com.cdhxqh.bowei.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.activity.In_Company_Activity;

/**
 * 库存查询*
 */
public class InventoryFragment extends Fragment {

    /**
     * 公司库存*
     */
    private LinearLayout c_linearlayout;
    /**
     * 捷运库存*
     */
    private LinearLayout m_linearlayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        c_linearlayout = (LinearLayout) view.findViewById(R.id.inventory_company);
        m_linearlayout = (LinearLayout) view.findViewById(R.id.inventory_mtn);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        c_linearlayout.setOnClickListener(c_linearlayoutOnClickListener);
        m_linearlayout.setOnClickListener(m_linearlayoutOnClickListener);
    }

    private View.OnClickListener c_linearlayoutOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),In_Company_Activity.class);
            startActivityForResult(intent,0);
        }
    };


    private View.OnClickListener m_linearlayoutOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
