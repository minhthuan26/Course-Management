> [!IMPORTANT]
> THIS IS JUST A PROJECT FOR STUDYING PURPOSE

# Course Manangement
### Description
  - Project for manage students, teachers, courses, assignment and score
  - This project build by JavaFx with 3-layers model
### Technology
  - Language: Java, JavaFx
  - Database: MSSQL Server
### UI
  - Teacher Management
  ![](./assets/z5516079619261_3b06496c90ac0a71027a7d540b3de5ba.jpg)
  - Student Management
  ![](./assets/z5516079619260_394baed8dbb7bc4ebc1ae4fdac07286e.jpg)
  - Assigment Management
  ![](./assets/z5516079619258_1c7374fde51a109c0d6e342d75d3f98f.jpg)
  - Course Management
  ![](./assets/z5516079619075_3af5d8682011f748ec7721c4c97af64a.jpg)
  ![](./assets/z5516079619073_685fb1778b56dc405622ccdfd46e3674.jpg)
  - Score Management
  ![](./assets/z5516079599457_559ea3a858bcf56b80bd5f48a7ad0dbe.jpg)
### HOW TO RUN
  - Clone this project from github: ```git clone https://github.com/minhthuan26/Course-Management.git```
  - Open project with Intellij IDE
  - Add VM option: ```--module-path "<Path to javafx-sdk-19/lib>" --add-modules javafx.controls,javafx.fxml```
  - Install MSSQL Server from [MSSQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)
  - Create SQL Server Database name **CourseManagement** by run this file script ```SQLQuery1.sql```
  - Change connection info in **src/QuanLiKhoaHoc/DAL/ConnectDB.java** if need
  - Run **src/QuanLiKhoaHoc/GUI/Main/MainStartApp** class to start


