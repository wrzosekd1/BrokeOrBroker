package com.example.david.brokeorbroker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/9/2016.
 */
public class FavoriteAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public FavoriteAdapter(Context context,int resource){
        super(context,resource);
    }

    public void add(Favorite object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        FavoriteHolder favoriteHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            favoriteHolder = new FavoriteHolder();
            favoriteHolder.tv_percent_change = (TextView)row.findViewById(R.id.tv_right);
            favoriteHolder.tv_company = (TextView)row.findViewById(R.id.tv_left);
            row.setTag(favoriteHolder);

        }
        else{
            favoriteHolder = (FavoriteHolder)row.getTag();
        }
        Favorite favorite = (Favorite)this.getItem(position);
        favoriteHolder.tv_percent_change.setText(favorite.getPercent_change());
        favoriteHolder.tv_company.setText(favorite.getCompany());
        double percent_change = Double.parseDouble(favoriteHolder.tv_percent_change.getText().toString());

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        favoriteHolder.tv_percent_change.setText(df.format(percent_change) + "%");

        if(percent_change>0){
            favoriteHolder.tv_percent_change.setTextColor(Color.parseColor("#2eb82e"));
        }
        else if(percent_change<0){
            favoriteHolder.tv_percent_change.setTextColor(0xffff0000);
        }
        return row;
    }
    static class FavoriteHolder{
        TextView tv_percent_change,tv_company;
    }
}
