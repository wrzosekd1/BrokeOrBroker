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


public class FavoriteList extends ActionBarActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    FavoriteAdapter favoriteAdapter;
    ListView listView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        user = (User)getIntent().getExtras().getSerializable("sampleObject");
        favoriteAdapter = new FavoriteAdapter(this,R.layout.row_layout);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(favoriteAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String company;
            String percent_change;
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);

                company = JO.getString("company");
                percent_change = JO.getString("percent_change");
                Favorite favorite = new Favorite( percent_change,company);
                favoriteAdapter.add(favorite);
                count++;

            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> favoriteAdapter, View view, int position, long id) {
                    String symbol = String.valueOf(favoriteAdapter.getItemAtPosition(position));
                    Intent i = new Intent(FavoriteList.this, Search.class);
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
