package com.zdd.assistant.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Project Name: MyAssistant
 * File Name:    ResizeRelativeLayout.java
 * ClassName:    ResizeRelativeLayout
 *
 * Description: 自定义RelativeLayout
 *
 * @author ZDD
 * @date 2017年02月16日 14:28
 */
public class ResizeRelativeLayout extends RelativeLayout
{

    public ResizeRelativeLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ResizeRelativeLayout(Context context)
    {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int childWidthSize = getMeasuredWidth();
        // 宽度
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        //重设高度
        heightMeasureSpec = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
