package com.yeebee.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeebee.R;
import com.yeebee.StageItemAdapter;
import com.yeebee.javabean.CityItem;
import com.yeebee.javabean.Stage;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.GsonTools;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是注册投资阶段页面
 */
public class InvestmentStageActivity extends AppCompatActivity {


    String userId;
    int classNum = 8;
    @InjectView(R.id.lv_stage)
    ListView mLvStage;
    private SharedPreferenceUtil mSharedPreferenceUtil;

    private String mKeyName;
    private String mKeyName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_stage);
        ButterKnife.inject(this);
        mSharedPreferenceUtil = new SharedPreferenceUtil(InvestmentStageActivity.this, "userInfo");
        userId = mSharedPreferenceUtil.getUserId();
        new InvestmentStageInfo().execute();

    }

    //获取投资阶段接口
    public class InvestmentStageInfo extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            return getDate();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final Stage result = GsonTools.getObject(o.toString(), Stage.class);
            if (result.getRecode() == 1) {
                StageItemAdapter stageItemAdapter=new StageItemAdapter(
                        InvestmentStageActivity.this,result.getList());
                mLvStage.setAdapter(stageItemAdapter);
                mLvStage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Stage.ListBean date= (Stage.ListBean) parent.getItemAtPosition(position);
                        String stage=date.getKEYNAME();
                        Log.d("////////////",stage);
                        int stageId=date.getKEYID();
                        Intent intent=new Intent();
                        intent.putExtra("stage",stage);
                        intent.putExtra("stageId",stageId);
                        InvestmentStageActivity.this.setResult(RESULT_OK,intent);
                        InvestmentStageActivity.this.finish();
                    }
                });
//                List<Stage.ListBean> a = result.getList();
//                mKeyName = a.get(0).getKEYNAME();
//                Log.d("***---", mKeyName);
//                mKeyName1 = a.get(1).getKEYNAME();
//                Log.d("***---", mKeyName1);
//                int keyId=a.get(0).getKEYID();
//                Log.d("***---",""+ keyId);
//                int keyId1=a.get(0).getKEYID();
//                Log.d("***---",""+ keyId1);
//                mStage1.setText(mKeyName);
//                mStage2.setText(mKeyName1);

            }
        }

        private String getDate() {
            JSONObject json = new JSONObject();
            try {
                json.put("UserId", userId);
                json.put("USERCLASS", classNum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String date_params = json.toString();
            String code_params = "U124";// 登录接口编号
            String myData = ConnectUtils.Post_Myparams(date_params, code_params);
            Log.d("*******", myData);
            return myData;
        }

    }

   /* @OnClick({R.id.stage_1, R.id.stage_2, R.id.stage_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击按键传递参数跳转
            case R.id.stage_1:
                Intent intent=new Intent();
                intent.putExtra("mKeyName",mKeyName);
                InvestmentStageActivity.this.setResult(RESULT_OK,intent);
                InvestmentStageActivity.this.finish();
                break;
            case R.id.stage_2:
                Intent intent1=new Intent();
                intent1.putExtra("mKeyName1",mKeyName1);
                InvestmentStageActivity.this.setResult(RESULT_OK,intent1);
                InvestmentStageActivity.this.finish();
                break;
            case R.id.stage_cancel:
                finish();
                break;
        }
    }*/
}
