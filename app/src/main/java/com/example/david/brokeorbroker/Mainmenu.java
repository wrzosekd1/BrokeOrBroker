package com.example.david.brokeorbroker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class Mainmenu extends ActionBarActivity {

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("sampleObject");


    }


    //Search
    public void SearchCompany(View view) {

        Intent i = new Intent(this, Search.class);
        i.putExtra("sampleObject", user);
        this.startActivity(i);

    }
}
