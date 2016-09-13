package com.yeebee.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yeebee.R;
import com.yeebee.javabean.InterviewList;

/**
 * 这是约谈列表adapter
 */
public class InterviewListItemAdapter extends BaseAdapter {

    private List<InterviewList.ListBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public InterviewListItemAdapter(Context context,List<InterviewList.ListBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public InterviewList.ListBean getItem(int position) {
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
        initializeViews((InterviewList.ListBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(InterviewList.ListBean object, ViewHolder holder) {
        //TODO implement
        //下载图片---.error(R.mipmap.ic_launcher)
        Glide.with(context).load(object.getProImage()).error(R.mipmap.ic_launcher).into(holder.imgInterviewItem);
        holder.tvInterviewTitle.setText(object.getProTitle());
        Log.d("**---**", object.getProTitle());
        holder.tvInterviewDigest.setText(object.getProDesc());
        if (object.getInterviewsType()==1){
            holder.tvInterviewStatus.setText("已约谈");
        }else{
            holder.tvInterviewStatus.setText("已放弃");
        }

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
