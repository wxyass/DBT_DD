package dd.tsingtaopad.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;

/**
 * 项目名称：营销移动智能工作平台 </br> 
 * 文件名：SlideSwitch.java</br>
 * 作者：hongen </br>
 * 创建时间：2014-2-11</br> 
 * 功能描述: </br> 
 * 版本 V 1.0</br> 
 * 修改履历</br> 
 * 日期 原因 BUG号 修改人 修改版本</br>
 */
public class SlideSwitch extends View {

    public static final String TAG = "SlideSwitch";

    public static final int SWITCH_OFF = 0;// 关闭状态
    public static final int SWITCH_ON = 1;// 打开状态
    public static final int SWITCH_SCROLING = 2;// 滚动状态

    // 用于显示的文本
    private String onText = "";
    private String offText = "";

    private int switchStatus = SWITCH_OFF;

    private boolean hasScrolled = false;// 表示是否发生过滚动

    private int mSrcX = 0, mDstX = 0;

    private int mBmpWidth = 0;
    private int mBmpHeight = 0;
    private int mThumbWidth = 0;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private OnSwitchChangedListener mOnSwitchChangedListener = null;

    // 开关状态图
    private Bitmap mSwitch_off, mSwitch_on, mSwitch_thumb;

    /**
     * @param context
     */
    public SlideSwitch(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SlideSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlideSwitch);
        if (ta.hasValue(R.styleable.SlideSwitch_onText)) {
            onText = ta.getString(R.styleable.SlideSwitch_onText);
        }
        if (ta.hasValue(R.styleable.SlideSwitch_offText)) {
            offText = ta.getString(R.styleable.SlideSwitch_offText);
        }
        String defStatus = ta.getString(R.styleable.SlideSwitch_defStatus);
        if (CheckUtil.isBlankOrNull(defStatus)
                || "off".equalsIgnoreCase(defStatus)) {
            switchStatus = SWITCH_OFF;
        } else {
            switchStatus = SWITCH_ON;
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ta.getColor(R.styleable.SlideSwitch_textColor, 0XFFFFFFFF));
        mPaint.setTextSize(ta.getDimension(R.styleable.SlideSwitch_textSize, 17));
        ta.recycle();
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化三幅图片
    private void init() {
        Resources res = getResources();
        mSwitch_off = BitmapFactory.decodeResource(res,R.drawable.bg_switch_off);
        mSwitch_on = BitmapFactory.decodeResource(res, R.drawable.bg_switch_on);
        mSwitch_thumb = BitmapFactory.decodeResource(res,R.drawable.bg_switch_bt);
        
        mBmpWidth = mSwitch_on.getWidth();
        mBmpHeight = mSwitch_on.getHeight();
        mThumbWidth = mSwitch_thumb.getWidth();
    }

    @Override
    public void setLayoutParams(LayoutParams params) {
        params.width = mBmpWidth;
        params.height = mBmpHeight;
        super.setLayoutParams(params);
    }

    /**
     * 为开关控件设置状态改变监听函数
     * 
     * @param onSwitchChangedListener
     *            参见 {@link OnSwitchChangedListener}
     */
    public void setOnSwitchChangedListener(
            OnSwitchChangedListener onSwitchChangedListener) {
        mOnSwitchChangedListener = onSwitchChangedListener;
    }

    /**
     * 设置开关上面的文本
     * 
     * @param onText
     *            控件打开时要显示的文本
     * @param offText
     *            控件关闭时要显示的文本
     */
    public void setText(final String onText, final String offText) {
        this.onText = onText;
        this.offText = offText;
        invalidate();
    }

    /**
     * 设置开关的状态
     * 
     * @param on
     *            是否打开开关 打开为true 关闭为false
     */
    public void setStatus(boolean on) {
        this.switchStatus = (on ? SWITCH_ON : SWITCH_OFF);
    }
    
