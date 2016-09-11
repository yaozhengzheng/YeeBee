package com.yeebee.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeebee.R;


/**
 * Created by Administrator on 2016/7/1 0001.
 */

public class DialogUtils {
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        ImageView img = (ImageView) v.findViewById(R.id.img_loading);
        TextView tipTextView = (TextView) v.findViewById(R.id.tv_loading);// 提示文字
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(
                context, R.anim.rotate_loading);
        // 使用ImageView显示动画
        img.startAnimation(animation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.Loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;

    }
}
