package com.upshift.upmap.upmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.upshift.upmap.upmap.item.item_maker_sieuthi;

import java.util.List;

/**
 * Created by Administrator on 6/3/2016.
 */
public class ListAdapter extends ArrayAdapter<item_maker_sieuthi> {

    public ListAdapter(Context context, int resource, List<item_maker_sieuthi> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_sot, null);
        }
        item_maker_sieuthi p = getItem(position);
        if (p != null) {
            ImageView img = (ImageView)view.findViewById(R.id.iv_sortlocation);
            Picasso.with(getContext()).load("https://images.idgesg.net/images/article/2017/07/location-pixabay-1200x800-100728584-large.jpg")
                    .centerCrop()
                    .resize(300,300).into(img);
            TextView name = (TextView) view.findViewById(R.id.name_sort);
            name.setText(p.getName());
            TextView add = (TextView) view.findViewById(R.id.add_sort);
            add.setText(p.getAdd());
            TextView ser_sort = (TextView) view.findViewById(R.id.ser_sort);
            ser_sort.setText(p.getService());

        }
        return view;
    }

}