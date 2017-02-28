package com.example.dialog;

import android.content.Context;
import android.view.View;

public class DialogFactory {

	
	/**
	 * 根据布局文件创建支持动画的Dialog
	 * @param ctx 上下文对象
	 * @param layoutResID  布局文件的id
	 * @param anim_in 进入动画的xml
	 * @param anim_out 划出动画的xml
	 * @return
	 */
	public static AnimDialog createAnimDialog(Context ctx, int layoutResID, View view, int anim_in, int anim_out) {
		AnimDialog animDialog = new AnimDialog(ctx, android.R.style.Theme_Translucent_NoTitleBar, anim_in, anim_out);
		if (layoutResID != 0) {

			animDialog.setContentView(layoutResID);
		} else {

			animDialog.setContentView(view);
		}
		return animDialog;
	}
}
