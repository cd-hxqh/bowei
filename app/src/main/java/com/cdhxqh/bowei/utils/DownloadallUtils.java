package com.cdhxqh.bowei.utils;

import android.content.Context;
import android.widget.Button;

import com.cdhxqh.bowei.R;
import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.manager.HttpManager;
import com.cdhxqh.bowei.manager.HttpRequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by think on 2015/10/15.
 */
public class DownloadallUtils {

    public void downloadall(final Context cxt, final List<String> url, final List<Button> button){
        for(int i = 0;i < url.size();i ++){
            final int finalI = i;
            final int finalI1 = i;
            HttpManager.getData(cxt, url.get(finalI), new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(data);
                        if (jsonObject.getString("errmsg").equals(cxt.getResources().getString(R.string.request_ok))) {
                            button.get(finalI1).setText(cxt.getResources().getString(R.string.downloaded));
                            String result = jsonObject.getString("result");
                            if (url.get(finalI) == Constants.LOCATIONS) {
                                JsonUtils.parsingLocations(cxt, result);
                            } else if (url.get(finalI) == Constants.ASSET) {
                                JsonUtils.parsingAsset(cxt, result);
                            } else if (url.get(finalI) == Constants.WORKDW) {
                                JsonUtils.parsingWorkdw(cxt, result);
                            } else if (url.get(finalI) == Constants.WORKZY) {
                                JsonUtils.parsingWorkzy(cxt, result);
                            } else if (url.get(finalI) == Constants.WORKTYPE) {
                                JsonUtils.parsingWorkType(cxt, result);
                            } else if (url.get(finalI) == Constants.ACWORKTYPE) {
                                JsonUtils.parsingAcWorkType(cxt, result);
                            } else if (url.get(finalI) == Constants.FAILURECODE) {
                                JsonUtils.parsingFailurecode(cxt, result);
                            } else if (url.get(finalI) == Constants.FAILURELIST) {
                                JsonUtils.parsingFailureList(cxt, result);
                            } else if (url.get(finalI) == Constants.JOBPLAN) {
                                JsonUtils.parsingJobPlan(cxt, result);
                            } else if (url.get(finalI) == Constants.JOBTASK) {
                                JsonUtils.parsingJobTask(cxt, result);
                            } else if (url.get(finalI) == Constants.JOBMATERIAL) {
                                JsonUtils.parsingJobMaterial(cxt, result);
                            } else if (url.get(finalI) == Constants.PERSON) {
                                JsonUtils.parsingErson(cxt, result);
                            } else if (url.get(finalI) == Constants.ALNDOMAIN) {
                                JsonUtils.parsingAlndomain(cxt, result);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {
                }

                @Override
                public void onFailure(String error) {
                    button.get(finalI1).setText(cxt.getResources().getString(R.string.download_fail));
                }
            });

//            HttpManager.getData(cxt, url, new HttpRequestHandler<String>() {
//                @Override
//                public void onSuccess(String data) {
//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(data);
//                        if (jsonObject.getString("errmsg").equals(cxt.getResources().getString(R.string.request_ok))) {
//                            button.setText(cxt.getResources().getString(R.string.downloaded));
//                            String result = jsonObject.getString("result");
//                            if (url == Constants.LOCATIONS) {
//                                JsonUtils.parsingLocations(cxt, result);
//                            } else if (url == Constants.ASSET) {
//                                JsonUtils.parsingAsset(cxt, result);
//                            } else if (url == Constants.WORKDW) {
//                                JsonUtils.parsingWorkdw(cxt, result);
//                            } else if (url == Constants.WORKZY) {
//                                JsonUtils.parsingWorkzy(cxt, result);
//                            } else if (url == Constants.WORKTYPE) {
//                                JsonUtils.parsingWorkType(cxt, result);
//                            } else if (url == Constants.ACWORKTYPE) {
//                                JsonUtils.parsingAcWorkType(cxt, result);
//                            } else if (url == Constants.FAILURECODE) {
//                                JsonUtils.parsingFailurecode(cxt, result);
//                            } else if (url == Constants.FAILURELIST) {
//                                JsonUtils.parsingFailureList(cxt, result);
//                            } else if (url == Constants.JOBPLAN) {
//                                JsonUtils.parsingJobPlan(cxt, result);
//                            } else if (url == Constants.JOBTASK) {
//                                JsonUtils.parsingJobTask(cxt, result);
//                            } else if (url == Constants.JOBMATERIAL) {
//                                JsonUtils.parsingJobMaterial(cxt, result);
//                            } else if (url == Constants.PERSON) {
//                                JsonUtils.parsingErson(cxt, result);
//                            } else if (url == Constants.ALNDOMAIN) {
//                                JsonUtils.parsingAlndomain(cxt, result);
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onSuccess(String data, int totalPages, int currentPage) {
//                }
//
//                @Override
//                public void onFailure(String error) {
//                    button.setText(cxt.getResources().getString(R.string.download_fail));
//                }
//            });
        }

    }
}
