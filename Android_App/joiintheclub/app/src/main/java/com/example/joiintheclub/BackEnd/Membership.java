package com.example.joiintheclub.BackEnd;
import org.json.JSONObject;

import java.lang.reflect.Method;

import com.example.joiintheclub.User;
import com.example.joiintheclub.Group;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

public class Membership {
    private static int LeaderID;
    private boolean IsMember;
    private boolean IsLeader;

    JSONObject requestGET = new JSONObject();
    AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
    JSONObject requestPOST = new JSONObject();
    AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());

    private boolean CheckMembership(){
        //for now
        return IsMember=true;
    }
    private boolean CheckLeadership(){
        //for now
        return IsLeader=true;
    }
    public static int GetLeader(int GroupID){
        //get Group ID number
        Group x=new Group();

        //for now
        LeaderID=0;

        //use x for Requester to return
        //the leader's ID number for Group x
        return LeaderID;
    }

    public static void PromoteMember(int GroupID, int UseriD){
        Group x=new Group();
        x.Get();
        //use requester to update user's status to a member
    }
    public static void DemoteMember(int GroupID, int UserID){
        User user = new User();

        //if(user.IsMember==true)
        //use requester to update user's status back to nonmember
        //else;

    }
}
