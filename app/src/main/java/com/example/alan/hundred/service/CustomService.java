package com.example.alan.hundred.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.alan.hundred.MainActivity;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Function :
 * Author : Alan
 * Modify Date : 20/9/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomService extends IntentService {

    private String url = "https://seller.kuaimayiliao.com/sellertest/order/fc";

    private Timer timer;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public CustomService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                requestData();
            }
        },3000,3000);

    }

    private void requestData() {
        HttpParams params = new HttpParams();
        params.put("mid", "1704100050");
        params.put("t", "15");

        RxVolley.post(url, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Log.d("TANG", t);
                parsingJson(t);
            }
        });
    }

    private void parsingJson(String t) {

        try {
            JSONObject object = new JSONObject(t);
            int type = (int) object.get("t");
         //   MainActivity.mHandler.sendEmptyMessage(0x123);
            switch (type) {
                case -1:

                    break;
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
