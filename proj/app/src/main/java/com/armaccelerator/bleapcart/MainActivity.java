package com.armaccelerator.bleapcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{
    public String ble_mac = null;  //蓝牙BLE MAC
    public String hub_mac = "CC:1B:E0:E0:25:90";  //HUB MAC
    public String hub_ip  = null;  //HUB IP
    public boolean hub_FLAG = false;

    private Button send_hub_mac,  send_ble_mac;
    private ImageButton send_ahead, send_back, send_left, send_right, send_stop;
    private TextView tx_response;

    private final int VIBRATE_TIME = 60;

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                if (send_hub_mac == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                    String result = null;
                    String requestUrl = "http://api.cassianetworks.com/cassia/hubs/CC:1B:E0:E0:25:90";
                    result = httpRequest.sendRequestWithHttpClient(requestUrl);
                    Toast toast = Toast.makeText(MainActivity.this, String.format(""), Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (send_ble_mac == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                    Toast toast = Toast.makeText(MainActivity.this, String.format("连接失败，请检查HUB MAC：%s",hub_ip), Toast.LENGTH_SHORT);
                    toast.show();
                }
               if (send_ahead == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                }
                if (send_back == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                }
                if (send_left == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                }
                if (send_right == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                }
                if (send_stop == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

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
        send_ahead = (ImageButton) this.findViewById(R.id.ahead);
        send_back = (ImageButton) this.findViewById(R.id.back);
        send_left = (ImageButton) this.findViewById(R.id.left);
        send_right = (ImageButton) this.findViewById(R.id.right);
        send_stop = (ImageButton) this.findViewById(R.id.stop);
        send_hub_mac.setOnClickListener(listener);
        send_ble_mac.setOnClickListener(listener);
        send_ahead.setOnClickListener(listener);
        send_back.setOnClickListener(listener);
        send_left.setOnClickListener(listener);
        send_right.setOnClickListener(listener);
        send_stop.setOnClickListener(listener);
        tx_response = (TextView)findViewById(R.id.tx_response_s);
    }
}
