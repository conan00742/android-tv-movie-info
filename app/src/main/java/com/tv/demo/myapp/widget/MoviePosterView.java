package com.tv.demo.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.tv.demo.myapp.Utils;

/**
 * Created by Krot on 5/29/18.
 */

public class MoviePosterView extends android.support.v7.widget.AppCompatImageView {

    private final int WIDTH = Utils.convertDpToPixcel(300);

    public MoviePosterView(Context context) {
        super(context);
    }

    public MoviePosterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoviePosterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = (WIDTH * 9) / 16;
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(WIDTH, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
