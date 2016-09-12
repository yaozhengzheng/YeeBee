package com.yeebee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeebee.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 16245 on 2016/09/12.
 */

public class DetailsItemActivity extends AppCompatActivity {

    @InjectView(R.id.img_details_item)
    ImageView mImgDetailsItem;
    @InjectView(R.id.tv_details_jieDuan)
    TextView mTvDetailsJieDuan;
    @InjectView(R.id.tv_details_lingYu)
    TextView mTvDetailsLingYu;
    @InjectView(R.id.tv_details_diZhi)
    TextView mTvDetailsDiZhi;
    @InjectView(R.id.tv_details_money)
    TextView mTvDetailsMoney;
    @InjectView(R.id.tv_details_project_details)
    TextView mTvDetailsProjectDetails;
    @InjectView(R.id.tv_details_questioning)
    TextView mTvDetailsQuestioning;
    @InjectView(R.id.tv_details_checkBP)
    TextView mTvDetailsCheckBP;
    @InjectView(R.id.tv_details_attention)
    TextView mTvDetailsAttention;
    @InjectView(R.id.tv_details_attention1)
    TextView mTvDetailsAttention1;
    @InjectView(R.id.tv_details_abandon)
    TextView mTvDetailsAbandon;
    @InjectView(R.id.tv_details_title)
    TextView mTvDetailsTitle;

    String img;
    String Title;
    String money;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_item);
        ButterKnife.inject(this);
        intentDate();
    }

    // 获取点击item传过来的参数
    private void intentDate() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        img = bundle.getString("imgInterviewItem");
        Title = bundle.getString("tvInterviewTitle");
        money = bundle.getString("tvChooseProjectMoney");
        mTvDetailsTitle.setText(Title);
        mTvDetailsMoney.setText(money);
        mImgDetailsItem.setImageResource(R.mipmap.logo);
    }
}
