CREATE DATABASE IF NOT EXISTS TEST_BEAT_DB;
USE TEST_BEAT_DB;

CREATE TABLE IF NOT EXISTS `EMPLOYEE`(
    `ID` BIGINT UNSIGNED  AUTO_INCREMENT,
    `NAME` VARCHAR,
    `LOCATION` VARCHAR(50)  ,
    `GENDER` VARCHAR(50)  ,
    `DATE_OF_JOINING` DATE  ,
    `DESIGNATION` VARCHAR(50)  ,
    `CATEGORY` VARCHAR(50)  ,
    `PROJECT_ID` BIGINT  ,
    `LEAD_ID` BIGINT  ,
    `ORGANIZATION_ID` BIGINT  ,
    `BAND` BIGINT  ,
    `DATE_OF_LEAVING` DATE NULL,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `SKILLS`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `SKILL` VARCHAR(50)  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `SKILL_AND_EXPERIENCE`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `EMP_ID` BIGINT  ,
    `SKILLS` VARCHAR(50)  ,
    `EXP_AT` DATE  ,
    `EXP` INT  ,
    `STATUS` BOOLEAN  ,
    `UPDATED_ON` DATE  ,
    `EDITOR_ID` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `PROJECT`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `NAME` VARCHAR(50)  ,
    `ORGANIZATION` VARCHAR(50)  ,
    `PROJECT_MANAGER` VARCHAR(50)  ,
    `START_DATE` DATE  ,
    `LOCATION` VARCHAR(50)  ,
    `END_DATE` DATE NULL,
    `DESCRIPTION` VARCHAR(200) NULL,
    `PROJECT_TYPE` VARCHAR(50)  ,
    `EDITOR_ID` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `EMPLOYEE_HISTORY`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `EMP_ID` BIGINT  ,
    `NAME` VARCHAR(50)  ,
    `LEAD_ID` BIGINT  ,
    `CLIENT_COUNTERPART_ID` BIGINT NULL,
    `ORGANIZATION_ID` VARCHAR(50)  ,
    `ORGANIZATION_DEPARTMENT` VARCHAR(50)  ,
    `ACCOLITE_DEPARTMENT` VARCHAR(50)  ,
    `FROM_DATE` DATE  ,
    `TO_DATE` DATE NULL,
    `EDITOR_ID` BIGINT  ,
    `PROJECT_ID` BIGINT  ,
    `DATE_OF_JOINING_PROJECT` BIGINT  ,
    `DATE_OF_LEAVING_PROJECT` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `EXP_SLABS`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `SLAB` VARCHAR(50)  ,
    PRIMARY KEY(`ID`)
);

insert into EXP_SLABS (SLAB) Select '0-2' Where not exists(select * from EXP_SLABS where SLAB='0-2');
insert into EXP_SLABS (SLAB) Select '2-4' Where not exists(select * from EXP_SLABS where SLAB='2-4');
insert into EXP_SLABS (SLAB) Select '4-6' Where not exists(select * from EXP_SLABS where SLAB='4-6');
insert into EXP_SLABS (SLAB) Select '6-8' Where not exists(select * from EXP_SLABS where SLAB='6-8');
insert into EXP_SLABS (SLAB) Select '8-10' Where not exists(select * from EXP_SLABS where SLAB='8-10');
insert into EXP_SLABS (SLAB) Select '10-14' Where not exists(select * from EXP_SLABS where SLAB='10-14');
insert into EXP_SLABS (SLAB) Select '14-18' Where not exists(select * from EXP_SLABS where SLAB='14-18');
insert into EXP_SLABS (SLAB) Select '18-22' Where not exists(select * from EXP_SLABS where SLAB='18-22');
insert into EXP_SLABS (SLAB) Select '22-26' Where not exists(select * from EXP_SLABS where SLAB='22-26');
insert into EXP_SLABS (SLAB) Select '26-30' Where not exists(select * from EXP_SLABS where SLAB='26-30');
insert into EXP_SLABS (SLAB) Select '30-35' Where not exists(select * from EXP_SLABS where SLAB='30-35');
insert into EXP_SLABS (SLAB) Select '35-40' Where not exists(select * from EXP_SLABS where SLAB='35-40');
insert into EXP_SLABS (SLAB) Select '40-50' Where not exists(select * from EXP_SLABS where SLAB='40-50');



CREATE TABLE IF NOT EXISTS `SLAB_CHARGES`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `SLAB_ID` BIGINT  ,
    `ORG_ID` BIGINT  ,
    `COST` BIGINT  ,
    `STATUS` boolean  ,
    `EDITOR_ID` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `ROLES`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `ACCESS_NAME` VARCHAR(50)  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `LOGIN`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `EMPID` BIGINT  ,
    `NAME` VARCHAR(50)  ,
    `EMAIL` VARCHAR(50)  ,
    `PASSWORD` VARCHAR(50)  ,
    `ACCESS_ID` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `ORGANIZATION`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `ORG_NAME` VARCHAR(50)  ,
    `LOCATION` VARCHAR(50)  ,
    `OWNER` BIGINT  ,
    `PARENT_ORG` BIGINT  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `ROLE_GROUPS`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `NAME` VARCHAR(50)  ,
    `ACCESS` VARCHAR(50) NULL,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `CLIENT_COUNTERPART`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `NAME` VARCHAR(50)  ,
    `DESIGNATION` VARCHAR(50)  ,
    `CLIENT_ID` BIGINT  ,
    `ORG_ID` VARCHAR(50)  ,
    PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `BAND`(
    `ID` BIGINT UNSIGNED   AUTO_INCREMENT,
    `BAND` VARCHAR(50)  ,
    PRIMARY KEY(`ID`)
); 
insert into BAND (BAND)
 Select 'B2' Where not exists(select * from BAND where BAND='B2');
 insert into BAND (BAND)
 Select 'B2' Where not exists(select * from BAND where BAND='B2');
 insert into BAND (BAND)
 Select 'B3' Where not exists(select * from BAND where BAND='B3');
 insert into BAND (BAND)
 Select 'B4H' Where not exists(select * from BAND where BAND='B4H');
 insert into BAND (BAND)
 Select 'B4L' Where not exists(select * from BAND where BAND='B4L');
 insert into BAND (BAND)
 Select 'B5H' Where not exists(select * from BAND where BAND='B5H');
 insert into BAND (BAND)
 Select 'B5L' Where not exists(select * from BAND where BAND='B5L');
 insert into BAND (BAND)
 Select 'B6' Where not exists(select * from BAND where BAND='B6');
  insert into BAND (BAND)
 Select 'B7' Where not exists(select * from BAND where BAND='B7');

