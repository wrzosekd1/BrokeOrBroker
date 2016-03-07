package com.example.david.brokeorbroker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LogIn extends Activity {

    EditText ET_NAME, ET_PASS;
    String login_name, login_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ET_NAME = (EditText) findViewById(R.id.user_name);
        ET_PASS = (EditText) findViewById(R.id.user_pass);
    }

    public void userReg(View view) {

        startActivity(new Intent(this, Register.class));
    }

    public void userLogin(View view) {

        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        User user = new User(login_name, login_pass);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        user.login(backgroundTask);

    }

}
