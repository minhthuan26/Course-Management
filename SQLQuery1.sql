create database QuanLiKhoaHoc
go

use QuanLiKhoaHoc
go

create table Person(
	PersonId int not null identity(1,1),
	FirstName nvarchar(255) not null,
	LastName nvarchar(255) not null,
	Email varchar(255) not null,
	PhoneNumber varchar(15),
	DateOfBirth Date not null,
	PersonImage varchar(max)
)

create table Roles(
	RoleId int not null identity(1,1),
	RoleName nvarchar(255) not null
)

create table PersonRole(
	RoleId int not null,
	PersonId int not null
)

create table Course(
	CourseId int not null identity(1,1),
	CourseName nvarchar(255) not null,
	CourseDescription nvarchar(max) not null,
	DateCreate Date not null default getdate(),
	DateStart Date not null,
	DateEnd Date not null,
	CourseImage varchar(max)
)

create table OnsiteCourse(
	OnsiteCourseId int not null identity(1,1),
	CourseId int not null,
	RoomId int not null,
	TimeStart time not null,
	TimeEnd time not null,
	DayOccur Date not null,
	LessonQuantity int not null
)

create table OnlineCourse(
	OnlineCourseId int not null identity(1,1),
	CourseId int not null,
	CourseUrl varchar(max)
)

create table Room(
	RoomId int not null identity(1,1),
	RoomName nvarchar(255) not null
)

create table CourseRegister(
	RegisterId int not null identity(1,1),
	CourseId int not null,
	PersonId int not null
)

create table Assignment(
	CourseId int not null,
	PersonId int not null
)

create table CourseResult(
	ResultId int not null identity(1,1),
	RegisterId int not null
)

create table ResultDetail(
	DetailId int not null identity(1,1),
	ResultId int not null,
	Score float default 0,
	Rating nvarchar(255) 
)

create table Account(
	AccountId int not null identity(1,1),
	AccountName varchar(255) not null,
	AccountPassword varchar(255) not null,
	PersonId int not null
)

/*Add Constraint*/

/*Primary key*/
alter table Person 
add constraint PK_PersonId primary key (PersonId)

alter table Roles
add constraint PK_RoleId primary key (RoleId)

alter table Account 
add constraint PK_AccountId primary key (AccountId)

alter table CourseRegister 
add constraint PK_RegisterId primary key (RegisterId)

alter table Course 
add constraint PK_CourseId primary key (CourseId)

alter table CourseResult
add constraint PK_ResultId primary key (ResultId)

alter table OnsiteCourse
add constraint PK_OnsiteCourseId primary key (OnsiteCourseId)

alter table OnlineCourse
add constraint PK_OnlineCourseId primary key (OnlineCourseId)

alter table ResultDetail
add constraint PK_DetailId primary key (DetailId)

alter table Room
add constraint PK_RoomId primary key (RoomId)

/*Foreign key*/
alter table PersonRole
add constraint FK_PersonRole_Person_PersonId 
foreign key (PersonId) 
references Person(PersonId)

alter table PersonRole
add constraint FK_PersonRole_Roles_RoleId 
foreign key (RoleId) 
references Roles(RoleId)

alter table OnsiteCourse
add constraint FK_OnsiteCourse_Course_CourseId 
foreign key (CourseId) 
references Course(CourseId)

alter table OnsiteCourse
add constraint FK_OnsiteCourse_Room_RoomId 
foreign key (RoomId) 
references Room(RoomId)

alter table OnlineCourse
add constraint FK_OnlineCourse_Course_CourseId 
foreign key (CourseId) 
references Course(CourseId)

alter table CourseRegister
add constraint FK_CourseRegister_Person_PersonId 
foreign key (PersonId) 
references Person(PersonId)

alter table CourseRegister
add constraint FK_CourseRegister_Course_CourseId 
foreign key (CourseId) 
references Course(CourseId)

alter table Assignment
add constraint FK_Assignment_Person_PersonId 
foreign key (PersonId) 
references Person(PersonId)

alter table Assignment
add constraint FK_Assignment_Course_CourseId 
foreign key (CourseId) 
references Course(CourseId)

alter table CourseResult
add constraint FK_CourseResult_CourseRegister_RegisterId 
foreign key (RegisterId) 
references CourseRegister(RegisterId)

alter table ResultDetail
add constraint FK_ResultDetail_CourseResult_ResultId 
foreign key (ResultId) 
references CourseResult(ResultId)

alter table Account
add constraint FK_Account_Person_PersonId 
foreign key (PersonId) 
references Person(PersonId)