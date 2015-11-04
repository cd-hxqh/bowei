package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderServe;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.ui.activity.OrderTaskActivity;
import com.cdhxqh.bowei.ui.activity.ServeDetailActivity;
import com.cdhxqh.bowei.ui.activity.TaskDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by think on 2015/8/17.
 */
public class OrderTaskAdapter extends RecyclerView.Adapter<OrderTaskAdapter.ViewHolder> {
    Context mContext;
    OrderTaskActivity activity;
    ArrayList<OrderTask> list=new ArrayList<OrderTask>();
    public HashMap<Integer,OrderTask> checkedlist = new HashMap<>();
    public OrderTaskAdapter(Context context,OrderTaskActivity activity){
        this.mContext = context;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_task_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked&&!checkedlist.containsKey(position)){
                    checkedlist.put(position,list.get(position));
                }else if (!isChecked&&checkedlist.containsKey(position)){
                    checkedlist.remove(position);
                }
            }
        });
        if(activity.isMultiple){
            holder.imageView.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.checkBox.setVisibility(View.GONE);
        }

        if(checkedlist.size()>0){
            if(checkedlist.containsKey(position)){
                holder.checkBox.setChecked(true);
            }else {
                holder.checkBox.setChecked(false);
            }
        }
        holder.task.setText(list.get(position).getTask());
        holder.digest.setText(list.get(position).getDigest());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!activity.isMultiple) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, TaskDetailActivity.class);
                    intent.putExtra("orderTask", list.get(position));
                    intent.putExtra("isByserch",activity.orderMain.isByserch());
                    mContext.startActivity(intent);
                } else {
                    holder.checkBox.performClick();
                }
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(activity.orderMain.getId()!=0) {
                    activity.changeitem();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**编号**/
        public TextView task;
        /**描述**/
        public TextView digest;

        public ImageView imageView;

        public CheckBox checkBox;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.order_task_content);
            task = (TextView) itemView.findViewById(R.id.order_task_name);
            digest = (TextView) itemView.findViewById(R.id.order_task_digest);
            imageView = (ImageView) itemView.findViewById(R.id.task_main_in);
            checkBox = (CheckBox) itemView.findViewById(R.id.task_checkbox);
        }
    }

    public void update(ArrayList<OrderTask> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OrderTask orderServe = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == orderServe) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(orderServe);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
