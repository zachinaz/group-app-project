package test.myapplication;
import java.lang.reflect.Method;

import com.example.joiintheclub.Group;
import com.example.joiintheclub.User;
import java.lang.reflect.Method;

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
