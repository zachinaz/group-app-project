package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


class Requester {

    //Global responseStr is set from the background thread
    static String responseStr = "";

    //Call handleJSON to check if API returned an error
    //  @param JSONObject response : the JSON response from the server
    //  returns false if API returned an error
    static boolean handleJSON(JSONObject response) {
        return !response.has("err");
    }

    //Call requester to interface when needing to interface with the api
    //  @param String targetUrl : the specific api resource that the information is stored on.
    //      e.g. '/user' when needing to get, create, update, or delete user information
    //  @param String method : the desired method needed
    //      e.g. 'GET' when wanting to pull information from the api
    //  @param JSONObject requestBody : a map of keys (e.g. "first_name") and values (e.g. "Bob") needed for the api request
    //      e.g. {"first_name", "last_name"} when wanting to update the first name and last name of a user on the api
    //  returns null (if failed) or (if success) JSONObject requestBody : a json object of keys and values (see above) of information from the api
    static JSONObject requester(String targetUrl, String method, JSONObject requestBody) {

        requesterHelper(targetUrl, method, requestBody);

        System.out.println(responseStr);

        try {
            return new JSONObject(responseStr);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //requesterHelper creates a background thread independent from the requester class
    private static void requesterHelper(String targetUrl, String method, JSONObject requestBody) {

        //Url of api
        String baseUrl = "http://35.185.248.192:5050/api";
        String totalUrl = baseUrl + targetUrl;

        //Executes a background thread, making a request to the server
        try {
            new HttpRequest().execute(totalUrl, method, requestBody.toString()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
