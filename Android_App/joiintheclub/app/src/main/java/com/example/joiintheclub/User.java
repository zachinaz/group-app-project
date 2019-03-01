package com.example.joiintheclub;

import java.util.LinkedHashMap;

public class User {


    private String firstName;
    private String lastName;
    private String email;
    private String pwd;


    //private String profilePicURL;
    //private int userID;
    private static boolean isActive;
    public LinkedHashMap dict;

    public static boolean createUser(String firstName,
                              String lastName,
                              String email,
                              String pwd,
                              LinkedHashMap dict)
    {
       //????? dict = <firstName,lastName>;

        //Request info from requester to see if already exists
        Requester.requester("/user", "GET", dict);
            //if it returns something, then it already exists ???

        //If it already exists
        if (isActive)
        {
            return false;
        }
        //If it doesn't exist yet
        else
        {
            //Create it here
            //  ...
            //maybe this
               Requester.requester("/user", "POST",
                       dict);

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
