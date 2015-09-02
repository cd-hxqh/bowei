package com.cdhxqh.bowei.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.MaterialInfo;

/**
 * Created by think on 2015/8/26.
 */
public class AddMaterialInfoActivity extends BaseActivity {
    private int requestCode;
    private ImageView backimg;
    private TextView titlename;
    private EditText number;
    private EditText name;
    private EditText size;
    private Button inputbtn;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materialinfo);

        requestCode = getIntent().getIntExtra("requestCode",0);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);

        number = (EditText) findViewById(R.id.material_info_number);
        name = (EditText) findViewById(R.id.material_info_name);
        size = (EditText) findViewById(R.id.material_info_size);
        inputbtn = (Button) findViewById(R.id.info_input);
    }

    @Override
    protected void initView() {
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMaterialInfoActivity.this.setResult(0);
                finish();
            }
        });
        if(requestCode==2){
            titlename.setText(getResources().getString(R.string.give_material_add_new));
        }else if(requestCode==3){
            titlename.setText(getResources().getString(R.string.consume_material_add_new));
        }else if(requestCode==4){
            titlename.setText(getResources().getString(R.string.jieyun_add_new));
        }else if(requestCode==5){
            titlename.setText(getResources().getString(R.string.material_consume_add_new));
        }

        inputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                MaterialInfo materialInfo = new MaterialInfo();
                materialInfo.setNumber(number.getText().toString());
                materialInfo.setName(name.getText().toString());
                materialInfo.setSize(Integer.parseInt(size.getText().toString()));
                intent.putExtra("materialInfo", materialInfo);
                AddMaterialInfoActivity.this.setResult(requestCode,intent);
                finish();
            }
        });
    }
}
