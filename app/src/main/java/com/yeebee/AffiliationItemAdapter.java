package com.yeebee;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeebee.javabean.Affiliation;

public class AffiliationItemAdapter extends BaseAdapter {

    private List<Affiliation.ListBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public AffiliationItemAdapter(Context context,List<Affiliation.ListBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Affiliation.ListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.affiliation_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Affiliation.ListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Affiliation.ListBean object, ViewHolder holder) {
        //TODO implement
        holder.tvAffiliation.setText(object.getKEYNAME());
    }

    protected class ViewHolder {
        private TextView tvAffiliation;

        public ViewHolder(View view) {
            tvAffiliation = (TextView) view.findViewById(R.id.tv_affiliation);
        }
    }
}
