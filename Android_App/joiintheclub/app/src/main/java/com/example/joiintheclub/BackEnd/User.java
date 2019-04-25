package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class User {

    //VARIABLES
    //*********************************************

    //Local storage of the userID used to talk with the DB
    private static String userID;

    //Local user information
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;

    //Local storage of the status of the user
    private static boolean isLoggedIn = false;


    private static String[] userGroupMembership;

    //FUNCTIONS
    //*********************************************

    public static String getUserID() {
        return userID;
    }
    private static void setUserID(String userID) {
        User.userID = userID;
    }

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    private static void setIsLoggedIn(boolean isLoggedIn) {
        User.isLoggedIn = isLoggedIn;
    }

    //Call login when needing to test a user's credentials and if successful, login a user
    //    //  @param String email: the email of the attempted login
    //    //  @param String pwd: the password of the attempted login
    //    //  returns false (if login failed) and true (if login succeeded)
    public static boolean login(String email, String pwd)
    {

        String method = "GET";

        //Make sure user entered something for email and password
        if (email.isEmpty() || pwd.isEmpty()) {
            return false;
        }
        //Make sure user email contains a @. Simple email validation
        else if (!email.contains("@")) {
            return false;
        }

        //Create a JSON object for request and response
        JSONObject loginRequestGET = new JSONObject();
        AtomicReference<JSONObject> loginResponseGET = new AtomicReference<> (new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            loginRequestGET.put("method", method);
            loginRequestGET.put("email", email);
            loginRequestGET.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        loginResponseGET.set(Requester.requester("/user/login", method, loginRequestGET));

        //Unpacks the Response message sent by the requester
        try {
            if (Requester.handleJSON(loginResponseGET.get())) {
                //Searches for "user_id" as a key in the responsePOST.
                Object userID = loginResponseGET.get().get("user_id");

                if (userID.toString().equals("0")) {
                    //credentials not found, returning false
                    return false;
                }
                else {
                    //Set the "user_id" from DB, login to true
                    setUserID(userID.toString());
                    setIsLoggedIn(true);
                    System.out.println(getUserID());
                    return true;
                }
            }
            else {
                //Error returned from the DB
                return false;
            }

        } catch (JSONException e) {
            //Prints error message to console via stacktrace
            e.printStackTrace();
            return false;
        }
    }

    //Call logout when needing to log a user our of the app
    public static void logout() {
        User.setIsLoggedIn(false);
    }

    //Call createUser to create a User in the Database
    public static boolean createUser(String firstName,
                                     String lastName,
                                     String email,
                                     String pwd)
    {
        //Creates new JSONObjects to pass to and receive from Requester.requester
        //  Note: responses need to be in Atomic Reference since the requester works on a different thread.
        JSONObject requestPOST = new JSONObject();
        AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());

            try {
                //Populates the object with keys and values
                //  e.g. "first_name": "Jim"
                //  Note: keys need to be in python format since the api is written in python (e.g. first_name)
                //        while values can be in java format (e.g. firstName)
                requestPOST.put("first_name", firstName);
                requestPOST.put("last_name", lastName);
                requestPOST.put("email", email);
                requestPOST.put("password", pwd);
            } catch (JSONException e){ //Catch necessary since dictToApi.put can throw the exception JSONException
                //Prints the error message to the console via stacktrace
                e.printStackTrace();
            }

            //Saves the output of Request.requester to a JSONObject responsePOST
            responsePOST.set(Requester.requester("/user", "POST", requestPOST));

            try {
                if (Requester.handleJSON(responsePOST.get())) {
                    //Searches for "user_id" as a key in the responsePOST.
                    Object userID = responsePOST.get().get("user_id");
                    setUserID(userID.toString());
                    System.out.println(getUserID());
                    return true;
                }
                else {
                    //Error returned from the DB
                    return false;
                }

            } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
                //Prints the error message to the console via stacktrace
                e.printStackTrace();
                return false;
            }
        }



