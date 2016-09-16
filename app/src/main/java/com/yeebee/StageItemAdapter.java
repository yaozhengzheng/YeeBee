package com.yeebee;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeebee.javabean.Stage;

public class StageItemAdapter extends BaseAdapter {

    private List<Stage.ListBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public StageItemAdapter(Context context,List<Stage.ListBean> list) {
        this.context = context;
        this.objects=list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Stage.ListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.stage_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Stage.ListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Stage.ListBean object, ViewHolder holder) {
        //TODO implement
        holder.tvStage.setText(object.getKEYNAME());
    }

    protected class ViewHolder {
        private TextView tvStage;

        public ViewHolder(View view) {
            tvStage = (TextView) view.findViewById(R.id.tv_stage);
        }
    }
}
