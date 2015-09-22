package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.WorkerInfo;
import com.cdhxqh.bowei.ui.activity.WorkerInfoActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/25.
 */
public class WorkerInfoAdapter extends RecyclerView.Adapter<WorkerInfoAdapter.ViewHolder> {
    Context mContext;
    ArrayList<WorkerInfo> list=new ArrayList<WorkerInfo>();
    public WorkerInfoAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_worker_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.number.setText(list.get(position).getNumber());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("workinfo",list.get(position));
                intent.setClass(mContext,WorkerInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**员工编号**/
        public TextView number;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.worker_info_list_content);
            number = (TextView) itemView.findViewById(R.id.worker_info_number);
        }
    }

    public void update(ArrayList<WorkerInfo> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                WorkerInfo workerInfo = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == workerInfo) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(workerInfo);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
