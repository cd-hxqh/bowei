package com.cdhxqh.bowei.application;

import android.app.Application;

import com.cdhxqh.bowei.config.Constants;
import com.cdhxqh.bowei.webserviceclient.AndroidClientService;

/**
 * Created by think on 2015/8/11.
 */
public class BaseApplication extends Application {
    private static BaseApplication mContext;

    public AndroidClientService getWsService() {
        return new AndroidClientService(Constants.getWsUrl(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

}
