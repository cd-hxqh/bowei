package com.cdhxqh.bowei.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cdhxqh.bowei.Dao.AlndomainDao;
import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.bean.Alndomain;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;
import com.cdhxqh.bowei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 基本信息下载页面
 * Created by think on 2015/9/6.
 */
public class DownloadFragment extends Fragment {
    private static final String TAG = "DownloadFragment";
    private Button locations, asset, workdw, workzy, worktype, acworktype, failurecode, failurelist,
            jobplan, jobtask, jobmaterial, erson, download_all, alndomain;
    private ProgressDialog mProgressDialog;
    private int downloading_item;
    private int downedCount;
    private boolean isall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download_fragment, container, false);
        locations = (Button) view.findViewById(R.id.locations);
        asset = (Button) view.findViewById(R.id.asset);
        workdw = (Button) view.findViewById(R.id.workdw);
        workzy = (Button) view.findViewById(R.id.workzy);
        worktype = (Button) view.findViewById(R.id.worktype);
        acworktype = (Button) view.findViewById(R.id.acworktype);
        failurecode = (Button) view.findViewById(R.id.failurecode);
        failurelist = (Button) view.findViewById(R.id.failurelist);
        jobplan = (Button) view.findViewById(R.id.jobplan);
        jobtask = (Button) view.findViewById(R.id.jobtask);
        jobmaterial = (Button) view.findViewById(R.id.jobmaterial);
        erson = (Button) view.findViewById(R.id.erson);
        download_all = (Button) view.findViewById(R.id.download_all);
        alndomain = (Button) view.findViewById(R.id.alndomain);

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
        return view;
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
                mProgressDialog = ProgressDialog.show(getActivity(), null,
                        getString(R.string.requesting), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
            }
        }
        HttpManager.getData(getActivity(), url, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (jsonObject.getString("errmsg").equals(getResources().getString(R.string.request_ok))) {
                        button.setText(getResources().getString(R.string.downloaded));
                        String result = jsonObject.getString("result");
                        if (url == Constants.LOCATIONS) {
                            JsonUtils.parsingLocations(getActivity(), result);
                        } else if (url == Constants.ASSET) {
                            JsonUtils.parsingAsset(getActivity(), result);
                        } else if (url == Constants.WORKDW) {
                            JsonUtils.parsingWorkdw(getActivity(), result);
                        } else if (url == Constants.WORKZY) {
                            JsonUtils.parsingWorkzy(getActivity(), result);
                        } else if (url == Constants.WORKTYPE) {
                            JsonUtils.parsingWorkType(getActivity(), result);
                        } else if (url == Constants.ACWORKTYPE) {
                            JsonUtils.parsingAcWorkType(getActivity(), result);
                        } else if (url == Constants.FAILURECODE) {
                            JsonUtils.parsingFailurecode(getActivity(), result);
                        } else if (url == Constants.FAILURELIST) {
                            JsonUtils.parsingFailureList(getActivity(), result);
                        } else if (url == Constants.JOBPLAN) {
                            JsonUtils.parsingJobPlan(getActivity(), result);
                        } else if (url == Constants.JOBTASK) {
                            JsonUtils.parsingJobTask(getActivity(), result);
                        } else if (url == Constants.JOBMATERIAL) {
                            JsonUtils.parsingJobMaterial(getActivity(), result);
                        } else if (url == Constants.PERSON) {
                            JsonUtils.parsingErson(getActivity(), result);
                        } else if(url == Constants.ALNDOMAIN){
                            JsonUtils.parsingAlndomain(getActivity(),result);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                downedCount++;
                if(isall==false||(isall==true&&downedCount==13)){
                    if(mProgressDialog.isShowing()){
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
                if(isall==false||(isall==true&&downedCount==13)){
                    if(mProgressDialog.isShowing()){
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
