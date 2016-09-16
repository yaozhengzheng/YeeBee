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
import com.yeebee.javabean.CityItem;

public class CityItemAdapter extends BaseAdapter {

    private List<CityItem.ListBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CityItemAdapter(Context context,List<CityItem.ListBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CityItem.ListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.city_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CityItem.ListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CityItem.ListBean object, ViewHolder holder) {
        //TODO implement
        holder.tvCityItem.setText(object.getKEYNAME());
    }

    protected class ViewHolder {
        private TextView tvCityItem;

        public ViewHolder(View view) {
            tvCityItem = (TextView) view.findViewById(R.id.tv_city_item);
        }
    }
}
