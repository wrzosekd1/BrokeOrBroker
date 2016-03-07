package com.example.david.brokeorbroker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Register extends Activity {

    AlertDialog alertDialog;
    EditText ET_NAME, ET_USER_NAME, ET_USER_PASS, ET_EMAIL;
    String name, user_name, user_pass, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ET_NAME = (EditText) findViewById(R.id.name);
        ET_USER_NAME = (EditText) findViewById(R.id.new_user_name);
        ET_USER_PASS = (EditText) findViewById(R.id.new_user_pass);
        ET_EMAIL = (EditText) findViewById(R.id.email);

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Registration Error");

    }

    public void userReg(View view) {

        name = ET_NAME.getText().toString();
        user_name = ET_USER_NAME.getText().toString();
        user_pass = ET_USER_PASS.getText().toString();
        email = ET_EMAIL.getText().toString();
        User user = new User(name, email, user_name, user_pass);
        if (!name.isEmpty() && !email.isEmpty() && !user_name.isEmpty() && !user_pass.isEmpty()) {


            BackgroundTask backgroundTask = new BackgroundTask(this);

            user.createAccount(backgroundTask);
        } else {
            alertDialog.setMessage("You need to fill in all fields");
            alertDialog.show();
        }

    }

    public void userReturn(View view) {
        startActivity(new Intent(this, LogIn.class));
    }

}
