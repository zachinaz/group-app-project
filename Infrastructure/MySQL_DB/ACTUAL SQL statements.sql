/* For GET USER */
select `user`.FirstName, `user`.LastName, `user`.EmailAddress, `user`.Password 
from `user` where `user`.UserID = /* API provided value */;

/* For POST USER */
insert into `user` values(default, /* API provided values: fName, lName, email, password */);
select `user`.userID from `user` where `user`.FirstName = "API PROVIDED VALUE" and 
`user`.LastName = "API PROVIDED VALUE";

/* For DELETE USER */
delete from `user` where `user`.userID = /* API PROVIDED VALUE */;

/* For GET MEMBERSHIP */
select `membership`.GroupID from `membership` where `membership`.userID = `user`.UserID and 
`membership`.UserID = /* API PROVIDED VALUE */;

/* For POST MEMBERSHIP */
insert into `membership` values(default,0, /* API PROVIDED VALUES */);

/* For PUT MEMBERSHIP */
update `membership` set UserPrivileges = /* API PROVIDED VALUES */ where 
`membership`.userID = /* API PROVIDED VALUES */ and 
`membership`.GroupID = /* API PROVIDED VALUES */;

/* For DELETE MEMBERSHIP */
delete from `membership` where `membership`.UserID = /* API PROVIDED VALUES */ 
and `membership`.GroupID = /* API PROVIDED VALUES */;

/* For GET LOGIN */
select `user`.userID from `user` where `user`.EmailAddress = /* API PROVIDED VALUES */
and `user`.Password = /* API PROVIDED VALUES */; 

/* for GET GROUP */
select `record`.GroupName, `record`.GroupDescription, `record`.LeaderID from `record`
where `record`.GroupID = /* API PROVIDED VALUES */;

/* for POST GROUP */
insert into `record` values(default, /* API PROVIDED VALUES */, /* API PROVIDED VALUES */, /* API PROVIDED VALUES */, /* API PROVIDED VALUES */);
select `record`.GroupID from `record` where `record`.GroupName = /* API PROVIDED VALUES */;

/* for DELETE GROUP */
delete from `record` where `record`.GroupID = /* API PROVIDED VALUES */;