package com.example.aws_iotcore_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    MqttAndroidClient mqttAndroidClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //クライアントの作成
        mqttAndroidClient = new MqttAndroidClient(this, "192.168.11.9", "piyo");
        try {
            mqttAndroidClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }



    }


    @Override
    protected void onPause() {
        //アプリがバックグラウンドに移動したことを示すために呼び出される。
        //この段階ではアプリキル = Activityが消滅するとは限らない。
        super.onPause();
        try {
            Log.e("192.168.11.9", "publish!");
            mqttAndroidClient.publish("topic/hoge", "hello world".getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}