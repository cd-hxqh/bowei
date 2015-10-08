package com.cdhxqh.bowei.ui.adapter;

import android.app.Activity;
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
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.ui.activity.OrderListActivity;
import com.cdhxqh.bowei.ui.activity.MaintenanceDetailActivity;
import com.cdhxqh.bowei.ui.activity.OrderSearchActivity;
import com.cdhxqh.bowei.ui.activity.ServeDetailActivity;
import com.cdhxqh.bowei.ui.activity.ServiceDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class OrderMaintenanceAdapter extends RecyclerView.Adapter<OrderMaintenanceAdapter.ViewHolder> {
    Context mContext;
    ArrayList<OrderMain> list = new ArrayList<OrderMain>();

    public OrderMaintenanceAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_main_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderMain orderMain = list.get(position);
        holder.number.setText(orderMain.getNumber() + "");
        holder.describe.setText(orderMain.getDescribe());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.imageView.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent();
                    intent.putExtra("ordermain", orderMain);
                    if (!orderMain.getWordtype().equals("")) {
                        if (orderMain.getWordtype().equals(Constants.MAINTENANCE_TYPE) || orderMain.getWordtype().equals(Constants.MAINTENANCE_TYPE1)) {
                            intent.setClass(mContext, MaintenanceDetailActivity.class);
                        } else if (orderMain.getWordtype().equals(Constants.SERVE_TYPE)) {
                            intent.setClass(mContext, ServeDetailActivity.class);
                        } else if (orderMain.getWordtype().equals(Constants.SERVICE_TYPE)) {
                            intent.setClass(mContext, ServiceDetailActivity.class);
                        }
                    } else {
                        intent.setClass(mContext, MaintenanceDetailActivity.class);
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
        /**
         * 编号*
         */
        public TextView number;
        /**
         * 描述*
         */
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

    public void adddate(ArrayList<OrderMain> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                list.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
