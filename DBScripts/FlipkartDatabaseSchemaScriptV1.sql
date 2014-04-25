DROP DATABASE FlipKartDatabase;
CREATE DATABASE FlipKartDatabase;


CREATE  TABLE `FlipKartDatabase`.`UserCredantials` (
  `userId` INT NOT NULL AUTO_INCREMENT ,
  `firstName` VARCHAR(45) NOT NULL ,
  `lastName` VARCHAR(45) NULL ,
  `gender` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `role` VARCHAR(45) NOT NULL ,
  `dateOfBirth` DATE NOT NULL ,
  `addressLine1` VARCHAR(100) NOT NULL ,
  `addressLine2` VARCHAR(100) NULL ,
  `city` VARCHAR(100) NOT NULL,
  `country` VARCHAR(100) NOT NULL ,
  `pinCode` INT NOT NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `phoneNumber` VARCHAR(15) NOT NULL ,
  `dateOfRegistration` DATE NOT NULL ,
  PRIMARY KEY (`userId`) );


CREATE  TABLE `FlipKartDatabase`.`Category` (
  `categoryId` VARCHAR(50) NOT NULL ,
  `categoryName` VARCHAR(100) NOT NULL ,
  `image` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`categoryId`) );


CREATE  TABLE `FlipKartDatabase`.`ProductInfo` (
  `productId` INT NOT NULL ,
  `productName` VARCHAR(100) NOT NULL ,
  `price` FLOAT NOT NULL ,
  `image` VARCHAR(500) NOT NULL ,
  `offer` INT NULL ,
  `categoryId` VARCHAR(50) NOT NULL ,
  `keywords` VARCHAR(150) NOT NULL ,
  `description` VARCHAR(200) NOT NULL ,
  `brand` VARCHAR(100) NOT NULL ,
  `warranty` INT NOT NULL ,
  `offerValidity` DATE NULL ,
  PRIMARY KEY (`productId`) , 
  INDEX `fk_Keywords_categoryId` (`categoryId` ASC) ,
  CONSTRAINT `fk_Keywords_caterogyId`
    FOREIGN KEY (`categoryId` )
    REFERENCES `FlipKartDatabase`.`Category` (`categoryId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION );



CREATE  TABLE `FlipKartDatabase`.`Keywords` (
  `productId` INT NOT NULL ,
  `keyword` VARCHAR(50) NOT NULL ,
  INDEX `fk_Keywords_pid` (`productId` ASC) ,
  CONSTRAINT `fk_Keywords_pid`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



CREATE  TABLE `FlipKartDatabase`.`WishList` (
  `userId` INT NOT NULL ,
  `productId` INT NOT NULL ,
  INDEX `fk_WishList_productId` (`userId` ASC) ,
  INDEX `fk_WishList_userId` (`productId` ASC) ,
  CONSTRAINT `fk_WishList_productId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WishList_userId`
    FOREIGN KEY (`userId` )
    REFERENCES `FlipKartDatabase`.`UserCredantials` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE  TABLE `FlipKartDatabase`.`BankDetails` (
  `creditCardNumber` INT NOT NULL ,
  `bankName` VARCHAR(100) NOT NULL ,
  `nameOfAccountHolder` VARCHAR(100) NOT NULL ,
  `bankLoginId` VARCHAR(100) NOT NULL ,
  `CVV` INT NOT NULL ,
  `transactionPassword` VARCHAR(100) NOT NULL ,
  `accountNumber` INT NOT NULL ,
  `expiryDate` DATE NOT NULL ,
  `issueDate` DATE NOT NULL ,
  `balance` FLOAT NOT NULL ,
  PRIMARY KEY (`creditCardNumber`) );



CREATE  TABLE `FlipKartDatabase`.`Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(100) NOT NULL ,
  `orderDate` DATE NOT NULL ,
  `deliveryDate` DATE NOT NULL ,
  PRIMARY KEY (`orderId`));



CREATE TABLE `FlipKartDatabase`.`Feedback` (
  `email` VARCHAR(50) NULL,
  `mobileNumber` VARCHAR(50) NULL,
  `category` VARCHAR(50) NULL,
  `message` VARCHAR(500) NULL,
  `feedbackDate` DATETIME NULL);



CREATE  TABLE `FlipKartDatabase`.`Cart` (
  `userId` INT NOT NULL ,
  `productId` INT NOT NULL ,
  `quantity` INT NOT NULL ,
  INDEX `fk_Cart_userId` (`userId` ASC) ,
  INDEX `fk_Cart_productId` (`productId` ASC) ,
  CONSTRAINT `fk_Cart_userId`
    FOREIGN KEY (`userId` )
    REFERENCES `FlipKartDatabase`.`UserCredantials` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cart_productId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE UNIQUE INDEX cartindex ON `FlipKartDatabase`.`Cart` (`userId`, `productId`);


CREATE TABLE FlipKartDatabase.Payment (
    transactionId INT NOT NULL AUTO_INCREMENT,
    orderId INT NOT NULL,
    bank VARCHAR(100) NOT NULL,
    paymentType VARCHAR(45) NOT NULL,
    amount FLOAT NOT NULL,
    paymentDate DATE NOT NULL,
    payeeAccountNumber INT NOT NULL,
    PRIMARY KEY (transactionId),
    INDEX fk_Payment_orderId (orderId ASC),
    CONSTRAINT fk_Payment_orderId FOREIGN KEY (orderId)
        REFERENCES FlipKartDatabase.Order (orderId)
        ON DELETE NO ACTION ON UPDATE NO ACTION 
 );



CREATE  TABLE `FlipKartDatabase`.`CategoryRelation` (
  `categoryId` VARCHAR(50) NOT NULL ,
  `subCategoryId` VARCHAR(50) NOT NULL ,
  INDEX `fk_CategoryRelation_categoryId` (`categoryId` ASC) ,
  INDEX `fk_CategoryRelation_subCategoryId` (`subCategoryId` ASC) ,
  CONSTRAINT `fk_CategoryRelation_categoryId`
    FOREIGN KEY (`categoryId` )
    REFERENCES `FlipKartDatabase`.`Category` (`categoryId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CategoryRelation_subCategoryId`
    FOREIGN KEY (`subCategoryId` )
    REFERENCES `FlipKartDatabase`.`Category` (`categoryId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE  TABLE `FlipKartDatabase`.`Advertizement` (
  `productId` INT NOT NULL ,
  `image` VARCHAR(45) NOT NULL ,
  `timeStamp` DATETIME NOT NULL ,
  `caption` VARCHAR(100) NOT NULL,
  `advertizementType` VARCHAR(20) NOT NULL,
  INDEX `fk_Advertizement_productId` (`productId` ASC) ,
  CONSTRAINT `fk_Advertizement_productId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
	ON DELETE NO ACTION
    ON UPDATE NO ACTION);



CREATE  TABLE `FlipKartDatabase`.`Seller` (
  `sellerId` INT NOT NULL AUTO_INCREMENT ,
  `userId` INT NOT NULL ,
  `description` VARCHAR(500) NOT NULL ,
  PRIMARY KEY (`sellerId`) ,
  INDEX `fk_Seller_userId` (`userId` ASC) ,
  CONSTRAINT `fk_Seller_userId`
    FOREIGN KEY (`userId` )
    REFERENCES `FlipKartDatabase`.`UserCredantials` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE  TABLE `FlipKartDatabase`.`OrderStock` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `productId` INT(11) NOT NULL ,
  `sellerId` INT(11) NOT NULL ,
  `orderQuantity` INT(11) NOT NULL ,
  `orderDate` DATE NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_OrderStock_pId` (`productId` ASC) ,
  INDEX `fk_OrderStock_sId` (`sellerId` ASC) ,
  CONSTRAINT `fk_OrderStock_pId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderStock_sId`
    FOREIGN KEY (`sellerId` )
    REFERENCES `FlipKartDatabase`.`Seller` (`sellerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
    

CREATE  TABLE `FlipKartDatabase`.`Stock` (
  `productId` INT NOT NULL ,
  `availableQuantity` INT NOT NULL CHECK( availableQuantity <= maximumQuantity 
    AND availableQuantity >= 0 ),
  `minimumQuantity` INT NOT NULL CHECK( minimumQuantity >= 0 ),
  `maximumQuantity` INT NOT NULL CHECK( maximumQuantity >= 0 ),
  `sellerId` INT NOT NULL ,
  `stockUpdateDate` DATE NOT NULL,
  INDEX `fk_Stock_productId` (`productId` ASC) ,
  INDEX `fk_Stock_sellerId` (`sellerId` ASC) ,
  CONSTRAINT `fk_Stock_productId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_sellerId`
    FOREIGN KEY (`sellerId` )
    REFERENCES `FlipKartDatabase`.`Seller` (`sellerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);





CREATE  TABLE `FlipKartDatabase`.`ReviewNRating` (
  `productSellerId` INT NOT NULL ,
  `productSellerIdType` VARCHAR(45) NOT NULL ,
  `userId` INT NOT NULL ,
  `review` VARCHAR(500) NULL ,
  `rating` VARCHAR(45) NULL ,
    PRIMARY KEY (`productSellerId` , `userId`),
  INDEX `fk_ReviewNRating_userId` (`userId` ASC) ,
  INDEX `fk_ReviewNRating_productSellerId1` (`productSellerId` ASC) ,
  INDEX `fk_ReviewNRating_productSellerId2` (`productSellerId` ASC) ,
  CONSTRAINT `fk_ReviewNRating_userId`
    FOREIGN KEY (`userId` )
    REFERENCES `FlipKartDatabase`.`UserCredantials` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReviewNRating_productSellerId1`
    FOREIGN KEY (`productSellerId` )
    REFERENCES `FlipKartDatabase`.`Seller` (`sellerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReviewNRating_productSellerId2`
    FOREIGN KEY (`productSellerId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    

/*  Create Script OrderDescription Table*/
CREATE  TABLE `FlipKartDatabase`.`OrderDescription` (
  `orderID` INT NOT NULL ,
  `productId` INT NOT NULL ,
  `quantity` INT NOT NULL ,
  `price` FLOAT NOT NULL ,
  PRIMARY KEY (`orderID`, `productId`) ,
  INDEX `fk_orderDescription_orderId` (`orderID` ASC) ,
  INDEX `fk_orderDescription_productId` (`productId` ASC) ,
  CONSTRAINT `fk_orderDescription_orderId`
    FOREIGN KEY (`orderID` )
    REFERENCES `FlipKartDatabase`.`Order` (`orderId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderDescription_productId`
    FOREIGN KEY (`productId` )
    REFERENCES `FlipKartDatabase`.`ProductInfo` (`productId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


/*  Create Script OrderShippingAddress Table*/
CREATE  TABLE `FlipKartDatabase`.`OrderShipingAddress` (
  `orderId` INT NOT NULL ,
  `customerName` VARCHAR(45) NOT NULL ,
  `customerEmail` VARCHAR(45) NOT NULL ,
  `addressLine1` VARCHAR(45) NOT NULL ,
  `addressLine2` VARCHAR(45) NULL ,
  `pincode` VARCHAR(6) NOT NULL ,
  `city` VARCHAR(45) NOT NULL ,
  `customerPhoneNumber` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`orderId`) ,
  INDEX `fk_OrderShipingAddress_orderId` (`orderId` ASC) ,
  CONSTRAINT `fk_OrderShipingAddress_orderId`
    FOREIGN KEY (`orderId` )
    REFERENCES `FlipKartDatabase`.`Order` (`orderId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


    