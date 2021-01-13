package com.example.spinner;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class SpinnerAdapter extends BaseAdapter {

    private java.util.List<SpinnerBean> spinnerBeans;

    private Context context;
    private Spinner spinner;

    public SpinnerAdapter(Context context, Spinner spinner, java.util.List<SpinnerBean> familyMemberBeans) {
        this.context = context;
        this.spinnerBeans = familyMemberBeans;
        this.spinner = spinner;
    }

    public void update(java.util.List<SpinnerBean> list) {
        this.spinnerBeans = list;
    }

    @Override
    public int getCount() {
        return null == spinnerBeans ? 0 : spinnerBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnerBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            convertView = mLayoutInflater.inflate(R.layout.family_bean_item_layout, null);
        }

        convertView.setMinimumHeight(120);
        ImageView ivFamilyMemberIcon = convertView.findViewById(R.id.iv_image);
        Glide.with(context).load(spinnerBeans.get(position).getUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.mipmap.img_header_man)
                .into(ivFamilyMemberIcon);
        TextView tvFamilyMemberName = convertView.findViewById(R.id.tv_text);
        tvFamilyMemberName.setText(spinnerBeans.get(position).getName());

            convertView.setBackground(context.getDrawable(R.drawable.spinner_item_border));
//        if (position == spinner.getSelectedItemPosition()) {
//        } else if (position > 0 || position < spinnerBeans.size()) {
//            convertView.setBackground(context.getDrawable(R.drawable.spinner_popup_item_background_default));
//        } else if (position == spinnerBeans.size() - 1) {
//            convertView.setBackground(context.getDrawable(R.drawable.spinner_popup_item_background_bottom));
//        }

        if (spinner.getSelectedItemPosition() == position) {
            tvFamilyMemberName.setTextColor(Color.parseColor("#28C3F2"));
        }

        return convertView;
    }

    @Override
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            convertView = mLayoutInflater.inflate(R.layout.family_bean_item_layout, null);
        }

        convertView.setMinimumHeight(120);
        ImageView ivFamilyMemberIcon = convertView.findViewById(R.id.iv_image);
        Glide.with(context).load(spinnerBeans.get(position).getUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.mipmap.img_header_man)
                .into(ivFamilyMemberIcon);
        android.widget.TextView tvFamilyMemberName = convertView.findViewById(R.id.tv_text);

        convertView.setPadding(5, 5, 95, 5);
        if (position == spinner.getSelectedItemPosition()) {
            convertView.setBackgroundColor(Color.TRANSPARENT);
            tvFamilyMemberName.setTextColor(Color.WHITE);
        }


        tvFamilyMemberName.setText(spinnerBeans.get(position).getName());
        return convertView;
    }
}