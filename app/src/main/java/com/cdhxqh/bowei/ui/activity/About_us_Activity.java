package com.cdhxqh.bowei.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.ui.widget.CumTextView;

public class About_us_Activity extends BaseActivity {

    /**
     * 返回按钮*
     */
    private ImageView backImage;

    /**
     * 标题*
     */
    private TextView titleText;

    /**
     * 公司介绍*
     */
    private CumTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImage = (ImageView) findViewById(R.id.info_title_img_left);
        titleText = (TextView) findViewById(R.id.info_title_name);

        textView = (CumTextView) findViewById(R.id.us_info_id);
    }

    @Override
    protected void initView() {
        backImage.setOnClickListener(backOnClickListener);
        titleText.setText(getString(R.string.about_us));
        textView.setText("     "+getResources().getString(R.string.about_us_text));
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
