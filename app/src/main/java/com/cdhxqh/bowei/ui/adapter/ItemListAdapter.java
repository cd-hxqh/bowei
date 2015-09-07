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
import com.cdhxqh.bowei.ui.activity.ItemChooseListActivity;
import com.cdhxqh.bowei.ui.activity.MaintenanceActivity;
import com.cdhxqh.bowei.ui.activity.MaintenanceDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    Context mContext;
    ItemChooseListActivity activity;
    ArrayList<String> list=new ArrayList<String>();
    public ItemListAdapter(Context context, ItemChooseListActivity activity){
        this.mContext = context;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_choose, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**内容**/
        public TextView textView;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_choose_content);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public void update(ArrayList<String> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == string) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(string);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
