package com.example.joiintheclub.BackEnd;

public class Membership {
    private static int LeaderID;
    public static int GetLeader(int GroupID){
        //get Group ID number
        Group x=new Group();

        //for now
        LeaderID=0;

        //use x for Requester to return
        //the leader's ID number for Group x
        return LeaderID;
    }
    public static boolean IsMember(){
        return true;
    }

}
