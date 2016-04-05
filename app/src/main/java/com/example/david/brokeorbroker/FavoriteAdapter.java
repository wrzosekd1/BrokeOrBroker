package com.example.david.brokeorbroker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
            favoriteHolder.tv_percent_change = (TextView)row.findViewById(R.id.tv_percent_change);
            favoriteHolder.tv_company = (TextView)row.findViewById(R.id.tv_company);
            row.setTag(favoriteHolder);

        }
        else{
            favoriteHolder = (FavoriteHolder)row.getTag();
        }
        Favorite favorite = (Favorite)this.getItem(position);
        favoriteHolder.tv_percent_change.setText(favorite.getPercent_change());
        favoriteHolder.tv_company.setText(favorite.getCompany());
        return row;
    }
    static class FavoriteHolder{
        TextView tv_percent_change,tv_company;
    }
}
