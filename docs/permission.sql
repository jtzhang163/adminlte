-- 用户表
CREATE TABLE users(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  email VARCHAR2(50) UNIQUE NOT NULL,
  username VARCHAR2(50),
  PASSWORD VARCHAR2(100),
  phoneNum VARCHAR2(20),
  STATUS INT
);

-- 角色表
CREATE TABLE role(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  roleName VARCHAR2(50) ,
  roleDesc VARCHAR2(50)
);


-- 用户角色关联表
CREATE TABLE users_role(
  userId varchar2(32),
  roleId varchar2(32),
  PRIMARY KEY(userId,roleId),
  FOREIGN KEY (userId) REFERENCES users(id),
  FOREIGN KEY (roleId) REFERENCES role(id)
);

-- 资源权限表
CREATE TABLE permission(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  permissionName VARCHAR2(50) ,
  url VARCHAR2(50)
);

-- 角色权限关联表
CREATE TABLE role_permission(
  permissionId varchar2(32),
  roleId varchar2(32),
  PRIMARY KEY(permissionId,roleId),
  FOREIGN KEY (permissionId) REFERENCES permission(id),
  FOREIGN KEY (roleId) REFERENCES role(id)
);


select * from users;
select * from role;
select * from permission;
select * from users_role;
select * from role_permission;

insert into users (email, username, password, phonenum, status) values( 'jtzhang@163.com', '123', '123', '1881999222', '1');

insert into role values('wwww','ADMIN','vip');
insert into role values('XXXXX','ADMIN','vip');
insert into role(rolename, roledesc) values('USER','common');

insert into permission (permissionName, url)values('user_findAll','/user/findAll');//ID:0B4DBE07DAAD4DC5BE7B2E7E6085E0E7
insert into permission (permissionName, url)values('user_findById','/user/findById');//ID:ED52376A16CD4609A80F93E7D7083401

-- 46FA27455F1540FB8EDB79C9D5693E72 wwww
insert into  users_role values('46FA27455F1540FB8EDB79C9D5693E72', 'wwww');
insert into  users_role values('46FA27455F1540FB8EDB79C9D5693E72', 'YYYY');
insert into users_role(userId, roleId) values('46FA27455F1540FB8EDB79C9D5693E72', 'YYYY');
insert into users_role(userId, roleId) values('46FA27455F1540FB8EDB79C9D5693E72', '4C32F4944A9D4B768AA731E4BB0C33AF')

insert into  role_permission(permissionId, roleid) values('E9BE5DD0F58A4AAE853E04FDE81B830E', 'BD56655152004843BF5B526225687720');
insert into  role_permission(permissionId, roleid) values('B088B970BEAA44229E44BE2D1B6D3E3F', 'AA8F529505D245AF861BDD0AA18D2C6D');
insert into  role_permission(permissionId, roleid) values('B2E32158F3394FBFA5B7B48E49A932D0', '700A19BB767048C6868CDAF7FE9A0D71');
insert into  role_permission(permissionId, roleid) values('21B0BE7A7FCA42768D7398302C7835FF', 'BD56655152004843BF5B526225687720');


select * from role where id in (select roleId from users_role where userId ='46FA27455F1540FB8EDB79C9D5693E72')


alter table users modify password varchar2(100);
update users set password = '$2a$10$7VJPykMi1NIZNypCnXd9TOpyb8KW0ak/Ud/ABCQmg5fStfimN65Ti' where username = '123';


CREATE TABLE sysLog(
  id VARCHAR2(32) default SYS_GUID() PRIMARY KEY,
  visitTime timestamp,
  username VARCHAR2(50),
  ip VARCHAR2(30),
  url VARCHAR2(50),
  executionTime int,
  method VARCHAR2(200)
);
