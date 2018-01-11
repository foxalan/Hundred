package com.example.alan.hundred.activity.storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.alan.hundred.Config.Config;
import com.example.alan.hundred.R;
import com.example.alan.hundred.activity.BaseHomeActivity;
import com.example.alan.hundred.util.L;
import com.example.alan.hundred.util.ShareUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Function :
 * Author : Alan
 * Modify Date : 14/10/17
 * Issue : TODO
 * Whether solve :
 * 　　1.文件
 * 　　2.Share
 * 3.database
 */

public class StorageActivity extends BaseHomeActivity implements GestureDetector.OnGestureListener {

    private int count;
    private EditText et_share;

    private EditText et_file;
    private String content;
    private String FILE_NAME = "crazyit.bin";

    private String current_content;
    private InputStream inputStream;
    private ImageView iv_assets;

    private GestureDetector detector;

    private float currentScale = 1.0f;
    private Matrix matrix;
    private Bitmap bitmap;

    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detector = new GestureDetector(this, this);
        matrix = new Matrix();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_storage;
    }

    @Override
    public void initViews() {
        et_share = (EditText) findViewById(R.id.et_share);
        et_file = (EditText) findViewById(R.id.et_file);
        iv_assets = (ImageView) findViewById(R.id.iv_assets);
    }

    @Override
    public void initData() {
        count = ShareUtils.getInt(this, Config.APP_USE_COUNT, 0);

        InputStream abpath = getClass().getResourceAsStream("/assets/bg/land_bg.jpg");
        bitmap = BitmapFactory.decodeStream(abpath);
        if (bitmap != null) {
            iv_assets.setImageBitmap(bitmap);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
    }

    @Override
    public void initEvents() {

        current_content = readToFile();
        et_file.setText(current_content);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_share:
                et_share.setText("APP USE TIME :" + count);
                break;
            case R.id.bt_file:
                content = et_file.getText().toString();
                writeToFile(content);
                break;
            default:
                break;
        }
    }

    public void writeToFile(String content) {
        try {
            FileOutputStream outputStream = openFileOutput(FILE_NAME, MODE_APPEND);
            PrintStream ps = new PrintStream(outputStream);
            ps.print(content);
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readToFile() {
        String content = "";

        try {
            FileInputStream inputStream = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer("");
            int hasread = 0;

            while ((hasread = inputStream.read(buff)) != -1) {
                stringBuffer.append(new String(buff, 0, hasread));
            }

            content = stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return content;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    /**
     * 按下
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        L.e("onDown");
        return false;
    }

    /**
     * 长按
     *
     * @param e
     * @return
     */
    @Override
    public void onLongPress(MotionEvent e) {
        L.e("onLongPress");
    }

    /**
     * 手指在屏上按下,而且并未移动和松开时
     *
     * @param e
     * @return
     */
    @Override
    public void onShowPress(MotionEvent e) {

    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }


    /**
     * 拖动
     *
     * @param e1
     * @param e2
     * @param velocityX 　速度
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        velocityX = velocityX > 4000 ? 4000 : velocityX;
        velocityX = velocityX < -4000 ? -4000 : velocityX;

        currentScale = currentScale + velocityX / 4000;
        currentScale = currentScale > 0.01 ? currentScale : 0.01f;

        matrix.reset();
        matrix.setScale(currentScale, currentScale, 200, 200);

        //    Drawable bitmap = iv_assets.getDrawable();

        BitmapDrawable tmp = (BitmapDrawable) iv_assets.getDrawable();

//        if (!tmp.getBitmap().isRecycled()) {
//            tmp.getBitmap().recycle();
//        }

        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        iv_assets.setImageBitmap(bitmap1);


        return false;
    }
}
