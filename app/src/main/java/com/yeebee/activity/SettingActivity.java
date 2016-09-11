package com.yeebee.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 *设置页面
 */
public class SettingActivity extends AppCompatActivity {

    @InjectView(R.id.img_setting_photo)
    ImageView mImgSettingPhoto;
    @InjectView(R.id.tv_setting_name)
    TextView mTvSettingName;
    @InjectView(R.id.img_myTalk)
    ImageView mImgMyTalk;
    @InjectView(R.id.img_change_password)
    ImageView mImgChangePassword;
    @InjectView(R.id.img_contact_us)
    ImageView mImgContactUs;
    @InjectView(R.id.img_about_us)
    ImageView mImgAboutUs;
    @InjectView(R.id.img_check_update)
    ImageView mImgCheckUpdate;
    @InjectView(R.id.img_platform_agreement)
    ImageView mImgPlatformAgreement;
    @InjectView(R.id.btn_exit)
    Button mBtnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeting);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.img_myTalk, R.id.img_change_password, R.id.img_contact_us, R.id.img_about_us, R.id.img_check_update, R.id.img_platform_agreement, R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            //跳转到约谈列表
            case R.id.img_myTalk:
                Toast.makeText(this, "约谈列表", Toast.LENGTH_SHORT).show();
                break;

            //跳转到重置密码
            case R.id.img_change_password:
                Toast.makeText(this, "重置密码", Toast.LENGTH_SHORT).show();
                break;

            //跳转到联系我们
            case R.id.img_contact_us:
                Toast.makeText(this, "联系我们", Toast.LENGTH_SHORT).show();
                break;

            //跳转到关于我们
            case R.id.img_about_us:
                Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
                break;

            //跳转到检查更新
            case R.id.img_check_update:
                Toast.makeText(this, "检查更新", Toast.LENGTH_SHORT).show();
                break;

            //跳转到平台协议
            case R.id.img_platform_agreement:
                Toast.makeText(this, "平台协议", Toast.LENGTH_SHORT).show();
                break;

            //退出
            case R.id.btn_exit:
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
