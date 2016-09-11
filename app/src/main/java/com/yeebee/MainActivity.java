package com.yeebee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yeebee.activity.MessageActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @InjectView(R.id.et_user_id)
    EditText mEtUserId;
    @InjectView(R.id.et_user_pw)
    EditText mEtUserPw;
    @InjectView(R.id.btn)
    Button mBtn;
    @InjectView(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        onClick();

    }

    @OnClick(R.id.btn)
    public void onClick() {
        startActivity(new Intent(MainActivity.this, MessageActivity.class));
    }
}
