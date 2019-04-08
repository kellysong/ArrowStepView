package com.sjl.stepview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sjl.stepview.R;
import com.sjl.stepview.util.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 高仿鹏涛app的公交卡充值步骤条
 *
 * @author Kelly
 * @version 1.0.0
 * @filename ArrowStepView.java
 * @time 2019/4/1 11:03
 * @copyright(C) 2019 song
 */
public class ArrowStepView extends View {
    private final List<String> mSteps = new ArrayList<>();

    private static final int START_STEP = 1;
    /**
     * 指示器数量,最大4个，建议3个
     */
    private static final int MAX_STEP = 4;


    private int mCurrentStep = START_STEP;

    private Paint mPaint;
    private int mWidth, mHeight;
    /**
     * 指示器宽度
     */
    private int mStepWidth;

    /**
     * 指示器高度
     */
    private int mStepHeight;
    /**
     * 指示器三角形高度
     */
    private int mTriangleWidth;

    /**
     * 文字和左边数字距离
     */
    private int mTextMarginLeft;

    /**
     * 标题文本大小
     */
    private int mTextSize;

    /**
     * 圆圈文字大小
     */
    private int mCircleTextSize;

    /**
     * 圆圈文字颜色
     */
    private int mTextColor;


    /**
     * 圆圈半径
     */
    private int mCircleRadius;


    /**
     * 默认背景选中颜色
     */
    private int defaultSelectColor;

    /**
     * 当前选中背景颜色
     */
    private int currentSelectColor;


    public ArrowStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ArrowStepView);
        mCircleRadius = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_circle_radius, ViewUtils.dip2px(context, 6));
        mCircleTextSize = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_circle_text_size, ViewUtils.sp2px(context, 10));

        mTextSize = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_title_size, ViewUtils.dip2px(context, 12));
        mTextColor = ta.getColor(R.styleable.ArrowStepView_asv_text_color, Color.parseColor("#5B5C5C"));

        mStepHeight = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_height, ViewUtils.dip2px(context, 38));
        mTriangleWidth = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_triangle_width, ViewUtils.dip2px(context, 15));
        mTextMarginLeft = ta.getDimensionPixelSize(R.styleable.ArrowStepView_asv_text_margin_left, ViewUtils.dip2px(context, 8));

        defaultSelectColor = ta.getColor(R.styleable.ArrowStepView_asv_default_select_color, Color.parseColor("#63BCFF"));
        currentSelectColor = ta.getColor(R.styleable.ArrowStepView_asv_current_select_color, Color.parseColor("#0190FF"));
        mCurrentStep = ta.getInteger(R.styleable.ArrowStepView_asv_current_select_step, START_STEP);
        Log.i("SIMPLE_LOGGER", "mCurrentStep:" + mCurrentStep);

        ta.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(defaultSelectColor);                    //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (isInEditMode()) {
            String[] steps = {"操作1", "操作2", "操作3"};
            setSteps(Arrays.asList(steps));