//implementation of updateUser depends on the UI
//put user - only user_id is necessary
    //3 digit response means just that it worked ???
    //no data has to e returned

    //update user does it as a package or now
    public static boolean updateUser(String userID, String firstName, String lastName,
                                     String email, String pwd)
    {
        //
        //Create a JSON object for request and response
        JSONObject updateRequestPUT = new JSONObject();
        AtomicReference<JSONObject> updateResponsePUT = new AtomicReference<> (new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            updateRequestPUT.put("user_id", userID);
            updateRequestPUT.put("first_name", firstName);
            updateRequestPUT.put("last_name", lastName);
            updateRequestPUT.put("email", email);
            updateRequestPUT.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        updateResponsePUT.set(Requester.requester("/user", "PUT",
                updateRequestPUT));

        try {
            if (Requester.handleJSON(updateResponsePUT.get())) {
                //Searches for "user_id" as a key in the responsePOST.
                Object userID_response = updateResponsePUT.get().get("user_id");

                //Ensures that the correct user was edited
                if (userID != userID_response)
                    return false;

                setUserID(userID_response.toString());
                System.out.println(getUserID());
                return true;
            } else {
                //Error returned from the DB
                return false;
            }
        } catch(JSONException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static String[] getUserGroups(String userID, boolean isLoggedIn)
    {
        //Array for storing groupID for groups this user is a member of
        String[] userMemberGroup = new String[10];



        //maybe have variable specifying how many groups exist
        int numExistGroups = 10;

        //test cases I guess??
        //String testGroupID = "0001";
        int numUserInvolvement = 0;

        //Make sure user is logged in
        if (isLoggedIn)
        {
            //Get the groups of this logged in user
            //store it in array
            //Go through the groups ??
            JSONObject userMemberRequestGET = new JSONObject();
            AtomicReference<JSONObject> userMemberResponseGET =
                    new AtomicReference<> (new JSONObject());

            JSONObject userMemberGroupRequestGET = new JSONObject();
            AtomicReference<JSONObject> userMemberGroupResponseGET =
                    new AtomicReference<> (new JSONObject());


            try {
                userMemberRequestGET.put("user_id", userID);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                //unsure about this!!!

            }

            userMemberResponseGET.set(Requester.requester("/user/membership",
                    "GET", userMemberRequestGET));

            userMemberGroupResponseGET.set(Requester.requester("/group",
                    "GET", userMemberGroupRequestGET));

            String testGroupID = "0000";
// public static boolean CheckMembership(String GroupID, String UserID){
         //   if (Membership.IsMember == true)
            try {
                if (Membership.CheckMembership(testGroupID, userID))
                {
                    String group_id = userMemberGroupResponseGET.get().get("group_id").toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return userMemberGroup;
            }


                    //   String first_name = userInfoResponseGET.get().get("first_name").toString();
            //unsure especially about below

            /*

        //Unpacks the Response message sent by the requester
        try {
            String user_id = loginResponseGET.get().get("user_id").toString();

            if (user_id.equals("0")) {
                //credentials not found, returning false
                return false;
            }
            else {
                //credentials found, returning true
                User.setUserID(user_id);
                User.setIsLoggedIn(true);
                return true;
            }
        } catch (JSONException e) {
            //Prints error message to console via stacktrace
            e.printStackTrace();
            return false;
        }

             */
            //get response of list of groups

        }
        return userMemberGroup;

    }

//maybe not boolean since may have to return info
    //public static boolean userInformation(String userID)
    public static String[] userInformation(String userID)
    {
        //Array of strings containing user profile information
        String[] userProfile = new String[6];

            /*
        //User ID is in the first position for
        userProfile[0] = userID;
        //JSON object for requesting user information

        JSONObject userInfoRequestGET = new JSONObject();
        AtomicReference<JSONObject> userInfoResponseGET =
                new AtomicReference<> (new JSONObject());

        //Populate this json object with the user ID
        try {
            userInfoRequestGET.put("user_ID", userID);
        } catch (JSONException e) {
            e.printStackTrace();
            return userProfile;
        }

        //Verifies user ID with DB, keeps response in userInfoResponse
        userInfoResponseGET.set(Requester.requester("/user", "GET", userInfoRequestGET));

        //Unpacks response sent by requester
        try {
            String user_id = userInfoResponseGET.get().get("user_id").toString();

            //Credentials found
               //UNSURE IF THIS CORRECT!!!!
            /*
            if (!user_id.equals("0")) {

            }

            //Credentials not found
            if (user_id.equals("0")) {
                //return false;
            }

            //Credentials found
            else {
                //Get the user information -????????

                String first_name = userInfoResponseGET.get().get("first_name").toString();
                String last_name = userInfoResponseGET.get().get("last_name").toString();
                String email = userInfoResponseGET.get().get("email").toString();
                String password = userInfoResponseGET.get().get("password").toString();


                userProfile[1] = first_name;
                userProfile[2] = last_name;
                userProfile[3] = email;
                userProfile[4] = password;

              }

        } catch (JSONException e)
        {
            e.printStackTrace();
           // return false;
        }*/


            //this is just for now - simply for hardcode testing
            //later uncomment above as appropriate
            //later comment out/remove below section

            userProfile[0] = "123456";
            userProfile[1] = "Stephen";
            userProfile[2] = "Styffe";
            userProfile[3] = "sample@gmail.com";
            userProfile[4] = "password1";

        return userProfile;
    }

    /*maybe do this later, since need email functionality
    public static void forgotPassword(int userID)
    {
        verifyUser(userID);
        updateUser(userID);


    }
    */

    /*
    //Allows a user to create a new group
    public static boolean createGroup (
            int userID,
            String GroupName,
            String description,
            String color
    )
    {

        //User sends createGroup requests to requester
        //  Sends GroupName
        //  program sends Date

        //if it does not already exist, it becomes verified

        //Create new JSON object to pass and receive from Requester.requester
        JSONObject groupRequestGET = new JSONObject();
        AtomicReference<JSONObject> groupResponseGET =
                new AtomicReference<>(new JSONObject());
        JSONObject groupRequestPOST = new JSONObject();
        AtomicReference<JSONObject> groupResponsePOST =
                new AtomicReference<>(new JSONObject());

        //Request info from requester to see if already exists
        groupResponseGET.set(Requester.requester("/group", "GET",
                groupRequestGET));


        //SHOULD VERIFY IF GROUP ALREADY EXISTS (BASED ON ITS NAME)


            try {
                //Populate object with keys and values needed for group creation
                //  Keys (in_python), Values (inJava)
                groupRequestPOST.put("user_ID", userID);
                groupRequestPOST.put("name", GroupName);
                groupRequestPOST.put("description", description);
                groupRequestPOST.put("color", color);

            } catch (JSONException e) {
                //Prints error message to console via stacktrace
                e.printStackTrace();
            }

            //Saves the output of Request.requester to a JSONObject responsePOST
            groupResponsePOST.set(Requester.requester("/group", "POST",
                    groupRequestPOST));

            try {
                //Searches for "group_id" as key in responsePOST
                Object GroupID = groupResponsePOST.get().get("group_id");
            } catch (JSONException e) { //catch needed since groupResopnsePOST.get can throw exception JSONException
                //Prints error message to console via stacktrace
                e.printStackTrace();
            }

        return true;




    }
*/
}


