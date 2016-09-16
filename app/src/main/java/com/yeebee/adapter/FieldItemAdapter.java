package com.yeebee.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeebee.R;
import com.yeebee.javabean.FieldItem;

public class FieldItemAdapter extends BaseAdapter {

    private List<FieldItem.ListBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public FieldItemAdapter(Context context, List<FieldItem.ListBean> list) {
        this.context = context;
        this.objects=list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public FieldItem.ListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.field_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FieldItem.ListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(FieldItem.ListBean object, ViewHolder holder) {
        //TODO implement
        holder.tvField.setText(object.getKEYNAME());
    }

    protected class ViewHolder {
        private TextView tvField;

        public ViewHolder(View view) {
            tvField = (TextView) view.findViewById(R.id.tv_field);
        }
    }
}
