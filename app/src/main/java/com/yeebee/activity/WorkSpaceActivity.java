package com.yeebee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yeebee.R;
import com.yeebee.adapter.WorkspaceChooseProjectItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WorkSpaceActivity extends AppCompatActivity {

    @InjectView(R.id.workspace_choose)
    TextView mWorkspaceChoose;
    @InjectView(R.id.woekspace_audit)
    TextView mWoekspaceAudit;
    @InjectView(R.id.workspace_govern)
    TextView mWorkspaceGovern;
    @InjectView(R.id.img_shuaXin)
    ImageView mImgShuaXin;
    @InjectView(R.id.lv_workspace_choose_project1)
    ListView mLvWorkspaceChooseProject1;
    @InjectView(R.id.lv_workspace_review_project2)
    ListView mLvWorkspaceReviewProject2;
    @InjectView(R.id.lv_workspace_pipe_project3)
    ListView mLvWorkspacePipeProject3;

    private WorkspaceChooseProjectItemAdapter mChooseProjectItemAdapter;
    private List<Map<String, Object>> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_space);
        ButterKnife.inject(this);
        date=getDate();
        mChooseProjectItemAdapter=new WorkspaceChooseProjectItemAdapter(this,date);
        mLvWorkspaceChooseProject1.setAdapter(mChooseProjectItemAdapter);
        mLvWorkspaceChooseProject1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(WorkSpaceActivity.this,DetailsItemActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("imgInterviewItem","");
                bundle.putString("tvInterviewTitle","");
                bundle.putString("tvInterviewDigest","");
                bundle.putString("tvInterviewStatus","");
                bundle.putString("tvChooseProjectMoney","");
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
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

//    @OnClick(R.id.lv_workspace_choose_project1)
//    public void onClick() {
//    }
}
