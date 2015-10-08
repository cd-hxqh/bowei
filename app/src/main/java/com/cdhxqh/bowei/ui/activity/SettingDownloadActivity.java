package com.cdhxqh.bowei.ui.activity;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/9/25.
 */
public class SettingDownloadActivity extends BaseActivity {
    private static final String TAG = "SettingDownloadActivity";

    private ImageView backimg;
    private TextView titlename;
    private Button locations, asset, workdw, workzy, worktype, acworktype, failurecode, failurelist,
            jobplan, jobtask, jobmaterial, erson, download_all, alndomain;
    private ProgressDialog mProgressDialog;
    private int downloading_item;
    private int downedCount;
    private boolean isall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_dowmload_activity);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backimg = (ImageView) findViewById(R.id.info_title_img_left);
        titlename = (TextView) findViewById(R.id.info_title_name);
        locations = (Button) findViewById(R.id.locations);
        asset = (Button) findViewById(R.id.asset);
        workdw = (Button) findViewById(R.id.workdw);
        workzy = (Button) findViewById(R.id.workzy);
        worktype = (Button) findViewById(R.id.worktype);
        acworktype = (Button) findViewById(R.id.acworktype);
        failurecode = (Button) findViewById(R.id.failurecode);
        failurelist = (Button) findViewById(R.id.failurelist);
        jobplan = (Button) findViewById(R.id.jobplan);
        jobtask = (Button) findViewById(R.id.jobtask);
        jobmaterial = (Button) findViewById(R.id.jobmaterial);
        erson = (Button) findViewById(R.id.erson);
        download_all = (Button) findViewById(R.id.download_all);
        alndomain = (Button) findViewById(R.id.alndomain);

    }

    @Override
    protected void initView() {
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlename.setText(getResources().getString(R.string.download));
        locations.setOnClickListener(buttonclick);
        asset.setOnClickListener(buttonclick);
        workdw.setOnClickListener(buttonclick);
        workzy.setOnClickListener(buttonclick);
        worktype.setOnClickListener(buttonclick);
        acworktype.setOnClickListener(buttonclick);
        failurecode.setOnClickListener(buttonclick);
        failurelist.setOnClickListener(buttonclick);
        jobplan.setOnClickListener(buttonclick);
        jobtask.setOnClickListener(buttonclick);
        jobmaterial.setOnClickListener(buttonclick);
        erson.setOnClickListener(buttonclick);
        download_all.setOnClickListener(buttonclick);
        alndomain.setOnClickListener(buttonclick);
    }

    private View.OnClickListener buttonclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.locations://位置
                    downloaddata(Constants.LOCATIONS, (Button) v);
                    locations.setText(getResources().getString(R.string.downloading));
                    downloading_item = 1;
                    break;
                case R.id.asset://资产
                    downloaddata(Constants.ASSET, (Button) v);
                    asset.setText(getResources().getString(R.string.downloading));
                    downloading_item = 2;
                    break;
                case R.id.workdw://单位
                    downloaddata(Constants.WORKDW, (Button) v);
                    workdw.setText(getResources().getString(R.string.downloading));
                    downloading_item = 3;
                    break;
                case R.id.workzy://专业
                    downloaddata(Constants.WORKZY, (Button) v);
                    workzy.setText(getResources().getString(R.string.downloading));
                    downloading_item = 4;
                    break;
                case R.id.worktype://工作类型
                    downloaddata(Constants.WORKTYPE, (Button) v);
                    worktype.setText(getResources().getString(R.string.downloading));
                    downloading_item = 5;
                    break;
                case R.id.acworktype://实际工作类型
                    downloaddata(Constants.ACWORKTYPE, (Button) v);
                    acworktype.setText(getResources().getString(R.string.downloading));
                    downloading_item = 6;
                    break;
                case R.id.failurecode://故障代码
                    downloaddata(Constants.FAILURECODE, (Button) v);
                    failurecode.setText(getResources().getString(R.string.downloading));
                    downloading_item = 7;
                    break;
                case R.id.failurelist://故障代码关系
                    downloaddata(Constants.FAILURELIST, (Button) v);
                    failurelist.setText(getResources().getString(R.string.downloading));
                    downloading_item = 8;
                    break;
                case R.id.jobplan://所有工作计划
                    downloaddata(Constants.JOBPLAN, (Button) v);
                    jobplan.setText(getResources().getString(R.string.downloading));
                    downloading_item = 9;
                    break;
                case R.id.jobtask://所有工作计划任务
                    downloaddata(Constants.JOBTASK, (Button) v);
                    jobtask.setText(getResources().getString(R.string.downloading));
                    downloading_item = 10;
                    break;
                case R.id.jobmaterial://专业工作计划物料
                    downloaddata(Constants.JOBMATERIAL, (Button) v);
                    jobmaterial.setText(getResources().getString(R.string.downloading));
                    downloading_item = 11;
                    break;
                case R.id.erson://工作班组
                    downloaddata(Constants.PERSON, (Button) v);
                    erson.setText(getResources().getString(R.string.downloading));
                    downloading_item = 12;
                    break;
                case R.id.download_all://全部下载
                    DownloadAll();
                    downloading_item = 13;
                    break;
                case R.id.alndomain:
                    downloaddata(Constants.ALNDOMAIN, (Button) v);
                    alndomain.setText(getResources().getString(R.string.downloading));
                    downloading_item = 14;
                    break;
            }
        }
    };

    private void downloaddata(final String url, final Button button) {

        if(isall==false||(isall==true&&downedCount==0)){
            if(mProgressDialog==null) {
                mProgressDialog = ProgressDialog.show(this, null,
                        getString(R.string.requesting), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
            }
        }
        HttpManager.getData(this, url, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        button.setText(getResources().getString(R.string.downloaded));
                        String result = jsonObject.getString("result");
                        if (url == Constants.LOCATIONS) {
                            JsonUtils.parsingLocations(SettingDownloadActivity.this, result);
                        } else if (url == Constants.ASSET) {
                            JsonUtils.parsingAsset(SettingDownloadActivity.this, result);
                        } else if (url == Constants.WORKDW) {
                            JsonUtils.parsingWorkdw(SettingDownloadActivity.this, result);
                        } else if (url == Constants.WORKZY) {
                            JsonUtils.parsingWorkzy(SettingDownloadActivity.this, result);
                        } else if (url == Constants.WORKTYPE) {
                            JsonUtils.parsingWorkType(SettingDownloadActivity.this, result);
                        } else if (url == Constants.ACWORKTYPE) {
                            JsonUtils.parsingAcWorkType(SettingDownloadActivity.this, result);
                        } else if (url == Constants.FAILURECODE) {
                            JsonUtils.parsingFailurecode(SettingDownloadActivity.this, result);
                        } else if (url == Constants.FAILURELIST) {
                            JsonUtils.parsingFailureList(SettingDownloadActivity.this, result);
                        } else if (url == Constants.JOBPLAN) {
                            JsonUtils.parsingJobPlan(SettingDownloadActivity.this, result);
                        } else if (url == Constants.JOBTASK) {
                            JsonUtils.parsingJobTask(SettingDownloadActivity.this, result);
                        } else if (url == Constants.JOBMATERIAL) {
                            JsonUtils.parsingJobMaterial(SettingDownloadActivity.this, result);
                        } else if (url == Constants.PERSON) {
                            JsonUtils.parsingErson(SettingDownloadActivity.this, result);
                        } else if (url == Constants.ALNDOMAIN) {
                            JsonUtils.parsingAlndomain(SettingDownloadActivity.this, result);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                downedCount++;
                if (isall == false || (isall == true && downedCount == 13)) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        mProgressDialog = null;
                        isall = false;
                        downedCount = 0;
                    }
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {
                downedCount++;
                if (isall == false || (isall == true && downedCount == 13)) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        mProgressDialog = null;
                        isall = false;
                        downedCount = 0;
                    }
                }
                button.setText(getResources().getString(R.string.download_fail));
//                if(url==Constants.LOCATIONS){
//                    locations.setText(getResources().getString(R.string.download_fail));
//                }
            }
        });
    }

    private void DownloadAll() {
        isall = true;
        downedCount = 0;
        locations.performClick();
        asset.performClick();
        workdw.performClick();
        workzy.performClick();
        worktype.performClick();
        acworktype.performClick();
        failurecode.performClick();
        failurelist.performClick();
        jobplan.performClick();
        jobtask.performClick();
        jobmaterial.performClick();
        erson.performClick();
        alndomain.performClick();
    }
}
