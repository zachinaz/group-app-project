package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
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

    public static boolean createGroup(
            /*
            String firstName,
            String lastName,
            String GroupName
            */
    )
    {
        /*
        //idea1
        //User sends createGroup requests to requester
        //  Sends GroupName
        //  program sends Date

        //if it does not already exist, it becomes verified

        //Create new JSON object to pass and receive from Requester.requester
        JSONObject userGroupRequestGET = new JSONObject();
        AtomicReference<JSONObject> userGroupResponseGET =
                new AtomicReference<>(new JSONObject());
        JSONObject userGroupRequestPOST = new JSONObject();
        AtomicReference<JSONObject> userGroupResponsePOST =
                new AtomicReference<>(new JSONObject());

        //Request info from requester to see if already exists
        userGroupResponseGET.set(Requester.requester("/group", "GET",
                userGroupRequestGET));

        //If the group already exists

        //if (...)
        {
            return false;
        }

        //If it does not exist yet
        else
        {

          //  try {
                //Populate object with keys and values
                //Keys (in_python), Values (inJava)

                //below lines have errors when uncommented
                //  put - unhandled exception JSON
                /*
                userGroupRequestPOST.put("first_name", firstName);
                userGroupRequestPOST.put("last_name", lastName);
                userGroupRequestPOST.put("group_name", GroupName);
                */
            //should I use something other than e
            //} catch (JSONException e) {
                //Prints error message to console via stacktrace
             //   e.printStackTrace();
           // }
        /*
            userGroupResponsePOST.set(Requester.requester("/group", "POST",
                    userGroupRequestPOST));
            try {
                //Searches for "group_id" as key in responsePOST
                Object GroupID = userGroupResponsePOST.get().get("group_id");
            } catch (JSONException e) { //catch needed since userGroupResopnsePOST.get can throw exception JSONException
                //Prints error message to console via stacktrace
                e.printStackTrace();
            }
          */
            return true;
        //}
       /*
      private String GroupName;
        public static int GroupID;
        private int NumOfPeople;
        private boolean verified;
        Date DateCreated;
        */


    }

}
