package com.example.joiintheclub.BackEnd;
import com.example.joiintheclub.Membership;
import com.example.joiintheclub.Group;
import com.example.joiintheclub.User;
import com.example.joiintheclub.Membership;


public class GroupRequest {

    public boolean isPending;
    private int RequestID;

    JSONObject requestGET = new JSONObject();
    AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
    JSONObject requestPOST = new JSONObject();
    AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());

    public static void sendRequest(){
        Group x = new Group();

        Membership y = new Membership();
        y.GetLeader(x.Get());

        //use Requester to store Leader's ID to notify the leader
        //push to requester a new request and its status


    }
    public static void acceptRequest(){
        //update membership status
        Group currentGroup=new Group();
        Membership x = new Membership();
        User requester=new User();
        x.PromoteMember(currentGroup.Get(), requester.get());

        //update pending status
        //user requester to post "pending_status" to complete
    }
    public static void rejectRequest(){
        Group currentGroup=new Group();
        Membership x = new Membership();
        User requester=new User();
        x.DemoteMember(currentGroup.Get(), requester.get());

        //user requester to delete group request
    }
}

