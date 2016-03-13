package com.example.david.brokeorbroker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 3/12/2016.
 */
public class HotStocksAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public HotStocksAdapter(Context context, int resource) {

        super(context, resource);
    }


    public void add(HotStocks object) {
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
        HotStocksHolder hotStocksHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            hotStocksHolder = new HotStocksHolder();
            hotStocksHolder.tv_percent_change = (TextView) row.findViewById(R.id.tv_right);
            hotStocksHolder.tv_company = (TextView) row.findViewById(R.id.tv_left);
            row.setTag(hotStocksHolder);

        } else {
            hotStocksHolder = (HotStocksHolder) row.getTag();
        }
        HotStocks hotStocks = (HotStocks) this.getItem(position);
        hotStocksHolder.tv_percent_change.setText(hotStocks.getPercent_change());
        hotStocksHolder.tv_company.setText(hotStocks.getCompany());
        return row;
    }



    static class HotStocksHolder {
        TextView tv_percent_change, tv_company;
    }
}
