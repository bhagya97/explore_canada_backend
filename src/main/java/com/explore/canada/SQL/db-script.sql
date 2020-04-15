use explore_canada;

DROP TABLE USER_INFO;
CREATE TABLE USER_INFO
(
    USER_ID VARCHAR(25) NOT NULL,
    USER_EMAIL VARCHAR(50) NOT NULL,
    USER_FIRST_NAME VARCHAR(150),
    USER_LAST_NAME VARCHAR(150),
    USER_PASSWORD VARCHAR(250),
    USER_DATE_OF_BIRTH VARCHAR(15),
    USER_IS_ACTIVE VARCHAR(1),
    constraint PK_USER_INFO PRIMARY KEY (USER_EMAIL)
);

DROP TABLE SEARCH_INFO;
CREATE TABLE  SEARCH_INFO
(
    SEARCH_ID int auto_increment primary key,
    PLACE     varchar(150)  null,
    LOCATION  varchar(150)  null,
    NAME      varchar(150)  null,
    CATEGORY  varchar(50)   null,
    URL       varchar(1500) null
);

DROP TABLE bus_details;
CREATE TABLE `bus_details` (
  `bus_id` varchar(45) NOT NULL,
  `company` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `depart_time` varchar(45) DEFAULT NULL,
  `arrival_time` varchar(45) DEFAULT NULL,
  `departure_date` varchar(45) DEFAULT NULL,
  `arrival_date` varchar(45) DEFAULT NULL,
  `adult_fare` float DEFAULT NULL,
  `child_fare` float DEFAULT NULL,
  `available_seats` int(2) DEFAULT NULL,
  PRIMARY KEY (`bus_id`)
);

DROP TABLE card_details;
CREATE TABLE `card_details` (
  `card_number` bigint(20) NOT NULL,
  `cvv` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `expiry_month` int(11) DEFAULT NULL,
  `expiry_year` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  PRIMARY KEY (`cvv`,`card_number`)
);

DROP TABLE transaction;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `bus_id` varchar(45) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `adults` varchar(45) DEFAULT '0',
  `children` varchar(45) DEFAULT '0',
  `payment` float DEFAULT NULL,
  `date` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
);

DELIMITER $$

DROP PROCEDURE IF EXISTS spRegisterUser $$

CREATE PROCEDURE spRegisterUser (
    IN userId VARCHAR(20),
    IN userFirstName VARCHAR(100),
    IN userLastName VARCHAR(100),
    IN userEmail VARCHAR(320),
    IN userPassword VARCHAR(76),
    IN userDateOfBirth VARCHAR(15),
    IN userIsActive VARCHAR(1),
    OUT id VARCHAR(20)
)
BEGIN
    INSERT INTO USER_INFO(user_id, user_email, user_first_name, user_last_name, user_password, user_date_of_birth, user_is_active)
    VALUES (userId, userEmail,userFirstName,userLastName,userPassword,userDateOfBirth,userIsActive);

    SELECT USER_ID
    INTO @id
    FROM USER_INFO
    WHERE USER_ID = userId;
END $$

DELIMITER ;



DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadUserByEmailID $$

CREATE PROCEDURE spLoadUserByEmailID (
    IN emailID VARCHAR(320)
)
BEGIN
    SELECT USER_ID, USER_EMAIL, USER_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_DATE_OF_BIRTH
    FROM USER_INFO
    WHERE USER_INFO.USER_EMAIL = emailID and USER_INFO.USER_IS_ACTIVE = 'Y';
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadUserByID $$

CREATE PROCEDURE spLoadUserByID (
    IN userID VARCHAR(25)
)
BEGIN
    SELECT USER_ID, USER_EMAIL, USER_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_DATE_OF_BIRTH
    FROM USER_INFO
    WHERE USER_INFO.USER_ID = userID and USER_INFO.USER_IS_ACTIVE = 'Y';
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllUsers $$

CREATE PROCEDURE spLoadAllUsers ()
BEGIN
    SELECT USER_ID, USER_EMAIL, USER_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_DATE_OF_BIRTH
    FROM USER_INFO
    WHERE USER_INFO.USER_IS_ACTIVE = 'Y';
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteUserByEmailID $$

CREATE PROCEDURE spDeleteUserByEmailID (
    IN emailID VARCHAR(320)
)
BEGIN
    UPDATE USER_INFO SET USER_INFO.USER_IS_ACTIVE = 'N'
    WHERE USER_INFO.USER_EMAIL = emailID and USER_INFO.USER_IS_ACTIVE = 'Y';
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spUpdateUserByEmailID $$

