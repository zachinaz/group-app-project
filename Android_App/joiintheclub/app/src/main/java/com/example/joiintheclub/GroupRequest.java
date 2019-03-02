package com.example.joiintheclub;
import com.example.joiintheclub.Group;
import com.example.joiintheclub.User;
import com.example.joiintheclub.Membership;

public class GroupRequest {
    public boolean isPending;
    private int RequestID;

    public void sendRequest(){
        Group x = new Group();

        Membership y = new Membership();
        y.GetLeader(x.Get());

        //use Requester to store Leader's ID to notify the leader

        

    }
}
