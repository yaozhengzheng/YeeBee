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

/**
 * 这是约谈列表adapter
 */
public class InterviewListItemAdapter extends BaseAdapter {

    private List<Map<String, Object>> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public InterviewListItemAdapter(Context context,List<Map<String, Object>> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Map<String, Object> getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.interview_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Map<String, Object>) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Map<String, Object> object, ViewHolder holder) {
        //TODO implement
        holder.imgInterviewItem.setImageResource(R.mipmap.logo);
        holder.tvInterviewTitle.setText("亿蜂平台");
        holder.tvInterviewDigest.setText("一指导性以及提升性为主的深度创业指导");
        holder.tvInterviewStatus.setText("已约谈");

    }

    protected class ViewHolder {
        private ImageView imgInterviewItem;
        private TextView tvInterviewTitle;
        private TextView tvInterviewDigest;
        private TextView tvInterviewStatus;

        public ViewHolder(View view) {
            imgInterviewItem = (ImageView) view.findViewById(R.id.img_interview_item);
            tvInterviewTitle = (TextView) view.findViewById(R.id.tv_interview_title);
            tvInterviewDigest = (TextView) view.findViewById(R.id.tv_interview_digest);
            tvInterviewStatus = (TextView) view.findViewById(R.id.tv_interview_status);
        }
    }
}
