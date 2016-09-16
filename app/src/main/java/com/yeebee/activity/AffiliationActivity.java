package com.yeebee.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeebee.AffiliationItemAdapter;
import com.yeebee.R;
import com.yeebee.javabean.Affiliation;
import com.yeebee.javabean.CityItem;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.GsonTools;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是注册所属机构界面
 */
public class AffiliationActivity extends AppCompatActivity {

    @InjectView(R.id.lv_affiliation)
    ListView mLvAffiliation;

    String userId;
    int classNum = 6;
    private SharedPreferenceUtil mSharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliation);
        ButterKnife.inject(this);
        mSharedPreferenceUtil = new SharedPreferenceUtil(AffiliationActivity.this, "userInfo");
        userId = mSharedPreferenceUtil.getUserId();
        new Affiliations().execute();
    }

    public class Affiliations extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            return getDate();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final Affiliation result = GsonTools.getObject(o.toString(), Affiliation.class);
            if (result.getRecode() == 1) {
                AffiliationItemAdapter affiliationItemAdapter = new AffiliationItemAdapter(
                        AffiliationActivity.this, result.getList());
                mLvAffiliation.setAdapter(affiliationItemAdapter);
                mLvAffiliation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Affiliation.ListBean date= (Affiliation.ListBean) parent.getItemAtPosition(position);
                        String affiliation=date.getKEYNAME();
                        Log.d("////////////",affiliation);
                        Intent intent=new Intent();
                        intent.putExtra("affiliation",affiliation);
                        AffiliationActivity.this.setResult(RESULT_OK,intent);
                        AffiliationActivity.this.finish();
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
        String code_params = "U124";// 所属机构接口编号
        String myData = ConnectUtils.Post_Myparams(date_params, code_params);
        Log.d("*******", myData);
        return myData;

    }

}
