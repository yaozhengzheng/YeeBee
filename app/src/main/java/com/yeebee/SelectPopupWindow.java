package com.yeebee;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 16245 on 2016/09/09.
 */

public class SelectPopupWindow extends PopupWindow implements View.OnClickListener{

    @InjectView(R.id.tianshilun)
    TextView mTianshilun;
    @InjectView(R.id.alun)
    TextView mAlun;
    @InjectView(R.id.blun)
    TextView mBlun;
    @InjectView(R.id.cancel)
    TextView mCancel;
    private View mView;

    public SelectPopupWindow(Context context, View.OnClickListener itemsClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //给 dialog里边的按键设置监听
        mView = inflater.inflate(R.layout.popwindows, null);
        ButterKnife.inject(this,mView);
        mTianshilun.setOnClickListener(itemsClick);
        mAlun.setOnClickListener(itemsClick);
        mBlun.setOnClickListener(itemsClick);
        //设置SelectPopupWindow的view
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //设置点击窗体外的位置自动消失
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height=mView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
    //dialog取消按键
    @OnClick(R.id.cancel)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }
}
