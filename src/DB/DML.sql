-- Initial Data
INSERT INTO `Users` VALUES ('A123456789', '王明', 'wang@email.com', '111999666');
INSERT INTO `Product` (`ProductName`, `Price`, `FeeRate`) VALUES ('高科技基金', 5000.00, 0.0150);

-- Stored Procedure
DELIMITER $$
CREATE PROCEDURE `sp_add_like_list_item`(
    IN p_UserID VARCHAR(20),
    IN p_ProductNo INT,
    IN p_OrderName INT,
    IN p_Account VARCHAR(20),
    IN p_TotalFee DECIMAL(18, 4),
    IN p_TotalAmount DECIMAL(18, 4)
)
BEGIN
INSERT INTO `Like List` (`UserID`, `ProductNo`, `Order Name`, `Account`, `Total Fee`, `Total Amount`)
VALUES (p_UserID, p_ProductNo, p_OrderName, p_Account, p_TotalFee, p_TotalAmount);
SELECT LAST_INSERT_ID() as NewSN;
END$$
DELIMITER ;