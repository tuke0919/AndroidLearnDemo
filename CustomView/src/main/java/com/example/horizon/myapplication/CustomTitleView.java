package com.example.horizon.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 Created by liming on 2016/4/13.
 */
public class CustomTitleView extends View {

    //用于存储自定属性的值
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    //控制View的绘制
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context)
    {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        //获得我们所定义的自定义样式属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = typedArray.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor1:
                    // 默认颜色设置为黑色
                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();//记得回收


        //获得绘制文本的宽和高
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        //设置监听事件
        this.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTitleText = randomText();
                postInvalidate();
            }

        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //从MeasureSpec中获取Mode和Size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        //依据模式获取预设的width和height
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        //殊途同归，传递我们自定义的数据
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

        //绘制噪点
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth((float)5.0);
        float X = getMeasuredWidth();
        float Y = getMeasuredHeight();
        float[] pts = new float[100];
        for(int i=1;i<=60;i++){
            if(i%2==0)
            pts[i] = (float)(Math.random()*X);
            else
            pts[i] = (float) (Math.random()*Y);
        }
        canvas.drawPoints(pts,mPaint);
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

}

