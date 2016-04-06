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
        String empty = "";
        Intent i = new Intent(this, Search.class);
        i.putExtra("sampleObject", user);
        i.putExtra("symbol", empty);
        this.startActivity(i);

    }

    //Favorites
    public void Favorites(View view){
        BackgroundTask backgroundTask = new BackgroundTask(this);
        String method = "favoriteMenu";
        String username = user.getUsername();
        backgroundTask.execute(method, username);
    }

    //History
    public void History(View view){
        BackgroundTask backgroundTask = new BackgroundTask(this);
        String method = "history";
        String username = user.getUsername();
        backgroundTask.execute(method, username);
    }

    //Hot Stocks
    public void HotStocks(View view){
        BackgroundTask backgroundTask = new BackgroundTask(this);
        String method = "hotstocks";
        String username = user.getUsername();
        backgroundTask.execute(method, username);
    }


    //Log Out
    public void LogOut(View view){
        user.logout();
        startActivity(new Intent(this,LogIn.class));
    }
}
