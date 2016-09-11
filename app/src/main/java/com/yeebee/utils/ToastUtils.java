package com.yeebee.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.yeebee.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/6/30 0030.
 */

public class ToastUtils extends Toast {

    private static Toast mToast;

    public ToastUtils(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, int resId, CharSequence text,
                                 int duration) {
        Toast result = new Toast(context);
        // 获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.item_toast, null);
        // 实例化ImageView和TextView对象
        ImageView imageView = (ImageView) layout.findViewById(R.id.img_toast);
        TextView textView = (TextView) layout.findViewById(R.id.tv_toast);
        //这里我为了给大家展示就使用这个方面既能显示无图也能显示带图的toast
        if (resId == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(resId);
        }
        textView.setText(text);
        result.setView(layout);
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);
        return result;
    }

    /**
     * 调用有动画的Toast
     */
    public static Toast makeTextAnim(Context context, CharSequence text,
                                     int duration, int styleId) {
        Toast toast = makeText(context, text, duration);
        toast.setText(text);
        toast.setDuration(duration);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.item_toast_1, null);
        TextView tv = (TextView) layout.findViewById(R.id.tv_toast_1);
        tv.setText(text);
        toast.setView(layout);
        try {
            Object mTN;
            mTN = getField(toast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    params.windowAnimations = styleId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toast;
    }

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

    //可以显示图片的方形toast
    public static void showToast(Context context, int ic_id, String content) {
        mToast = ToastUtils.makeText(context, ic_id, content, 800);
        mToast.show();
    }

    //带有动画效果的toast
    public static void showToast(Context context, CharSequence content) {
        mToast = ToastUtils.makeTextAnim(context, content, 800, R.style.ToastAnimation);
        mToast.show();
    }
}
