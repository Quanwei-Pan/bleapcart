package com.armaccelerator.bleapcart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class cassiaHub {

    public  static String discoverHub(String hubMac){

        String hubIp = null;
        String result = null;
        String requestUrl = "http://api.cassianetworks.com/cassia/hubs/deviceId";
        requestUrl = requestUrl.replace("deviceId",hubMac);

        try {
            result = sendHttpGet(requestUrl);
            JSONObject oJTObject = new JSONObject(result);
            hubIp = (String) oJTObject.get("ip");
        }catch (Exception e)
        {
        }
        return hubIp;
    }

    public  static String connectBle(String bleMac,String hubIp){

        String result = null;
        String requestUrl = "http://deviceIp/gap/nodes/nodeId/connection?mac=&chip=";
        requestUrl = requestUrl.replace("nodeId",bleMac);
        requestUrl = requestUrl.replace("deviceIp",hubIp);
        try {
            result = sendHttpPost(requestUrl);
        }catch (Exception e)
        {
        }
        return result;
    }

    public  static String disconnectBle(String bleMac,String hubIp){

        String result = null;
        String requestUrl = "http://deviceIp/gap/nodes/nodeId/connection?mac=";
        requestUrl = requestUrl.replace("nodeId",bleMac);
        requestUrl = requestUrl.replace("deviceIp",hubIp);
        try {
            result = sendHttpDelete(requestUrl);
        }catch (Exception e)
        {
        }
        return result;
    }

    public  static int sendCommand(String bleMac,String hubIp, String comm){
        String requestUrl = "http://deviceIp/gatt/nodes/nodeId/handle/16/value/comm_tx/?mac=";
        requestUrl = requestUrl.replace("deviceIp",hubIp);
        requestUrl = requestUrl.replace("nodeId",bleMac);
        switch (comm) {
            case "ahead":
                String ahead_tx = "24312c302c302c302c302c302c302c302c302c302324302c302c302c302c302c302c302c302c302c3023";
                try{
                    String response = sendHttpGet(requestUrl.replace("comm_tx",ahead_tx));
                    if (response != null)
                        return 0;
                    else
                        return -1;
                }catch (Exception e)
                {

                }
                break;
            case "back":
                String back_tx = "24322c302c302c302c302c302c302c302c302c302324302c302c302c302c302c302c302c302c302c3023";
                try{
                    String response = sendHttpGet(requestUrl.replace("comm_tx",back_tx));
                    if (response != null)
                        return 1;
                    else
                        return -1;
                }catch (Exception e)
                {

                }
                break;
            case "left":
                String left_tx = "24332c302c302c302c302c302c302c302c302c302324302c302c302c302c302c302c302c302c302c3023";
                try{
                    String response = sendHttpGet(requestUrl.replace("comm_tx",left_tx));
                    if (response != null)
                        return 2;
                    else
                        return -1;
                }catch (Exception e)
                {

                }
                break;
            case "right":
                String right_tx = "24342c302c302c302c302c302c302c302c302c302324302c302c302c302c302c302c302c302c302c3023";
                try{
                    String response = sendHttpGet(requestUrl.replace("comm_tx",right_tx));
                    if (response != null)
                        return 3;
                    else
                        return -1;
                }catch (Exception e)
                {

                }
                break;
            case "stop":
                String stop_tx = "24302c302c302c302c302c302c302c302c302c3023";
                try{
                    String response = sendHttpGet(requestUrl.replace("comm_tx",stop_tx));
                    if (response != null)
                        return 4;
                    else
                        return -1;
                }catch (Exception e)
                {

                }
                break;
            default:
                break;
        }
        return -1;
    }

    //HTTP GET方法
    public static  String sendHttpGet(String url) {
        String result = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                String response = EntityUtils.toString(entity, "utf-8"); //将entity当中的数据转换为字符串
                result = response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //HTTP POST方法
    public static  String sendHttpPost(String url) {

        String result = null;

        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("timeOut", "300");
            jsonObj.put("type", "random");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            HttpEntity requestEntity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(requestEntity);
            try {
                HttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(entity.getContent()));
                    result = reader.readLine();
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return result;
    }

    //HTTP DELETE方法
    public static  String sendHttpDelete(String url) {
        String result = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        try {
            HttpResponse response = httpClient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
