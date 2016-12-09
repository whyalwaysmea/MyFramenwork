package com.whyalwaysmea.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Long
 * on 2016/12/9.
 */

public class CollapseView extends LinearLayout{

    private Context mContext;
    private LinearLayout mTitleLayout;
    private TextView mNumber;
    private TextView mTitle;
    private ImageView mArrow;
    private RelativeLayout mContentLayout;

    private int duration = 300;
    private int mParentWidthMeasureSpec;
    private int mParentHeightMeasureSpec;

    public CollapseView(Context context) {
        super(context);
        init(context);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CollapseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.collapse_layout, this);
        mTitleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        mNumber = (TextView) findViewById(R.id.number);
        mTitle = (TextView) findViewById(R.id.title);
        mArrow = (ImageView) findViewById(R.id.arrow);
        mContentLayout = (RelativeLayout) findViewById(R.id.contentLayout);

        mTitleLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateArrow();
            }
        });

        collapse(mContentLayout);

    }

    public void setNumber(String number) {
        if(!TextUtils.isEmpty(number)) {
            mNumber.setText(number);
        }
    }

    public void setTitle(String title) {
        if(!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }

    public void setContentLayout(int layoutId) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        mContentLayout.addView(view);
    }

    public void rotateArrow() {
        int degree = 0;
        if(mArrow.getTag() == null || mArrow.getTag().equals(true)) {
            mArrow.setTag(false);
            degree = -180;
            expand(mContentLayout);
        } else {
            mArrow.setTag(true);
            degree = 0;
            collapse(mContentLayout);
        }
        mArrow.animate().setDuration(duration).rotation(degree);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mParentWidthMeasureSpec = widthMeasureSpec;
        this.mParentHeightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    // 折叠
    private void collapse(final RelativeLayout contentLayout) {
        final int measuredHeight = contentLayout.getMeasuredHeight();
        /*Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    contentLayout.setVisibility(View.GONE);
                } else {
                    contentLayout.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    contentLayout.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };*/
        ValueAnimator animation = ValueAnimator.ofFloat(1, 0);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                if(value == 0) {
                    contentLayout.setVisibility(GONE);
                } else {
                    contentLayout.getLayoutParams().height = (int) (measuredHeight * value);
                    contentLayout.requestLayout();
                }
                System.out.println("value == " + value);
                System.out.println("contentLayout.getLayoutParams().height == " + contentLayout.getLayoutParams().height);
            }
        });


        animation.setDuration(duration);
        animation.setTarget(contentLayout);
        animation.start();

//        contentLayout.startAnimation(animation);

    }

    // 展开
    private void expand(final RelativeLayout contentLayout) {
        contentLayout.measure(mParentWidthMeasureSpec, mParentHeightMeasureSpec);
        int measuredWidth = contentLayout.getMeasuredWidth();
        final int measuredHeight = contentLayout.getMeasuredHeight();
        contentLayout.setVisibility(VISIBLE);

        Animation animation = new Animation(){
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1) {
                    contentLayout.getLayoutParams().height = measuredHeight;
                } else {
                    contentLayout.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
                }
                contentLayout.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        contentLayout.startAnimation(animation);
    }
}
