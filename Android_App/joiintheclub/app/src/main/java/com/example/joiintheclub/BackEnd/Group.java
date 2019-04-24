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
        String[][] displayInfo = {{}};
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

         //Populate JSON request object with values passed into function
        try {
            requestGET.put("group", "all"); //API just needs any JSON to function
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //Saves the output of Request.requester to a JSONObject responsePOST
        responseGET.set(Requester.requester("/group/search", "GET", requestGET));

        try {
            if (Requester.handleJSON(responseGET.get())) {

                //Parses the number of groups from the requester
                Object groupCount = responseGET.get().get("count");
                int count = Integer.parseInt(groupCount.toString());

                //Iterates through every group returned
                for (int i = 0; i < count; i++) {
                    JSONObject group = responseGET.get().getJSONObject("group" + i);

                    System.out.println(group);
                    displayInfo[i][0] = group.get("name").toString();
                    displayInfo[i][1] = group.get("leader_id").toString();
                    displayInfo[i][2] = group.get("color").toString();
                    displayInfo[i][3] = group.get("description").toString();
                }
                System.out.println(displayInfo);
                return displayInfo;
            }
            else {
                //Error returned from the DB
                return null;
            }

        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            e.printStackTrace();
            return null;
        }
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

        String[][] userGroups = {{}};

        //use requester to get Group iD
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        try {
            requestGET.put("user_id", User.getUserID());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        responseGET.set(Requester.requester("/group", "GET", requestGET));
        //test case
        //GroupID = 1424;

        try {
            if (Requester.handleJSON(responseGET.get())) {

                //Parses the number of groups from the requester
                Object groupCount = responseGET.get().get("count");
                int count = Integer.parseInt(groupCount.toString());

                //Iterates through every group returned
                for (int i = 0; i < count; i++) {
                    JSONObject group = responseGET.get().getJSONObject("membership" + i);

                    System.out.println(group);
                    userGroups[i][0] = group.get("name").toString();
                    userGroups[i][1] = group.get("leader_id").toString();
                    userGroups[i][2] = group.get("color").toString();
                    userGroups[i][3] = group.get("description").toString();
                }
                System.out.println(userGroups);
                return userGroups;
            }
            else {
                //Error returned from the DB
                return null;
            }
        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            e.printStackTrace();
            return null;
        }
    }

    public static void setGroupName(String groupName) {
        Group.GroupName= groupName;
    }

    public static void setGroupID(String groupID) {
        Group.GroupID= groupID;
    }



}
