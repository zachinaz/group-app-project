from flask import Flask, request, Response
from json import dumps, loads

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
            first_name = "John"
            last_name = "Doe"
            email = "testemail@gmail.com"
            password = "P@ssword"
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
            user_id = "3"
            resp = {"request_type":"POST","message":f"User {user_id} Successfully Created"}
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
            if "first_name" in json_data:
                first_name = json_data.get("first_name")
            if "last_name" in json_data:
                last_name = json_data.get("last_name")
            if "email" in json_data:
                email = json_data.get("email")
            if "password" in json_data:
                password = json_data.get("password")
            #SQL UPDATE first_name, last_name, email, password on user_id
            resp = {"request_type":"PUT","message":f"User {user_id} Successfully Updated"}
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
            resp = {"request_type":"DELETE","message":f"User {user_id} Successfully Deleted"}
            status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user--

#--/api/user/login--
@app.route('/api/user/login', methods=['GET'])
def login():
    resp = {}
    status = 404
    login = True #SHOULD BE FALSE ONCE SQL IS ADDED!!!!!!!!!!

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
            user_id = "3"
            if login:
                resp = {"request_type":"GET", "message":f"User {user_id} Successfully Logged In", "user_id": f"{user_id}"}
                status = 200
            else:
                resp = {"err: Credentials are invalid"}
                status = 401
    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user/login--

#--/api/group--
@app.route('/api/group', methods=['GET', 'POST', 'DELETE'])
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
            name = "Test Group"
            description = "Four score and twenty years ago..."
            color = "Blue"
            resp = {"request_type":"GET", "name": f"{name}", "description": f"{description}", "color": f"{color}"}
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
            user_id = json_data.get("user_id")
            name = json_data.get("name")
            description = json_data.get("description")
            color = json_data.get("color")
            #SQL INSERT user_id, name, description, color
            group_id = "3"
            resp = {"request_type":"POST", "message": f"Group {group_id} Successfully Created", "group_id": f"{group_id}"}
            status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/group--

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
