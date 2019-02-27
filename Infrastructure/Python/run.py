from flask import Flask, request, Response
from json import dumps, loads

app = Flask(__name__)

#--/api/user--
@app.route('/api/user', methods=['GET', 'POST', 'DELETE'])
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
            #SQL INPUT first_name, last_name, email, password
            resp = {"request_type":"POST","message":f"User {first_name} {last_name} Successfully Created"}
            status = 200

    #--DELETE--
    # @args: user_id
    elif request.method == 'DELETE':
        json_data = request.get_json(force=True)
        if not json_data:
            resp = {"err": "No data provided"}
            status = 400
        elif ("user_id") not in json_data:
            resp = {"err": "Missing required fields"}
            status = 400
        user_id = json_data.get("user_id")
        #SQL DELETE on user_id
        resp = {"request_type":"DELETE","message":f"User {user_id} Successfully Deleted"}
        status = 200

    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user--

#--/api/user/login--
@app.route('/api/user/login',methods=['GET'])
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
                resp = {"request_type":"GET", "message":f"User {user_id} Successfully Logged In"}
                status = 200
            else:
                resp = {"err: Credentials are invalid"}
                status = 401
    return Response(dumps(resp),status=status,mimetype='application/json')
#END OF --/api/user/login--



if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
