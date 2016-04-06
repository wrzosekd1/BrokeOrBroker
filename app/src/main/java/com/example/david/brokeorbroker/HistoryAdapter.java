package com.example.david.brokeorbroker;

/**
 * Created by David on 3/11/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class HistoryAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public HistoryAdapter(Context context,int resource){
        super(context,resource);
    }

    public void add(History object){
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
        HistoryHolder historyHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            historyHolder = new HistoryHolder();
            historyHolder.tv_date = (TextView)row.findViewById(R.id.tv_right);
            historyHolder.tv_company = (TextView)row.findViewById(R.id.tv_left);
            row.setTag(historyHolder);

        }
        else{
            historyHolder = (HistoryHolder)row.getTag();
        }
        History history = (History)this.getItem(position);
        historyHolder.tv_date.setText(history.getDate());
        historyHolder.tv_company.setText(history.getCompany());
        return row;
    }
    static class HistoryHolder{
        TextView tv_date,tv_company;
    }
}
