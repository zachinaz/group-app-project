package com.example.joiintheclub.BackEnd;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
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

    //Call login when needing to test a user's credentials and if successful, login a user
    //    //  @param String email: the email of the attempted login
    //    //  @param String pwd: the password of the attempted login
    //    //  returns false (if login failed) and true (if login succeeded)
    public static boolean login(String email, String pwd)
    {
        //Test cases for now
        //email = "john@gmail.com";
        //pwd = "1234";


        //Make sure user entered something for email and password
        if (email.isEmpty() || pwd.isEmpty()) {
            return false;
        }

        //Create a JSON object for request and response
        JSONObject loginRequestGET = new JSONObject();
        AtomicReference<JSONObject> loginResponseGET = new AtomicReference<> (new JSONObject());

        //Populate JSON request object with values passed into function
        try {
            loginRequestGET.put("email", email);
            loginRequestGET.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        //Verifies email and password with the DB. Keeps response in loginResponseGET JSON object
        loginResponseGET.set(Requester.requester("/user/login", "GET", loginRequestGET));

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
    }

    //Call logout when needing to log a user our of the app
    public static void logout() {
        User.setIsLoggedIn(false);
    }

    public static boolean createUser(String firstName,
                                     String lastName,
                                     String email,
                                     String pwd)
    {
        //Creates new JSONObjects to pass to and receive from Requester.requester
        //  Note: responses need to be in Atomic Reference since the requester works on a different thread.
        JSONObject requestGET = new JSONObject();
        AtomicReference<JSONObject> responseGET = new AtomicReference<>(new JSONObject());
        JSONObject requestPOST = new JSONObject();
        AtomicReference<JSONObject> responsePOST = new AtomicReference<>(new JSONObject());
        //????? dict = <firstName,lastName>;

        //Request info from requester to see if already exists
        responseGET.set(Requester.requester("/user", "GET", requestGET));

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
            //Create it here
            //  ...
            //maybe this
            //Saves the output of Request.requester to a JSONObject responsePOST
            responsePOST.set(Requester.requester("/user", "POST", requestPOST));

            try {
                //Searches for "user_id" as a key in the responsePOST.
                Object userID = responsePOST.get().get("user_id");
            } catch (JSONException e){ //Catch necessary since responsePOST.get can throw the exception JSONException
                //Prints the error message to the console via stacktrace
                e.printStackTrace();
            }

            return true;
        }



    //was originally a private void method
    //different classes may need to call get() method
    // public int get() {
    public static String get(
            String userID
    )
    {
        JSONObject userGetRequestGET = new JSONObject();
        AtomicReference<JSONObject> getResponseGET = new AtomicReference<>(new JSONObject());
        getResponseGET.set(Requester.requester("/user", "GET", userGetRequestGET));


        //return 0; //for now
        return "0";
    }

    //this will check if the user exists or not
    public static boolean verifyUser(String userID)
    {
        get(userID);
        return false;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        User.userID = userID;
    }

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        User.isLoggedIn = isLoggedIn;
    }

//implementation of updateUser depends on the UI
//put user - only user_id is necessary
    //3 digit response means just that it worked ???
    //no data has to e returned

    //update user does it as a package or now
    public static boolean updateUser(String userID, String firstName, String lastName,
                                     String email, String pwd)
    {
        if (verifyUser(userID))
        {
            //
            //Create a JSON object for request and response
            JSONObject updateRequestPUT = new JSONObject();
            AtomicReference<JSONObject> updateResponsePUT = new AtomicReference<> (new JSONObject());

            //Populate JSON request object with values passed into function
            try {
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
            return true;

        }
        else
        {
            //unable to verify
            return false;
        }

    }


    public static String[] getUserGroups(String userID, boolean isLoggedIn)
    {
        //Array for storing groupID for groups this user is a member of
        String[] userMemberGroup = new String[10];



        //maybe have variable specifiyng how many groups exist
        int numExistGroups = 10;

        //test cases I guess??
        //String testGroupID = "0001";
        int numUserInvolvement = 0;

        //Make sure user is logged in
        if (isLoggedIn == true)
        {
            //Get the groups of this logged in user
            //store it in array
            //Go through the groups ??
            JSONObject userMemberRequestGET = new JSONObject();
            AtomicReference<JSONObject> userMemberResponseGET =
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
    private static String[] userInformation(String userID)
    {
        //Array of strings containing user profile information
        String[] userProfile = new String[6];

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
            if (!user_id.equals("0")) {

            /*
            //Credentials not found
            if (user_id.equals("0")) {
                //return false;
            }
            */
                //Credentials found
                //else
                // {
                //Get the user information -????????

                String first_name = userInfoResponseGET.get().get("first_name").toString();
                String last_name = userInfoResponseGET.get().get("last_name").toString();
                String email = userInfoResponseGET.get().get("email").toString();
                String password = userInfoResponseGET.get().get("password").toString();
                //stuff for profile picture here!!!!!

                userProfile[1] = first_name;
                userProfile[2] = last_name;
                userProfile[3] = email;
                userProfile[4] = password;
                //PROFILE PICTURE HERE

                /*
                //Variables now have this information - ?????
                User.firstName = first_name;
                User.lastName = last_name;
                User.email = email;
                */
                //profile picture!!!!!

                //}
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
           // return false;
        }
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


