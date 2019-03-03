package com.lichao.websockettest;

import android.app.Application;
import android.content.Context;

import com.dhh.websocket.Config;
import com.dhh.websocket.RxWebSocket;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ChaoLi on 2019/3/3 0003 - 21:08
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MyApplication extends Application {

    private static Context context;
    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        context = getApplicationContext();

        Config config = new Config.Builder()
                .setShowLog(true, "webSocketLog")
                .setClient(new OkHttpClient.Builder()
                        .pingInterval(10, TimeUnit.SECONDS)// 设置心跳间隔，这个是10秒检测一次
                        .build())
                .setReconnectInterval(10, TimeUnit.SECONDS)// 设置重连
                .build();
        RxWebSocket.setConfig(config);
    }
}
