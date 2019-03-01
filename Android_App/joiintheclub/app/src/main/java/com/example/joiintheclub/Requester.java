package com.example.joiintheclub;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

public class Requester {

    private static String baseUrl = "localhost:5000/api";

    //Call requester to interface when needing to interface with the api
    //  @param String targetUrl : the specific api resource that the information is stored on.
    //      e.g. '/user' when needing to get, create, update, or delete user information
    //  @param String method : the desired method needed
    //      e.g. 'GET' when wanting to pull information from the api
    //  @param LinkedHashMap<String, String> dict : a map of keys (e.g. "first_name") and values (e.g. "Bob") needed for the api request
    //      e.g. {"first_name", "last_name"} when wanting to update the first name and last name of a user on the api
    //  returns LinkedHashMap<String, String> dict : a map of keys and values (see above) of information from the api
    public static LinkedHashMap<String, String> requester(String targetUrl, String method, LinkedHashMap<String, String> dict) {

        try {
            //Create URL object from static base url plus the targeted API
            String totalUrl = baseUrl + targetUrl;
            URL object = new URL(totalUrl);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(method.toUpperCase());

            JSONObject parent = new JSONObject();



            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(parent.toString());
            wr.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(con.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dict;
    }

}
