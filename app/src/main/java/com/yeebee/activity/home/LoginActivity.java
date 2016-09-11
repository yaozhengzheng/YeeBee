package com.yeebee.activity.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeebee.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是登陆页面
 */
public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.iv_iv_login)
    ImageView mIvIvLogin;
    @InjectView(R.id.tv_yeebee_invest)
    TextView mTvYeebeeInvest;
    @InjectView(R.id.iv_login_name_icon)
    ImageView mIvLoginNameIcon;
    @InjectView(R.id.et_name)
    EditText mEtName;
    @InjectView(R.id.rl_login_name)
    RelativeLayout mRlLoginName;
    @InjectView(R.id.iv_login_pwd_icon)
    ImageView mIvLoginPwdIcon;
    @InjectView(R.id.et_pwd)
    EditText mEtPwd;
    @InjectView(R.id.rl_login_pwd)
    RelativeLayout mRlLoginPwd;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;
    @InjectView(R.id.tv_attesttation)
    TextView mTvAttesttation;
    @InjectView(R.id.tv_forgot)
    TextView mTvForgot;
    @InjectView(R.id.activity_login)
    RelativeLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_attesttation, R.id.tv_forgot})
    public void onClick(View view) {
        switch (view.getId()) {
            //登陆
            case R.id.tv_login:
                break;
            //跳到注册页面
            case R.id.tv_attesttation:
                break;
            //跳到忘记密码界面
            case R.id.tv_forgot:
                break;
        }
    }
}
