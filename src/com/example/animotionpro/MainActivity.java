package com.example.animotionpro;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.InputEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int MESSAGE_SCROLL_TO = 1;
	private static final int FRAME_COUNT = 30;
	private static final int DELAYED_TIME = 30;
	private int mCount = 0;

	Button button;
	CustomView mView;
	Button mView2;
	ImageView image;
	ImageView tv_level;
	ImageView clip_image;
	TextView tv_scale;
	ListView listview;
	TextView btn_property;
	TextView tv_width;

	boolean isStatusOn = false;
	String[] arr = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
			"16" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.button);
		mView = (CustomView) findViewById(R.id.view);
		mView2 = (Button) findViewById(R.id.view2);
		image = (ImageView) findViewById(R.id.imageView);
		tv_level = (ImageView) findViewById(R.id.tv_level);
		clip_image = (ImageView) findViewById(R.id.clip_image);
		tv_scale = (TextView) findViewById(R.id.tv_scale);
		listview = (ListView) findViewById(R.id.list);
		btn_property = (TextView) findViewById(R.id.btn_property);
		tv_width = (TextView) findViewById(R.id.tv_width);

		// 展示listview的item动画效果
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item, R.id.tv, arr);
		listview.setAdapter(adapter);

		// ScaleDrawable 的使用
		// ScaleDrawable scaleDrawable=(ScaleDrawable) image.getBackground();
		// scaleDrawable.setLevel(1);
		// onClipClick(clip_image);
	}

	/**
	 * view包装类，简介提供view属性set和get方法
	 * 
	 * @author yl
	 *
	 */
	private static class ViewWraper {
		private View mTarget;

		public ViewWraper(View mTarget) {
			super();
			this.mTarget = mTarget;
		}

		public int getWidth() {
			return mTarget.getLayoutParams().width;
		}

		public void setWidth(int width) {
			mTarget.getLayoutParams().width = width;
			mTarget.requestLayout();
		}
	}

	/**
	 * 改变textview的宽度属性动画
	 * 
	 * @param v
	 */
	public void onWidthClick(final View v) {
//		ObjectAnimator.ofInt(new ViewWraper(tv_width)/* tv_width */, "width", 500).setDuration(1000).start();///* 这种方法是用一个包装类简介实现目标的set/get方法 */
		
		//采用valueAnimator实现textview增加宽度的属性动画
		ValueAnimator valueAnimator=ValueAnimator.ofInt(0,100);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			private IntEvaluator mEvaluator=new IntEvaluator();
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int currentValue=(int) animation.getAnimatedValue();
				Log.d(TAG, "current value:"+currentValue);
				float fraction=animation.getAnimatedFraction();
				v.getLayoutParams().width=mEvaluator.evaluate(fraction, v.getWidth(), 500);
				v.requestLayout();
			}
		});
		
		valueAnimator.setDuration(1000).start();
	}

	/**
	 * 几个常见属性动画
	 * 
	 * @param v
	 */
	public void onPropertyClick(View v) {
		// ObjectAnimator.ofFloat(btn_property, "translationY",
		// -150).setDuration(1500).start();
		// ValueAnimator colorAnim = ObjectAnimator.ofInt(v, "backgroundColor",
		// /* red */0xffff8080,
		// /* blue */0xff8080ff);
		// colorAnim.setDuration(3000);
		// colorAnim.setEvaluator(new ArgbEvaluator());
		// colorAnim.setRepeatCount(ValueAnimator.INFINITE);
		// colorAnim.setRepeatMode(ValueAnimator.REVERSE);
		// colorAnim.start();

		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(v, "rotationX", 0, 360), // x轴旋转
				ObjectAnimator.ofFloat(v, "rotationY", 0, 180), // y轴旋转
				ObjectAnimator.ofFloat(v, "rotation", 0, -90), ObjectAnimator.ofFloat(v, "translationX", 0, 90), // 水平移动
				ObjectAnimator.ofFloat(v, "translationY", 0, 90), // 竖直移动
				ObjectAnimator.ofFloat(v, "scaleX", 1, 1.5f), // x轴缩放
				ObjectAnimator.ofFloat(v, "scaleY", 1, 0.5f), // y轴缩放
				ObjectAnimator.ofFloat(v, "alpha", 1, 0.25f, 1)// 透明度变化

		);
		set.setDuration(5 * 1000).start();
	}

	/**
	 * 缩放中心点在y轴
	 * 
	 * @param v
	 */
	public void onScaleClick(View v) {
		Log.d("MainActivity", "onScaleClick");
		Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_anim);
		tv_scale.startAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				Log.d(TAG, "onAnimationStart");

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				Log.d(TAG, "onAnimationRepeat:" + animation.getRepeatCount());

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Log.d(TAG, "onAnimationEnd");

			}
		});
	}

	/**
	 * ClipDrawable的使用
	 * 
	 * @param v
	 */
	public void onClipClick(View v) {
		ClipDrawable clipDrawable = (ClipDrawable) clip_image.getDrawable();
		clipDrawable.setLevel(7000);
	}

	public void onlevelClick(View v) {
		if (isStatusOn) {
			tv_level.setImageLevel(0);
		} else {
			tv_level.setImageLevel(1);
		}
		isStatusOn = !isStatusOn;
	}

	/**
	 * handler 动画
	 * 
	 * @param v
	 */
	public void onClick(View v) {
		handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
	}

	/**
	 * 使用Scroller 动画（滑动的是内容）
	 * 
	 * @param v
	 */
	public void onClick2(View v) {
		mView.smoothScrollTo(-300, -500);
	}

	/**
	 * 改变布局参数 动画（滑动的是view本身）
	 * 
	 * @param v
	 */
	public void onClick3(View v) {
		Toast.makeText(MainActivity.this, "我在这儿", Toast.LENGTH_SHORT).show();
		MarginLayoutParams params = (MarginLayoutParams) mView2.getLayoutParams();
		// params.width+=100;
		params.leftMargin += 100;
		mView2.setLayoutParams(params);

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_SCROLL_TO:
				mCount++;
				if (mCount <= FRAME_COUNT) {
					float fraction = mCount / FRAME_COUNT;
					int scrolly = (int) (fraction * 500);
					button.scrollTo(0, -scrolly);
					handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
				}

				break;

			default:
				break;
			}
		};
	};
}
