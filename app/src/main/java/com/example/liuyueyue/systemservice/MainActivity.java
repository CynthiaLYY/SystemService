package com.example.liuyueyue.systemservice;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main,null);
        setContentView(view);
    }
    public void doClick(View v){
        switch (v.getId()){
            case R.id.network:
                if(isNetWorkConnected(MainActivity.this)==true){
                    Toast.makeText(MainActivity.this,"网络已经打开",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"网络未连接",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wifi:
                WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(WIFI_SERVICE);
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this,"wifi已经关闭",Toast.LENGTH_SHORT).show();
                }else{
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this,"wifi已经打开",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.voice:
                AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
                int  max = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int  current = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                Toast.makeText(MainActivity.this,"系统最大音量为"+max,Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"系统当前音量为"+current,Toast.LENGTH_SHORT).show();
                break;
            case R.id.getPackag:
               ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(ACTIVITY_SERVICE);
                String packageName = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(MainActivity.this,"当前运行的activity包名："+packageName,Toast.LENGTH_SHORT).show();

                break;
        }
    }
    public boolean isNetWorkConnected(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null){
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