    public boolean getStatus() {
        return (this.switchStatus == 0 ? false : true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            mSrcX = (int) event.getX();
            break;
            
        case MotionEvent.ACTION_UP:
            if (hasScrolled == false)// 如果没有发生过滑动，就意味着这是一次单击过程
            {
                switchStatus = Math.abs(switchStatus - 1);
                int xFrom = 10, xTo = 62;
                if (switchStatus == SWITCH_OFF) {
                    xFrom = 62;
                    xTo = 10;
                }
                AnimationTransRunnable runnable = new AnimationTransRunnable(
                        xFrom, xTo, 1);
                new Thread(runnable).start();
            } else {
                invalidate();
                hasScrolled = false;
            }
            // 状态改变的时候 回调事件函数
            if (mOnSwitchChangedListener != null) {
                mOnSwitchChangedListener.onSwitchChanged(this, switchStatus);
            }
            performClick();
            break;

        default:
            break;
        }
        
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘图的时候 内部用到了一些数值的硬编码，其实不太好，
        // 主要是考虑到图片的原因，图片周围有透明边界，所以要有一定的偏移
        // 硬编码的数值只要看懂了代码，其实可以理解其含义，可以做相应改进。
//        mPaint.setTextSize(14);
//        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        if (switchStatus == SWITCH_OFF) {
            drawBitmap(canvas, null, null, mSwitch_off);
            drawBitmap(canvas, null, null, mSwitch_thumb);
//            mPaint.setColor(Color.rgb(105, 105, 105));
            canvas.translate(mSwitch_thumb.getWidth(), 0);
            canvas.drawText(offText, 0, 20, mPaint);
        } else if (switchStatus == SWITCH_ON) {
            drawBitmap(canvas, null, null, mSwitch_on);
            int count = canvas.save();
            canvas.translate(mSwitch_on.getWidth() - mSwitch_thumb.getWidth(),
                    0);
            drawBitmap(canvas, null, null, mSwitch_thumb);
//            mPaint.setColor(Color.WHITE);
            canvas.restoreToCount(count);
            canvas.drawText(onText, 17, 20, mPaint);
        } else // SWITCH_SCROLING
        {
            switchStatus = mDstX > 35 ? SWITCH_ON : SWITCH_OFF;
            drawBitmap(canvas, new Rect(0, 0, mDstX, mBmpHeight), new Rect(0,
                    0, (int) mDstX, mBmpHeight), mSwitch_on);
//            mPaint.setColor(Color.WHITE);
            canvas.drawText(onText, 17, 20, mPaint);

            int count = canvas.save();
            canvas.translate(mDstX, 0);
            drawBitmap(canvas, new Rect(mDstX, 0, mBmpWidth, mBmpHeight),
                    new Rect(0, 0, mBmpWidth - mDstX, mBmpHeight), mSwitch_off);
            canvas.restoreToCount(count);

            count = canvas.save();//保存在save()方法之前的操作
            canvas.clipRect(mDstX, 0, mBmpWidth, mBmpHeight);
            canvas.translate(mThumbWidth, 0);
//            mPaint.setColor(Color.rgb(105, 105, 105));
            canvas.drawText(offText, 0, 20, mPaint);
            canvas.restoreToCount(count);//restore是清除在save()之后的操作

            count = canvas.save();
            canvas.translate(mDstX - mThumbWidth / 2, 0);
            drawBitmap(canvas, null, null, mSwitch_thumb);
            canvas.restoreToCount(count);
        }
    }

    public void drawBitmap(Canvas canvas, Rect src, Rect dst, Bitmap bitmap) {
        dst = (dst == null ? new Rect(0, 0, bitmap.getWidth(),
                bitmap.getHeight()) : dst);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, src, dst, paint);
    }

    /**
     * AnimationTransRunnable 做滑动动画所使用的线程
     */
    private class AnimationTransRunnable implements Runnable {
        private int srcX, dstX;
        private int duration;

        /**
         * 滑动动画
         * 
         * @param srcX
         *            滑动起始点
         * @param dstX
         *            滑动终止点
         * @param duration
         *            是否采用动画，1采用，0不采用
         */
        public AnimationTransRunnable(float srcX, float dstX, final int duration) {
            this.srcX = (int) srcX;
            this.dstX = (int) dstX;
            this.duration = duration;
        }

        @Override
        public void run() {
            final int patch = (dstX > srcX ? 5 : -5);
            if (duration == 0) {
                SlideSwitch.this.switchStatus = SWITCH_SCROLING;
                SlideSwitch.this.postInvalidate();
            } else {
                Log.d(TAG, "start Animation: [ " + srcX + " , " + dstX + " ]");
                int x = srcX + patch;
                while (Math.abs(x - dstX) > 5) {
                    mDstX = x;
                    SlideSwitch.this.switchStatus = SWITCH_SCROLING;
                    SlideSwitch.this.postInvalidate();
                    x += patch;
                   /* try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
                mDstX = dstX;
                SlideSwitch.this.switchStatus = mDstX > 35 ? SWITCH_ON
                        : SWITCH_OFF;
                SlideSwitch.this.postInvalidate();
            }
        }

    }

    public static interface OnSwitchChangedListener {
        /**
         * 状态改变 回调函数
         * 
         * @param status
         *            SWITCH_ON表示打开 SWITCH_OFF表示关闭
         */
        public abstract void onSwitchChanged(SlideSwitch obj, int status);
    }

}