CREATE PROCEDURE spUpdateUserByEmailID (
    IN userEmail VARCHAR(320),
    IN userFirstName VARCHAR(100),
    IN userLastName VARCHAR(100),
    IN userDateOfBirth VARCHAR(15)
)
BEGIN
    UPDATE USER_INFO SET USER_INFO.USER_FIRST_NAME = userFirstName,
                         USER_INFO.USER_LAST_NAME = userLastName,
                         USER_INFO.USER_DATE_OF_BIRTH = userDateOfBirth
    WHERE USER_INFO.USER_EMAIL = userEmail and USER_INFO.USER_IS_ACTIVE = 'Y';
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spUpdateCardBalance $$

create procedure spUpdateCardBalance(
IN cardNumber BIGINT(20),
IN cardBalance FLOAT
)
BEGIN
    update card_details set balance=cardBalance where card_number=cardNumber;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spInsertIntoTransaction $$

create procedure spInsertIntoTransaction(
    IN busId VARCHAR(45),
    IN customerName VARCHAR(45),
    IN numOfAdults VARCHAR(45),
    IN numOfChildren VARCHAR(45),
    IN paymentAmount NUMERIC,
    IN transactionDate VARCHAR(25)
)
BEGIN
    insert into transaction(bus_id,customer_name,adults,children,payment,date)
    values(busId,
           customerName,
           numOfAdults,
           numOfChildren,
           paymentAmount,
           transactionDate);
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS sploadBusDetailsBySourceAndDestinations $$

create procedure sploadBusDetailsBySourceAndDestinations(
    IN busSource VARCHAR(45),
    IN busDestination VARCHAR(45)
)
BEGIN
    select
            bus_id,
            company,
            source,
            destination,
            depart_time,
            arrival_time,
            departure_date,
            arrival_date,
            adult_fare,
            child_fare,
            available_seats
    from bus_details where source=busSource and destination=busDestination;
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spUpdateSeatDetails $$

create procedure spUpdateSeatDetails(
    IN busID VARCHAR(45),
    IN noOfReservedSeats INTEGER
)
BEGIN
    UPDATE bus_details
    SET available_seats = (available_seats - noOfReservedSeats)
    where available_seats >= noOfReservedSeats and bus_id = busID;
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadBusDetailsById $$

create procedure spLoadBusDetailsById(
    IN busID VARCHAR(45)
)
BEGIN
    select
        bus_id,
        company,
        source,
        destination,
        depart_time,
        arrival_time,
        departure_date,
        arrival_date,
        adult_fare,
        child_fare,
        available_seats
    from bus_details where bus_id=busID;
END $$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadCardDetailsById $$

create procedure spLoadCardDetailsById(
    IN cardNumber VARCHAR(20),
    IN cardHolderName VARCHAR(45)
)
BEGIN
    select
           card_number,
           cvv,
           name,
           expiry_month,
           expiry_year,
           balance
    from card_details
    where card_number = cardNumber
    and name = cardHolderName;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllPlaces $$

create procedure spLoadAllPlaces()
BEGIN
    SELECT SEARCH_ID,
		   PLACE,
           LOCATION,
           NAME,
           CATEGORY,
           URL
    FROM SEARCH_INFO;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadPlacesByKeyword $$

create procedure spLoadPlacesByKeyword(IN keyword VARCHAR(500))
BEGIN
    SELECT SEARCH_ID,
		   PLACE,
           LOCATION,
           NAME,
           CATEGORY,
           URL
    FROM SEARCH_INFO
    WHERE   upper(PLACE) LIKE CONCAT('%', upper(keyword) , '%') OR
            upper(LOCATION) LIKE CONCAT('%', upper(keyword) , '%') OR
            upper(NAME) LIKE CONCAT('%', upper(keyword) , '%') OR
            upper(CATEGORY) LIKE CONCAT('%', upper(keyword) , '%');
END $$

DELIMITER ;


