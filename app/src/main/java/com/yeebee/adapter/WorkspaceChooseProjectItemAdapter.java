package com.yeebee.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeebee.R;

public class WorkspaceChooseProjectItemAdapter extends BaseAdapter {

    private List<Map<String,Object>> objects = new ArrayList<Map<String,Object>>();

    private Context context;
    private LayoutInflater layoutInflater;

    public WorkspaceChooseProjectItemAdapter(Context context,List<Map<String,Object>> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Map<String,Object> getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.workspace_choose_project_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Map<String,Object>)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Map<String,Object> object, ViewHolder holder) {
        //TODO implement
        holder.imgChooseProject.setImageResource(R.mipmap.logo);
        holder.tvChooseProjectTitle.setText("亿蜂平台");
        holder.tvChooseProjectDegist.setText("以指导性为目的的开发平台");
        holder.tvChooseProjectBianqian1.setText("种子期");
        holder.tvChooseProjectBianqian2.setText("互联网");
        holder.tvChooseProjectMoney.setText("融资需求：100万");

    }

    protected class ViewHolder {
        private ImageView imgChooseProject;
    private TextView tvChooseProjectTitle;
    private TextView tvChooseProjectDegist;
    private TextView tvChooseProjectBianqian1;
    private TextView tvChooseProjectBianqian2;
    private TextView tvChooseProjectMoney;

        public ViewHolder(View view) {
            imgChooseProject = (ImageView) view.findViewById(R.id.img_choose_project);
            tvChooseProjectTitle = (TextView) view.findViewById(R.id.tv_choose_project_title);
            tvChooseProjectDegist = (TextView) view.findViewById(R.id.tv_choose_project_degist);
            tvChooseProjectBianqian1 = (TextView) view.findViewById(R.id.tv_choose_project_bianqian1);
            tvChooseProjectBianqian2 = (TextView) view.findViewById(R.id.tv_choose_project_bianqian2);
            tvChooseProjectMoney = (TextView) view.findViewById(R.id.tv_choose_project_money);
        }
    }
}
