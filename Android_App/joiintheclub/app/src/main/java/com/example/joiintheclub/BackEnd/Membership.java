package com.example.joiintheclub.BackEnd;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class Membership {
    private static int LeaderID;
    public static boolean IsMember;
    private boolean IsLeader;



    public static boolean CheckMembership(String GroupID, String UserID){
        //true if the member has privilege false if not

        //Make sure user entered something for email and password
        if (GroupID.isEmpty() || UserID.isEmpty()) {
            return false;
        }
        //Create a JSON object for request and response
        JSONObject MembershipRequestGET = new JSONObject();
        AtomicReference<JSONObject> MembershipResponseGET = new AtomicReference<> (new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            MembershipRequestGET.put("user_id", UserID);
            MembershipRequestGET.put("group_id", GroupID);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return IsMember=true;
    }

    public static int GetLeader(String GroupID){
        //get Group ID number
        //return the ID number of the leader -- one leader for now
        Group x=new Group();

        //for now
        LeaderID=0;

        //use x for Requester to return
        //the leader's ID number for Group x
        return LeaderID;
    }

    public static void PromoteMember(String GroupID, String UserID){
        //Make sure user entered something for email and password
        if (GroupID.isEmpty() || UserID.isEmpty()) {

        }
        //Create a JSON object for request and response
        JSONObject MembershipRequestGET = new JSONObject();
        AtomicReference<JSONObject> MembershipResponseGET = new AtomicReference<> (new JSONObject());
        JSONObject MembershipRequestPOST = new JSONObject();
        AtomicReference<JSONObject> MembershipResponsePOST = new AtomicReference<>(new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            MembershipRequestGET.put("user_id", UserID);
            MembershipRequestGET.put("group_id", GroupID);
        } catch (JSONException e) {
            e.printStackTrace();

        }
        //use requester to update user's status to a member
    }
    public static void DemoteMember(String GroupID, String UserID){
        User user = new User();

        //if(user.IsMember==true)
        //use requester to update user's status back to nonmember
        //else;

    }
}