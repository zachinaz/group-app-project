package com.example.joiintheclub;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
//import java.util.LinkedHashMap;
//import java.util.Map;

public class Requester {

    /*private static JSONObject mapToJson(LinkedHashMap<String, String> jsonArgs) {

        //New JSON object
        JSONObject parent = new JSONObject();

        //For each entry in jsonArgs, pull key and value, and add them to the JSON object
        for(Map.Entry<String, String> entry : jsonArgs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            try {
                parent.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return parent;
    }*/

    //Call requester to interface when needing to interface with the api
    //  @param String targetUrl : the specific api resource that the information is stored on.
    //      e.g. '/user' when needing to get, create, update, or delete user information
    //  @param String method : the desired method needed
    //      e.g. 'GET' when wanting to pull information from the api
    //  @param LinkedHashMap<String, String> requestBody : a map of keys (e.g. "first_name") and values (e.g. "Bob") needed for the api request
    //      e.g. {"first_name", "last_name"} when wanting to update the first name and last name of a user on the api
    //  returns LinkedHashMap<String, String> requestBody : a map of keys and values (see above) of information from the api
    @TargetApi(Build.VERSION_CODES.KITKAT) //Needed for StandardCharsets.UTF_8
    public static JSONObject requester(String targetUrl, String method, JSONObject requestBody) {

        //Url of api
        String baseUrl = "localhost:5000/api";

        try {
            //Create URL object from static base url plus the targeted API
            String totalUrl = baseUrl + targetUrl;
            URL object = new URL(totalUrl);

            //Establish an HTTP connection with a URL object
            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            //Request Header values set
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            //Request Method set
            con.setRequestMethod(method.toUpperCase());

            //New OutputStream object
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            //Converts the JSON object 'parent' to String and then to bytes to be flushed
            wr.write(requestBody.toString());
            wr.flush();

            int status = con.getResponseCode();

            switch(status) {
                case 400:
                case 401:
                case 404:
                    System.out.println(con.getResponseMessage());
                    //Unsuccessful response
                case 200:
                    //Successful response
                    StringBuilder response = new StringBuilder();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();
                    JSONObject responseBody = new JSONObject(response.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return requestBody;
    }

}
