package com.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

/**
 * 可以在 “显示” 和 “隐藏” 时添加动画效果的dialog
 * @author Jack
 * @version 创建时间：2014年5月27日 下午3:26:50
 */
public class AnimDialog extends Dialog {

	/** 显示时加载的动画 */
	private Animation anim_in;
	/** 隐藏时加载的动画 */
	private Animation anim_out;
	/** 显示动画监听器 */
	private AnimInListener animInListener;
	/** 隐藏动画监听器 */
	private AnimOutListener animOutListener;

	public interface IAnimDialogListener {

		public void onDialogInStart();

		public void onDialogInEnd();

		public void onDialogOutStart();

		public void onDialogOutEnd();
	}

	private IAnimDialogListener mListener;

	/**
	 * @param mListener the mListener to set
	 */
	public void setAnimDialogListener(IAnimDialogListener listener) {
		mListener = listener;
	}

	/**
	 * @return the mListener
	 */
	public IAnimDialogListener getAnimDialogListener() {
		return mListener;
	}

	private class AnimInListener implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {
			if (null != mListener) {
				mListener.onDialogInEnd();
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
			if (null != mListener) {
				mListener.onDialogInStart();
			}
		}
	}

	private class AnimOutListener implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {
			superDismiss();
			if (null != mListener) {
				mListener.onDialogOutEnd();
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
			if (null != mListener) {
				mListener.onDialogOutStart();
			}
		}
	}

	public AnimDialog(Context context, int style) {
		super(context, style);
		setCancelable(true);
		animInListener = new AnimInListener();
		animOutListener = new AnimOutListener();
	}

	public AnimDialog(Context context, int style, int anim_in, int anim_out) {
		this(context, style);
		setAnimationIn(AnimationUtils.loadAnimation(context, anim_in));
		setAnimationOut(AnimationUtils.loadAnimation(context, anim_out));
	}

	public void setAnimationIn(Animation in) {
		if (in != null) {
			this.anim_in = in;

			if (animInListener != null) {
				anim_in.setAnimationListener(animInListener);
			}
		}
	}

	public void setAnimationOut(Animation out) {
		if (null != out) {
			this.anim_out = out;

			if (animOutListener != null) {
				anim_out.setAnimationListener(animOutListener);
			}
		}
	}

	@Override
	public void show() {
		super.show();
		View dialog_root = ((ViewGroup) getWindow().getDecorView().getRootView()).getChildAt(0);
		if (anim_in != null) {
			dialog_root.startAnimation(anim_in);
		}
	}

	private void superDismiss() {
		super.dismiss();
	}

	@Override
	public void dismiss() {
		View dialog_root = ((ViewGroup) getWindow().getDecorView().getRootView()).getChildAt(0);
		if (anim_out != null) {
			dialog_root.startAnimation(anim_out);
		} else {
			superDismiss();
		}
	}
}