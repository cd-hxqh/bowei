package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.bean.OrderServe;
import com.cdhxqh.bowei.ui.activity.MaintenanceDetailActivity;
import com.cdhxqh.bowei.ui.activity.ServeDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class OrderServeAdapter extends RecyclerView.Adapter<OrderServeAdapter.ViewHolder> {
    Context mContext;
    ArrayList<OrderServe> list=new ArrayList<OrderServe>();
    public OrderServeAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_main_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.number.setText(list.get(position).getNumber()+"");
        holder.describe.setText(list.get(position).getDescribe());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("orderserve",list.get(position));
                intent.setClass(mContext,ServeDetailActivity.class);
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
        /**编号**/
        public TextView number;
        /**描述**/
        public TextView describe;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.order_main_content);
            number = (TextView) itemView.findViewById(R.id.order_main_number);
            describe = (TextView) itemView.findViewById(R.id.order_main_describe);
        }
    }

    public void update(ArrayList<OrderServe> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OrderServe orderServe = list.get(i);
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
