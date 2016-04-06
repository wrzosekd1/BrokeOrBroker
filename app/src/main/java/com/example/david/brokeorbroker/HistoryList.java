package com.example.david.brokeorbroker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class HistoryList extends ActionBarActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    HistoryAdapter historyAdapter;
    ListView listView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        user = (User)getIntent().getExtras().getSerializable("sampleObject");
        historyAdapter = new HistoryAdapter(this,R.layout.row_layout);
        listView = (ListView)findViewById(R.id.hlistview);
        listView.setAdapter(historyAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("history_response");
            int count = 0;
            String company;
            String date;
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);

                company = JO.getString("company");
                date = JO.getString("date");
                History history = new History( date,company);
                historyAdapter.add(history);
                count++;

            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> historyAdapter, View view, int position, long id) {
                    String symbol = String.valueOf(historyAdapter.getItemAtPosition(position));
                    Intent i = new Intent(HistoryList.this, Search.class);
                    i.putExtra("symbol", symbol);
                    i.putExtra("sampleObject", user);
                    startActivity(i);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
