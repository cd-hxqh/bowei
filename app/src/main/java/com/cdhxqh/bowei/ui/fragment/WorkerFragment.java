package com.cdhxqh.bowei.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.ui.adapter.WorkerInfoAdapter;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/25.
 */
public class WorkerFragment extends Fragment {
    private RecyclerView recyclerView;
    private WorkerInfoAdapter workerInfoAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_1, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView = (RecyclerView) view.findViewById(R.id.worker_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        workerInfoAdapter = new WorkerInfoAdapter(getActivity());
        recyclerView.setAdapter(workerInfoAdapter);
        addData();
        return view;
    }

    private void addData() {
        ArrayList<WorkerInfo> list = new ArrayList<WorkerInfo>();
        for (int i = 0; i < 3; i++) {
            WorkerInfo workerInfo = new WorkerInfo();
            workerInfo.setNumber(100+i);
            workerInfo.setName("李丽");
            list.add(i,workerInfo);
        }
        workerInfoAdapter.update(list, true);
    }
    public void adddata(WorkerInfo workerInfo){
        ArrayList<WorkerInfo> list = new ArrayList<WorkerInfo>();
        list.add(0,workerInfo);
        workerInfoAdapter.update(list, true);
    }
}
