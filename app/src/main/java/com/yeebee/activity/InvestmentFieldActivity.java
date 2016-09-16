package com.yeebee.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeebee.adapter.FieldItemAdapter;
import com.yeebee.R;
import com.yeebee.javabean.FieldItem;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.GsonTools;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是注册 投资领域页面
 */
public class InvestmentFieldActivity extends AppCompatActivity {

    @InjectView(R.id.field_item)
    ListView mFieldItem;

    String userId;
    int classNum = 110;
    private SharedPreferenceUtil mSharedPreferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_field);
        ButterKnife.inject(this);
        mSharedPreferenceUtil = new SharedPreferenceUtil(InvestmentFieldActivity.this, "userInfo");
        userId = mSharedPreferenceUtil.getUserId();
        new FieldItems().execute();
    }

    public class FieldItems extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            return getDate();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final FieldItem result = GsonTools.getObject(o.toString(), FieldItem.class);
            if (result.getRecode()==1){
                FieldItemAdapter fieldItemAdapter=new FieldItemAdapter(
                        InvestmentFieldActivity.this,result.getList());
                mFieldItem.setAdapter(fieldItemAdapter);
                mFieldItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FieldItem.ListBean date= (FieldItem.ListBean) parent.getItemAtPosition(position);
                        String field=date.getKEYNAME();
                        Log.d("////////////",field);
                        Intent intent=new Intent();
                        intent.putExtra("field",field);
                        InvestmentFieldActivity.this.setResult(RESULT_OK,intent);
                        InvestmentFieldActivity.this.finish();
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
        String code_params = "U124";// 投资领域接口编号
        String myData = ConnectUtils.Post_Myparams(date_params, code_params);
        Log.d("*******", myData);
        return myData;

    }
}
