package com.yeebee.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.yeebee.R;
import com.yeebee.utils.Constants;
import com.yeebee.utils.ToastUtils;

/**
 * Created by Administrator on 2016/9/6 0006.
 */

public abstract class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (!isNetworkConnected(BaseActivity.this)) {
            ToastUtils.showToast(BaseActivity.this, R.mipmap.error_network, Constants.NETWORK_ERROR);
        }

        initData();
        initView();
    }
    //设置显示的内容
    protected abstract void setContentView();

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //判断当前网络是否可用
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
