import pymysql


# for GET USER
def getUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getUserStatement = "select `user`.FirstName as first_name, `user`.LastName as last_name, `user`.EmailAddress as email, `user`.Password as password from `user` where `user`.UserID = %s;"
	try:
		cursor.execute(getUserStatement, (user_id))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result



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
	except:
		connection.close()
		return 0

	connection.close()
	return result



# for DELETE USER
def deleteUser(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteUserStatement = "delete from `user` where `user`.userID = %s;"
	try:
		cursor.execute(deleteUserStatement, (user_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0


# for GET MEMBERSHIP
def getMembership(user_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	listGroupIDs = list()
	UserGroupInfo = list()
	
	getMembershipStatement = "select `membership`.GroupID as group_id from `membership` where `membership`.UserID = %s;"
	try:
		cursor.execute(getMembershipStatement, (user_id))
		result1 = cursor.fetchall()
	except:
		connection.close()
		return 0
	
	for x in result1:
		listGroupIDs.append(x.get('group_id'))
	
		
	returnGroupInfo = "select GroupName, LeaderID, GroupColor, GroupDescription from `record` where GroupID = %s;"
	for i in listGroupIDs:
		try:
			cursor.execute(returnGroupInfo, (i))
			UserGroupInfo.append(cursor.fetchone())
		except:
			return 0
	
	connection.close()
	return UserGroupInfo

# for POST MEMBERSHIP
def postMembership(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postMembershipStatement = "insert into `membership` values(default,0, %s, %s);"
	selectMembershipStatement = "select `membership`.memberID from `membership` where `membership`.userID = %s and `membership`.groupID = %s;"


	try:
		cursor.execute(postMembershipStatement,(user_id, group_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectMembershipStatement, (user_id, group_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
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
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
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
	except:
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
	except:
		connection.close()
		return 0


	connection.close()
	return result


# for POST GROUP
def postGroup(leader_id, name, description, color):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postGroupInsert = "insert into `record` values(default, %s, %s, %s, %s);"
	postGroupLeader = "insert into `membership` values(default, 1, %s, (select `record`.GroupID from `record` where `record`.GroupName = %s))"
	postGroupSelect = "select `record`.GroupID from `record` where `record`.GroupName = %s;"
	try:
		cursor.execute(postGroupInsert, (name, leader_id, color, description))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(postGroupLeader, (leader_id, name))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(postGroupSelect, (name))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result


# for DELETE GROUP
def deleteGroup(group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteGroupStatement = "delete from `record` where GroupID = %s;"
	try:
		cursor.execute(deleteGroupStatement, (group_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

# for GET ANNOUNCEMENT
def getAnnouncement(announcement_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectStatement = "select * from `announcement` where `announcement`.announcementID = %s "
	try:
		cursor.execute(selectStatement, (announcement_id))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result

def postAnnouncement(user_id, content, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `announcement` values(default, %s, curdate(), %s, %s);"
	selectStatement = "select `announcement`.announcementID from `announcement` where `announcement`.LeaderID = %s and `announcementID`.GroupID = %s and `announcement`.content = %s;"
	try:
		cursor.execute(postStatement, (user_id, content, group_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement, (leader_id, group_id, content))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result

def deleteAnnouncement(announcement_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteStatement = "delete from `announcement` where announcementID = %s;"

	try:
		cursor.execute(deleteStatement, (announcement_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

def getComment(comment_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectStatement = "select * from `comment` where `comment`.commentID = %s;"
	try:
		cursor.execute(selectStatement, (comment_id))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result

def postComment(member_id, announcement_id, content):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `comment` values(default, %s, curdate(), %s, %s);"
	selectStatement = "select `comment`.commentID from `comment` where `comment`.memberID = %s and `comment`.announcementID = %s and `comment`.content = %s"

	try:
		cursor.execute(postStatement, (member_id, content, announcement_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement, (member_id, announcement_id, content))
		result = cursor.fetchone()
	except:
		connection.close()
		return 0

	connection.close()
	return result

def deleteComment(comment_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteStatement = "delete from `comment` where `comment`.commentID = %s"

	try:
		cursor.execute(deleteStatement, (comment_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

def getMemberPrivilege(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getStatement = "select `membership`.UserPrivileges from `membership` where `membership`.userID = %s and `membership`.groupID = %s"
	try:
		cursor.execute(getStatement, (user_id, group_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return -1

def getEvent(event_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	getStatement = "select DateAndTime, eventName, LeaderID, Location, Description, GroupID from `event` where EventID = %s"
	try:
		cursor.execute(getStatement, (event_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def postEvent(event_name, event_DateAndTime, leader_id, location, description, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `event` values(default, %s, %s, %s, %s, %s, %s)"
	selectStatement = "select EventID from `event` where DateAndTime = %s and Location = %s and GroupID = %s"

	try:
		cursor.execute(postStatement,(event_DateAndTime, event_name, leader_id, location, description, group_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement,(event_DateAndTime, location, group_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def deleteEvent(event_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteStatement = "delete from `event` where EventID = %s"

	try:
		cursor.execute(deleteStatement, (event_id))
		result = cursor.rowcount
		connection.commit()
		connction.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

def getPoll(poll_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectStatement = "select LeaderID, question, ResponseOptions, PollDescription, GroupID, DateAndTime from `poll repository` where PollID = %s"

	try:
		cursor.execute(selectStatement, (poll_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0


def postPoll(leader_id, pollQuestion, pollResponseOptions, pollDescription, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `poll repository` values(default, %s, %s, %s, %s, %s, curdate());"
	selectStatement = "select PollID from `poll repository` where LeaderID = %s and GroupID = %s and question = %s"

	try:
		cursor.execute(postStatement,(leader_id, pollQuestion, pollResponseOptions, pollDescription, group_id))
		connction.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement,(leader_id, group_id, pollQuestion))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def deletePoll(poll_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteStatement = "delete from `poll repository` where PollID = %s"

	try:
		cursor.execute(deleteStatement,(poll_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

def getPollResponse(response_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectStatement = "select memberID, userResponse, DateAndTime, groupID, pollID from `poll response` where ResponseID = %s"

	try:
		cursor.execute(selectStatement, (response_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def postPollResponse(member_id, userResponse, group_id, poll_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `poll response` values(default, %s, %s, curdate(), %s, %s);"
	selectStatement = "select ResponseID from `poll repository` where PollID = %s and memberID = %s"

	try:
		cursor.execute(postStatement,(member_id, userResponse, group_id, poll_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement,(poll_id, member_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def deletePollResponse(response_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	deleteStatement = "delete from `poll response` where ResponseID = %s"

	try:
		cursor.execute(deleteStatement, (response_id))
		result = cursor.rowcount
		connection.commit()
		connection.close()
		return result
	except:
		connection.rollback()
		connection.close()
		return 0

def getRequest(leader_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	selectStatement = "select `request`.RequestID, `request`.GroupID, `request`.UserID, `record`.GroupName from `request`, `record` where `request`.GroupID = `record`.GroupID and `record`.LeaderID = %s;"

	try:
		cursor.execute(selectStatement, (leader_id))
		result = cursor.fetchall()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def postRequest(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()

	postStatement = "insert into `request` values(default, %s, %s);"
	selectStatement = "select RequestID from `request` where UserID = %s and GroupID = %s"

	try:
		cursor.execute(postStatement,(group_id, user_id))
		connection.commit()
	except:
		connection.rollback()
		connection.close()
		return 0
	try:
		cursor.execute(selectStatement,(user_id, group_id))
		result = cursor.fetchone()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def searchGroup(name):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	selectStatement = "select LeaderID, GroupColor, GroupDescription from `record` where GroupName = %s;"
	
	try:
		cursor.execute(selectStatement, (name))
		result = cursor.fetchall()
		connection.close()
		return result
	except:
		connection.close()
		return 0

def putUser(user_id, first_name, last_name, email, password):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateStatement = "update `user` set FirstName = %s, LastName = %s, email = %s, password = %s where UserID = %s;"
	try:
		cursor.execute(updateStatement, (first_name, last_name, email, password, user_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0

def putGroup(group_id, name, description, color):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateStatement = "update `record` set GroupName = %s, GroupDescription = %s, GroupColor = %s where GroupID = %s"
	
	try:
		cursor.execute(updateStatement,(name, description, color, group_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0

def deleteRequest(user_id, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	deleteStatement = "delete from `request` where UserID = %s and GroupID = %s;"
	
	try:
		cursor.execute(deleteStatement, (user_id, group_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0

def putAnnouncement(leader_id, content, group_id, announcement_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateStatement = "update `announcement` set LeaderID = %s, content = %s, GroupID = %s where AnnouncementID = %s;"
	
	try:
		cursor.execute(updateStatement,(leader_id, content, group_id, announcement_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0
		
def putEvent(event_id, DateAndTime, event_name, leader_id, location, description, group_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateStatement = "update `event` set DateAndTime = %s, eventName = %s, LeaderID = %s, location = %s, description = %s, GroupID = %s where EventID = %s"
	
	try:
		cursor.execute(updateStatement,(DateAndTime, event_name, leader_id, location, description, group_id, event_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0

def putComment(content, announcement_id, comment_id):
	connection = pymysql.connect(host='35.185.248.192', user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)
	cursor = connection.cursor()
	
	updateStatement = "update `comment` set content = %s, DateAndTime = curdate(), announcement_id = %s where CommentID = %s;"
	
	try:
		cursor.execute(updateStatement,(content, announcement_id, comment_id))
		connection.commit()
		connection.close()
		return 1
	except:
		connection.rollback()
		connection.close()
		return 0