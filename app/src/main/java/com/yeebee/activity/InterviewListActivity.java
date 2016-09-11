package com.yeebee.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.yeebee.R;
import com.yeebee.adapter.InterviewListItemAdapter;

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
    private List<Map<String,Object>> date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_list);
        ButterKnife.inject(this);
        date=getDate();
        mListItemAdapter=new InterviewListItemAdapter(this,date);
        mLvInterViewList.setAdapter(mListItemAdapter);
    }

    private List<Map<String, Object>> getDate() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("imgInterviewItem", R.mipmap.search);
            map.put("tvInterviewTitle", "");
            map.put("tvInterviewDigest", "");
            map.put("tvInterviewStatus", "");
            list.add(map);
        }
        return list;
    }


}
