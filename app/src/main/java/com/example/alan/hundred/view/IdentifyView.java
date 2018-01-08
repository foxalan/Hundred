package com.example.alan.hundred.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.example.alan.hundred.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 24/7/17$
 * Input Parameter 自定义View
 */

public class IdentifyView extends View implements View.OnClickListener {

    private int text_color;
    private float text_size;
    private int text_background;

    private Paint paint;

    private String str_identify = "4396";


    public IdentifyView(Context context) {
        this(context, null);
    }

    public IdentifyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdentifyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IdentifyView, defStyleAttr, 0);

        text_color = typedArray.getColor(R.styleable.IdentifyView_identify_text_color, Color.BLACK);
        text_background = typedArray.getColor(R.styleable.IdentifyView_identify_text_background, Color.GREEN);
        text_size = typedArray.getDimension(R.styleable.IdentifyView_identify_text_size, 16);

        paint = new Paint();
        paint.setColor(text_color);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(text_size);

        setOnClickListener(this);

        setBackgroundColor(text_background);
    }

    public String getStr_identify() {
        return str_identify;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthSpec) {
            case MeasureSpec.EXACTLY:
                if (width < 300) {
                    width = 300;
                }
                break;
            case MeasureSpec.AT_MOST:
                width = 300;
                break;

        }

        switch (heightSpec) {
            case MeasureSpec.EXACTLY:
                if (height < 120) {
                    height = 120;
                }
                break;
            case MeasureSpec.AT_MOST:
                height = 120;
                break;

        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawText(text,0,0,getWidth(),getHeight(),paint);
    //    int x = (int) ((getWidth() - 2 * text_size) / 2);
    //    int y = (int) (text_size + (getHeight() - text_size) / 2);

        Rect mTextBound = new Rect();
        paint.setColor(text_color);
        paint.getTextBounds(str_identify, 0, str_identify.length(), mTextBound);

        Paint.FontMetrics fm = paint.getFontMetrics();

        int x = getWidth()/2- mTextBound.width()/2;
        int y = (int) (getHeight()/2 - (fm.ascent+fm.bottom)/2);

        canvas.drawText(str_identify, x, y, paint);

        drawPoint(canvas);

    }

    /**
     * 画点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        Random random = new Random();

        paint.setColor(Color.BLACK);
        for (int i = 0; i < 100; i++) {

            canvas.drawCircle(random.nextInt(getMeasuredWidth()), random.nextInt(getMeasuredHeight()), 1, paint);
        }

        paint.setColor(Color.BLUE);
        for(int i = 0; i< 15 ; i++) {

            canvas.drawLine(random.nextInt(getMeasuredWidth()), random.nextInt(getMeasuredWidth()),
                    random.nextInt(getMeasuredWidth()), random.nextInt(getMeasuredWidth()), paint);

        }

    }


    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }

    @Override
    public void onClick(View view) {
        String text = randomText();
        str_identify = text;
        postInvalidate();
        // invalidate();


    }
}
