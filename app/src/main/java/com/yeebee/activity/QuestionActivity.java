package com.yeebee.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeebee.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 这是约谈页面
 */
public class QuestionActivity extends AppCompatActivity {

    @InjectView(R.id.tv_title_quest)
    TextView mTvTitleQuest;
    @InjectView(R.id.img_message_item)
    ImageView mImgMessageItem;
    @InjectView(R.id.tv_title_item)
    TextView mTvTitleItem;
    @InjectView(R.id.tv_digest_item)
    TextView mTvDigestItem;
    @InjectView(R.id.ll_quest)
    LinearLayout mLlQuest;
    @InjectView(R.id.img_quest_date)
    ImageView mImgQuestDate;
    @InjectView(R.id.rl_date)
    RelativeLayout mRlDate;
    @InjectView(R.id.rl_leave)
    RelativeLayout mRlLeave;
    @InjectView(R.id.edt_quest)
    EditText mEdtQuest;
    @InjectView(R.id.quest_push)
    Button mQuestPush;
    @InjectView(R.id.activity_question)
    RelativeLayout mActivityQuestion;
    @InjectView(R.id.date)
    TextView mDate;
    @InjectView(R.id.tv_showNumber)
    TextView mTvShowNumber;

    //自定义显示年月日
    private int year;
    private int month;
    private int day;
    //自定义字数限制
    private int num = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.inject(this);
        //初始化Calendar日历对象
        Calendar mycalendar = Calendar.getInstance();
        year = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        //设置输入框字数控制
        initEdt();
        //设置textView不同颜色
        setNumber();
    }

    //设置textView不同颜色
    private void setNumber() {
        //创建一个 SpannableString对象
        SpannableString sp = new SpannableString("还可以输入150个字");
        sp.setSpan(new ForegroundColorSpan(Color.RED), 5, 8,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //SpannableString对象设置给TextView
        mTvShowNumber.setText(sp);
        //设置TextView可点击
        mTvShowNumber.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //控制输入框输入字数
    private void initEdt() {
        mEdtQuest.addTextChangedListener(new TextWatcher() {

            private CharSequence wordNum;//记录输入的字数
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //实时记录输入字数
                wordNum = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                //TextView显示剩余字数
                mTvShowNumber.setText("还可以输入" + number + "个字");
                selectionStart = mEdtQuest.getSelectionStart();
                selectionEnd = mEdtQuest.getSelectionEnd();
                if (wordNum.length() > num) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    mEdtQuest.setText(s);
                    mEdtQuest.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }


    @OnClick({R.id.img_quest_date, R.id.edt_quest, R.id.quest_push})
    public void onClick(View view) {
        switch (view.getId()) {
            //设置日期
            case R.id.img_quest_date:
                setDateDialog();
                break;
            //输入内容
            case R.id.edt_quest:
                break;
            //提交
            case R.id.quest_push:
                break;
        }
    }

    //显示日历弹窗
    public void setDateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                mDate.setText(year + "年" + (++month) + "月" + day + "日");
            }
        };
        //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
        DatePickerDialog dialog = new DatePickerDialog(QuestionActivity.this, 0, listener, year, month, day);
        dialog.show();
    }

}
