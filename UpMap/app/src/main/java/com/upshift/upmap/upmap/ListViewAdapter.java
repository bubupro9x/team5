package com.upshift.upmap.upmap;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PC on 11/17/2017.
 */

public class ListViewAdapter implements ListAdapter {
    private ArrayList<String> listData;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> listData){
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        if(listData != null && !listData.isEmpty()){
            return listData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null){
            rowView = inflater.inflate(R.layout.row_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) rowView.findViewById(R.id.layout);
            viewHolder.img = (ImageView) rowView.findViewById(R.id.item_img);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        Picasso.with(rowView.getContext()).load(listData.get(position)).centerCrop().resize(150,150).into(holder.img);
//        holder.textItem.setText(listData.get(position));


        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return listData.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private class ViewHolder {
        public LinearLayout layout;
        public ImageView img;
    }
}