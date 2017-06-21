package com.armaccelerator.bleapcart;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class cassiaHub {

    public  static String discoverHub(String hubMac){
        String hubIp = null;
        String result = "00001";
        try {
            String requestUrl = "http://api.cassianetworks.com/cassia/hubs/CC:1B:E0:E0:25:90";
            URL realUrl = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200) {
                result = "GET成功";
            } else {
                result = "GET失败";
            }
            return  result;
        }catch (Exception e)
        {
            return  result;
        }
    }
}