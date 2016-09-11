package com.yeebee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * （失败）
 * 获取城市列表
 * Created by 16245 on 2016/09/10.
 */

public class ActionSheetActivity extends AppCompatActivity {
    private View.OnClickListener mClickListener;
    private TextView mProvinceTextView;
    private TextView mCityTextView;
    private TextView mDistinguishTextView;
    private ArrayList<String> mProvinceList;
    private ArrayList<String> mCityList;
    private ArrayList<String> mDistinguishList;
    private JSONObject mJsonObject = null;
    private int mProvinceId;// 省id
    private int mCityId;// 市id
    private int mDistinguishId;// 区id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_sheet);
        setListener();
        mProvinceTextView = (TextView) findViewById(R.id.publish_job_province_choose);
        mCityTextView = (TextView) findViewById(R.id.publish_job_city_choose);
        mDistinguishTextView = (TextView) findViewById(R.id.publish_job_distinguish_choose);
        mProvinceTextView.setOnClickListener(mClickListener);
        mCityTextView.setOnClickListener(mClickListener);
        mDistinguishTextView.setOnClickListener(mClickListener);
    }

    /* 监听实现 */
    private void setListener() {
        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (v.getId()) {
                /* 检索类型监听 */
                    case R.id.publish_job_province_choose:
//                        DialogShow(getProvinceData(), 1);
                        break;
                    case R.id.publish_job_city_choose:
                        DialogShow(mCityList, 2);
                        break;
                    case R.id.publish_job_distinguish_choose:
                        DialogShow(mDistinguishList, 3);
                        break;
                }
            }
        };
    }

    /* 显示省市区的dialog */
    public void DialogShow(final ArrayList<String> mList, final int type) {
        ActionSheetDialog dialog = new ActionSheetDialog(ActionSheetActivity.this)
                .builder().setTitle("请选择操作").setCancelable(false)
                .setCanceledOnTouchOutside(false);
        for (int i = 0; i < mList.size(); i++) {
            final String item = mList.get(i);
            final int position = i;
            dialog.addSheetItem(mList.get(i), ActionSheetDialog.SheetItemColor.Blue,
                    new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            if (type == 1) {
                                mProvinceTextView.setText(item);
                                mProvinceId = position;
                                mCityList = getCityData();
                                // mCityTextView.setText(mCityList.get(0));
                                mCityTextView.setText("市");
                                mDistinguishTextView.setText("区");
                                mCityTextView.setEnabled(true);
                                mDistinguishTextView.setEnabled(false);
                            } else if (type == 2) {
                                mCityTextView.setText(item);
                                mCityId = position;
                                mDistinguishList = getDistinguish();
                                // mDistinguishTextView.setText(mDistinguishList
                                // .get(0));
                                mDistinguishTextView.setText("区");
                                mDistinguishTextView.setEnabled(true);
                            } else {
                                mDistinguishId = position;
                                mDistinguishTextView.setText(item);
                            }
                        }
                    });
        }
        dialog.show();
    }

    /* 得到省 */
   /* private ArrayList<String> getProvinceData() {
        // TODO Auto-generated method stub
        InputStream inputStream = getResources()
                .openRawResource(R.raw.location);
        String str = Utils.getFileToString(inputStream);

        try {
            mJsonObject = new JSONObject(str);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ArrayList<String> mList = new ArrayList<String>();
        for (int i = 0; i < mJsonObject.length(); i++) {
            try {
                JSONObject provinceObject = mJsonObject.getJSONObject((i + 1)
                        + "");
                mList.add(provinceObject.getString("province_name"));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return mList;
    }*/

    /* 得到市 */
    private ArrayList<String> getCityData() {
        JSONObject cityObject = null;
        try {
            cityObject = mJsonObject.getJSONObject((mProvinceId + 1) + "")
                    .getJSONObject("city");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> mList = new ArrayList<String>();
        for (int i = 0; i < cityObject.length(); i++) {
            try {
                JSONObject cityJSONObject = cityObject.getJSONObject((i + 1)
                        + "");
                mList.add(cityJSONObject.getString("city_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mList;
    }

    /* 得到区 */
    public ArrayList<String> getDistinguish() {
        JSONObject areaObject = null;
        try {
            areaObject = mJsonObject.getJSONObject((mProvinceId + 1) + "")
                    .getJSONObject("city").getJSONObject((mCityId + 1) + "")
                    .getJSONObject("area");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> mList = new ArrayList<String>();
        for (int i = 0; i < areaObject.length(); i++) {
            try {
                String cityString = areaObject.getString((i + 1) + "");
                mList.add(cityString.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


}
