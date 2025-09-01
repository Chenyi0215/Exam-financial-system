
CREATE TABLE `Users` (
                        `UserID` VARCHAR(20) NOT NULL PRIMARY KEY,
                        `UserName` VARCHAR(50) NOT NULL,
                        `Email` VARCHAR(100) NOT NULL UNIQUE,
                        `Account` VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Product` (
                           `No` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `ProductName` VARCHAR(100) NOT NULL,
                           `Price` DECIMAL(18,4) NOT NULL,
                           `FeeRate` DECIMAL(5,4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Like List` (
                             `SN` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `UserID` VARCHAR(20) NOT NULL,
                             `ProductNo` INT NOT NULL,
                             `Order Name` INT NOT NULL COMMENT '購買數量',
                             `Account` VARCHAR(20) NOT NULL,
                             `Total Fee` DECIMAL(18,4) NOT NULL,
                             `Total Amount` DECIMAL(18,4) NOT NULL,
                             FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`),
                             FOREIGN KEY (`ProductNo`) REFERENCES `Product` (`No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;