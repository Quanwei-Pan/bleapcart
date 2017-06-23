package com.armaccelerator.bleapcart;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    public String ble_mac = "00:0E:0B:13:C5:B4";  //蓝牙BLE MAC
    public String hub_mac = "CC:1B:E0:E0:25:90";  //HUB MAC
    public String hub_ip  = null;  //HUB IP

    public static final int SHOW_RESPONSE = 0;

    private Button send_hub_mac,  send_ble_mac, disconnect_ble;
    private ImageButton send_ahead, send_back, send_left, send_right, send_stop;
    private TextView tx_response;
    private EditText editText;

    //新建Handler,接收Message更新TextView控件的内容
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

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            try {

                //HUB_MAC按键触发
                int VIBRATE_TIME = 60;
                if (send_hub_mac == view) {
                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String response = cassiaHub.discoverHub(hub_mac);
                            Message message = new Message();
                            message.what = SHOW_RESPONSE;
                            message.obj = response;
                            handler.sendMessage(message);
                            hub_ip = response;
                        }
                    }).start();
                }

                //按键触发
                if (send_ble_mac == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    ble_mac = editText.getText().toString();
                    if(ble_mac.equals(""))
                    {
                        ble_mac = "00:0E:0B:13:C5:B4";
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String response = cassiaHub.connectBle(ble_mac,hub_ip);
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("BLE连接状态：%s",response), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();
                }

                //按键触发
                if (disconnect_ble == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = cassiaHub.disconnectBle(ble_mac,hub_ip);
                            if(result.equals("OK")) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(MainActivity.this, "连接断开", Toast.LENGTH_SHORT);
                                toast.show();
                                Looper.loop();
                            }
                        }
                    }).start();
                }
                if (send_ahead == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int index = cassiaHub.sendCommand(ble_mac,hub_ip,"ahead");
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("方向：%d",index), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();

                }
                if (send_back == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int index = cassiaHub.sendCommand(ble_mac,hub_ip,"back");
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("方向：%d",index), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();

                }
                if (send_left == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int index = cassiaHub.sendCommand(ble_mac,hub_ip,"left");
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("方向：%d",index), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();
                }
                if (send_right == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int index = cassiaHub.sendCommand(ble_mac,hub_ip,"right");
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("方向：%d",index), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();

                }
                if (send_stop == view) {

                    vibrateHelp.vSimple(view.getContext(), VIBRATE_TIME);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int index = cassiaHub.sendCommand(ble_mac,hub_ip,"stop");
                            Looper.prepare();
                            Toast toast = Toast.makeText(MainActivity.this, String.format("方向：%d",index), Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    }).start();

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
        disconnect_ble = (Button) this.findViewById(R.id.discon_ble_mac_button);
        send_ahead = (ImageButton) this.findViewById(R.id.ahead);
        send_back = (ImageButton) this.findViewById(R.id.back);
        send_left = (ImageButton) this.findViewById(R.id.left);
        send_right = (ImageButton) this.findViewById(R.id.right);
        send_stop = (ImageButton) this.findViewById(R.id.stop);

        send_hub_mac.setOnClickListener(listener);
        send_ble_mac.setOnClickListener(listener);
        disconnect_ble.setOnClickListener(listener);
        send_ahead.setOnClickListener(listener);
        send_back.setOnClickListener(listener);
        send_left.setOnClickListener(listener);
        send_right.setOnClickListener(listener);
        send_stop.setOnClickListener(listener);

        tx_response = (TextView)findViewById(R.id.tx_response_s);

        editText = (EditText) findViewById(R.id.ble_mac);
    }
}
