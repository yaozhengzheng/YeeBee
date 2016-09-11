package com.yeebee.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeebee.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是约谈列表项目信息页面
 */
public class ProjectInformationActivity extends AppCompatActivity {

    @InjectView(R.id.img_project_information)
    ImageView mImgProjectInformation;
    @InjectView(R.id.tv_title_information)
    TextView mTvTitleInformation;
    @InjectView(R.id.tv_digest_information)
    TextView mTvDigestInformation;
    @InjectView(R.id.ll_project_information)
    LinearLayout mLlProjectInformation;
    @InjectView(R.id.rl_information)
    RelativeLayout mRlInformation;
    @InjectView(R.id.tv_name_information)
    TextView mTvNameInformation;
    @InjectView(R.id.tv_name_information1)
    TextView mTvNameInformation1;
    @InjectView(R.id.tv_position_information)
    TextView mTvPositionInformation;
    @InjectView(R.id.tv_position_information1)
    TextView mTvPositionInformation1;
    @InjectView(R.id.tv_number_information)
    TextView mTvNumberInformation;
    @InjectView(R.id.tv_number_information1)
    TextView mTvNumberInformation1;
    @InjectView(R.id.ll_information)
    LinearLayout mLlInformation;
    @InjectView(R.id.rl_information1)
    RelativeLayout mRlInformation1;
    @InjectView(R.id.tv_showEdt)
    TextView mTvShowEdt;
    @InjectView(R.id.btn_cancel)
    Button mBtnCancel;
    @InjectView(R.id.activity_project_information)
    RelativeLayout mActivityProjectInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_information);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_cancel)
    public void onClick() {
        //返回列表

    }
}
