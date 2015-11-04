package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.ChooseItem;
import com.cdhxqh.bowei.ui.activity.ItemChooseListActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    Context mContext;
    ItemChooseListActivity activity;
    ArrayList<ChooseItem> list=new ArrayList<ChooseItem>();
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
        holder.name.setText(list.get(position).getName());
        holder.value.setText(list.get(position).getValue());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity.requestCode==2){
                    activity.responseData(list.get(position).getValue(),list.get(position).getParent());
                }
                if(activity.requestCode==9||activity.requestCode==12||activity.requestCode==13
                        ||activity.requestCode==14||activity.requestCode==16){
                    activity.responseData(list.get(position).getValue(),list.get(position).getParent());
                }else {
                    activity.responseData(list.get(position).getValue());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout relativeLayout;
        /**内容**/
        public TextView name;
        public TextView value;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (LinearLayout) itemView.findViewById(R.id.item_choose_content);
            name = (TextView) itemView.findViewById(R.id.item_name);
            value = (TextView) itemView.findViewById(R.id.item_value);
        }
    }

    public void update(ArrayList<ChooseItem> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ChooseItem chooseItem = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == chooseItem) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(chooseItem);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
