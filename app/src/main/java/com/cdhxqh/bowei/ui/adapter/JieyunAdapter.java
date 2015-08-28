package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/25.
 */
public class JieyunAdapter extends RecyclerView.Adapter<JieyunAdapter.ViewHolder> {
    Context mContext;
    ArrayList<MaterialInfo> list=new ArrayList<MaterialInfo>();
    public JieyunAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_jieyun, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.number.setText(list.get(position).getNumber());
        holder.name.setText(list.get(position).getName());
        holder.size.setText(list.get(position).getSize()+"");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(mContext,ServeDetailActivity.class);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**物料编号**/
        public TextView number;
        /**物料名称**/
        public TextView name;
        /**物料数量**/
        public TextView size;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.jieyun_list_content);
            number = (TextView) itemView.findViewById(R.id.jieyun_number);
            name = (TextView) itemView.findViewById(R.id.jieyun_name);
            size = (TextView) itemView.findViewById(R.id.jieyun_size);
        }
    }

    public void update(ArrayList<MaterialInfo> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MaterialInfo materialInfo = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == materialInfo) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(materialInfo);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
