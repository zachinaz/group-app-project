package com.example.joiintheclub.BackEnd;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicReference;

import test.myapplication.GroupRequest;

public class Group {

        public static String GroupName;
        public static int GroupID;
        private int NumOfPeople;
        private boolean verified;
        Date DateCreated;

    JSONObject requestGET = new JSONObject();
    AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
    JSONObject requestPOST = new JSONObject();
    AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());


        public static void SearchGroup (String userInput){
            Group n = new Group();
            if (n.VerifyGroup(userInput)==true){
            //get requester to return Group info based on
            //userInput (group name, description, color, icon)

                try {
                    //Searches for "group_id"
                    Object userID = responsePOST.get().get("group_id");
                } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
                    //Prints the error message to the console via stacktrace
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Group not found");

        }

        public static int Get(){
            //use requester to get Group iD
            JSONObject requestGET = new JSONObject();
            AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
            JSONObject requestPOST = new JSONObject();
            AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());
            responseGET.set(Requester.requester("/group", "GET", requestGET));

            //still need to save Group ID with what get returns
            GroupID = 0001;
            return GroupID;
        }

        private boolean VerifyGroup (String userInput){

            //call requester here to verify that userInput group exists
                //if (userInput group exists in Database)
                    //return true;
                //else return false;

            //for now
            return verified=true;

        }



}

