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
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.bean.OrderTask;
import com.cdhxqh.bowei.ui.activity.KnowKedgeDetailsActivity;
import com.cdhxqh.bowei.ui.activity.OrderTaskActivity;
import com.cdhxqh.bowei.ui.activity.TaskDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 * 知识库适配器
 */
public class KnowKedgeAdapter extends RecyclerView.Adapter<KnowKedgeAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Knowledge> list = new ArrayList<Knowledge>();

    public KnowKedgeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.konw_kedge_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Knowledge knowledge=list.get(position);
        holder.number.setText(knowledge.getKnowledgeid() + "");
        holder.desc.setText(knowledge.getKnowdesc());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("Knowledge",knowledge);
                intent.setClass(mContext, KnowKedgeDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });

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
         * 编号*
         */
        public TextView number;
        /**
         * 描述*
         */
        public TextView desc;

        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.content_id);
            number = (TextView) itemView.findViewById(R.id.number_id);
            desc = (TextView) itemView.findViewById(R.id.desc_id);
        }
    }

    public void update(ArrayList<Knowledge> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Knowledge knowledge = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == knowledge) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(knowledge);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
