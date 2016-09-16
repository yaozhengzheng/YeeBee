package com.yeebee.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeebee.R;
import com.yeebee.adapter.InterviewListItemAdapter;
import com.yeebee.javabean.InterviewList;
import com.yeebee.utils.ConnectUtils;
import com.yeebee.utils.GsonTools;
import com.yeebee.utils.SharedPreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是约谈列表页面
 */

public class InterviewListActivity extends AppCompatActivity {

    @InjectView(R.id.lv_interView_list)
    ListView mLvInterViewList;

    private InterviewListItemAdapter mListItemAdapter;
    private List<InterviewList.ListBean> date;

    private SharedPreferenceUtil mSharedPreferenceUtil;

    String userId;
    int PageSize = 10;
    int CurrentPage = 1;
    private Context mContext = InterviewListActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_list);
        ButterKnife.inject(this);
//        date=getDate();
//        mListItemAdapter=new InterviewListItemAdapter(this,date);
//        mLvInterViewList.setAdapter(mListItemAdapter);
        mSharedPreferenceUtil = new SharedPreferenceUtil(InterviewListActivity.this, "userInfo");
        userId = mSharedPreferenceUtil.getUserId();
        new GetInterviewInfo().execute();
    }

    public class GetInterviewInfo extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] params) {
            return getDate();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final InterviewList result = GsonTools.getObject(o.toString(), InterviewList.class);
            if (result.getRecode() == 1) {
                date = result.getList();
                mListItemAdapter = new InterviewListItemAdapter
                        (InterviewListActivity.this, result.getList());
                mLvInterViewList.setAdapter(mListItemAdapter);
              /*  mLvInterViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        date.get(position).getId();
                        InterviewList.ListBean il= (InterviewList.ListBean) parent.getItemAtPosition(position);
                        String title=il.getProTitle();
//                        Log.d("要传递的title",title);
                        Intent intent = new Intent();
                        intent.setClass(InterviewListActivity.this, DetailsItemActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("tvInterviewTitle",title);
                        intent.putExtra("bundle",bundle);
                        startActivity(intent);
                    }
                });*/
            }
        }
    }

    private String getDate() {
        JSONObject json = new JSONObject();
        try {
            json.put("UserId", userId);
            json.put("PageSize", PageSize);
            json.put("CurrentPage", CurrentPage);
            Log.d("sp---->", userId);
            json.put("USERIMEI", SharedPreferenceUtil.getIMEI(mContext));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String date_params = json.toString();
        String code_params = "I072_5";// 登录接口编号
        String myData = ConnectUtils.Post_Myparams(date_params, code_params);
        Log.d("*******", myData);
        return myData;

    }

}
