/* Select query to obtain the names of the group leaders for every group in the DB */
SELECT DISTINCT record.GroupName, `user`.FirstName as LeaderFirstName, `user`.LastName as LeaderLastName FROM record, `user`, membership
WHERE record.LeaderID = `user`.UserID; 

/* Select query to show the members of every group in the DB */
SELECT `user`.FirstName, `user`.LastName, record.GroupName, membership.UserPrivileges FROM `user`, record, membership
WHERE `user`.UserID = membership.UserID AND membership.GroupID = record.GroupID;
 