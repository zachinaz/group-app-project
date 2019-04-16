package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

class Requester {

    //Call requester to interface when needing to interface with the api
    //  @param String targetUrl : the specific api resource that the information is stored on.
    //      e.g. '/user' when needing to get, create, update, or delete user information
    //  @param String method : the desired method needed
    //      e.g. 'GET' when wanting to pull information from the api
    //  @param JSONObject requestBody : a map of keys (e.g. "first_name") and values (e.g. "Bob") needed for the api request
    //      e.g. {"first_name", "last_name"} when wanting to update the first name and last name of a user on the api
    //  returns null (if failed) or (if success) JSONObject requestBody : a json object of keys and values (see above) of information from the api
    static JSONObject requester(String targetUrl, String method, JSONObject requestBody) {

        //Url of api
        String baseUrl = "http://35.185.248.192:5050/api";
        String totalUrl = baseUrl + targetUrl;
        String responseStr = "";

        HttpRequest request = new HttpRequest();

        try {
            responseStr = request.execute(totalUrl, method, requestBody.toString()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


        try {
            return new JSONObject(responseStr);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
