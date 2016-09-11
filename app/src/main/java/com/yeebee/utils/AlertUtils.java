package com.yeebee.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yeebee.R;


/**
 * Created by Administrator on 2016/7/6 0006.
 */

public class AlertUtils {

    public static void createAlertDialog(Context context, String title, String content, final Activity activity) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.Loading_dialog).create();
        dialog.show();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_alert, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_alert_title);
        tvTitle.setText(title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_alert_content);
        tvContent.setText(content);
        TextView tvCertain = (TextView) view.findViewById(R.id.tv_alert_certain);
        tvCertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.finish();
            }
        });
        dialog.getWindow().setContentView(view);
    }
}
