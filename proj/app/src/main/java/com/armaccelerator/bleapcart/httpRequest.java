package com.armaccelerator.bleapcart;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class httpRequest {

    public static final int SHOW_RESPONSE = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    tx_response.setText(response);
                    break;
                default:
                    break;
            }
        }
    };

    //发送网络请求，开启线程
    public static  String sendRequestWithHttpClient(final String url) {
        final String[] result = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                //用HttpClient发送请求
                HttpClient httpCient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8"); //将entity当中的数据转换为字符串
                        result[0] = response.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();//这个start()方法不要忘记了
        return result[0];
    }
}
