package com.example.spinner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SpinnerAdapter extends BaseAdapter {

    private java.util.List<SpinnerBean> spinnerBeans;

    private Context context;

    public SpinnerAdapter(Context context, java.util.List<SpinnerBean> familyMemberBeans) {
        this.context = context;
        this.spinnerBeans = familyMemberBeans;
    }

    public void update(java.util.List<SpinnerBean> list) {
//        this.familyMemberBeans.clear();
//        this.familyMemberBeans.addAll(list);
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
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            convertView = mLayoutInflater.inflate(R.layout.family_bean_item_layout, null);
        }

        ImageView ivFamilyMemberIcon = convertView.findViewById(R.id.iv_family_bean_icon);
        Glide.with(context).load(spinnerBeans.get(position).getUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.mipmap.img_header_man)
                .into(ivFamilyMemberIcon);
        android.widget.TextView tvFamilyMemberName = convertView.findViewById(R.id.tv_family_member_name);
        tvFamilyMemberName.setText(spinnerBeans.get(position).getName());
        return convertView;
    }
}