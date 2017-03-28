package com.huangxl.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



/**
 * Created by huangxl on 2017/3/28.
 */

public class PullRefreshLayout  extends LinearLayout{

    private static final String TAG = PullRefreshLayout.class.getSimpleName();
    View headerView;
    View contentView;
    private int downX;
    private int downY;
    private int disX;
    private int disY;
    private MarginLayoutParams layoutParms;
    private MarginLayoutParams headerLayoutParam;
    private int headerHeight;


    public PullRefreshLayout(Context context) {
        super(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount=getChildCount();
        if (childCount==0)return;
        if (childCount==2) {
            headerView = getChildAt(0);
            contentView=getChildAt(1);
            post(new Runnable() {
                @Override
                public void run() {
                    layoutParms = (MarginLayoutParams) contentView.getLayoutParams();
                    headerLayoutParam = (MarginLayoutParams) headerView.getLayoutParams();
                    Log.v("TEST","--headerHeight is "+headerView.getHeight());
                    headerHeight = headerView.getHeight();
                    headerLayoutParam.topMargin=-headerHeight;
                    headerView.setLayoutParams(headerLayoutParam);
                }
            });

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int headerViewTop=headerView.getTop();
        Log.v(TAG,"---headviewTop is "+headerViewTop);
        if (headerViewTop>0){
            Log.v(TAG,"-----33333333333333333333333333-----------------onInterceptTouchEvent return false");
            boolean interceptResult=super.onInterceptTouchEvent(ev);
            return interceptResult;
        }
        Log.v(TAG,"-----44444444444444444444444444444444444444-----------------onInterceptTouchEvent return true");

        return true;
    }

   /* @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interTouchResult=super.onInterceptTouchEvent(ev);
        Log.v(TAG,"---onInterceptTouchEvent result is "+interTouchResult);
        return interTouchResult;
    }*/

   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean dispatchResult=super.dispatchTouchEvent(ev);
        Log.v(TAG,"----dispatchTouchEvent result is "+dispatchResult);
        return dispatchResult;
    }*/

     @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int headerViewTop=headerView.getTop();
        Log.v(TAG,"---headviewTop is "+headerViewTop);
        if (headerViewTop>0){
            boolean dispatchResult =   super.dispatchTouchEvent(ev);

            Log.v(TAG,"-111111111111111111111111111---dispatchTouchEvent return "+dispatchResult);
            return dispatchResult;
        }

        Log.v(TAG,"-22222222222222222222---dispatchTouchEvent return 0"+true);

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                disX = (int) (ev.getX()-downX);
                disY = (int) (ev.getY()-downY);
                Log.v(TAG,"disX is "+disX+" disY is "+disY);
                int marginVale=disY-headerHeight;
               /* if (marginVale>0){
                    marginVale=0;
                }*/
                headerLayoutParam.topMargin=marginVale;
                Log.v(TAG,"----topMargin is "+(disY-headerHeight));
                headerView.setLayoutParams(headerLayoutParam);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }

}