insert into SEARCH_INFO(PLACE,LOCATION,NAME,CATEGORY,URL)
values
('Niagara Falls','Toronto','Niagara Falls','Water Fall','https://www.planetware.com/photos-large/CDN/canada-ontario-niagara-falls-2.jpg'),
('Banff National Park & the Rocky Mountains','Alberta','Banff National Park & the Rocky Mountains','Park','https://www.planetware.com/photos-large/CDN/canada-banff-national-park-moraine-lake-2.jpg'),
('Toronto''s CN Tower','Toronto','Toronto''s CN Tower','City','https://www.planetware.com/photos-large/CDN/canada-ontario-toronto-cn-tower-2.jpg'),
('Old Quebec','Quebec','Old Quebec','City','https://www.planetware.com/photos-large/CDN/canada-view-over-old-quebec.jpg'),
('Whistler','Vancouver','Whistler','Mountain','https://www.planetware.com/photos-large/CDN/canada-british-columbia-whistler-ski-hill.jpg'),
('Ottawa''s Parliament Hill','Ottawa','Ottawa''s Parliament Hill','Mountai & Hills','https://www.planetware.com/photos-large/CDN/canada-ontario-ottawa-parliament-hill.jpg'),
('St. John''s Signal Hill National Historic Site','Newfoundland','St. John''s Signal Hill National Historic Site','City','https://www.planetware.com/photos-large/CDN/canada-newfoundland-st-johns-signal-hill-view.jpg'),
('Vancouver Island','Victoria','Vancouver Island','Island','https://www.planetware.com/wpimages/2019/03/canada-british-colombia-attractions-tofino-beach-and-people.jpg'),
('Bay of Fundy','Nova Scotia','Bay of Fundy','Island','https://www.planetware.com/photos-large/CDN/canada-bay-of-fundy-1.jpg'),
('Victoria''s Inner Harbour','Victoria','Victoria''s Inner Harbour','Harbour','https://www.planetware.com/photos-large/CDN/canada-british-columbia-victoria-inner-harbour.jpg'),
('Gros Morne National Park','Newfoundland''s','Gros Morne National Park','Park','https://www.planetware.com/photos-large/CDN/canada-newfoundland-gros-morne-national-park.jpg'),
('Vancouver''s Stanley Park','Vancouver','Vancouver''s Stanley Park','Park','https://www.planetware.com/photos-large/CDN/canada-british-columbia-vancouver-stanley-park.jpg'),
('Canadian Museum for Human Rights','Winnipeg','Canadian Museum for Human Rights','Museum','https://www.planetware.com/photos-large/CDN/canada-manitoba-winnipeg-museum-for-human-rights.jpg'),
('Tofino: A Water Lover’s Paradise','Vancouver Island','Tofino: A Water Lover’s Paradise','Beach','https://assets.traveltriangle.com/blog/wp-content/uploads/2017/08/Tofino.jpg'),
('Butchart Gardens','Brentwood Bay','Butchart Gardens','Garden','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Butchart-Gardens.jpg'),
('Okanagan Valley','British Columbia','Okanagan Valley','Valley','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Okanagan-Valley1.jpg'),
('Gros Morne National Park','Newfoundland','Gros Morne National Park','Park','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Gros-Morne-National-Park.jpg'),
('Algonquin Provincial Park','Ontario','Algonquin Provincial Park','Park','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Algonquin-Park.jpg'),
('Lake Louise','Alberta','Lake Louise','Lake','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Lake-Louise.jpg'),
('The Yukon: A Magical Delight','Yukon','The Yukon: A Magical Delight','Mountains','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Yukon.jpg'),
('Pyramid Lake','Alberta','Pyramid Lake','Lake','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2017/08/Pyramid-Lake.jpg'),
('Cape Breton','Nova Scotia','Cape Breton','Woods','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2019/11/Cape-Bretron.jpg'),
('Forillon National Park','Quebec','Forillon National Park','Park','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2020/01/Forillon-National-Park.jpg'),
('Moraine Lake','Calgary','Moraine Lake','Lake','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2020/01/Moraine-Lake.jpg'),
('Jasper National Park','Calgary','Jasper National Park','Park','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2020/01/Jasper-National-Park.jpg'),
('Peggy’s Cove','Nova Scotia','Peggy’s Cove','Beach','https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2020/01/Peggy%E2%80%99s-Cove.jpg'),
('The Grotto','Ontario','The Grotto','Park','http://themountainschool.com/wp-content/uploads/2015/11/Logan-7.jpg'),
('The Royal Canadian Mint','Manitoba','The Royal Canadian Mint','Monumant','https://notablelife.com/media/2014/10/Mint.jpg'),
('Algonquin Park','Ontario','Algonquin Park','Park','http://travelphotography.theplanetd.com/North-America/Ontario/i-BLTBb33/1/X3/Norther-Ontario-3-X3.jpg');


INSERT INTO bus_details VALUES
('B008234','Greyhound Canada','Nova Scotia','Vancouver','6:30','15:30','28/03/2020','28/03/2020',270,250.78,50),
('B148332','Coach Canada','Toronto','Newfoundland','08:00','16:00','27/03/2020','27/03/2020',70,50,60),
('B287445','Maritime Bus','Calgary','Peggy''s cove','10:00','11:00','28/03/2020','28/03/2020',15,10.75,50),
('B5865','Red Arrow','Nova Scotia','Yukon','10:50','23.55','29/03/2020','29/03/2020',105,80.85,20),
('B50265','Red Arrow','Alberta','Calgary','10:50','23.55','29/03/2020','29/03/2020',105,80.85,20),
('B58475','Red Arrow','Ontario','Calgary','10:50','23.55','29/03/2020','29/03/2020',105,80.85,20),
('B561965','Red Arrow','Toronto','Calgary','10:50','23.55','29/03/2020','29/03/2020',105,80.85,20),
('B2881263','Mega Bus','Toronto','Victoria','12:15','22:30','25/03/2020','27/03/2020',60.25,45,55);