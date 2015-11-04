package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Doclinks;
import com.cdhxqh.bowei.bean.Knowledge;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.ui.adapter.DoclinksAdapter;
import com.cdhxqh.bowei.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 文档名称*
     */
    private TextView nameText;

    /**
     * 知识大类*
     */
    private TextView dlText;

    /**
     * 知识小类*
     */
    private TextView xlText;

    /**
     * 备注*
     */
    private TextView bzText;

    private Knowledge knowledge;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    DoclinksAdapter doclinksAdapter;

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
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.info_title_img_left);
        titleText = (TextView) findViewById(R.id.info_title_name);

        numberText = (TextView) findViewById(R.id.knowledge_number_id);
        descText = (TextView) findViewById(R.id.knowledge_desc_id);

        dlText = (TextView) findViewById(R.id.knowledge_document_dl_id);
        xlText = (TextView) findViewById(R.id.knowledge_document_xl_id);
        bzText = (TextView) findViewById(R.id.knowledge_document_bz_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backOnClickListener);
        titleText.setText(getString(R.string.knowkedge_details_text));
        numberText.setText(knowledge.getKnowbh());
        descText.setText(knowledge.getKnowdesc());
        dlText.setText(knowledge.getKnowdl());
        xlText.setText(knowledge.getKnowxl());
        bzText.setText(knowledge.getKnowbz());

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        doclinksAdapter = new DoclinksAdapter(this);
        recyclerView.setAdapter(doclinksAdapter);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 创建进度条*
     */
    private void createProgressDialog() {
        mProgressDialog = ProgressDialog.show(KnowKedgeDetailsActivity.this, null, getString(R.string.loading_text), true, true);
    }

    /**
     * 关闭进度条
     */
    private void colseProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    /**
     * 获取文件路径数据*
     */
    private void getHttpData() {

        HttpManager.getDataInfo(KnowKedgeDetailsActivity.this, Constants.GETKNOW_PATH(knowledge.getKnowledgeid() + ""), new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                colseProgressDialog();
                if (!data.equals("")) {
                    ArrayList<Doclinks> doclinksList = JsonUtils.parsingDoclinks(KnowKedgeDetailsActivity.this, data);
                    doclinksAdapter.update(doclinksList,true);
//                    if(doclinksList.size()==1){
//                        nameText.setText(doclinksList.get(0).getDescription());
//                        String html = "<a href='" + doclinksList.get(0).getUrlname() + "'>" + doclinksList.get(0).getUrlname() + "</a>";//注意这里必须加上协议号，即http://。
//                        CharSequence path = Html.fromHtml(html);
//                        pathText.setText(path);
//                        pathText.setMovementMethod(LinkMovementMethod.getInstance());
//                    }

                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                colseProgressDialog();
            }

            @Override
            public void onFailure(String error) {
                colseProgressDialog();
            }
        });
    }
}
