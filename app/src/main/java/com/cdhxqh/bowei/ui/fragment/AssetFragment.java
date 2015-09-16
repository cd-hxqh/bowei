package com.cdhxqh.bowei.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.activity.In_Company_Activity;
import com.cdhxqh.bowei.ui.activity.In_Deptinventory_Activity;
import com.cdhxqh.bowei.ui.activity.MipcaActivityCapture;

/**
 * 资产fragment
 */
public class AssetFragment extends Fragment {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    /**二维码扫描**/
    private Button codeBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.asset_layout, container, false);
        codeBtn=(Button)view.findViewById(R.id.code_btn_id);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        codeBtn.setOnClickListener(codeBtnOnClickListener);
    }

    private View.OnClickListener codeBtnOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MipcaActivityCapture.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
        }
    };
}
