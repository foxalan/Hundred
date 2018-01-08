package com.example.alan.hundred.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Function :
 * @Author : Alan
 * Modify Date : 20/9/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CustomService(String name) {
        super(name);
    }

    //    private String url = "https://seller.kuaimayiliao.com/sellertest/order/fc";
//
//    private Timer timer;
//
//    /**
//     * Creates an IntentService.  Invoked by your subclass's constructor.
//     */
//    public CustomService() {
//        super("MyIntentService");
//    }
//
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                requestData();
//            }
//        },3000,3000);

    }

//    private void requestData() {
//        HttpParams params = new HttpParams();
//        params.put("mid", "1704100050");
//        params.put("t", "15");
//
//        RxVolley.post(url, params, new HttpCallback() {
//            @Override
//            public void onSuccess(String t) {
//                super.onSuccess(t);
//                Log.d("TANG", t);
//                parsingJson(t);
//            }
//        });
//    }

    private void parsingJson(String t) {

        try {
            JSONObject object = new JSONObject(t);
            int type = (int) object.get("t");

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
                default:
                    break;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
