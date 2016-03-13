package com.example.david.brokeorbroker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HotStocksList extends ActionBarActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_stocks_list);

        user = (User)getIntent().getExtras().getSerializable("sampleObject");
        HotStocksAdapter hotStocksAdapter;
        ListView listView;
        hotStocksAdapter = new HotStocksAdapter(this, R.layout.row_layout);
        listView = (ListView) findViewById(R.id.hslistview);
        listView.setAdapter(hotStocksAdapter);



        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("hotstocks_response");
            int count = 0;
            String company;
            String percent_change;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);

                company = JO.getString("company");
                percent_change = JO.getString("percent_change");
                HotStocks hotStocks = new HotStocks(percent_change, company);
                hotStocksAdapter.add(hotStocks);
                count++;

            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> hotStocksAdapter, View view, int position, long id) {
                    String symbol = String.valueOf(hotStocksAdapter.getItemAtPosition(position));
                    Intent i = new Intent(HotStocksList.this, Search.class);
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
