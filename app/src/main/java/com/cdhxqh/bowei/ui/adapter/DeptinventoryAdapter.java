package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Deptinventory;
import com.cdhxqh.bowei.bean.Inventory;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 * 捷运适配器
 */
public class DeptinventoryAdapter extends RecyclerView.Adapter<DeptinventoryAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Deptinventory> list = new ArrayList<Deptinventory>();

    public DeptinventoryAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Deptinventory inventory=list.get(position);
        holder.number.setText(inventory.getItemnum() + "");
        holder.desc.setText(inventory.getBjmc());
        holder.locationdesc.setText(inventory.getLocation());
        holder.curbaltotal.setText(inventory.getCurbaltotal());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * 背景布局*
         */
        public RelativeLayout relativeLayout;
        /**
         * 物料编号*
         */
        public TextView number;
        /**
         * 物料名称
         */
        public TextView desc;
        /**库房**/
        public TextView locationdesc;
        /**数量**/
        public TextView curbaltotal;

        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.content_id);
            number = (TextView) itemView.findViewById(R.id.item_num_id);
            desc = (TextView) itemView.findViewById(R.id.item_name_id);
            locationdesc = (TextView) itemView.findViewById(R.id.locationdesc_id);
            curbaltotal = (TextView) itemView.findViewById(R.id.curbaltotal_id);
        }
    }

    public void update(ArrayList<Deptinventory> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Deptinventory inventory = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == inventory) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(inventory);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
