package com.example.david.brokeorbroker;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Search extends ActionBarActivity {

    User user;
    String companySymbol;
    EditText cSymbol;
    TextView tSymbol,tDate,tHigh,tLow,tOpen,tClose,tVolume,tPercentChange,tPredict;
    Button favoriteButton;
    String symbol;
    String symbolsInDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        cSymbol = (EditText) findViewById(R.id.cSymbol);
        favoriteButton = (Button) findViewById(R.id.bFavorite);
        favoriteButton.setVisibility(View.INVISIBLE);
        tSymbol = (TextView) findViewById(R.id.tvSymbol);
        tSymbol.setVisibility(View.INVISIBLE);
        tDate = (TextView) findViewById(R.id.tvDate);
        tDate.setVisibility(View.INVISIBLE);
        tHigh = (TextView) findViewById(R.id.tvHigh);
        tHigh.setVisibility(View.INVISIBLE);
        tLow = (TextView) findViewById(R.id.tvLow);
        tLow.setVisibility(View.INVISIBLE);
        tOpen = (TextView) findViewById(R.id.tvOpen);
        tOpen.setVisibility(View.INVISIBLE);
        tClose = (TextView) findViewById(R.id.tvClose);
        tClose.setVisibility(View.INVISIBLE);
        tVolume = (TextView) findViewById(R.id.tvVolume);
        tVolume.setVisibility(View.INVISIBLE);
        tPercentChange = (TextView) findViewById(R.id.tvPC);
        tPercentChange.setVisibility(View.INVISIBLE);
        tPredict = (TextView) findViewById(R.id.tvPredict);
        tPredict.setVisibility(View.INVISIBLE);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("sampleObject");
        symbol = getIntent().getExtras().getString("symbol");

        if(!symbol.equals("")){
            symbol = symbol.toUpperCase();
            String username = user.getUsername();
            BackgroundTask backgroundTask = new BackgroundTask(this);
            String method = "search";
            backgroundTask.execute(method, username, symbol);
        }
    }

    public void Search(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        companySymbol = cSymbol.getText().toString();
        companySymbol = companySymbol.toUpperCase();
        String tempSymbol = companySymbol;
        companySymbol = "," + companySymbol + ",";
        String username = user.getUsername();
        symbolsInDatabase = ",YHOO,F,NFLX,ADBE,GE,FB,";
        if (symbolsInDatabase.contains(companySymbol)) {
            BackgroundTask backgroundTask = new BackgroundTask(this);
            String method = "search";
            backgroundTask.execute(method, username, tempSymbol);
        }
    }

    public void Favorite(View view) {

        companySymbol = tSymbol.getText().toString();
        String username = user.getUsername();
        if (!companySymbol.equals("Symbol")) {
            BackgroundTask backgroundTask = new BackgroundTask(this);
            String method = "favorite";
            backgroundTask.execute(method,  username,  companySymbol);
        }
    }
}
