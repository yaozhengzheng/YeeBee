package com.yeebee.activity.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.MainActivity;
import com.yeebee.R;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.NetWorkUtils;
import com.yeebee.utils.PhoneUtils;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

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

    //用户输入信息
    String mPhone;
    private String string_username;
    private String string_password;
    private Context mContext = LoginActivity.this;
    private SharedPreferenceUtil mSharedPreferenceUtil;

    private static final int MSG_SET_NUM = 2001;
    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x120){
                Toast.makeText(LoginActivity.this,
                        "登录成功！", Toast.LENGTH_SHORT).show();
                Message msg2 = Message.obtain();
                msg2.what = MSG_SET_NUM;
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            if (msg.what==0x121){
                Toast.makeText(LoginActivity.this,
                        "密码错误！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 0x123) {
                Toast.makeText(LoginActivity.this,
                        "账号不存在！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 0x124) {
                Toast.makeText(LoginActivity.this,
                        "登录失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mSharedPreferenceUtil=new SharedPreferenceUtil(this,"userInfo");
    }

    @OnClick({R.id.tv_login, R.id.tv_attesttation, R.id.tv_forgot})
    public void onClick(View view) {
        switch (view.getId()) {
            //登陆
            case R.id.tv_login:
                if(!validatePhone()){
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                string_username=mEtName.getText().toString().trim();
                if (isNull(mEtPwd)){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                string_password = mEtPwd.getText().toString().trim();
                if (NetWorkUtils.isNetWorkAvaliable(mContext)){
                    login();
                }else{
                    setNetwork();
                }
                break;
            //跳到注册页面
            case R.id.tv_attesttation:
                break;
            //跳到忘记密码界面
            case R.id.tv_forgot:
                break;
        }
    }

    private void setNetwork() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("当前无网络连接，是否进行设置？");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 登录 用户登录接口编号：U007_2
     */
    private void login() {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
             JSONObject json=new JSONObject();

                try {
                    json.put("UserName", string_username);
                    json.put("UserPwd", string_password);
                    json.put("USERIMIE", SharedPreferenceUtil.getIMEI(mContext));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String date_params=json.toString();
                String code_params="U007_2";// 登录接口编号

                String myData= ConnectUtils.Post_Myparams(date_params,code_params);
                if (myData!=null){
                    JSONObject myDataObj=null;
                    try {
                        myDataObj=new JSONObject(myData);
                        int reco=myDataObj.getInt("Recode");
                        if (reco==1){
                            String userId = myDataObj.getString("USERID");
                            String USERNICK = myDataObj.optString("USERNICK");
                            mSharedPreferenceUtil.setUserId(userId);
                            mSharedPreferenceUtil.setPhone(mPhone);
                            mSharedPreferenceUtil.setNickName(USERNICK);
                            mSharedPreferenceUtil.setIsLogin(true);
                            Message msg=handler.obtainMessage();
                            msg.what=0x120;
                            handler.sendMessage(msg);
                        }
                        if (reco==2){
                            Message msg=handler.obtainMessage();
                            msg.what=0x121;
                            handler.sendMessage(msg);
                        }
                        if (reco == 0) {
                            Message msg = handler.obtainMessage();
                            msg.what = 0x123;
                            handler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Message msg = handler.obtainMessage();
                    msg.what = 0x124;
                    handler.sendMessage(msg);
                }
                return null;
            }
        }.execute();
    }

    //判断editText是否是空
private boolean isNull(EditText editText) {
    String text = editText.getText().toString().trim();
    if (text != null && text.length() > 0) {
        return false;
    }
    return true;
}

    private boolean validatePhone() {
        mPhone = null;
        if (isNull(mEtName)) {
            Toast.makeText(LoginActivity.this, "请输入账号",
                    Toast.LENGTH_SHORT).show();
            mEtName.requestFocus();
            return false;
        }
        String phone = mEtName.getText().toString().trim();
        if (PhoneUtils.isNumeric(phone)) {
            mPhone = phone;
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "账号格式不正确",
                    Toast.LENGTH_SHORT).show();
            mEtName.requestFocus();
            return false;
        }
    }
}
