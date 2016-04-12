package com.example.david.brokeorbroker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by David on 2/12/2016.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;
    Context ctx;
    User user;
    String json_string;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Message...");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/register.php";
        String login_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/login.php";
        String update_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/stockDownloader.php";
        String search_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/SearchCompany.php";
        String favorite_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/getFavorites.php";
        String add_removeFavorite_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/Favorite.php";
        String history_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/getHistory.php";
        String hotstocks_url = "http://menesesj2.leto.feralhosting.com/BrokeBroker/GetHotStocks.php";
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
        } else if (method.equals("search")) {
            String username = params[1];
            String companySymbol = params[2];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("symbol", "UTF-8") + "=" + URLEncoder.encode(companySymbol, "UTF-8");
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
        } else if (method.equals("favoriteMenu")) {
            String username = params[1];
            user = new User(username);
            try {
                URL url = new URL(favorite_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("favorite")) {
            String username = params[1];
            String companySymbol = params[2];
            try {
                URL url = new URL(add_removeFavorite_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("symbol", "UTF-8") + "=" + URLEncoder.encode(companySymbol, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                bufferedReader.close();
                IS.close();


                httpURLConnection.disconnect();

                return "done";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("history")) {
            String username = params[1];
            user = new User(username);
            try {
                URL url = new URL(history_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("hotstocks")) {
            String username = params[1];
            user = new User(username);
            try {

                URL url = new URL(hotstocks_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
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
            Intent successIntent = new Intent(ctx, Mainmenu.class);
            ctx.startActivity(successIntent);
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
        } else if (result.contains("server_response")) {
            json_string = result;
            Intent i = new Intent(ctx, FavoriteList.class);
            i.putExtra("json_data", json_string);
            i.putExtra("sampleObject", user);
            ctx.startActivity(i);
        } else if (result.contains("history_response")) {
            json_string = result;
            Intent i = new Intent(ctx, HistoryList.class);
            i.putExtra("json_data", json_string);
            i.putExtra("sampleObject", user);
            ctx.startActivity(i);
        } else if (result.contains("hotstocks_response")) {
            json_string = result;
            Intent i = new Intent(ctx, HotStocksList.class);
            i.putExtra("json_data", json_string);
            i.putExtra("sampleObject", user);
            ctx.startActivity(i);
        }

        String arr[] = result.split(" ", 10);
        String firstWord = arr[0];
        if (firstWord.equals("SearchSuccess")) {
            String symbol = arr[1];
            String date = arr[2];
            String open = arr[3];
            String high = arr[4];
            String low = arr[5];
            String close = arr[6];
            String volume = arr[7];
            String percent_change = arr[9];
            TextView tvSymbol = (TextView) ((Activity) ctx).findViewById(R.id.tvSymbol);
            tvSymbol.setText(symbol);
            tvSymbol.setVisibility(View.VISIBLE);
            TextView tvDate = (TextView) ((Activity) ctx).findViewById(R.id.tvDate);
            tvDate.setText(date);
            tvDate.setVisibility(View.VISIBLE);
            TextView tvOpen = (TextView) ((Activity) ctx).findViewById(R.id.tvOpen);
            tvOpen.setText("Open: " + open);
            tvOpen.setVisibility(View.VISIBLE);
            TextView tvHigh = (TextView) ((Activity) ctx).findViewById(R.id.tvHigh);
            tvHigh.setText("High: " + high);
            tvHigh.setVisibility(View.VISIBLE);
            TextView tvLow = (TextView) ((Activity) ctx).findViewById(R.id.tvLow);
            tvLow.setText("Low: " + low);
            tvLow.setVisibility(View.VISIBLE);
            TextView tvClose = (TextView) ((Activity) ctx).findViewById(R.id.tvClose);
            tvClose.setText("Close: " + close);
            tvClose.setVisibility(View.VISIBLE);
            TextView tvVolume = (TextView) ((Activity) ctx).findViewById(R.id.tvVolume);
            tvVolume.setText("Volume: " + volume);
            tvVolume.setVisibility(View.VISIBLE);
            TextView tvPercent = (TextView) ((Activity) ctx).findViewById(R.id.tvPC);
            tvPercent.setText(percent_change);
            tvPercent.setVisibility(View.VISIBLE);
            Button fButton = (Button) ((Activity) ctx).findViewById(R.id.bFavorite);
            fButton.setVisibility(View.VISIBLE);

        }


    }
}
