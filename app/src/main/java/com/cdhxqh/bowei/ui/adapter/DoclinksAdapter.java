package com.cdhxqh.bowei.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Doclinks;
import com.cdhxqh.bowei.bean.OrderMain;
import com.cdhxqh.bowei.ui.activity.ServeDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 2015/8/17.
 */
public class DoclinksAdapter extends RecyclerView.Adapter<DoclinksAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Doclinks> list=new ArrayList<Doclinks>();
    public DoclinksAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doclinks_adpter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getDescription());
        String html = "<a href='" + list.get(position).getUrlname() + "'>" + list.get(position).getUrlname() + "</a>";//注意这里必须加上协议号，即http://。
        CharSequence path = Html.fromHtml(html);
        holder.path.setText(path);
        holder.path.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**名称**/
        public TextView name;
        /**地址**/
        public TextView path;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.order_main_content);
            name = (TextView) itemView.findViewById(R.id.document_name_text);
            path = (TextView) itemView.findViewById(R.id.document_path_text);
        }
    }

    public void update(ArrayList<Doclinks> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Doclinks doclinks = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == doclinks) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(doclinks);
            }
        }
        list = data;
        notifyDataSetChanged();
    }
}
