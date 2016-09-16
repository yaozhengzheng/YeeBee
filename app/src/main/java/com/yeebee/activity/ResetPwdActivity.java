package com.yeebee.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.R;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是忘记密码页面
 */
public class ResetPwdActivity extends AppCompatActivity {

    @InjectView(R.id.iv_iv_login)
    ImageView mIvIvLogin;
    @InjectView(R.id.tv_yeebee_invest)
    TextView mTvYeebeeInvest;
    @InjectView(R.id.et_tel)
    EditText mEtTel;
    @InjectView(R.id.tv_code)
    Button mTvCode;
    @InjectView(R.id.et_code)
    EditText mEtCode;
    @InjectView(R.id.et_pwd)
    EditText mEtPwd;
    @InjectView(R.id.et_make_sure_pwd)
    EditText mEtMakeSurePwd;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;

    String userTel;
    String userPwd;
    String userPwd1;
    String userYanzheng;

    private Context mContext = ResetPwdActivity.this;
    private static final int MSG_SET_NUM = 2001;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(ResetPwdActivity.this,
                        "发送验证码成功！", Toast.LENGTH_SHORT).show();
                Message msg2 = Message.obtain();
                msg2.what = MSG_SET_NUM;
            }
            if (msg.what == 0) {
                Toast.makeText(ResetPwdActivity.this,
                        "手机号格式不正确！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 2) {
                Toast.makeText(ResetPwdActivity.this,
                        "手机号码不存在！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 3) {
                Toast.makeText(ResetPwdActivity.this,
                        "短信发送错误！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(ResetPwdActivity.this,
                        "找回密码成功！", Toast.LENGTH_SHORT).show();
                Message msg2 = Message.obtain();
                msg2.what = MSG_SET_NUM;
                //找回密码成功跳转到登陆页面
                startActivity(new Intent(ResetPwdActivity.this,LoginActivity.class));
            }
            if (msg.what == 0) {
                Toast.makeText(ResetPwdActivity.this,
                        "手机号格式不正确！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 2) {
                Toast.makeText(ResetPwdActivity.this,
                        "手机号码不存在！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 3) {
                Toast.makeText(ResetPwdActivity.this,
                        "短信发送错误！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_code, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            //发送验证码
            case R.id.tv_code:
                userTel=mEtTel.getText().toString().trim();
                postYanZheng();
                break;
            //提交
            case R.id.tv_login:
                userTel=mEtTel.getText().toString().trim();
                userPwd=mEtPwd.getText().toString().trim();
                userPwd1=mEtMakeSurePwd.getText().toString().trim();
                userYanzheng=mEtCode.getText().toString().trim();
                post();
                break;
        }
    }

    //获取忘记密码验证码接口
    public void postYanZheng() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject json = new JSONObject();
                try {
                    json.put("USERTEL", userTel);
                    json.put("USERPWD",userPwd);
                    json.put("USERYANZHENG", userYanzheng);
                    json.put("USERIMIE", SharedPreferenceUtil.getIMEI(mContext));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String date_params = json.toString();
                String code_params = "U008";// 验证码接口编号
                String myData = ConnectUtils.Post_Myparams(date_params, code_params);
                Log.d("验证码接口数据*****", myData);
                if (myData != null) {
                    JSONObject myDataObj = null;
                    try {
                        myDataObj = new JSONObject(myData);
                        int recode = myDataObj.getInt("Recode");
                        if (recode == 1) {
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                        if (recode == 0) {
                            Message msg = handler.obtainMessage();
                            msg.what = 0;
                            handler.sendMessage(msg);
                        }
                        if (recode == 2) {
                            Message msg = handler.obtainMessage();
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                        if (recode == 3) {
                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            handler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }.execute();
    }

    //提交接口
    public void post() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject json = new JSONObject();
                try {
                    json.put("USERTEL", userTel);
                    json.put("USERPWD",userPwd);
                    json.put("USERYANZHENG", userYanzheng);
                    json.put("USERIMIE", SharedPreferenceUtil.getIMEI(mContext));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String date_params = json.toString();
                String code_params = "U009";// 忘记密码接口编号
                String myData = ConnectUtils.Post_Myparams(date_params, code_params);
                Log.d("忘记密码接口数据*****", myData);
                if (myData != null) {
                    JSONObject myDataObj = null;
                    try {
                        myDataObj = new JSONObject(myData);
                        int recode = myDataObj.getInt("Recode");
                        if (recode == 1) {
                            Message msg = handler1.obtainMessage();
                            msg.what = 1;
                            handler1.sendMessage(msg);
                        }
                        if (recode == 0) {
                            Message msg = handler1.obtainMessage();
                            msg.what = 0;
                            handler1.sendMessage(msg);
                        }
                        if (recode == 2) {
                            Message msg = handler1.obtainMessage();
                            msg.what = 2;
                            handler1.sendMessage(msg);
                        }
                        if (recode == 3) {
                            Message msg = handler1.obtainMessage();
                            msg.what = 3;
                            handler1.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }.execute();
    }

}
