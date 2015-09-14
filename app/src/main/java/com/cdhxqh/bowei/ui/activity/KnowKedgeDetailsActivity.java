package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Knowledge;

/**
 * 知识库详情*
 */
public class KnowKedgeDetailsActivity extends BaseActivity {
    private static final String TAG = "KnowKedgeDetailsActivity";
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 标题*
     */
    private TextView titleText;

    /**
     * 编号*
     */
    private TextView numberText;
    /**
     * 描述*
     */
    private TextView descText;

    /**文档名称**/
    private TextView nameText;

    /**
     * 文档路径*
     */
    private TextView pathText;

    private Knowledge knowledge;


    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_kedge_details);
        getInitData();
        findViewById();
        initView();
        createProgressDialog();
        getHttpData();
    }




    /**
     * 获取数据*
     */
    private void getInitData() {
        knowledge = (Knowledge) getIntent().getSerializableExtra("Knowledge");
        Log.i(TAG, "knowledge=" + knowledge);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.info_title_img_left);
        titleText = (TextView) findViewById(R.id.info_title_name);

        numberText = (TextView) findViewById(R.id.knowledge_number_id);
        descText = (TextView) findViewById(R.id.knowledge_desc_id);
        nameText=(TextView)findViewById(R.id.knowledge_document_name_id);
        pathText = (TextView) findViewById(R.id.knowledge_path_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backOnClickListener);
        titleText.setText(getString(R.string.knowkedge_details_text));
        numberText.setText(knowledge.getKnowledgeid()+"");
        descText.setText(knowledge.getKnowdesc());
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**创建进度条**/
    private void createProgressDialog(){
        mProgressDialog = ProgressDialog.show(KnowKedgeDetailsActivity.this, null,getString(R.string.loading_text), true, true);
    }


    /**获取文件路径数据**/
    private void getHttpData() {


    }
}