package com.example.joiintheclub.BackEnd;
import java.util.Date;

public class Group {

        private String GroupName;
        public static int GroupID;
        private int NumOfPeople;
        private boolean verified;
        Date DateCreated;



        public static void SearchGroup (String userInput){
            Group n = new Group();
            if (n.VerifyGroup(userInput)==true)
            //get requester to return Group info based on
            //userInput (group name, description, color, icon)
            ;
            else
                System.out.println("Group not found");

        }

        public static int Get(){
            //use requester to get Group iD
            //for now,
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

