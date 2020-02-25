-- �û���
CREATE TABLE users(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  email VARCHAR2(50) UNIQUE NOT NULL,
  username VARCHAR2(50),
  PASSWORD VARCHAR2(50),
  phoneNum VARCHAR2(20),
  STATUS INT
);

-- ��ɫ��
CREATE TABLE role(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  roleName VARCHAR2(50) ,
  roleDesc VARCHAR2(50)
);


-- �û���ɫ������
CREATE TABLE users_role(
  userId varchar2(32),
  roleId varchar2(32),
  PRIMARY KEY(userId,roleId),
  FOREIGN KEY (userId) REFERENCES users(id),
  FOREIGN KEY (roleId) REFERENCES role(id)
);

-- ��ԴȨ�ޱ�
CREATE TABLE permission(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  permissionName VARCHAR2(50) ,
  url VARCHAR2(50)
);

-- ��ɫȨ�޹�����
CREATE TABLE role_permission(
  permissionId varchar2(32),
  roleId varchar2(32),
  PRIMARY KEY(permissionId,roleId),
  FOREIGN KEY (permissionId) REFERENCES permission(id),
  FOREIGN KEY (roleId) REFERENCES role(id)
);


select * from users;
select * from role;
select * from users_role;

insert into users (email, username, password, phonenum, status) values( 'jtzhang@163.com', '123', '123', '1881999222', '1');

insert into role values('XXXXX','ADMIN','vip');
insert into role values('YYYY','USER','vip');
-- 46FA27455F1540FB8EDB79C9D5693E72 wwww
insert into  users_role values('46FA27455F1540FB8EDB79C9D5693E72', 'wwww');










