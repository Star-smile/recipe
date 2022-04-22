package com.recipe.application.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;

/**
 * 设置每个单元知识点的卡片长宽
 */
public class SquareCarView extends CardView {
    public SquareCarView(Context context) {
        this(context, null);
    }

    public SquareCarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置每个知识点卡片图案的高宽
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        //高度和宽度一样
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize/4*3, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
