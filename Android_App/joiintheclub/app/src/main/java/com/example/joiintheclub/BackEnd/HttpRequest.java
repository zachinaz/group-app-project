package com.example.joiintheclub.BackEnd;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
        String requestMethod = params[1].toUpperCase();
        String requestBodyStr = params[2];
        String responseBodyStr = "";
        String inputLine;

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

            //New OutputStream object
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());

            //write on the output stream
            out.write(requestBodyStr);

            switch(con.getResponseCode()) {
                case 400:
                case 404:
                case 418:
                    //Handle 400 error set
                    break;
                case 200:
                case 204:
                    //Create a new InputStreamReader
                    InputStreamReader streamReader = new InputStreamReader(con.getInputStream());

                    //Create a new buffered reader and String Builder
                    BufferedReader reader = new BufferedReader(streamReader);
                    StringBuilder stringBuilder = new StringBuilder();

                    //Check if the line we are reading is not null
                    while((inputLine = reader.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }

                    //Close our InputStream and Buffered reader
                    reader.close();
                    streamReader.close();

                    //Set our responseBodyStr equal to our stringBuilder
                    responseBodyStr = stringBuilder.toString();
            }

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
