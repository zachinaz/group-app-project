from flask import Flask, request, Response
from json import dumps, loads
from mysql_run import *

app = Flask(__name__)

#--/api/user--
@app.route('/api/user', methods=['GET', 'POST', 'PUT', 'DELETE'])
def user():
    resp = {}
    status = 404

    #--GET--
    # @json: user_id , return first_name, last_name, email, password, profile_pic
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            #SQL SELECT user_id --> first_name, last_name, email, password, profile_pic
            userGET = getUser(user_id)
            print(userGET)
            if userGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            elif userGET == None:
                resp = {"err": "User not found in database"}
                status = 204
            else:
                first_name = userGET["first_name"]
                last_name = userGET["last_name"]
                email = userGET["email"]
                password = userGET["password"]
                resp = {"request_type":"GET", "first_name":f"{first_name}", "last_name":f"{last_name}", "email":f"{email}", "password":f"{password}"}
                status = 200

    #--POST--
    # @json: {first_name, last_name, email, password}
    elif request.method == 'POST':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("first_name" or "last_name" or "email" or "password") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            first_name = json_data.get("first_name")
            last_name = json_data.get("last_name")
            email = json_data.get("email")
            password = json_data.get("password")
            #SQL INSERT first_name, last_name, email, password
            userPOST= postUser(first_name, last_name, email, password)
            print(userPOST)
            #If Database returned an error
            if userPOST == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                user_id = userPOST["user_id"]
                resp = {"request_type":"POST","message":f"User {user_id} Successfully Created","user_id":f"{user_id}"}
                status = 200

    #--PUT--
    # @json: {user_id, first_name, last_name, email, password} , returns: {first_name, last_name, email, password}
    elif request.method == 'PUT':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            first_name = json_data.get("first_name")
            last_name = json_data.get("last_name")
            email = json_data.get("email")
            password = json_data.get("password")
            #SQL UPDATE first_name, last_name, email, password on user_id
            resp = {"request_type":"PUT","message":f"User {user_id} Successfully Updated","user_id":f"{user_id}"}
            status = 200

    #--DELETE--
    # @json: user_id
    elif request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            #SQL DELETE USER on user_id
            userDEL = deleteUser(user_id)
            print(userDEL)
            #If Database returned an error
            if userDEL == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                resp = {"request_type":"DELETE","message":f"User {user_id} Successfully Deleted","user_id":f"{user_id}"}
                status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user--

#--/api/user/membership--
@app.route('/api/user/membership', methods=['GET', 'POST', 'PUT', 'DELETE'])
def membership():
    resp = {}
    status = 404

    group_id = []

    #--GET--
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            #SQL SELECT user_id --> group_id[]
            membershipGET = getMembership(user_id)
            print(membershipGET)
            if membershipGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                group_id = [{membershipGET["group_id"]}] #Need to allow this variable to be a list
                resp = {"request_type":"GET", "message":f"User {user_id} a member of Group {group_id}", "group_id": f"{group_id}"}
                status = 200

    #--POST--
    if request.method == 'POST':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            #SQL INSERT user_id, group_id in membership
            membershipPOST = postMembership(user_id, group_id)
            print(membershipPOST)
            resp = {"request_type":"POST", "message":f"Membership created of User {user_id} in Group {group_id}"}
            status = 200

    #--PUT--
    if request.method == 'PUT':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            privilege = json_data.get("privilege")
            #SQL UPDATE privilege on user_id, group_id
            membershipPUT = updateMembership(user_id, group_id, privilege)
            print(membershipPUT)
            resp = {"request_type":"PUT", "message":f"User {user_id} in Group {group_id} privileges updated"}
            status = 200

    #--DELETE--
    if request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            #SQL DELETE membership on user_id, group_id
            membershipDEL = deleteMembership(user_id, group_id)
            print(membershipDEL)
            resp = {"request_type":"DELETE", "message":f"User {user_id}\'s membership in Group {group_id} has been deleted"}
            status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user/membership--

