package com.example.animotionpro;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class CustomLinner extends LinearLayout {

	private FrameLayout parent;

	public CustomLinner(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CustomLinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomLinner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean dispatchTouchEvent(MotionEvent event) {
//		int x = (int) event.getX();
//		int y = (int) event.getY();
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			parent.requestDisallowInterceptTouchEvent(true);// 表示不拦截
//			break;
//
//		case MotionEvent.ACTION_MOVE:
//			if (父容器需要点击事件) {
//				parent.requestDisallowInterceptTouchEvent(false);// 表示拦截
//			}
//			break;
//
//		case MotionEvent.ACTION_UP:
//			break;
//		default:
//			break;
//		}
//
//		return super.dispatchTouchEvent(event);
//	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent event) {
//		boolean intercepted = false;
//		int x = (int) event.getX();
//		int y = (int) event.getY();
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			intercepted = false;
//			break;
//
//		case MotionEvent.ACTION_MOVE:
//			if (父容器需要点击事件) {
//				intercepted = true;
//			} else {
//				intercepted = false;
//			}
//			break;
//
//		case MotionEvent.ACTION_UP:
//			intercepted = false;
//			break;
//		default:
//			break;
//		}
//
//		return intercepted;
//	}
}
