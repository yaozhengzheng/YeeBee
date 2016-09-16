package com.yeebee.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.R;
import com.yeebee.SelectPopupWindow;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.SharedPreferenceUtil;
import com.yeebee.utils.TelNumMatch;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是注册页面
 */
public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.img_regist)
    ImageView mImgRegist;
    @InjectView(R.id.edt_name)
    EditText mEdtName;
    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.edt_idNum)
    EditText mEdtIdNum;
    @InjectView(R.id.regist_jieduan)
    TextView mRegistJieduan;
    @InjectView(R.id.img_i1)
    ImageView mImg1;
    @InjectView(R.id.regist_jigou)
    TextView mRegistJigou;
    @InjectView(R.id.img_i2)
    ImageView mImg2;
    @InjectView(R.id.regist_touzilingyu)
    TextView mRegistTouzilingyu;
    @InjectView(R.id.img_i3)
    ImageView mImg3;
    @InjectView(R.id.regist_toucichengshi)
    TextView mRegistToucichengshi;
    @InjectView(R.id.img_i4)
    ImageView mImg4;
    @InjectView(R.id.edt_phone)
    EditText mEdtPhone;
    @InjectView(R.id.edt_yanzhengma)
    EditText mEdtYanzhengma;
    @InjectView(R.id.post_yanzhengma)
    Button mPostYanzhengma;
    @InjectView(R.id.tv_tiaokuan)
    TextView mTvTiaokuan;
    @InjectView(R.id.regist_cancel)
    Button mRegistCancel;
    @InjectView(R.id.regist)
    Button mRegist;
    @InjectView(R.id.edt_pwd)
    EditText mEdtPwd;
    @InjectView(R.id.edt_conf_pwd)
    EditText mEdtConfPwd;
    @InjectView(R.id.regist_jieduan1)
    TextView mRegistJieduan1;

    //自定义投资阶段弹窗
    SelectPopupWindow mPopupWindow;
    //自定义投资领域弹窗
    AlertDialog mDialog;

    //用户输入的注册信息
    String string_userImg;
    String string_userPhone;
    String string_userName;
    String string_pwd;
    String string_conf_pwd;
    String string_userYanZhengMa;
    String string_idCard;
    int int_tourenId = 0;
    int int_cityId;
    int int_jieduanId;
    int int_lingyuId = 0;
    String string_jieduanMingcheng;
    int int_jigouId = 0;
    String string_jigouName;
    @InjectView(R.id.rl_3)
    RelativeLayout mRl3;
    @InjectView(R.id.rl_4)
    RelativeLayout mRl4;
    @InjectView(R.id.rl_5)
    RelativeLayout mRl5;
    @InjectView(R.id.rl_6)
    RelativeLayout mRl6;

    private String city;
    private String field;

    private Context mContext = RegistActivity.this;
    private SharedPreferenceUtil mSharedPreferenceUtil;

    private static final int MSG_SET_NUM = 2001;
    //发送验证码handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(RegistActivity.this,
                        "发送验证码成功！", Toast.LENGTH_SHORT).show();
                Message msg2 = Message.obtain();
                msg2.what = MSG_SET_NUM;
            }
            if (msg.what == 2) {
                Toast.makeText(RegistActivity.this,
                        "发送失败！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 3) {
                Toast.makeText(RegistActivity.this,
                        "短信发送错误！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 4) {
                Toast.makeText(RegistActivity.this,
                        "改手机号已注册！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    //注册handler
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(RegistActivity.this,
                        "注册成功！", Toast.LENGTH_SHORT).show();
                Message msg2 = Message.obtain();
                msg2.what = MSG_SET_NUM;
                //注册成功后跳转到登陆页面
                startActivity(new Intent(RegistActivity.this,LoginActivity.class));
            }
            if (msg.what == 0) {
                Toast.makeText(RegistActivity.this,
                        "手机号格式不正确！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 2) {
                Toast.makeText(RegistActivity.this,
                        "手机号码不存在！", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 3) {
                Toast.makeText(RegistActivity.this,
                        "短信发送错误！", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==1&&resultCode==RESULT_OK){
            string_jieduanMingcheng = data.getExtras().getString("stage");
            int_jieduanId=data.getExtras().getInt("stageId");
            mRegistJieduan.setText(string_jieduanMingcheng);
        }else if (requestCode==2&&resultCode==RESULT_OK){
            string_jigouName = data.getExtras().getString("affiliation");
            mRegistJigou.setText(string_jigouName);
        }else if (requestCode==3&&resultCode==RESULT_OK){
            field = data.getExtras().getString("field");
            mRegistTouzilingyu.setText(field);
        }else{
            city = data.getExtras().getString("city");
            int_cityId= data.getExtras().getInt("cityId");
            mRegistToucichengshi.setText(city);
        }


        if (string_jieduanMingcheng != null) {
            Log.d("***拿到的数据----", string_jieduanMingcheng);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.img_regist, R.id.post_yanzhengma, R.id.regist_cancel,
            R.id.regist, R.id.rl_3, R.id.rl_4, R.id.rl_5, R.id.rl_6})
    public void onClick(View view) {
        switch (view.getId()) {
            //注册头像
            case R.id.img_regist:
                //上传注册头像
                break;

            //发送验证码
            case R.id.post_yanzhengma:
                string_userPhone = mEdtPhone.getText().toString().trim();

                // 判断手机号码是否规范
                if (TelNumMatch.isValidPhoneNumber(string_userPhone)) {

                    //发送验证码
                    postYanZheng();
                } else {
                    Toast.makeText(RegistActivity.this, "请输入正确的手机号",
                            Toast.LENGTH_SHORT).show();
                }
                break;


            //取消
            case R.id.regist_cancel:
                finish();
                break;

            //注册
            case R.id.regist:
//               if (IDCardValidate.validate_effective())
                string_pwd = mEdtPwd.getText().toString().trim();
                string_conf_pwd = mEdtConfPwd.getText().toString().trim();
                string_userPhone = mEdtPhone.getText().toString().trim();
                string_idCard = mEdtIdNum.getText().toString().trim();
                string_jieduanMingcheng = mRegistJieduan.getText().toString().trim();
                string_jigouName = mRegistJigou.getText().toString().trim();
                field=mRegistTouzilingyu.getText().toString().trim();
                city=mRegistToucichengshi.getText().toString().trim();
                string_userYanZhengMa = mEdtYanzhengma.getText().toString().trim();
                string_userName = mEdtName.getText().toString().trim();
                register();
                break;

            //弹出投资阶段弹窗
            case R.id.rl_3:
                startActivityForResult(new Intent(RegistActivity.this, InvestmentStageActivity.class), 1);
                break;

            //弹出所属机构弹窗
            case R.id.rl_4:
                startActivityForResult(new Intent(RegistActivity.this, AffiliationActivity.class), 2);
                break;

            //弹出投资领域弹窗
            case R.id.rl_5:
                startActivityForResult(new Intent(RegistActivity.this, InvestmentFieldActivity.class), 3);
                break;

            //弹出投资城市弹窗
            case R.id.rl_6:
                startActivityForResult(new Intent(RegistActivity.this, InvestmentCityActivity.class), 4);
                break;
        }
    }

    //注册接口

    public void register() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject json = new JSONObject();
                try {
                    json.put("USERPWD", string_pwd);
                    json.put("CONFIRMUSERPWD", string_conf_pwd);
                    json.put("USERTEL", string_userPhone);
                    json.put("INVESTORNAME", string_userName);
                    json.put("INVESTORIDCARD", string_idCard);
                    json.put("USERYANZHENG", string_userYanZhengMa);
                    json.put("INVESTORYSTAGENAME", string_jieduanMingcheng);
                    json.put("INVESTORLINGID", int_lingyuId);
                    json.put("INVESTORTOUCITYID",int_cityId);
                    json.put("INVESTORYSTAGEID", int_jieduanId);
                    json.put("INVESTORORGID", int_jigouId);
                    json.put("INVESTORORGNAME", string_jigouName);
                    json.put("INVESTORID", int_tourenId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String date_params = json.toString();
                String code_params = "U002_1";// 登录接口编号
                String myData = ConnectUtils.Post_Myparams(date_params, code_params);
                Log.d("注册接口数据*****", myData);
                if (myData != null) {
                    JSONObject myDataObj = null;
                    try {
                        myDataObj = new JSONObject(myData);
                        int recode = myDataObj.getInt("Recode");
                        if (recode == 1) {
                            string_pwd=myDataObj.getString("USERPWD");
                            mSharedPreferenceUtil.setPasswd(string_pwd);
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
                        if (recode == 4) {
                            Message msg = handler1.obtainMessage();
                            msg.what = 4;
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

    //获取验证码接口
    public void postYanZheng() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                JSONObject json = new JSONObject();
                try {
                    json.put("USERTEL", string_userPhone);
                    json.put("USERYANZHENG", string_userYanZhengMa);
                    json.put("USERIMIE", SharedPreferenceUtil.getIMEI(mContext));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String date_params = json.toString();
                String code_params = "U001_1";// 登录接口编号
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
                        if (recode == 4) {
                            Message msg = handler.obtainMessage();
                            msg.what = 4;
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

}
