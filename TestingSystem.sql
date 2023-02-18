-- create database
DROP DATABASE IF EXISTS TestingSystem;
CREATE DATABASE TestingSystem;
USE TestingSystem;

-- create table 1: Department
DROP TABLE IF EXISTS Department;
CREATE TABLE Department(
	id 			TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    DepartmentName 			NVARCHAR(30) NOT NULL UNIQUE KEY,
    TotalMember				int unsigned,
    `Type`                  enum('Dev','Test','ScrumMaster','PM') NOT NULL
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	id						TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    UserName				VARCHAR(50) NOT NULL UNIQUE KEY, -- Cannot update this field
    FirstName				NVARCHAR(50) NOT NULL,
    `Password`                NVARCHAR(150) DEFAULT '$10$cdP5s.5mILinqut.2mXC1.KHykW7D3C6l8cpRptLxvM1CGGc2etrq',
    `Role`					enum('USER','ADMIN','MANAGER') DEFAULT 'USER',
    LastName				NVARCHAR(50) NOT NULL,	-- create field fullName in POJO
    Department_ID 			TINYINT UNSIGNED NOT NULL,	-- Set default waiting
    CreateDate				DATETIME DEFAULT NOW(), -- Cannot update this field
    FOREIGN KEY(Department_ID) REFERENCES Department(id)
   
);


/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data Department
INSERT INTO Department(DepartmentName) 
VALUES
						(N'Marketing'	),
						(N'Sale'		),
						(N'Bảo vệ'		),
						(N'Nhân sự'		),
						(N'Kỹ thuật'	),
						(N'Tài chính'	),
						(N'Phó giám đốc'),
						(N'Giám đốc'	),
						(N'Thư kí'		),
						(N'Bán hàng'	);
               
-- Add data Account
INSERT INTO `Account`(UserName			, FirstName,	LastName,		 Department_ID	, CreateDate)
VALUES 				('dangblack'		,'Dang'	,		'Nguyen Hai'	,   '5'			, '2020-03-05'),
					('quanganh'		,'Anh'	,		'Tong Quang'	,   '1'			, '2020-03-05'),
                    ('vanchien'		,'Chien',		'Nguyen Van'	,   '2'			,  '2020-03-07'),
                    ('cocoduongqua'	,'Do'	,		'Duong'			,   '3'			,  '2020-03-08'),
                    ('doccocaubai'		,'Thang',		'Nguyen Chien'  ,   '4'			,  '2020-03-10'),
                    ('khabanh'			,'Kha'	,		'Ngo Ba'		,   '6'			,   NOW()),
                    ('huanhoahong'		,'Huan'	,		'Bui Xuan'		,   '7'			,  NOW()),
                    ('tungnui'			,'Tung'	,		'Nguyen Thanh'	,   '8'			,  '2020-04-07'),
                    ('duongghuu'		,'Huu'	,		'Duong Van'		,   '9'			, '2020-04-07'),
                    ('vtiaccademy'		,'Ai'	,		'Vi Ti'			,   '10'		, '2020-04-09');

UPDATE `testingsystem`.`department` SET `TotalMember` = '2' WHERE (`id` = '1');
UPDATE `testingsystem`.`department` SET `TotalMember` = '3' WHERE (`id` = '2');
UPDATE `testingsystem`.`department` SET `TotalMember` = '2' WHERE (`id` = '3');
UPDATE `testingsystem`.`department` SET `TotalMember` = '3' WHERE (`id` = '4');
UPDATE `testingsystem`.`department` SET `TotalMember` = '4' WHERE (`id` = '5');
UPDATE `testingsystem`.`department` SET `TotalMember` = '3' WHERE (`id` = '6');
UPDATE `testingsystem`.`department` SET `TotalMember` = '2' WHERE (`id` = '7');
UPDATE `testingsystem`.`department` SET `TotalMember` = '4' WHERE (`id` = '9');
UPDATE `testingsystem`.`department` SET `TotalMember` = '3' WHERE (`id` = '8');
UPDATE `testingsystem`.`department` SET `TotalMember` = '4' WHERE (`id` = '10');


