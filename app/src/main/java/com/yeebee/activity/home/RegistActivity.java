package com.yeebee.activity.home;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yeebee.R;
import com.yeebee.SelectPopupWindow;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);
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
                Toast.makeText(this, "touzichensghi", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //显示投资领域窗口
    private void showInvestmentFieldDialog(){
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.investmentfield_dialog,
                (ViewGroup) findViewById(R.id.investmentField));
        new AlertDialog.Builder(RegistActivity.this).
                setTitle("投资领域").setView(layout).
                setPositiveButton("确定",null).show();
       /* //获取屏幕宽高
        WindowManager manager=getWindowManager();
        Display display=manager.getDefaultDisplay();
        //获取对话框当前的参数值
        android.view.WindowManager.LayoutParams params=mDialog.getWindow().getAttributes();
        //高度设为屏幕的0.3
        params.height= (int) (display.getHeight()*0.3);
        params.width=display.getWidth();
        //设置生效
        mDialog.getWindow().setAttributes(params);*/

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

}
