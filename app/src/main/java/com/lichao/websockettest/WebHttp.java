package com.lichao.websockettest;

import android.util.Log;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

/**
 * 发送心跳，让服务器判断终端在线
 */
public class WebHttp {

    private static final String TAG = "WebHttp";

    /**
     * 服务器地址
     */
    private static String SERVER_IP = "http://106.12.134.56:8080/equipInfoApi/heartRegiSter?sessionId=1812060001&tenantName=Doumen";

    public static void HeartBeat() {
        try {
//            LogUtil.i(TAG, "HeartBeat JSON:" + new Gson().toJson(new HeartBeat(000, getDeviceId(), "终端连接成功")));
            OkHttpUtils.post()
                    .url(SERVER_IP)
                    .addParams("code", "000")
                    .addParams("deviceNum", "1812060001")
                    .addParams("msg", "连接成功,终端心跳")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "HeartBeat fail url:" + call.request().url());
                            Log.e(TAG, "HeartBeat fail body:" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i(TAG, "HeartBeat success" + response);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