#--/api/user/membership/privilege--
@app.route('/api/user/membership/privilege', methods=['GET'])
def privilege():
    resp = {}
    status = 404

    #--GET--
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            #SQL SELECT user_id, group_id --> return true or false or privilege level
            privilegeGET = getMemberPrivilege(user_id, group_id)
            print(privilegeGET)
            if privilegeGET == -1:
                resp = {"err": "Database could not perform action"}
                status = 418
            elif privilegeGET == None:
                resp = {"err": "Membership not found in Database"}
                status = 204
            else:
                privilege = privilegeGET["UserPrivileges"]
                resp = {"request_type":"GET", "message":f"User {user_id} is a member of Group {group_id}", "privilege": f"{privilege}"}
                status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user/membership/privilege--

#--/api/user/login--
@app.route('/api/user/login', methods=['GET'])
def login():
    resp = {}
    status = 404

    #--GET--
    # @json: {email, password} , returns user_id
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("email" or "password") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            email = json_data.get("email")
            password = json_data.get("password")
            #SQL SELECT email, password --> user_id
            loginGET = getLogin(email, password)
            print(loginGET)
            if loginGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            elif loginGET == None:
                resp = {"err": "Credentials not found in Database"}
                status = 204
            else:
                user_id = loginGET["user_id"]
                resp = {"request_type":"GET", "message":f"User {user_id} Successfully Logged In", "user_id": f"{user_id}"}
                status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user/login--

#--/api/group--
@app.route('/api/group', methods=['GET', 'POST', 'PUT', 'DELETE'])
def group():
    resp = {}
    status = 404

    #--GET--
    # @json: group_id , returns {name, description, color}
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            group_id = json_data.get("group_id")
            #SQL SELECT group_id --> name, description, color
            groupGET = getGroup(group_id)
            print(groupGET)
            if groupGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            elif groupGET == None:
                resp = {"err": "Group not found in Database"}
                status = 204
            else:
                name = groupGET["name"]
                description = groupGET["description"]
                color = groupGET["color"]
                leader_id = groupGET["leader_id"]
                resp = {"request_type":"GET", "name": f"{name}", "description": f"{description}", "color": f"{color}", "leader_id": f"{leader_id}"}
                status = 200

    #--POST--
    # @json: {user_id, name, description, color} , returns group_id
    if request.method == 'POST':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "name" or "description" or "color") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            leader_id = json_data.get("user_id")
            name = json_data.get("name")
            description = json_data.get("description")
            color = json_data.get("color")
            #SQL INSERT leader_id, name, description, color
            groupPOST = postGroup(leader_id, name, description, color)
            print(groupPOST)
            if groupPOST == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                group_id = groupPOST["GroupID"]
                resp = {"request_type":"POST", "message": f"Group {group_id} Successfully Created", "group_id": f"{group_id}"}
                status = 200

    #--PUT--
    # @json: {group_id, name, description, color}
    if request.method == 'PUT':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            group_id = json_data.get("group_id")
            name = json_data.get("name")
            description = json_data.get("description")
            color = json_data.get("color")
            #SQL UPDATE name, description, color
            resp = {"request_type":"PUT","message":f"Group {group_id} Successfully Updated"}
            status = 200

    #--DELETE--
    # @json: group_id
    if request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            group_id = json_data.get("group_id")
            #SQL DELETE GROUP on group_id
            groupDEL = deleteGroup(group_id)
            print(groupDEL)
            if groupDEL == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                resp = {"request_type":"DELETE","message":f"Group {group_id} Successfully Deleted"}
                status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/group--

#--/api/group/search--
@app.route('/api/group/search', methods=['GET'])
def group_search():
    resp = {}
    status = 404

    #--GET--
    # @json: leader_id, returns {user_id, group_id, group_name}
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("name") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            group_name = json_data.get("name")
            #SQL SELECT name --> description, color, leader_id
            description = groupGET["description"]
            color = groupGET["color"]
            leader_id = groupGET["leader_id"]
            resp = {"request_type":"GET", "name": f"{group_name}", "description": f"{description}", "color": f"{color}", "leader_id": f"{leader_id}"}
            status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/group/search--