//            selectedCurrentStep(mCurrentStep);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mHeight >= mStepHeight) {
            mHeight = mStepHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int stepSize = mSteps.size();
        if (stepSize == 0) {
            return;
        }
        canvas.drawColor(Color.WHITE);
        mStepWidth = mWidth / stepSize;
        float fraction = 0.0f;

        for (int i = 1; i <= mCurrentStep; i++) {
            final boolean isSelected = i == mCurrentStep;
            if (isSelected) {
                mPaint.setColor(currentSelectColor);
            } else {
                mPaint.setColor(getCurrentColor(fraction, defaultSelectColor, currentSelectColor));
                fraction += 1.0f / ((stepSize - 2) == 1 ? 5 : stepSize - 2);
            }
            if (i == 1) {
                mPaint.setColor(defaultSelectColor);
                Path path = new Path();
                path.moveTo(0, 0);
                path.lineTo(mStepWidth, 0);
                path.lineTo(mStepWidth + mTriangleWidth, mStepHeight / 2);
                path.lineTo(mStepWidth, mStepHeight);

                path.lineTo(0, mStepHeight);
                path.close();
                canvas.drawPath(path, mPaint);
            } else if (i > 1 && i < stepSize) {
                Path path = new Path();
                path.moveTo(mStepWidth * (i - 1), 0);
                path.moveTo(mStepWidth * i, 0);
                path.lineTo(mStepWidth * i + mTriangleWidth, mStepHeight / 2);
                path.lineTo(mStepWidth * i, mStepHeight);
                path.lineTo(mStepWidth * (i - 1), mStepHeight);
                path.lineTo(mStepWidth * (i - 1) + mTriangleWidth, mStepHeight / 2);
                path.lineTo(mStepWidth * (i - 1), 0);
                path.close();
                canvas.drawPath(path, mPaint);
            } else {
                Path path = new Path();
                path.moveTo(mStepWidth * (i - 1), 0);
                path.lineTo(mWidth, 0);
                path.lineTo(mWidth, mStepHeight);
                path.lineTo(mStepWidth * (i - 1), mStepHeight);
                path.lineTo(mStepWidth * (i - 1) + mTriangleWidth, mStepHeight / 2);
                path.lineTo(mStepWidth * (i - 1), 0);
                path.close();
                canvas.drawPath(path, mPaint);
            }
        }
        for (int i = 1; i <= stepSize; i++) {
            final String text = mSteps.get(i - 1);
            if (i <= mCurrentStep) {
                mPaint.setColor(Color.WHITE);
            } else {
                mPaint.setColor(mTextColor);
            }
            float v = mPaint.measureText(text);

            String number = String.valueOf(i);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(mStepWidth * i - mStepWidth / 2 - v / 2 - mTextMarginLeft, mStepHeight / 2, mCircleRadius, mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(mCircleTextSize);
            int baseLineY = getBaseLineY();


            canvas.drawText(number, mStepWidth * i - mStepWidth / 2 - v / 2 - mTextMarginLeft, baseLineY, mPaint);
            mPaint.setTextSize(mTextSize);
            baseLineY = getBaseLineY();
            canvas.drawText(text, mStepWidth * i - mStepWidth / 2, baseLineY, mPaint);
        }
    }


    /**
     * 获取基线y轴坐标
     * @return
     */
    public int getBaseLineY() {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textTop = fontMetrics.top;
        float textBottom = fontMetrics.bottom;
        float contentBottom = mStepHeight / 2;
        int baseLineY = (int) (contentBottom - textTop / 2 - textBottom / 2);
        return baseLineY;
    }


    /**
     * 设置步骤
     *
     * @param steps
     */
    public void setSteps(List<String> steps) {
        if (steps != null) {
            mSteps.clear();
            if (steps.size() > MAX_STEP) {
                throw new RuntimeException("the step too many");
            }
            mSteps.addAll(steps);
        } else {
            throw new RuntimeException("the steps is null.");
        }
        selectedCurrentStep(mCurrentStep);
    }

    /**
     * 当前步骤
     *
     * @param step
     */
    public void selectedCurrentStep(int step) {
        final int selected = step < START_STEP ?
                START_STEP : (step > mSteps.size() ? mSteps.size() : step);
        mCurrentStep = selected;
        invalidate();
    }

    public int getCurrentStep() {
        return mCurrentStep;
    }

    public int getStepCount() {
        return mSteps.size();
    }

    /**
     * 根据fraction值来计算当前的颜色。 fraction值范围  0f-1f
     */
    private int getCurrentColor(float fraction, int startColor, int endColor) {
        int redCurrent;
        int blueCurrent;
        int greenCurrent;
        int alphaCurrent;

        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaStart = Color.alpha(startColor);

        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaEnd = Color.alpha(endColor);

        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaDifference = alphaEnd - alphaStart;

        redCurrent = (int) (redStart + fraction * redDifference);
        blueCurrent = (int) (blueStart + fraction * blueDifference);
        greenCurrent = (int) (greenStart + fraction * greenDifference);
        alphaCurrent = (int) (alphaStart + fraction * alphaDifference);

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }


}
