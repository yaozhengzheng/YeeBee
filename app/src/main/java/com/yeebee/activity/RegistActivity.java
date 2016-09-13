package com.yeebee.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.FirstEvent;
import com.yeebee.R;
import com.yeebee.SelectPopupWindow;

import com.yeebee.model.CityModel;
import com.yeebee.model.DistrictModel;


import com.yeebee.model.ProvinceModel;
import com.yeebee.service.XmlParserHandler;
import com.yeebee.widget.OnWheelChangedListener;
import com.yeebee.widget.WheelView;
import com.yeebee.widget.adapters.ArrayWheelAdapter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.InputStream;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是注册页面
 */
public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.img_regist)
    ImageView mImgRegist;
    @InjectView(R.id.edt_name)
    EditText mEdtName;
    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.edt_idNum)
    EditText mEdtIdNum;
    @InjectView(R.id.regist_jieduan)
    TextView mRegistJieduan;
    @InjectView(R.id.img_i1)
    ImageView mImg1;
    @InjectView(R.id.regist_jigou)
    TextView mRegistJigou;
    @InjectView(R.id.img_i2)
    ImageView mImg2;
    @InjectView(R.id.regist_touzilingyu)
    TextView mRegistTouzilingyu;
    @InjectView(R.id.img_i3)
    ImageView mImg3;
    @InjectView(R.id.regist_toucichengshi)
    TextView mRegistToucichengshi;
    @InjectView(R.id.img_i4)
    ImageView mImg4;
    @InjectView(R.id.edt_phone)
    EditText mEdtPhone;
    @InjectView(R.id.edt_yanzhengma)
    EditText mEdtYanzhengma;
    @InjectView(R.id.post_yanzhengma)
    Button mPostYanzhengma;
    @InjectView(R.id.tv_tiaokuan)
    TextView mTvTiaokuan;
    @InjectView(R.id.regist_cancel)
    Button mRegistCancel;
    @InjectView(R.id.regist)
    Button mRegist;

    //自定义投资阶段弹窗
    SelectPopupWindow mPopupWindow;
    //自定义投资领域弹窗
    AlertDialog mDialog;

    //用户输入的注册信息
    String name;
    String idCard;
    String phone;

    //城市弹窗
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_regist);

        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @OnClick({R.id.img_regist, R.id.post_yanzhengma, R.id.regist_cancel,
            R.id.regist, R.id.img_i1, R.id.img_i2, R.id.img_i3, R.id.img_i4})
    public void onClick(View view) {
        switch (view.getId()) {
            //注册头像
            case R.id.img_regist:
                //上传注册头像
                break;

            //发送验证码
            case R.id.post_yanzhengma:

                break;

            //取消
            case R.id.regist_cancel:
                break;

            //注册
            case R.id.regist:
                break;

            //弹出投资阶段弹窗
            case R.id.img_i1:
                showInvestmentStage();
                break;

            //弹出所属机构弹窗
            case R.id.img_i2:
                Toast.makeText(this, "suoshujigou", Toast.LENGTH_SHORT).show();
                break;

            //弹出投资领域弹窗
            case R.id.img_i3:
                showInvestmentFieldDialog();
                break;

            //弹出投资城市弹窗
            case R.id.img_i4:
                PopWindow popWindow = new PopWindow(RegistActivity.this);
                popWindow.showAtLocation(findViewById(R.id.activity_register), Gravity.CENTER|Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    //显示投资领域窗口
    private void showInvestmentFieldDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.investmentfield_dialog,
                (ViewGroup) findViewById(R.id.investmentField));
        new AlertDialog.Builder(RegistActivity.this).
                setTitle("投资领域").setView(layout).
                setPositiveButton("确定", null).show();
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getInfo();
        mRegistToucichengshi.setText(msg);
    }

    //显示投资阶段窗口
    private void showInvestmentStage() {
        mPopupWindow = new SelectPopupWindow(RegistActivity.this, itemsClick);
        //显示窗口
        mPopupWindow.showAtLocation(RegistActivity.this.
                        findViewById(R.id.activity_register),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    //为投资阶段窗口实现监听类
    private View.OnClickListener itemsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    //显示城市三级联动
    public class PopWindow extends PopupWindow implements OnWheelChangedListener, View.OnClickListener {

        Context context;
        private View view;

        public PopWindow(Context context) {
            super(context);
            this.view = LayoutInflater.from(context).inflate(R.layout.pop, null);
            this.context = context;
            // 设置外部可点击
            this.setOutsideTouchable(true);
            /* 设置弹出窗口特征 */
            // 设置视图
            this.setContentView(this.view);
            // 设置弹出窗体的宽和高
            this.setHeight(700);
            this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

            // 设置弹出窗体可点击
            this.setFocusable(true);

            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            // 设置弹出窗体的背景
            this.setBackgroundDrawable(dw);
            // 设置弹出窗体显示时的动画，从底部向上弹出
            this.setAnimationStyle(R.style.Pop_Window);
            setUpViews();
            setUpListener();
            setUpData();
        }

        /**
         * 所有省
         */
        protected String[] mProvinceDatas;
        /**
         * key - 省 value - 市
         */
        protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
        /**
         * key - 市 values - 区
         */
        protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

        /**
         * key - 区 values - 邮编
         */
        protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

        /**
         * 当前省的名称
         */
        protected String mCurrentProviceName;
        /**
         * 当前市的名称
         */
        protected String mCurrentCityName;
        /**
         * 当前区的名称
         */
        protected String mCurrentDistrictName = "";

        /**
         * 当前区的邮政编码
         */
        protected String mCurrentZipCode = "";

        /**
         * 解析省市区的XML数据
         */

        protected void initProvinceDatas() {
            List<ProvinceModel> provinceList = null;
            AssetManager asset = getAssets();
            try {
                InputStream input = asset.open("province_data.xml");
                // 创建一个解析xml的工厂对象
                SAXParserFactory spf = SAXParserFactory.newInstance();
                // 解析xml
                SAXParser parser = spf.newSAXParser();
                XmlParserHandler handler = new XmlParserHandler();
                parser.parse(input, handler);
                input.close();
                // 获取解析出来的数据
                provinceList = handler.getDataList();
                //*/ 初始化默认选中的省、市、区
                if (provinceList != null && !provinceList.isEmpty()) {
                    mCurrentProviceName = provinceList.get(0).getName();
                    List<CityModel> cityList = provinceList.get(0).getCityList();
                    if (cityList != null && !cityList.isEmpty()) {
                        mCurrentCityName = cityList.get(0).getName();
                        List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                        mCurrentDistrictName = districtList.get(0).getName();
                        mCurrentZipCode = districtList.get(0).getZipcode();
                    }
                }
                //*/
                mProvinceDatas = new String[provinceList.size()];
                for (int i = 0; i < provinceList.size(); i++) {
                    // 遍历所有省的数据
                    mProvinceDatas[i] = provinceList.get(i).getName();
                    List<CityModel> cityList = provinceList.get(i).getCityList();
                    String[] cityNames = new String[cityList.size()];
                    for (int j = 0; j < cityList.size(); j++) {
                        // 遍历省下面的所有市的数据
                        cityNames[j] = cityList.get(j).getName();
                        List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                        String[] distrinctNameArray = new String[districtList.size()];
                        DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                        for (int k = 0; k < districtList.size(); k++) {
                            // 遍历市下面所有区/县的数据
                            DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                            // 区/县对于的邮编，保存到mZipcodeDatasMap
                            mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                            distrinctArray[k] = districtModel;
                            distrinctNameArray[k] = districtModel.getName();
                        }
                        // 市-区/县的数据，保存到mDistrictDatasMap
                        mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                    }
                    // 省-市的数据，保存到mCitisDatasMap
                    mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {

            }
        }

        private void setUpViews() {
            mViewProvince = (WheelView) view.findViewById(R.id.id_province);
            mViewCity = (WheelView) view.findViewById(R.id.id_city);
            mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
            mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        }

        private void setUpListener() {
            // 添加change事件
            mViewProvince.addChangingListener(this);
            // 添加change事件
            mViewCity.addChangingListener(this);
            // 添加change事件
            mViewDistrict.addChangingListener(this);
            // 添加onclick事件
            mBtnConfirm.setOnClickListener(this);
        }

        private void setUpData() {
            initProvinceDatas();
            mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(RegistActivity.this, mProvinceDatas));
            // 设置可见条目数量
            mViewProvince.setVisibleItems(7);
            mViewCity.setVisibleItems(7);
            mViewDistrict.setVisibleItems(7);
            updateCities();
            updateAreas();
        }

        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            // TODO Auto-generated method stub
            if (wheel == mViewProvince) {
                updateCities();
            } else if (wheel == mViewCity) {
                updateAreas();
            } else if (wheel == mViewDistrict) {
                mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
                mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
            }
        }

        /**
         * 根据当前的市，更新区WheelView的信息
         */
        private void updateAreas() {
            int pCurrent = mViewCity.getCurrentItem();
            mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
            String[] areas = mDistrictDatasMap.get(mCurrentCityName);

            if (areas == null) {
                areas = new String[]{""};
            }
            mViewDistrict.setViewAdapter(new ArrayWheelAdapter<>(context, areas));
            mViewDistrict.setCurrentItem(0);
        }

        /**
         * 根据当前的省，更新市WheelView的信息
         */
        private void updateCities() {
            int pCurrent = mViewProvince.getCurrentItem();
            mCurrentProviceName = mProvinceDatas[pCurrent];
            String[] cities = mCitisDatasMap.get(mCurrentProviceName);
            if (cities == null) {
                cities = new String[]{""};
            }
            mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
            mViewCity.setCurrentItem(0);
            updateAreas();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_confirm:
                    showSelectedResult();
                    break;
                default:
                    break;
            }
        }

        private void showSelectedResult() {
            EventBus.getDefault().post(new FirstEvent(mCurrentProviceName + "," + mCurrentCityName + ","
                    + mCurrentDistrictName + "," + mCurrentZipCode));

        }
    }
}
