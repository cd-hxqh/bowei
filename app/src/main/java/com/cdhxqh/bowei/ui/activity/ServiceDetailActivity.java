package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.OrderService;
import com.cdhxqh.bowei.ui.widget.OrderMorePopuowindow;

/**
 * Created by think on 2015/8/18.
 */
public class ServiceDetailActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private ImageView moreimg;
    private OrderService orderService;
    private EditText number;
    private EditText describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_detail_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.order_title_img_left);
        titlename = (TextView) findViewById(R.id.order_title_name);
        moreimg = (ImageView) findViewById(R.id.order_title_img_right);
        number = (EditText) findViewById(R.id.order_detail_number);
        describe = (EditText) findViewById(R.id.order_detail_describe);
    }

    @Override
    protected void initView() {
        getData();
        number.setText(orderService.getNumber() + "");
        describe.setText(orderService.getDescribe());
        titlename.setText(getResources().getString(R.string.service));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moreimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderMorePopuowindow orderMorePopuowindow = new OrderMorePopuowindow(ServiceDetailActivity.this);
                orderMorePopuowindow.showPopupWindow(moreimg);
            }
        });
    }

    private void getData(){
        orderService = (OrderService) getIntent().getSerializableExtra("orderservice");
    }
}
