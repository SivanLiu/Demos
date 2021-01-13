package com.example.spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapter extends BaseAdapter {

    private java.util.List<SpinnerBean> spinnerBeans;

    private Context context;
    private int choosePosition;
    private Spinner spinner;

    public interface SelectListener {
        public void clickItem(int position);
    }

    private SelectListener selectListener = new SelectListener() {
        @Override
        public void clickItem(int position) {

        }
    };


    public SpinnerAdapter(Context context, Spinner spinner, java.util.List<SpinnerBean> familyMemberBeans) {
        this.context = context;
        this.spinnerBeans = familyMemberBeans;
        this.spinner = spinner;
    }

    public void update(java.util.List<SpinnerBean> list) {
//        this.familyMemberBeans.clear();
//        this.familyMemberBeans.addAll(list);
        this.spinnerBeans = list;
    }

    public void select(int position) {
        choosePosition = position;
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
        Log.e("sss", "getItemId position " + position);
        return position;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            convertView = mLayoutInflater.inflate(R.layout.family_bean_item_layout, null);
        }

        TextView textView = convertView.findViewById(R.id.tv_text);
        if (position == spinner.getSelectedItemPosition()) {
            textView.setTextColor(Color.GREEN);
        } else {
            textView.setTextColor(Color.RED);
        }
        return convertView;
    }


    //    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Log.e("sss", "getDropDownView position " + position);
//        return initView(convertView, position, 1);
//    }

    @Override
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {
        Log.e("sss", "getView position " + position + "  " + spinner.getSelectedItemPosition());
        return initView(convertView, position, 0);
    }

    private View initView(View convertView, int position, int choose) {
        if (null == convertView) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            convertView = mLayoutInflater.inflate(R.layout.family_bean_item_layout, null);
        }

        convertView.setMinimumHeight(87);

        ImageView ivFamilyMemberIcon = convertView.findViewById(R.id.iv_image);
        Glide.with(context).load(spinnerBeans.get(position).getUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.mipmap.img_header_man)
                .into(ivFamilyMemberIcon);
        android.widget.TextView tvFamilyMemberName = convertView.findViewById(R.id.tv_text);
        if (choose == 0) {
            convertView.setBackgroundColor(Color.TRANSPARENT);
            convertView.setPadding(5, 5, 45, 5);
            tvFamilyMemberName.setTextColor(Color.WHITE);
        }

        if (choose == 1 && position == spinner.getSelectedItemPosition()) {
            convertView.setBackground(context.getDrawable(R.drawable.spinner_popup_item_background));
        } else if (choose == 1 && position == 0) {
            convertView.setBackground(context.getDrawable(R.drawable.spinner_popup_item_background0));
        }

        if (choose == 1 && spinner.getSelectedItemPosition() == position) {
            tvFamilyMemberName.setTextColor(Color.parseColor("#28C3F2"));
        }

        tvFamilyMemberName.setText(spinnerBeans.get(position).getName());
        return convertView;
    }
}