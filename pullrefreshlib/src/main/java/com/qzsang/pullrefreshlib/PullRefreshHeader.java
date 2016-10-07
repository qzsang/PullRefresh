/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.qzsang.pullrefreshlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;



public class PullRefreshHeader extends LinearLayout {
	public static final int ACTION_FINISH = 1; // 动作完成 回退
	public static final int STATE_ARROWS_TO_TOP = 2;//状态 箭头向上
	public static final int STATE_ARROWS_TO_DOWN = 3;//状态 箭头向下
	public static final int STATE_FRESHING = 4;//状态 箭头不在  显示刷新状态

	private LinearLayout mContainer;
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;

	private final int ROTATE_ANIM_DURATION = 50;

	private int mState = STATE_ARROWS_TO_DOWN;//代表view 显示的状态
	private boolean isFreshing = false;//是否处于刷新状态
	public PullRefreshHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public PullRefreshHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.pullrefresh_header, this);

		mArrowImageView = (ImageView)findViewById(R.id.xlistview_header_arrow);
//		mHintTextView = (TextView)findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar)findViewById(R.id.xlistview_header_progressbar);
		
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}

	public int getState() {
		return mState;
	}



	public void setState(int state) {
		if (mState == state){//状态一样不改变
			return;
		}

		switch (state) {
			case PullRefreshHeader.ACTION_FINISH:
				isFreshing = false;
				break;
			case PullRefreshHeader.STATE_ARROWS_TO_TOP:
				if (!isFreshing) {
					changeProgressState(false);
					startAimation(mRotateUpAnim);
				} else {
					return;
				}
				break;
			case PullRefreshHeader.STATE_ARROWS_TO_DOWN:
				if (!isFreshing) {
					changeProgressState(false);
					startAimation(mRotateDownAnim);
				} else {
					return;
				}
				break;
			case PullRefreshHeader.STATE_FRESHING:
				changeProgressState(true);
				isFreshing = true;
				break;
			default:

		}
		mState = state;
	}

	//动画效果
	private void startAimation (Animation animation) {
		mArrowImageView.clearAnimation();
		mArrowImageView.startAnimation(animation);

	}

	//视图初始化
	public void initViewState () {
		mArrowImageView.clearAnimation();
		changeProgressState(false);
		isFreshing = false;
		mState = STATE_ARROWS_TO_DOWN;
	}
	//改变状态
	private void changeProgressState (boolean isShowProgressBar) {
		if (isShowProgressBar) {	// 显示进度
			mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.INVISIBLE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else {	// 显示箭头图片
			mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
		}
	}
	

}
