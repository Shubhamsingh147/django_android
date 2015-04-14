package com.example.shubham.django_normal;

import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import java.util.List;

/**
 * Created by shubham on 30/3/15.
 */
public class ServiceHandler extends AsyncTask<String, Void, String>{


    static String response = null;
    public static final int GET = 1;
    public static final int POST = 2;

    public ServiceHandler(){

    }
    @Override
    protected String doInBackground(String... params) {
        return this.makeServiceCall(params[0],1,null);
    }

    public String makeServiceCall(String url, int method, List<NameValuePair> params){
        try {
            //http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            if (method == POST){

                HttpPost  httpPost = new HttpPost(url);
                if(params != null)
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpResponse = httpClient.execute(httpPost);

            }else if (method == GET){

                if (params != null){
                    String paramString = URLEncodedUtils.format(params,"utf-8");
                    url+="?" + paramString;
                }
                HttpGet httpGet= new HttpGet(url);
                Log.d("Url hit:",url);
                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            Log.d("response",response);
            //jsonArray = new JSONArray(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}