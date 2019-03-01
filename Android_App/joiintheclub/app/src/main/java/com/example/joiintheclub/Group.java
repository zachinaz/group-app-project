package com.example.joiintheclub;
import java.util.Date;
import java.util.LinkedHashMap;

public class Group {

        private String groupName;
        private int groupID;
        private int numOfPeople;
        Date DateCreated;



        public void searchGroup (String userInput){
        Group n = new Group();
        if (n.verifyGroup(userInput)==true)
            //get requester to return Group info based on
            //userInput (group name, description, color, icon)
            ;
        else
            System.out.println("Group not found");

        }

        private boolean verifyGroup (String userInput){

            //call requester here to verify that userInput group exists
                //if (userInput group exists in Database)
                    //return true;
                //else return false;
            return true;
        }



}

