package com.lichao.websockettest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dhh.websocket.RxWebSocket;
import com.dhh.websocket.WebSocketSubscriber;

import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    private MyApplication application;
    private ReConSocketHeartBeatThread reConSocketHeartBeatThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (MyApplication) getApplication();

        RxWebSocket.get("ws://61.154.164.33:5060/wx?sessionId=Test0001")
                .subscribe(new WebSocketSubscriber() {
                    @Override
                    protected void onOpen(@NonNull WebSocket webSocket) {
                        super.onOpen(webSocket);
                        Log.i(TAG, "onOpen:");
                    }

                    @Override
                    protected void onMessage(@NonNull String text) {
                        super.onMessage(text);
                        LogUtil.i(TAG, "onMessage:" + text);
                    }

                    @Override
                    protected void onReconnect() {
                        super.onReconnect();
                        Log.i(TAG, "onReconnect:");
                    }

                    @Override
                    protected void onClose() {
                        super.onClose();
                        Log.i(TAG, "onClose:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.i(TAG, "onError:");
                    }
                });

        if (reConSocketHeartBeatThread == null) {
            reConSocketHeartBeatThread = new ReConSocketHeartBeatThread();
            reConSocketHeartBeatThread.start();
        }
    }



    public class ReConSocketHeartBeatThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    // 设备连接后，定时（10秒）发送心跳信息
                    WebHttp.HeartBeat();
                    sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
