package com.example.david.brokeorbroker;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Search extends ActionBarActivity {

    User user;
    TextView user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        user_info = (TextView) findViewById(R.id.text_user);


        Intent i = getIntent();
        user = (User) i.getSerializableExtra("sampleObject");
        String user_name = user.username;
        user_info.setText(user_name);

    }


}
