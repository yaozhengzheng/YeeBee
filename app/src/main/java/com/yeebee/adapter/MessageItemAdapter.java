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

import static android.R.attr.data;

/**
 * 这是消息adapter
 */
public class MessageItemAdapter extends BaseAdapter {

    private List<Map<String, Object>> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MessageItemAdapter(Context context,List<Map<String, Object>> objects) {
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
            convertView = layoutInflater.inflate(R.layout.message_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Map<String, Object>)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Map<String, Object> object, ViewHolder holder) {
        //TODO implement
        holder.imgMessageItem.setImageResource(R.mipmap.logo);
        holder.tvTitleItem.setText("亿蜂平台");
        holder.tvDigestItem.setText("摘要摘要摘重要");
        holder.tvNameItem.setText("周先生");
        holder.tvTime1Item.setText("1分钟前");
    }

    protected class ViewHolder {
        private ImageView imgMessageItem;
    private TextView tvTitleItem;
    private TextView tvDigestItem;
    private ImageView imgManItem;
    private TextView tvNameItem;
    private ImageView imgTimeItem;
    private TextView tvTime1Item;

        public ViewHolder(View view) {
            imgMessageItem = (ImageView) view.findViewById(R.id.img_message_item);
            tvTitleItem = (TextView) view.findViewById(R.id.tv_title_item);
            tvDigestItem = (TextView) view.findViewById(R.id.tv_digest_item);
            imgManItem = (ImageView) view.findViewById(R.id.img_man_item);
            tvNameItem = (TextView) view.findViewById(R.id.tv_name_item);
            imgTimeItem = (ImageView) view.findViewById(R.id.img_time_item);
            tvTime1Item = (TextView) view.findViewById(R.id.tv_time1_item);
        }
    }
}
