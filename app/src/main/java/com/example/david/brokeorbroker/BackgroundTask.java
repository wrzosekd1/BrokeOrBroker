package com.example.david.brokeorbroker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by David on 2/12/2016.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;
    Context ctx;
    User user;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login information...");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://10.0.2.2/BrokeOrBroker/register.php";
        String login_url = "http://10.0.2.2/BrokeOrBroker/login.php";
        String update_url = "http://10.0.2.2/BrokeOrBroker/stockDownloader.php";
        String method = params[0];

        //Registers User
        if (method.equals("register")) {
            String name = params[1];
            String email = params[2];
            String user_name = params[3];
            String user_pass = params[4];
            user = new User(name, email, user_name, user_pass);
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                response = bufferedReader.readLine();
                response = response.trim();

                bufferedReader.close();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();

                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Logs user in
        else if (method.equals("login")) {
            String login_name = params[1];
            String login_pass = params[2];
            user = new User(login_name, login_pass);
            try {

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                        URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                line = bufferedReader.readLine();
                response += line;
                response = response.trim();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();




                /*
                URL url2 = new URL(update_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                bufferedWriter2.flush();
                bufferedWriter2.close();
                OS.close();

                InputStream IS1 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(IS1,"iso-8859-1"));


                bufferedReader2.close();
                IS1.close();
                //httpURLConnection2.connect();
                httpURLConnection2.disconnect();
                */

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {


        if (result.equals("Registration Success...")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        } else if (result.equals("That username already exists")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        } else if (result.equals("Login Failed.......Try Again")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        } else if (result.equals("Login Success")) {
            Intent i = new Intent(ctx, Mainmenu.class);
            i.putExtra("sampleObject", user);
            ctx.startActivity(i);
        }

    }
}
