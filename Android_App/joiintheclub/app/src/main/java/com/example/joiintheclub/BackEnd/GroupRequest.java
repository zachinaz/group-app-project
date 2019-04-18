package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;


public class GroupRequest {

    public boolean isPending;
    private int RequestID;

    //loadRequest called when a page needs to render any pending requests in the DB
    public static boolean loadRequest(String leaderID) {

        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        try {
            requestGET.put("user_id", leaderID);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        responseGET.set(Requester.requester("/group/request", "GET", requestGET));
        try {
            if (Requester.handleJSON(responseGET.get())) {
                //Searches for "user_id" as a key in the responsePOST.
                Object userID = responseGET.get().get("user_id");

                if (userID.toString().equals("0")) {
                    //credentials not found, returning false
                    return false;
                } else {
                    //Set the "user_id" from DB, login to true
                    //setUserID(userID.toString());
                    //setIsLoggedIn(true);
                    //System.out.println(getUserID());
                    return true;
                }
            } else {
                //Error returned from the DB
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    //sendRequest called when a user submits a request to a group
    public static boolean sendRequest(String userID, String groupID){

        JSONObject requestPOST = new JSONObject();
        AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());

        //use Requester to store Leader's ID to notify the leader
        //push to requester a new request and its status
        //Populate JSON request object with values passed into function
        try {
            requestPOST.put("user_id", userID);
            requestPOST.put("group_id", groupID);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        responsePOST.set(Requester.requester("/group/request", "POST", requestPOST));

        //Unpacks the Response message sent by the requester
        //No expected response. If no error thrown, the request was created (returns true)
        return Requester.handleJSON(responsePOST.get());
    }

    public static void acceptRequest(){
        //update membership status
        Group currentGroup=new Group();
        Membership x = new Membership();
        User requester=new User();
        //x.PromoteMember(currentGroup.Get(), requester.get());

        //update pending status
        //user requester to post "pending_status" to complete
    }
    public static void rejectRequest(){
        Group currentGroup=new Group();
        Membership x = new Membership();
        User requester=new User();
       // x.DemoteMember(currentGroup.Get(), requester.get());

        //user requester to delete group request
    }
}

