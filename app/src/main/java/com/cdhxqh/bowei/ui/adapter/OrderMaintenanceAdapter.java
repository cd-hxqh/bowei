package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.ui.activity.OrderListActivity;
import com.cdhxqh.bowei.ui.activity.MaintenanceDetailActivity;
import com.cdhxqh.bowei.ui.activity.ServeDetailActivity;
import com.cdhxqh.bowei.ui.activity.ServiceDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class OrderMaintenanceAdapter extends RecyclerView.Adapter<OrderMaintenanceAdapter.ViewHolder> {
    Context mContext;
    OrderListActivity activity;
    ArrayList<OrderMain> list=new ArrayList<OrderMain>();
    public OrderMaintenanceAdapter(Context context,OrderListActivity activity){
        this.mContext = context;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_main_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.number.setText(list.get(position).getNumber()+"");
        holder.describe.setText(list.get(position).getDescribe());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.imageView.getVisibility()==View.VISIBLE){
                    Intent intent = new Intent();
                    intent.putExtra("ordermain", list.get(position));
                    if(activity.name.equals(activity.getResources().getString(R.string.maintenance))){
                        intent.setClass(mContext, MaintenanceDetailActivity.class);
                    }else if(activity.name.equals(activity.getResources().getString(R.string.maintenance))){
                        intent.setClass(mContext, ServeDetailActivity.class);
                    }else if(activity.name.equals(activity.getResources().getString(R.string.maintenance))){
                        intent.setClass(mContext, ServiceDetailActivity.class);
                    }
                    mContext.startActivity(intent);
                }
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
        public TextView number;
        /**描述**/
        public TextView describe;

        public ImageView imageView;

        public CheckBox checkBox;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.order_main_content);
            number = (TextView) itemView.findViewById(R.id.order_main_number);
            describe = (TextView) itemView.findViewById(R.id.order_main_describe);
            imageView = (ImageView) itemView.findViewById(R.id.order_main_in);
            checkBox = (CheckBox) itemView.findViewById(R.id.order_checkbox);
        }
    }

    public void update(ArrayList<OrderMain> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OrderMain orderMain = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == orderMain) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(orderMain);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
