package com.oliver.base.view;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabWidget;

/**
 * 功能：//仿qq红点消息提醒
 * 作者：oliver
 * 日期：2017/7/25
 */
public class RedTipView extends View {

    private Paint mRedTipPaint;

    public RedTipView(Context context) {
        this(context, null);
    }

    public RedTipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedTipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!(getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams layoutParams =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.TOP);
            setLayoutParams(layoutParams);
        }

        mRedTipPaint = new Paint();
        mRedTipPaint.setAntiAlias(true);// 抗锯齿
        // Color.parseColor("#d3321b") 把16进制字符串转为颜色
        mRedTipPaint.setColor(Color.parseColor("#d3321b"));
    }


    public void setTipGravity(int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public int getTipGravity() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        return params.gravity;
    }

    public void setTipMargin(int dipMargin) {
        setTipMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }

    public void setTipMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }

    public int[] getTipMargin() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        return new int[]{params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin};
    }

    /*
     * Attach the BadgeView to the TabWidget
     *
     * @param target the TabWidget to attach the BadgeView
     *
     * @param tabIndex index of the tab
     */
    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    }

    /*
     * Attach the BadgeView to the target view
     *
     * @param target the view to attach the BadgeView
     */
    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }

        if (target == null) {
            return;
        }


        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);
        } else if (target.getParent() instanceof ViewGroup) {
            // use a new Framelayout container for adding badge
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            // new 新的FrameLayout作为TargetView(TextView) 和 消息数量提示的父布局
            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);

            badgeContainer.addView(target);

            // 直接添加的This
            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }

    }

    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }


    private boolean mIsShowDot = false;

    public boolean isShowDot() {
        return mIsShowDot;
    }

    // 给两个方法显示和隐藏消息红点
    public void showRedDot() {
        mIsShowDot = true;
        //  画一个点  搞个背景
        invalidate();// 重新绘制  重新调用onDraw()
    }

    public void hideRedDot() {
        mIsShowDot = false;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 5 dp  指定宽高
        setMeasuredDimension(dip2Px(10), dip2Px(10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsShowDot) {
            //  cx cy  radius  paint
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, mRedTipPaint);
        }
    }
}
