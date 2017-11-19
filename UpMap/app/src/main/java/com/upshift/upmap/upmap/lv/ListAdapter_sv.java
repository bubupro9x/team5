package com.upshift.upmap.upmap.lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.upshift.upmap.upmap.R;

import java.util.List;

/**
 * Created by Administrator on 6/3/2016.
 */
public class ListAdapter_sv extends ArrayAdapter<String> {

    public ListAdapter_sv(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_list_sv, null);
        }
        String p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            ImageView img = (ImageView) view.findViewById(R.id.icon_lv_sv);

            if (p.equals("1")){
                Picasso.with(getContext()).load(R.drawable.sv1).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có dịch vụ hổ trợ người khuyết tật");
            }
            if (p.equals("2")){
                Picasso.with(getContext()).load(R.drawable.sv2).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có bảng mã cho người mù");
            }
            if (p.equals("3")){
                Picasso.with(getContext()).load(R.drawable.sv3).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có thiết bị trợ thính");
            }
            if (p.equals("4")){
                Picasso.with(getContext()).load(R.drawable.s5).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có lối đi dành cho người khuyết tật");
            }
            if (p.equals("5")){
                Picasso.with(getContext()).load(R.drawable.s6).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có dịch vụ hổ trợ kí hiệu câm");
            }
            if (p.equals("6")){
                Picasso.with(getContext()).load(R.drawable.s8).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có xe hổ trợ người khuyết tật");
            }
            if (p.equals("7")){
                Picasso.with(getContext()).load(R.drawable.s9).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có sân chơi cho người khuyết tật");
            }
            if (p.equals("8")){
                Picasso.with(getContext()).load(R.drawable.s10).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Coffee có hổ trợ cho người khuyết tật");
            }
            if (p.equals("9")){
                Picasso.with(getContext()).load(R.drawable.s9).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Ăn uống có hổ trợ cho người khuyết tật");
            }
//            if (p.equals(10)){
//                Picasso.with(getContext()).load(R.drawable.s10).centerCrop().resize(70,70).into(img);
//                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
//                content.setText("Có dịch vụ hổ trợ người khuyết tật");
//            }
            if (p.equals("0")){
                Picasso.with(getContext()).load(R.drawable.s12).centerCrop().resize(70,70).into(img);
                TextView content = (TextView) view.findViewById(R.id.lv_content_sv);
                content.setText("Có cầu thang máy dành cho người khuyết tật");
            }


        }
        return view;
    }

}