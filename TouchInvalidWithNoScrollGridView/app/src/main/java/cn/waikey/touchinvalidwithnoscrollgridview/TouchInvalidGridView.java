package cn.waikey.touchinvalidwithnoscrollgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Tsang on 16/4/7.
 * TouchInvalidGridView clear problems with GridView inside ScrollView
 *  解决ScrollView嵌套GridView出现只显示一行的问题，将GridView直接舒展开。
 * 同时添加 GridView 点击空白区域的监听
 */
public class TouchInvalidGridView extends GridView {

    private OnTouchInvalidPositionListener mOnTouchInvalidPositionListener;

    public TouchInvalidGridView(Context context) {
        super(context);
    }

    public TouchInvalidGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchInvalidGridView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // HACK! TAKE THAT ANDROID!
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    public void setOnTouchInvalidPositionListener(OnTouchInvalidPositionListener mOnTouchInvalidPositionListener){
        this.mOnTouchInvalidPositionListener = mOnTouchInvalidPositionListener;
    }

    public interface OnTouchInvalidPositionListener{
        /**
         * @param motionEvent 可使用 MotionEvent.ACTION_DOWN 或者 MotionEvent.ACTION_UP等来按需要进行判断
         * @return 是否要终止事件
         */
        boolean onTouchInvalidPosition(int motionEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean defaultTouchEvent = super.onTouchEvent(ev);
        if(null == mOnTouchInvalidPositionListener){
            return defaultTouchEvent;
        }
        if(!isEnabled()){
            return isClickable() || isLongClickable();
        }
        final int motionPosition = pointToPosition((int)ev.getX(), (int)ev.getY());


        if( motionPosition == INVALID_POSITION ) {
            /*当你点击空白区域的时候，会返回INVALID_POSITION，由此便可判断点击了空白区域。*/
            super.onTouchEvent(ev);
            defaultTouchEvent = mOnTouchInvalidPositionListener.onTouchInvalidPosition(ev.getActionMasked());
        }
        return defaultTouchEvent;
    }
}
