alter table todo drop constraint FK366846F1BE0521;
alter table todo drop constraint FK36684678D024ED;
alter table tuser_role drop constraint FKC5833676F34F6047;
alter table tuser_role drop constraint FKC583367696A15441;
alter table tuser_todo_list drop constraint FKBB3F46F727685FE2;
alter table tuser_todo_list drop constraint FKBB3F46F7FF38A150;

drop table property if exists;
drop table role if exists;
drop table todo if exists;
drop table todo_list if exists;
drop table tuser if exists;
drop table tuser_role if exists;
drop table tuser_todo_list if exists;

create table property (pkey varchar(255) not null, value varchar(255), primary key (pkey));
create table role (role varchar(255) not null, primary key (role));
create table todo (id varchar(255) not null, completed bit not null, completionDate timestamp, creationDate timestamp, description varchar(255), dueDate timestamp, hasNotes bit not null, notes varchar(10000), priority integer not null, assignedUser_login varchar(255), todoList_id varchar(255), primary key (id));
create table todo_list (id varchar(255) not null, lastUpdate timestamp, name varchar(255), rssAllowed bit not null, primary key (id));
create table tuser (login varchar(255) not null, creationDate timestamp, dateFormat varchar(255), email varchar(255), enabled bit not null, firstName varchar(255), lastAccessDate timestamp, lastName varchar(255), password varchar(255), primary key (login));
create table tuser_role (tuser_login varchar(255) not null, roles_role varchar(255) not null, primary key (tuser_login, roles_role));
create table tuser_todo_list (users_login varchar(255) not null, todoLists_id varchar(255) not null, primary key (users_login, todoLists_id));
alter table todo add constraint FK366846F1BE0521 foreign key (assignedUser_login) references tuser;
alter table todo add constraint FK36684678D024ED foreign key (todoList_id) references todo_list;
alter table tuser_role add constraint FKC5833676F34F6047 foreign key (tuser_login) references tuser;
alter table tuser_role add constraint FKC583367696A15441 foreign key (roles_role) references role;
alter table tuser_todo_list add constraint FKBB3F46F727685FE2 foreign key (todoLists_id) references todo_list;
alter table tuser_todo_list add constraint FKBB3F46F7FF38A150 foreign key (users_login) references tuser;