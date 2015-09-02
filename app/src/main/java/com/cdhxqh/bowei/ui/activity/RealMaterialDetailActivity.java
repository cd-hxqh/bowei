package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;

/**
 * Created by think on 2015/9/1.
 */
public class RealMaterialDetailActivity extends BaseActivity {
    private ImageView backimg;
    private TextView titlename;
    private MaterialInfo materialInfo;
    private TextView number;
    private TextView name;
    private TextView size;
    private TextView warehouse_below;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_material_info);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);
        number = (TextView) findViewById(R.id.material_number);
        name = (TextView) findViewById(R.id.material_name);
        size = (TextView) findViewById(R.id.material_size);
        warehouse_below = (TextView) findViewById(R.id.warehouse_below);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.real_material));
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        materialInfo = (MaterialInfo) getIntent().getExtras().getSerializable("materialInfo");
        number.setText(materialInfo.getNumber());
        name.setText(materialInfo.getName());
        size.setText(materialInfo.getSize()+"");
        warehouse_below.setText(materialInfo.getWarehouse());
    }
}
