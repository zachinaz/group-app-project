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
        String[][] displayInfo;
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

                displayInfo = new String[count][];

                //Iterates through every group returned
                for (int i = 0; i < count; i++) {
                    JSONObject group = responseGET.get().getJSONObject("group" + (i + 1));

                    displayInfo[i] = new String[4];

                    System.out.println(group);
                    displayInfo[i][0] = group.get("name").toString();
                    displayInfo[i][1] = group.get("leader_id").toString();
                    displayInfo[i][2] = group.get("color").toString();
                    displayInfo[i][3] = group.get("description").toString();
                }
                return displayInfo;
            }
            else {
                //Error returned from the DB
                displayInfo = new String[1][5];
                displayInfo[0][0]  = "leader_id";
                displayInfo[0][1] = "question";
                displayInfo[0][2] = "poll_response_options";
                displayInfo[0][3] = "poll_description";
                return null;
            }

        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            displayInfo = new String[1][5];
            displayInfo[0][0]  = "leader_id";
            displayInfo[0][1] = "question";
            displayInfo[0][2] = "poll_response_options";
            displayInfo[0][3] = "poll_description";
            e.printStackTrace();
            return null;
        }
    }

    public static String[][] GetPolls (String groupID) {
        String[][] displayInfo;
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            requestGET.put("method", "GET");
            requestGET.put("group_id", groupID);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //Saves the output of Request.requester to a JSONObject responsePOST
        responseGET.set(Requester.requester("/poll/search", "GET", requestGET));

        try {
            if (Requester.handleJSON(responseGET.get())) {

                //Parses the number of polls from the requester
                Object pollCount = responseGET.get().get("count");
                int count = Integer.parseInt(pollCount.toString());

                displayInfo = new String[count][];

                //Iterates through every poll returned
                for (int i = 0; i < count; i++) {
                    JSONObject poll = responseGET.get().getJSONObject("poll" + (i + 1));

                    displayInfo[i] = new String[5];

                    System.out.println(poll);
                    displayInfo[i][0] = poll.get("leader_id").toString();
                    displayInfo[i][1] = poll.get("question").toString();
                    displayInfo[i][2] = poll.get("poll_response_options").toString();
                    displayInfo[i][3] = poll.get("poll_description").toString();
                    displayInfo[i][4] = poll.get("date_time").toString();
                }
                return displayInfo;
            }
            else {
                //Error returned from the DB
                displayInfo = new String[1][5];
                displayInfo[0][0]  = "leader_id";
                displayInfo[0][1] = "question";
                displayInfo[0][2] = "poll_response_options";
                displayInfo[0][3] = "poll_description";
                return null;
            }

        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            displayInfo = new String[1][5];
            displayInfo[0][0]  = "leader_id";
            displayInfo[0][1] = "question";
            displayInfo[0][2] = "poll_response_options";
            displayInfo[0][3] = "poll_description";

            e.printStackTrace();
            return null;
        }
    }

    public static String[][] GetAnnouncements (String groupID) {
        String[][] displayInfo;
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            requestGET.put("method", "GET");
            requestGET.put("group_id", groupID);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //Saves the output of Request.requester to a JSONObject responsePOST
        responseGET.set(Requester.requester("/announcement/search", "GET", requestGET));

        try {
            if (Requester.handleJSON(responseGET.get())) {

                //Parses the number of announcements from the requester
                Object announcementCount = responseGET.get().get("count");
                int count = Integer.parseInt(announcementCount.toString());

                displayInfo = new String[count][];

                //Iterates through every announcement returned
                for (int i = 0; i < count; i++) {
                    JSONObject ann = responseGET.get().getJSONObject("poll" + (i + 1));

                    displayInfo[i] = new String[3];

                    System.out.println(ann);
                    displayInfo[i][0] = ann.get("leader_id").toString();
                    displayInfo[i][1] = ann.get("content").toString();
                    displayInfo[i][2] = ann.get("date_time").toString();
                }
                return displayInfo;
            }
            else {
                //Error returned from the DB
                displayInfo = new String[1][5];
                displayInfo[0][0]  = "leader_id";
                displayInfo[0][1] = "question";
                displayInfo[0][2] = "poll_response_options";
                displayInfo[0][3] = "poll_description";
                return null;
            }

        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            displayInfo = new String[1][5];
            displayInfo[0][0]  = "leader_id";
            displayInfo[0][1] = "question";
            displayInfo[0][2] = "poll_response_options";
            displayInfo[0][3] = "poll_description";
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

        String[][] userGroups;

        //use requester to get Group iD
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());

        try {
            requestGET.put("method", "GET");
            requestGET.put("user_id", User.getUserID());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        responseGET.set(Requester.requester("/user/membership", "GET", requestGET));
        //test case
        //GroupID = 1424;

        try {
                if (Requester.handleJSON(responseGET.get())) {

                //Parses the number of groups from the requester
                Object groupCount = responseGET.get().get("count");
                int count = Integer.parseInt(groupCount.toString());

                userGroups = new String[count][];

                //Iterates through every group returned
                for (int i = 0; i < count; i++) {
                    JSONObject group = responseGET.get().getJSONObject("membership" + (i + 1));

                    userGroups[i] = new String[4];

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
                    userGroups = new String[1][5];
                    userGroups[0][0]  = "leader_id";
                    userGroups[0][1] = "question";
                    userGroups[0][2] = "poll_response_options";
                    userGroups[0][3] = "poll_description";
                return null;
            }
        } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
            //Prints the error message to the console via stacktrace
            userGroups = new String[1][5];
            userGroups[0][0]  = "leader_id";
            userGroups[0][1] = "question";
            userGroups[0][2] = "poll_response_options";
            userGroups[0][3] = "poll_description";
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
