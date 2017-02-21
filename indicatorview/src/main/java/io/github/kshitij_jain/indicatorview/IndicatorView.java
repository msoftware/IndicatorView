package io.github.kshitij_jain.indicatorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by flash on 21/2/17.
 */

public class IndicatorView extends View {

    private Context mContext;
    private Paint mActiveIndicatorPaint;
    private Paint mInactiveIndicatorPaint;
    private int mRadius;
    private int mSize;
    private int mPosition;
    private int mIndicatorsCount;

    public IndicatorView(Context context) {
        super(context);
        init(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mActiveIndicatorPaint = new Paint();
        mActiveIndicatorPaint.setColor(ContextCompat.getColor(context, R.color.active_indicator));
        mActiveIndicatorPaint.setAntiAlias(true);
        mInactiveIndicatorPaint = new Paint();
        mInactiveIndicatorPaint.setColor(ContextCompat.getColor(context, R.color.inactive_indicator));
        mInactiveIndicatorPaint.setAntiAlias(true);
        mRadius = getResources().getDimensionPixelSize(R.dimen.indicator_size);
        mSize = mRadius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mIndicatorsCount; i++) {
            canvas.drawCircle(mRadius + (mSize * i), mRadius, mRadius / 2, mInactiveIndicatorPaint);
        }
        canvas.drawCircle(mRadius + (mSize * mPosition), mRadius, mRadius / 2, mActiveIndicatorPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = mSize * mIndicatorsCount;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * mRadius + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}