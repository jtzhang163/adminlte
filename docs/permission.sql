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
insert into role values('YYYY','USER','vip');

insert into permission (permissionName, url)values('user_findAll','/user/findAll');//ID:0B4DBE07DAAD4DC5BE7B2E7E6085E0E7
insert into permission (permissionName, url)values('user_findById','/user/findById');//ID:ED52376A16CD4609A80F93E7D7083401

-- 46FA27455F1540FB8EDB79C9D5693E72 wwww
insert into  users_role values('46FA27455F1540FB8EDB79C9D5693E72', 'wwww');
insert into  users_role values('46FA27455F1540FB8EDB79C9D5693E72', 'YYYY');

insert into  role_permission values('0B4DBE07DAAD4DC5BE7B2E7E6085E0E7', 'XXXXX');
insert into  role_permission values('0B4DBE07DAAD4DC5BE7B2E7E6085E0E7', 'wwww');
insert into  role_permission values('ED52376A16CD4609A80F93E7D7083401', 'YYYY');

select * from role where id in (select roleId from users_role where userId ='46FA27455F1540FB8EDB79C9D5693E72')


alter table users modify password varchar2(100);
update users set password = '$2a$10$7VJPykMi1NIZNypCnXd9TOpyb8KW0ak/Ud/ABCQmg5fStfimN65Ti' where username = '123';
