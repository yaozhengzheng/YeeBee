package com.yeebee.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeebee.adapter.CityItemAdapter;
import com.yeebee.R;
import com.yeebee.javabean.CityItem;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.GsonTools;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是注册投资城市页面
 */
public class InvestmentCityActivity extends AppCompatActivity {

    @InjectView(R.id.city_item)
    ListView mCityItem;

    String userId;
    int classNum = 7;
    private SharedPreferenceUtil mSharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_city);
        ButterKnife.inject(this);
        mSharedPreferenceUtil = new SharedPreferenceUtil(InvestmentCityActivity.this, "userInfo");
        userId = mSharedPreferenceUtil.getUserId();
        new ChooseCity().execute();
    }

    public class ChooseCity extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            return getDate();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final CityItem result = GsonTools.getObject(o.toString(), CityItem.class);
            if (result.getRecode()==1){
                CityItemAdapter cityItemAdapter=new CityItemAdapter(
                        InvestmentCityActivity.this,result.getList());
                mCityItem.setAdapter(cityItemAdapter);
                mCityItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        CityItem.ListBean date= (CityItem.ListBean)
                                parent.getItemAtPosition(position);
                        String city=date.getKEYNAME();
                        Log.d("////////////",city);
                        int cityId=date.getKEYID();
                        Intent intent=new Intent();
                        intent.putExtra("city",city);
                        intent.putExtra("cityId",cityId);
                        InvestmentCityActivity.this.setResult(RESULT_OK,intent);
                        InvestmentCityActivity.this.finish();
                    }
                });
            }
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
        String code_params = "U124";// 投资城市接口编号
        String myData = ConnectUtils.Post_Myparams(date_params, code_params);
        Log.d("*******", myData);
        return myData;

    }

}
