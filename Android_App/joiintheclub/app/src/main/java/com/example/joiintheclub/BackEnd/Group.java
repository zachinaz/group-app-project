package com.example.joiintheclub.BackEnd;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;


public class Group {

    public static String GroupName;
    public static String GroupID;
    private int NumOfPeople;
    private boolean verified;
    Date DateCreated;

    //int array as group ID's for testing
    int[] GroupIDs = new int[]{1424, 1535, 2535, 2646, 1021};



    public static String[][] SearchGroup (){
        String[][] displayInfo={{"1","XOPOC","Dance crew"},
                {"2","Spanish Club","learn Spanish"},
                {"3","Computer Science Club", "Explore Comp Sci"}};
        //commented out until requester works
        /*Group n = new Group();
            JSONObject requestGET = new JSONObject();
            AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
            JSONObject requestPOST = new JSONObject();
            AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());
             //Populate JSON request object with values passed into function
            try {
                requestGET.put("name", userInput);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (n.VerifyGroup(userInput)==true) {
                //get requester to return Group info based on
                //userInput (group name, description, color, icon)

                //Unpacks the Response message sent by the requester
                try {
                    userInput = responseGET.get().get("name").toString();
                    if (userInput.equals("0")) {
                        //credentials not found, returning false
                        System.out.println("\nGroup name not found");
                    }
                    else {
                        //credentials found, returning true
                        Group.setGroupName(userInput);
                    }
                } catch (JSONException e) {
                    //Prints error message to console via stacktrace
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Group not found");*/
        return displayInfo;
        //hardcode for a test case
    }



    public boolean VerifyGroup (String GroupID){
        verified=false;
        //Creates new JSONObjects to pass to and receive from Requester.requester
        //  Note: responses need to be in Atomic Reference since the requester works on a different thread.

            JSONObject requestGET = new JSONObject();
            AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
            JSONObject requestPOST = new JSONObject();
            AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            requestGET.put("group_id", GroupID);
        } catch (JSONException e) {
            e.printStackTrace();
            verified= false;
        }

        //verify
        responseGET.set(Requester.requester("/group", "GET", requestGET));

        //Unpacks the Response message sent by the requester
        try {
            String group_id = responseGET.get().get("group_id").toString();
            if (group_id.equals("0")) {
                //credentials not found, returning false
                verified= false;
            }
            else {
                //credentials found, returning true
                Group.setGroupID(group_id);
                verified=true;
            }
        } catch (JSONException e) {
            //Prints error message to console via stacktrace
            e.printStackTrace();
            verified= false;
        }

        //hard code for a testcase
        /*int x =0;
        while (x<GroupIDs.length)
        {
            if(GroupID==GroupIDs[x])
            {
                verified=true;
                break;
            }
            x++;
        }
        System.out.print(verified);*/
        return verified;

    }

    public static String[][] Get(){
        //use requester to get Group iD
        //JSONObject requestGET = new JSONObject();
       // AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        //responseGET.set(Requester.requester("/group", "GET", requestGET));
        //test case
        //GroupID = 1424;



        String[][] displayGroups={{"1","XOPOC","Dance crew"},
                {"3","Computer Science Club", "Explore Comp Sci"}};
        return displayGroups;
    }

    public static void setGroupName(String groupName) {
        Group.GroupName= groupName;
    }

    public static void setGroupID(String groupID) {
        Group.GroupID= groupID;
    }



}
