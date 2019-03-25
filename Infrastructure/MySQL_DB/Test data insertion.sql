INSERT INTO `user` VALUES(default, "Stephen", "Styffe", "stephen.e.styffe@biola.edu","b@dp@$$w0rd","2019-02-22", null);
INSERT INTO `user` VALUES(default, "Zach","Chester", "zach.chester@biola.edu", "h0rr1bl3p@$$w0rd","2019-02-18", null); 

INSERT INTO `record` VALUES(default, "Zach's Group", (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), "Red","Optional description for Zach's Group"); 
INSERT INTO `record` VALUES(default, "Stephen's Group", (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), "Blue", "Optional description for Stephen's Group"); 

INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group")); 
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group")); 
INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName ="Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Stephen's Group")); 

INSERT INTO `user` VALUES(default, "Hannah", "Kim", "hannah.kim002@biola.edu", "r1d1cul0u$p@$$w0rd", "2019-02-23", null);
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Stephen's Group")); 


INSERT INTO `record` VALUES(default, "Hannah's Group", (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), "Orange" , "Optional description for Hannah's group");
INSERT INTO `membership` VALUES(default, 1, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group"));
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Stephen" AND LastName = "Styffe"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group"));
INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Zach" AND LastName = "Chester"), (SELECT GroupID FROM `record` WHERE GroupName = "Hannah's Group"));  

INSERT INTO `membership` VALUES(default, 0, (SELECT UserID FROM `user` WHERE FirstName = "Hannah" AND LastName = "Kim"), (SELECT GroupID FROM `record` WHERE GroupName = "Zach's Group"));  

INSERT INTO `announcement` VALUES(default,
(SELECT `user`.UserID FROM `user`, record WHERE `user`.userID = record.LeaderID AND `user`.FirstName = "Zach" AND `user`.LastName = "Chester"), 
"2019-02-24 13:15:00","Announcement content for Zach's Group", (SELECT record.GroupID FROM record, membership
 WHERE record.GroupID = membership.GroupID AND record.LeaderID = membership.UserID AND record.GroupName = "Zach's Group")); 

INSERT INTO `announcement` VALUES(default,
(SELECT `user`.UserID FROM `user`, record WHERE `user`.userID = record.LeaderID AND `user`.FirstName = "Hannah" AND `user`.LastName = "Kim"), 
"2019-02-24 13:15:00","Announcement content for Hannah's Group", (SELECT record.GroupID FROM record, membership
 WHERE record.GroupID = membership.GroupID AND record.LeaderID = membership.UserID AND record.GroupName = "Hannah's Group")); 
 
INSERT INTO `comment` VALUES(default, (SELECT membership.memberID FROM membership, `user`, record WHERE membership.UserID = `user`.UserID AND membership.GroupID = record.GroupID AND record.GroupName = "Zach's Group"
AND `user`.FirstName = "Hannah" AND `user`.LastName = "Kim"), "2019-02-26 18:30:00", "Comment text here", 
(SELECT announcement.AnnouncementID FROM announcement, record WHERE announcement.GroupID = record.GroupID AND record.GroupName = "Zach's Group" AND announcement.DateAndTime = "2019-02-24 13:15:00"));

INSERT INTO `event` VALUES(default, "2019-03-01 18:00:00", "Stephen's Event", 
(SELECT DISTINCT `user`.UserID FROM `user`, record, membership WHERE `user`.UserID = record.LeaderID 
AND membership.GroupID = record.GroupID AND `user`.FirstName = "Stephen" AND `user`.LastName = "Styffe")
, "Lim Center 182", "Optional description of Stephen's Event",
(SELECT record.GroupID FROM record, `user` WHERE record.LeaderID = `user`.UserID AND record.GroupName = "Stephen's Group")); 

INSERT INTO `user` VALUES(default, "Wesley", "Shiozaki", "wesley.shiozaki@biola.edu", "a_password", "2019-02-27", null);

INSERT INTO `request` VALUES(default, (SELECT record.GroupID FROM record WHERE record.GroupName = "Stephen's Group"),
(SELECT `user`.UserID FROM `user` WHERE `user`.FirstName = "Wesley" AND `user`.LastName = "Shiozaki"));

INSERT INTO `poll repository` VALUES(default,
(SELECT DISTINCT `user`.UserID FROM `user`, record WHERE `user`.UserID = record.LeaderID 
AND `user`.FirstName = "Hannah" AND `user`.LastName = "Kim"), "What is your favorite Biola eatery?", 5, "Optional poll description",
(SELECT DISTINCT record.GroupID FROM record WHERE record.GroupName = "Hannah's Group"), "2019-03-21 10:55:00"); 

INSERT INTO `poll response` VALUES(default, 
(SELECT DISTINCT membership.memberID FROM membership, `user`, record WHERE membership.UserID = `user`.UserID AND membership.GroupID = record.GroupID AND record.GroupName = "Hannah's Group"
AND `user`.FirstName = "Stephen" AND `user`.LastName = "Styffe"), 2,"2019-03-21 12:58:45",
(SELECT DISTINCT membership.GroupID FROM membership, record WHERE membership.GroupID = record.GroupID AND record.GroupName = "Hannah's Group"),
(SELECT DISTINCT `poll repository`.PollID FROM `poll repository`, `record` WHERE `poll repository`.GroupID = `record`.GroupID AND `record`.GroupName = "Hannah's Group" AND `poll repository`.DateAndTime = "2019-03-21 10:55:00")); 
