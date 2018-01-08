package com.example.alan.hundred.activity.network;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.alan.hundred.R;
import com.example.alan.hundred.util.ShareUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BreakDownloadActivity extends Activity {

    private ProgressBar progressBar;
    private Button bt_start;
    private Button bt_pause;
    private String str = "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg" ;
    private HttpURLConnection conn;
    private RandomAccessFile targetFile;
    private String targetlocation = "/mnt/sdcard/pic.jpg";
    private int currentLength;
    private InputStream inputStream;
    private static final String TAG = "tang";

    private int length;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x123:

                    double v = currentLength * 1.0 / length * 1.0;

                    Log.d("TANG","currentLength:"+currentLength+"  length:"+length);

                    int d = (int) (v * 100);
                    progressBar.setProgress(d);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaking_download);

        initLength();
        initViews();
        initEvent();
    }

    private void initLength() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                getLength();
            }
        }.start();
    }

    private void initEvent() {

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connect = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = connect.getActiveNetworkInfo();
                if ((info == null)||!info.isConnected()){
                    Toast.makeText(BreakDownloadActivity.this,"请先检查网络",Toast.LENGTH_SHORT).show();
                    return;
                }

                startDownload();
            }
        });

        bt_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseDownload();
            }
        });

    }

    private void pauseDownload() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                if (conn!=null){

                    conn.disconnect();
                    ShareUtils.putInt(BreakDownloadActivity.this,"point_finish",currentLength);
                }


            }
        }.start();


    }

    private void startDownload() {

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    URL url = new URL(str);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setRequestProperty("Range","bytes=" + ShareUtils.getInt(BreakDownloadActivity.this,"point_finish",0) +"-"+ length);
                    Log.d("TANG","current:"+currentLength+"  length:"+length);
                    conn.connect();

                    int code = conn.getResponseCode();

                    if (code == 206){

                        Log.d("TANG",length+"===============");
                        targetFile = new RandomAccessFile(targetlocation,"rw");
                        targetFile.setLength(length);

                        targetFile.seek(currentLength);


                        Log.d(TAG,currentLength+"-----------------------------------");

                        inputStream = conn.getInputStream();

                    //    skipFully(inputStream,currentLength);
                        byte[] bytes = new byte[512];
                        int len  = 0 ;
                        while ((len = inputStream.read(bytes))!=-1){
                            targetFile.write(bytes,0,len);
                            currentLength = currentLength + len;

                            Message message = new Message();
                            message.what = 0x123;
                            mHandler.sendMessageDelayed(message,50);

                        }
                        inputStream.close();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }

    private void initViews() {

        progressBar = (ProgressBar) findViewById(R.id.pb_break);
        bt_start = (Button) findViewById(R.id.bt_start_download);
        bt_pause = (Button) findViewById(R.id.bt_pause_download);

    }

    public void skipFully(InputStream in, long bytes) throws IOException {
        long remainning = bytes;
        long len = 0;
        while (remainning > 0) {

            len = in.skip(remainning);
            remainning -= len;
        }

    }

    private void getLength() {
        HttpURLConnection connection = null;
        try {
            //连接网络
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);

            if (connection.getResponseCode() == 200) {//网络连接成功
                //获得文件长度
                length = connection.getContentLength();

            }
            if (length <= 0) {
                //连接失败
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
