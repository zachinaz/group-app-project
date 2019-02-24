/* Sample data for creating two initial users, two initial groups, and three memberships (Zach to his own group and Stephen to his own group and Zach's group) */
 
INSERT INTO `user` VALUES(default, "Stephen", "Styffe", "stephen.e.styffe@biola.edu","b@dp@$$w0rd","2019-02-22", null);
INSERT INTO `user` VALUES(default, "Zach","Chester", "zach.chester@biola.edu", "h0rr1bl3p@$$w0rd","2019-02-18", null); 

INSERT INTO `record` VALUES(default, "Zach's Group", (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), "Optional description for Zach's Group"); 
INSERT INTO `record` VALUES(default, "Stephen's Group", (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), "Optional description for Stephen's Group"); 

INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group")); 
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group")); 
INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Stephen's Group"));  

/* Sample data for creating a new user and user requesting to join an existing group */

INSERT INTO `user` VALUES(default, "Hannah", "Kim", "hannah.kim002@biola.edu", "r1d1cul0u$p@$$w0rd", "2019-02-23", null);
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Stephen's Group")); 

/* More sample data */
INSERT INTO `record` VALUES(default, "Hannah's Group", (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), "Optional description for Hannah's group");
INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group"));
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName = "Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group"));
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group")); 

INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group"));