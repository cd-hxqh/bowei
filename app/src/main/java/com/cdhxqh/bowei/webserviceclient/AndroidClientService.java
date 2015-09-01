package com.cdhxqh.bowei.webserviceclient;



import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by think on 2015/8/11.
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public String NAMESPACE = "http://www.ibm.com/maximo/wsdl/CUWO";
    public String url = "http://182.92.8.94:7001/meaweb/wsdl/cuwo.wsdl";
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

    public String InsertWO(String s) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "InsertWO");
        soapReq.addProperty("json", s);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
//        Log.i(TAG, soapEnvelope.bodyIn.toString());
        try {
            Object retObj = soapEnvelope.getResponse();
            return retObj.toString()+"sss";
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return "xxx";
    }
}
