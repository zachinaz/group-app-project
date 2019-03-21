import pymysql

connection = pymysql.connect(host='35.185.248.192',
                             user='Stephen', password='StephenSEProject', db='app_db', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)

cursor = connection.cursor()
sql = "INSERT INTO `user` VALUES(default, 'Zach','Chester', 'zach.chester@biola.edu', 'h0rr1bl3p@$$w0rd','2019-02-18', null)"
try:
    cursor.execute(sql)
    connection.commit()
    print("Insertion successful\n")
except:
    connection.rollback()
    print("Error: cannot execute SQL statement.\n")


sql = "DELETE FROM `user`"
try:
    cursor.execute(sql)
    connection.commit()
    print("Successful deletion\n")
except:
    connection.rollback()
    print("Error in execution\n")


connection.close()


