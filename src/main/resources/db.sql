create table chapterlearningprogress
(
    studentID     int   not null,
    courseID      int   not null,
    chapterID     int   not null,
    questionCount int   null,
    accuracy      float null,
    primary key (studentID, courseID, chapterID)
);

create index fk_chapterID
    on chapterlearningprogress (chapterID);

create index fk_courseID
    on chapterlearningprogress (courseID);

create index fk_studentID
    on chapterlearningprogress (studentID);

create table chapters
(
    chapterID   int auto_increment
        primary key,
    courseID    int          null,
    chapterName varchar(255) null,
    content     text         null
);

create index fk_courseID
    on chapters (courseID);

create table courses
(
    courseID      int auto_increment
        primary key,
    teacherID     int          null,
    courseName    varchar(255) null,
    description   text         null,
    startDateTime datetime     null,
    endDateTime   datetime     null,
    maxStudents   int          null
);

create index fk_teacherID
    on courses (teacherID);

create table discussionreplies
(
    replyID       int auto_increment
        primary key,
    discussionID  int      null,
    replierID     int      null,
    content       text     null,
    replyDateTime datetime null
);

create index fk_discussionID
    on discussionreplies (discussionID);

create index fk_replierID
    on discussionreplies (replierID);

create table discussions
(
    discussionID int auto_increment
        primary key,
    courseID     int null
);

create index fk_courseID
    on discussions (courseID);

create table enrolledcourses
(
    studentID      int      null,
    courseID       int      null,
    enrollDateTime datetime null
);

create index fk_courseID
    on enrolledcourses (courseID);

create index fk_studentID
    on enrolledcourses (studentID);

create table questions
(
    questionID int auto_increment
        primary key,
    chapterID  int         null,
    type       varchar(50) null,
    content    text        null,
    answer     text        null
);

create index fk_chapterID
    on questions (chapterID);

create table salts
(
    userID int          not null
        primary key,
    salt   varchar(255) null
);

create table studentanswers
(
    studentID      int        not null,
    questionID     int        not null,
    answerDateTime datetime   null,
    answer         text       null,
    correction      tinyint(1) null,
    primary key (studentID, questionID)
);

create index fk_questionID
    on studentanswers (questionID);

create index fk_studentID
    on studentanswers (studentID);

create table students
(
    studentID         int auto_increment
        primary key,
    name              varchar(255) null,
    grade             varchar(20)  null,
    class             varchar(20)  null,
    encryptedPassword varchar(255) null
);

create table teachers
(
    teacherID         int auto_increment
        primary key,
    name              varchar(255) null,
    phoneNumber       varchar(20)  null,
    email             varchar(255) null,
    qq                varchar(20)  null,
    introduction      text         null,
    encryptedPassword varchar(255) null
);

