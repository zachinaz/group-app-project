import pymysql


# for GET USER
def getUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	getUserStatement = "select `user`.FirstName as first_name, `user`.LastName as last_name, `user`.EmailAddress as email, `user`.Password as password from `user` where `user`.UserID = user_id;"
	try:
		cursor.execute(getUserStatement)
		connection.commit()
	except:
		connection.rollback()

	connection.close()



# for POST USER
def postUser(first_name, last_name, email, password):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	postUserInsert = "insert into `user` values(default, first_name, last_name, email, password, sysdate(), null);"
	postUserSelect = "select `user`.userID as user_id from `user` where `user`.FirstName = first_name and `user`.LastName = last_name;"
	try:
		cursor.execute(postUserInsert)
		connection.commit()
	except:
		connection.rollback()

	try:
		cursor.execute(postUserSelect)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()



# for DELETE USER
def deleteUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteUserStatement = "delete from `user` where `user`.userID = user_id;"
	try:
		cursor.execute(deleteUserStatement)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()



# for GET MEMBERSHIP
def getMembership(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	getMembershipStatement = "select `membership`.GroupID as group_id from `membership` where `membership`.userID = `user`.UserID and `membership`.UserID = user_id;"
	try:
		cursor.execute(getMembershipStatement)
		connection.commit()
	except:
		connection.rollback()

	connection.close()



# for POST MEMBERSHIP
def postMembership(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	postMembershipStatement = "insert into `membership` values(default,0, user_id, group_id);"
	try:
		cursor.execute(postMembershipStatement)
		connection.commit()
	except:
		connection.rollback()

	connection.close()



# for PUT MEMBERSHIP
def updateMembership(user_id, group_id, privilege):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateMembershipStatement = "update `membership` set UserPrivileges = privilege where `membership`.userID = user_id and `membership`.GroupID = group_id;"
	try:
		cursor.execute(updateMembershipStatement)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()
	
	

# for DELETE MEMBERSHIP
def deleteMembership(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	deleteMembershipStatement = "delete from `membership` where `membership`.UserID = user_id and `membership`.GroupID = group_id;"
	try:
		cursor.execute(deleteMembershipStatement)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()
	

# for GET LOGIN
def getLogin(email, password):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	selectUser = "select `user`.userID as user_id from `user` where `user`.EmailAddress = email and `user`.Password = password;"
	try:
		cursor.execute(selectUser)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()



# for GET GROUP
def getGroup(group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	getGroupStatement = "select `record`.GroupName as name, `record`.GroupDescription as description, `record`.LeaderID as leader_id, `record`.GroupColor as color from `record` where `record`.GroupID = group_id;"
	try:
		cursor.execute(getGroupStatement)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()


# for POST GROUP
def postGroup(leader_id, name, description, color):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postGroupInsert = "insert into `record` values(default, name, leader_id, color, description);"
	postGroupSelect = "select `record`.GroupID from `record` where `record`.GroupName = name;"
	try:
		cursor.execute(postGroupInsert)
		connection.commit()
	except:
		connection.rollback()

	try:
		cursor.execute(postGroupSelect)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()


# for DELETE GROUP
def deleteGroup(group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	deleteGroupStatement = "delete from `record` where `record`.GroupID = group_id;"
	try:
		cursor.execute(deleteGroupStatement)
		connection.commit()
	except:
		connection.rollback()
	
	connection.close()
