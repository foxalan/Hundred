package com.example.alan.hundred.activity.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Function : 利用URL读取网络资源
 * Author : Alan
 * Modify Date : 13/9/17
 * Issue : TODO
 * Whether solve :
 */

public class UrlActivity extends BaseActivity {

    private String str_url;

    private EditText et_url;
    private Button bt_sure;
    private ImageView iv_url;

    private URL url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_url;
    }

    @Override
    public void initViews() {

        et_url = (EditText) findViewById(R.id.et_url);
        bt_sure = (Button) findViewById(R.id.bt_sure);
        iv_url = (ImageView) findViewById(R.id.iv_url);
    }

    @Override
    public void initData() {
        str_url = "http://www.crazyit.org/attachments/month_1008/20100812_7763e970f822325bfb019ELQVym8tW3A.png";
    }

    @Override
    public void initEvents() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    url = new URL(str_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);

                    InputStream inputStream = url.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    iv_url.setImageBitmap(bitmap);
                    inputStream.close();
                } catch (MalformedURLException e) {
                    Log.d("TANG","ERROR_1");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("TANG","ERROR_2");
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
