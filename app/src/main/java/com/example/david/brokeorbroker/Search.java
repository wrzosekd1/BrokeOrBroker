package com.example.david.brokeorbroker;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Search extends ActionBarActivity {

    User user;
    String companySymbol;
    EditText cSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cSymbol = (EditText) findViewById(R.id.cSymbol);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("sampleObject");

    }

    public void Search(View view){
        companySymbol = cSymbol.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        String method = "search";
        backgroundTask.execute(method, companySymbol);
    }


}
