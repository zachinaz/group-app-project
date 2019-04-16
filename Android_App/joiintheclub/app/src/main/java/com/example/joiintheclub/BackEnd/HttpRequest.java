package com.example.joiintheclub.BackEnd;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static android.support.constraint.Constraints.TAG;


public class HttpRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
        String requestMethod = params[1].toUpperCase();
        String requestBodyStr = params[2];
        String responseBodyStr = "";
        String inputLine;

        BufferedReader in = null;

        try {
            //Create a URL object holding our url
            URL totalUrl = new URL(stringUrl);

            //Create a connection
            HttpURLConnection con = (HttpURLConnection) totalUrl.openConnection();

            //Set method, headers, and timeouts
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            int READ_TIMEOUT = 15000;
            con.setReadTimeout(READ_TIMEOUT);
            int CONNECTION_TIMEOUT = 15000;
            con.setConnectTimeout(CONNECTION_TIMEOUT);

            con.setDoOutput(true);
            con.setDoInput(true);

            //New OutputStream object
            DataOutputStream os = new DataOutputStream(con.getOutputStream());

            //write on the output stream
            os.writeBytes(requestBodyStr);

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(con.getResponseCode()));
            Log.i("MSG", con.getResponseMessage());

            switch(con.getResponseCode()) {
                case 400:
                case 404:
                case 418:
                    //Handle 400 error set
                    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    break;
                case 200:
                case 204:
                    //Handle success
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    break;
            }

            while ((responseBodyStr = in.readLine()) != null) {
                Log.i(TAG, responseBodyStr);
            }
            in.close();

            con.disconnect();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return responseBodyStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
