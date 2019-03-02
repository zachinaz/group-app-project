package com.example.joiintheclub;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class User {


    private String firstName;
    private String lastName;
    private String email;
    private String pwd;


    //private String profilePicURL;
    //private int userID;
    private static boolean isActive;

    public static boolean createUser(String firstName,
                              String lastName,
                              String email,
                              String pwd)
    {
        //Creates new JSONObjects to pass to and receive from Requester.requester
        //  Note: responses need to be in Atomic Reference since the requester works on a different thread.
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
        JSONObject requestPOST = new JSONObject();
        AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());
        //????? dict = <firstName,lastName>;

        //Request info from requester to see if already exists
        responseGET.set(Requester.requester("/user", "GET", requestGET));
            //if it returns something, then it already exists ???

        //If it already exists
        if (isActive)
        {
            return false;
        }
        //If it doesn't exist yet
        else
        {

            try {
                //Populates the object with keys and values
                //  e.g. "first_name": "Jim"
                //  Note: keys need to be in python format since the api is written in python (e.g. first_name)
                //        while values can be in java format (e.g. firstName)
                requestPOST.put("first_name", firstName);
                requestPOST.put("last_name", lastName);
                requestPOST.put("email", email);
                requestPOST.put("password", pwd);
            } catch (JSONException e){ //Catch necessary since dictToApi.put can throw the exception JSONException
                //Prints the error message to the console via stacktrace
                e.printStackTrace();
            }
            //Create it here
            //  ...
            //maybe this
            //Saves the output of Request.requester to a JSONObject responsePOST
            responsePOST.set(Requester.requester("/user", "POST", requestPOST));

            try {
                //Searches for "user_id" as a key in the responsePOST.
                Object userID = responsePOST.get().get("user_id");
            } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            e.printStackTrace();
            }

            return true;
        }


    }
    private void get() {

    }
    private void set() {

    }

    public static boolean login() {
        //fix later

        return false;
    }
    public static boolean logout() {
        //fix later
        return false;
    }

    public static boolean createGroup()
    {
       /*
        private String groupName;
        private int groupID;
        private int numOfPeople;
        Date DateCreated;
        */




       return false;
    }

}
