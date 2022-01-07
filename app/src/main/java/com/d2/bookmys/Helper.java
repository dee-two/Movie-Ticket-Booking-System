package com.d2.bookmys;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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

public class Helper extends AsyncTask<String, String, String>
{
    //ProgressDialog pdLoading = new ProgressDialog(this);
    private HttpURLConnection conn;
    private URL url = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
      /*  pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();*/

    }
    @Override
    protected String doInBackground(String... params) {
        try {
            url = new URL("http", MainActivity.host, 80, "myPHP2/signup.php");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "exception";
        }
        try {
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(MainActivity.READ_TIMEOUT);
            conn.setConnectTimeout(MainActivity.CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", params[0])
                    .appendQueryParameter("password", params[1])
                    .appendQueryParameter("email", params[2])
                    .appendQueryParameter("mobile", params[3]);
            String query = builder.build().getEncodedQuery();
            Log.d(">>>>>", ""+params[0]+" "+params[1] + " " + params[2] + " " + params[3]);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "exception";
        }

        try {

            int response_code = conn.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                Log.d(">>>>>", "HTTP_OK");
                return(result.toString());

            }else{
                Log.d(">>>>>", "HTTP_ERROR");
                return("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            conn.disconnect();
        }


    }

    @Override
    protected void onPostExecute(String result) {

        //pdLoading.dismiss();
        Log.d(">>>>", "onPostExecute");
        if(result.equalsIgnoreCase("true")) {
            //Intent intent = new Intent(MainActivity.this,SuccessActivity.class);
            //startActivity(intent);
            //MainActivity.this.finish();
            Log.d(">>>>", "onPostExecute : Registration Successful");
            //Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_LONG).show();
        } else if (result.equalsIgnoreCase("false")){
            //Toast.makeText(MainActivity.this, "Invalid username or password : "+result, Toast.LENGTH_LONG).show();
            Log.d(">>>>", "onPostExecute : Failed To Register");
        } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
            //Toast.makeText(MainActivity.this, "Connection Problem.", Toast.LENGTH_LONG).show();
            Log.d(">>>>", "onPostExecute : Connection Problem");
        } else {
            Log.d(">>>>", "onPostExecute : Error @Else" + result);
        }
    }
}
