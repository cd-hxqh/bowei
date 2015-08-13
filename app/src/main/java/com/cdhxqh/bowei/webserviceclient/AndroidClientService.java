package com.cdhxqh.bowei.webserviceclient;

/**
 * Created by think on 2015/8/11.
 */
public class AndroidClientService {
    public String NAMESPACE = "";
    public String url = "";
    public int timeOut = 60000;

    public AndroidClientService() {
    }

    public AndroidClientService(String url) {
        this.url = url;
    }

    public void setTimeOut(int seconds) {
        this.timeOut = seconds * 1000;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
