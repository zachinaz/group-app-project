import pymysql


# for GET USER
def getUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getUserStatement = "select `user`.FirstName as first_name, `user`.LastName as last_name, `user`.EmailAddress as email, `user`.Password as password from `user` where `user`.UserID = %s;"
	try:
		cursor.execute(getUserStatement, (user_id))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0

	connection.close()
	return result.values()



# for POST USER
def postUser(first_name, last_name, email, password):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postUserInsert = "insert into `user` values(default, %s, %s, %s, %s, curdate(), null);"
	postUserSelect = "select `user`.userID as user_id from `user` where `user`.FirstName = %s and `user`.LastName = %s;"
	try:
		cursor.execute(postUserInsert, (first_name, last_name, email, password))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(postUserSelect, (first_name, last_name))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0

	connection.close()
	return result.values()



# for DELETE USER
def deleteUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteUserStatement = "delete from `user` where `user`.userID = %s;"
	try:
		cursor.execute(deleteUserStatement, (user_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0


# for GET MEMBERSHIP
def getMembership(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getMembershipStatement = "select `membership`.GroupID as group_id from `membership` where `membership`.userID = `user`.UserID and `membership`.UserID = %s;"
	try:
		cursor.execute(getMembershipStatement, (user_id))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0

	connection.close()
	return result.values()

# for POST MEMBERSHIP
def postMembership(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postMembershipStatement = "insert into `membership` values(default,0, %s, %s);"
	try:
		cursor.execute(postMembershipStatement,(user_id, group_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0


# for PUT MEMBERSHIP
def updateMembership(user_id, group_id, privilege):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	updateMembershipStatement = "update `membership` set UserPrivileges = privilege where `membership`.userID = %s and `membership`.GroupID = %s;"
	try:
		cursor.execute(updateMembershipStatement, (user_id, group_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0


# for DELETE MEMBERSHIP
def deleteMembership(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteMembershipStatement = "delete from `membership` where `membership`.UserID = %s and `membership`.GroupID = %s;"
	try:
		cursor.execute(deleteMembershipStatement, (user_id, group_id))
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0


# for GET LOGIN
def getLogin(email, password):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectUser = "select `user`.userID as user_id from `user` where `user`.EmailAddress = %s and `user`.Password = %s;"
	try:
		cursor.execute(selectUser, (email, password))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0

	
	connection.close()
	return result



# for GET GROUP
def getGroup(group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getGroupStatement = "select `record`.GroupName as name, `record`.GroupDescription as description, `record`.LeaderID as leader_id, `record`.GroupColor as color from `record` where `record`.GroupID = %s;"
	try:
		cursor.execute(getGroupStatement, (group_id))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		return 0

	
	connection.close()
	return result


# for POST GROUP
def postGroup(leader_id, name, description, color):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postGroupInsert = "insert into `record` values(default, %s, %s, %s, %s);"
	postGroupSelect = "select `record`.GroupID from `record` where `record`.GroupName = %s;"
	try:
		cursor.execute(postGroupInsert, (name, leader_id, color, description))
		connection.commit()
	except:
		connection.rollback()
		return 0
	try:
		cursor.execute(postGroupSelect, (name))
		result = cursor.fetchone()
		connection.commit()
	except:
		connection.rollback()
		return 0

	connection.close()
	return result.values()


# for DELETE GROUP
def deleteGroup(group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteGroupStatement = "delete from `record` where `record`.GroupID = %s;"
	try:
		cursor.execute(deleteGroupStatement, (group_id))
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0


