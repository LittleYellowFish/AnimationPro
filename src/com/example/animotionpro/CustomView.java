package com.example.animotionpro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

public class CustomView extends TextView {

	private Scroller mScroller;
	
	public CustomView(Context context) {
		this(context,null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		mScroller=new Scroller(context);
	}

	public void smoothScrollTo(int destX,int destY){
		int scrollX=getScrollX();
		int scrollY=getScrollY();
		
		int deltaX=destX-scrollX;
		int deltaY=destY-scrollY;
		
		mScroller.startScroll(scrollX, scrollY,deltaX , deltaY);
		invalidate();
	}
	
	
	@Override
	public void computeScroll() {
		super.computeScroll();
		if(mScroller.computeScrollOffset()){
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}
}
