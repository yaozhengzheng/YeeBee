package com.yeebee.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yeebee.adapter.MessageItemAdapter;
import com.yeebee.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 这是消息页面
 */
public class MessageActivity extends AppCompatActivity {

    @InjectView(R.id.img_message)
    ImageView mImgMessage;
    @InjectView(R.id.rl_message)
    RelativeLayout mRlMessage;
    @InjectView(R.id.lv_message)
    ListView mLvMessage;
    @InjectView(R.id.activity_message)
    RelativeLayout mActivityMessage;

    private MessageItemAdapter mItemAdapter;
    private List<Map<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.inject(this);
        data = getData();
        mItemAdapter=new MessageItemAdapter(this,data);
        mLvMessage.setAdapter(mItemAdapter);
    }
    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("imgMessageItem", R.mipmap.search);
            map.put("tvTitleItem", "");
            map.put("tvDigestItem", "");
            map.put("tvNameItem", "");
            map.put("tvTime1Item", "");
            list.add(map);
        }
        return list;
    }

//    @OnClick({R.id.img_message, R.id.rl_message, R.id.lv_message, R.id.activity_message})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.img_message:
//                break;
//            case R.id.rl_message:
//                break;
//            case R.id.lv_message:
//                break;
//            case R.id.activity_message:
//                break;
//        }
//    }
}