#--/api/group/request--
@app.route('/api/group/request', methods=['GET', 'POST', 'DELETE'])
def member_request():
    resp = {}
    status = 404

    #--GET--
    # @json: leader_id, returns {user_id, group_id, group_name}
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            leader_id = json_data.get("user_id")
            #SQL SELECT leader_id --> user_id, group_id, group_name
            requestGET = getRequest(leader_id)
            print(requestGET)

            if requestGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                resp = {"request_type":"GET"}
                for req in requestGET:
                    request_id = req["RequestID"]
                    user_id = req["UserID"]
                    group_id = req["GroupID"]
                    group_name = req["GroupName"]
                resp += {"request_id":f"{request_id}", "user_id":f"{user_id}", "group_id":f"{group_id}", "group_name":f"{group_name}"}
                status = 200

    #--POST--
    # @json: {user_id, group_id}
    if request.method == 'POST':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            #SQL insert group_id, user_id, group_name
            requestPOST = postRequest(user_id, group_id)
            print(requestPOST)

            if requestPOST == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                request_id = req["RequestID"]
                resp = {"request_type":"POST", "message":"Successfully created Request", "request_id":f"{request_id}"}
                status = 200

    #--DELETE--
    # @json: {user_id, group_id}
    if request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            user_id = json_data.get("user_id")
            group_id = json_data.get("group_id")
            #SQL insert group_id, user_id, group_name
            resp = {"request_type":"POST", "message":"Successfully deleted Request"}
            status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/group/request--

#--/api/event--
@app.route('/api/event', methods=['GET', 'POST', 'PUT', 'DELETE'])
def event():
    resp = {}
    status = 404

    #--GET--
    # @json: event_id , returns {DateAndTime, eventName, LeaderID, Location, Description, GroupID}
    if request.method == 'GET':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("event_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            event_id = json_data.get("event_id")
            #SQL SELECT event_id --> DateAndTime, eventName, LeaderID, Location, Description, GroupID
            eventGET = getEvent(event_id)
            print(eventGET)
            if eventGET == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            elif eventGET == None:
                resp = {"err": "Group not found in Database"}
                status = 204
            else:
                date_time = eventGET["DateAndTime"]
                name = eventGET["eventName"]
                leader_id = eventGET["LeaderID"]
                location = eventGET["Location"]
                description = eventGET["Description"]
                group_id = eventGET["GroupID"]
                resp = {"request_type":"GET", "date_time": f"{date_time}", "name": f"{name}", "leader_id": f"{leader_id}", "location": f"{location}", "description": f"{description}", "group_id": f"{group_id}"}
                status = 200

    #--POST--
    # @json: {event_name, event_DateAndTime, leader_id, location, description, group_id} , returns event_id
    if request.method == 'POST':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("name" or "leader_id" or "location" or "description" or "group_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            name = json_data.get("name")
            leader_id = json_data.get("leader_id")
            location = json_data.get("location")
            description = json_data.get("description")
            group_id = json_data.get("group_id")
            #SQL INSERT event_name, event_DateAndTime, leader_id, location, description, group_id
            eventPOST = postGroup(name, date_time, leader_id, location, description, group_id)
            print(eventPOST)
            if eventPOST == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                event_id = eventPOST["EventID"]
                resp = {"request_type":"POST", "message": f"Event {event_id} Successfully Created", "event_id": f"{event_id}"}
                status = 200

    #--PUT--
    # @json: {group_id, name, description, color}
    if request.method == 'PUT':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("event_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            event_id = json_data.get("event_id")
            name = json_data.get("name")
            description = json_data.get("description")
            location = json_data.get("location")
            #SQL UPDATE name, description, location
            resp = {"request_type":"PUT","message":f"Event {event_id} Successfully Updated"}
            status = 200

    #--DELETE--
    # @json: event_id
    if request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("event_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        else:
            event_id = json_data.get("event_id")
            #SQL DELETE EVENT on event_id
            eventDEL = deleteEvent(event_id)
            print(eventDEL)
            if eventDEL == 0:
                resp = {"err": "Database could not perform action"}
                status = 418
            else:
                resp = {"request_type":"DELETE","message":f"Event {event_id} Successfully Deleted"}
                status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/group--

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5050)
