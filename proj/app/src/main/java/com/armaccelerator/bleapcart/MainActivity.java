package com.armaccelerator.bleapcart;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.armaccelerator.bleapcart.cassiaHub;
import org.json.JSONException;

import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity{

    public String ble_mac = null;  //蓝牙BLE MAC
    public String hub_mac = "CC:1B:E0:E0:25:90";  //HUB MAC
    public String hub_ip  = null;  //HUB IP
    public boolean hub_FLAG = false;

    private Button send_hub_mac,  send_ble_mac;

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                if (send_hub_mac == view) {
                    String result;
                    String requestUrl = "http://api.cassianetworks.com/cassia/hubs/CC:1B:E0:E0:25:90";
                    URL realUrl = new URL(requestUrl);
                    HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    sleep(3000);
                    if (connection.getResponseCode() == 200) {
                        result = "GET成功";
                    } else {
                        result = "GET失败";
                    }
                    Toast toast = Toast.makeText(MainActivity.this, String.format("IP：%s",result), Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (send_ble_mac == view) {
                    Toast toast = Toast.makeText(MainActivity.this, String.format("连接失败，请检查HUB MAC：%s",hub_ip), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }catch(Exception e){

            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_hub_mac= (Button) this.findViewById(R.id.send_hub_mac_button);
        send_ble_mac = (Button) this.findViewById(R.id.send_ble_mac_button);
        send_hub_mac.setOnClickListener(listener);
        send_ble_mac.setOnClickListener(listener);
    }
}
