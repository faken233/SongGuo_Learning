create table chapterlearningprogress
(
    studentID   int   not null,
    chapterID   int   not null,
    answerCount int   null,
    accuracy    float null,
    primary key (studentID, chapterID)
);

create index fk_chapterID
    on chapterlearningprogress (chapterID);

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

create table multiplechoicequestions
(
    questionID int              not null
        primary key,
    options    text             null,
    answer     varchar(10)      null,
    type       tinyint unsigned not null,
    chapterID  int              not null,
    content    text             not null
);

create index multiplechoicequestions_chapterID_index
    on multiplechoicequestions (chapterID);

create table shortanswerquestions
(
    questionID int              not null
        primary key,
    chapterID  int              not null,
    answer     text             not null,
    content    text             not null,
    type       tinyint unsigned not null
);

create index shortanswerquestions_chapterID_index
    on shortanswerquestions (chapterID);

create table studentanswers
(
    studentID      int              not null,
    questionID     int              not null,
    answerDateTime datetime         null,
    answer         text             null,
    correction     varchar(10)      null,
    type           tinyint unsigned not null,
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
    className         varchar(20)  null,
    encryptedPassword varchar(255) null,
    phoneNumber       varchar(20)  null
);

create table teachers
(
    teacherID         int auto_increment
        primary key,
    name              varchar(255) null,
    phoneNumber       varchar(20)  null,
    email             varchar(255) null,
    qq                varchar(20)  null,
    description       text         null,
    encryptedPassword varchar(255) null
);

create table truefalsequestions
(
    questionID int auto_increment
        primary key,
    chapterID  int              not null,
    type       tinyint unsigned not null,
    content    text             not null,
    answer     varchar(10)      not null
);

create index truefalsequestions_chapterID_index
    on truefalsequestions (chapterID);


