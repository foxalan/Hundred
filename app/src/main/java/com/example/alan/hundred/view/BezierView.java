package com.example.alan.hundred.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alan on 8/3/17.
 */

public class BezierView extends View {

    private int mWidth;
    private int mHeight;

    private int pointStart_X;
    private int pointStart_Y;

    private int pointEnd_X;
    private int pointEnd_Y;

    private int point_X;
    private int point_Y;

    private int pointBezier_X;
    private int pointBezier_Y;

    private Paint paint_line;
    private Paint paint_bezier;

    private float i = 0.0f;
    private boolean isDraw = false;

    private List<Point> pointList = new ArrayList<>();
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {

                invalidate();
            }
        }
    };

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setFocusable(true);

        initPaints();
    }

    /**
     * 初始化画笔
     */
    private void initPaints() {

        paint_line = new Paint();
        paint_line.setColor(Color.RED);
        paint_line.setStrokeWidth(5);
        paint_line.setAntiAlias(true);
        paint_line.setStyle(Paint.Style.STROKE);

        paint_bezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_bezier.setColor(Color.YELLOW);
        paint_bezier.setStrokeWidth(100);
        paint_bezier.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = Math.min(mWidth, mHeight);
        mHeight = mWidth;

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        pointStart_X = w / 4;
        pointStart_Y = h / 2;

        pointEnd_X = w * 3 / 4;
        pointEnd_Y = h / 2;

        point_X = w / 2;
        point_Y = h / 4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(pointStart_X, pointStart_Y, point_X, point_Y, paint_line);
        canvas.drawLine(point_X, point_Y, pointEnd_X, pointEnd_Y, paint_line);

        Path path = new Path();
        path.moveTo(pointStart_X,pointStart_Y);
        path.quadTo(point_X,point_Y,pointEnd_X,pointEnd_Y);
        path.close();
        canvas.drawPath(path,paint_line);
        while (isDraw) {

            drawBezier(canvas, pointList);
        }

    }

    private void drawBezier(Canvas canvas, List<Point> pointList) {
        for (int i = 0; i < pointList.size(); i++) {
            Log.d("TANG", pointList.get(i).x + ":" + pointList.get(i).y);
            canvas.drawPoint(pointList.get(i).x, pointList.get(i).y, paint_bezier);

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        point_Y = (int) event.getY();
        point_X = (int) event.getX();

        invalidate();

        return super.onTouchEvent(event);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_A:
//                Log.d("TANG", "drawbezier");
//                isDraw = true;
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//
//                        pointBezier_X = (int) ((1 - i) * (1 - i) * pointStart_X + 2 * i * (1 - i) * point_X + i * i * pointEnd_X);
//                        pointBezier_Y = (int) ((1 - i) * (1 - i) * pointStart_Y + 2 * i * (1 - i) * point_Y + i * i * pointEnd_Y);
//                        Point point = new Point();
//                        point.x = pointBezier_X;
//                        point.y = pointBezier_Y;
//                        pointList.add(point);
//                        i = i + 0.01f;
//                        mHandler.sendEmptyMessage(0X123);
//
//                    }
//                }, 0, 1000);
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
