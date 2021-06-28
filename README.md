# DairyMilkManagement

inside your mysql workbench run these queries 

Create Database dairyMilk;
use dairyMilk;

CREATE TABLE `purchase` (
  `id` int DEFAULT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `StaffId` varchar(255) DEFAULT NULL,
  `seller_name` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `milktype` varchar(255) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `Rate` double DEFAULT NULL,
  `TotalAmount` double DEFAULT NULL
  );
  
  CREATE TABLE `sellmilk` (
  `id` int NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `staffid` varchar(255) DEFAULT NULL,
  `DealerName` varchar(255) DEFAULT NULL,
  `milktype` varchar(255) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `staff` (
  `staffid` varchar(255) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`staffid`)
);


CREATE TABLE `stock` (
  `id` int NOT NULL,
  `milktype` varchar(255) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `purchase_sales_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

insert into stock value(1, 'cow', 100);
insert into stock value(2, 'buffalo', 100);
insert into stock value(3, 'camel', 100);





Now got to the mysql connector link and download platform independent zip file  :
https://dev.mysql.com/downloads/connector/j/

![Screenshot 2021-05-05 140312](https://user-images.githubusercontent.com/72779637/117115350-dd2b1600-ad41-11eb-91ee-4e72e7d03c6d.png)

extract the zip file into any location . 

then open your project folder inside the netbeans (prefferable netbeans as it contains javafx and you need not to install it ) .
go to libraries

![image](https://user-images.githubusercontent.com/72779637/117115579-24190b80-ad42-11eb-950a-4ad29122b074.png)

right click and go to add JAR/folder

navigate to the extracted zip folder and inside it you wil find mysql connector add it . 

![image](https://user-images.githubusercontent.com/72779637/117115789-70644b80-ad42-11eb-8b44-2a47857f7c68.png)

Now build the project and run it !!!!!
