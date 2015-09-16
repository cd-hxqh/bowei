package com.cdhxqh.bowei.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.activity.MaintenanceDetailActivity;
import com.cdhxqh.bowei.ui.activity.OrderTaskActivity;
import com.cdhxqh.bowei.ui.activity.MaintenanceRealInfoActivity;
import com.cdhxqh.bowei.ui.activity.ServeRealInfoActivity;

/**
 * Created by think on 2015/8/20.
 */
public class OrderMorePopuowindow extends PopupWindow {
    private View conentView;

    public OrderMorePopuowindow(final Activity context, final String fromname, final int id) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_order_more, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        LinearLayout ordertask = (LinearLayout) conentView
                .findViewById(R.id.order_pop_task);
        LinearLayout realinfo = (LinearLayout) conentView
                .findViewById(R.id.order_pop_real_info);
        ordertask.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                OrderMorePopuowindow.this.dismiss();
                Intent intent = new Intent(context, OrderTaskActivity.class);
                intent.putExtra("fromname", fromname);
                intent.putExtra("orderid", id);
                context.startActivity(intent);

            }
        });

        realinfo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                OrderMorePopuowindow.this.dismiss();
                if (fromname.equals(context.getResources().getString(R.string.maintenance))) {
                    Intent intent = new Intent(context, MaintenanceRealInfoActivity.class);
                    intent.putExtra("orderid", id);
                    context.startActivity(intent);
                }else if(fromname.equals(context.getResources().getString(R.string.serve))){
                    Intent intent = new Intent(context, ServeRealInfoActivity.class);
                    intent.putExtra("orderid", id);
                    context.startActivity(intent);
                }else if(fromname.equals(context.getResources().getString(R.string.service))){
                    Intent intent = new Intent(context, MaintenanceRealInfoActivity.class);
                    intent.putExtra("orderid", id);
                    context.startActivity(intent);
                }
            }
        });
    }



    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            this.dismiss();
        }
    }

}
