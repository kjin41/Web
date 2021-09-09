-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hw04db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hw04db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hw04db` DEFAULT CHARACTER SET utf8 ;
USE `hw04db` ;

-- -----------------------------------------------------
-- Table `hw04db`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hw04db`.`products` ;

CREATE TABLE IF NOT EXISTS `hw04db`.`products` (
  `product_code` VARCHAR(20) NOT NULL,
  `product_name` VARCHAR(20) NOT NULL,
  `product_price` INT NULL DEFAULT 0,
  `product_cnt` INT NULL DEFAULT 1,
  PRIMARY KEY (`product_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hw04db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hw04db`.`users` ;

CREATE TABLE IF NOT EXISTS `hw04db`.`users` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_name` VARCHAR(40) NOT NULL,
  `address` VARCHAR(100) NULL,
  `phone_num1` VARCHAR(20) NULL,
  `phone_num2` VARCHAR(20) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hw04db`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hw04db`.`orders` ;

CREATE TABLE IF NOT EXISTS `hw04db`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_price` INT NOT NULL,
  `payment` VARCHAR(1) NULL DEFAULT 'F',
  `delivery` VARCHAR(1) NULL DEFAULT 'F',
  `product_code` VARCHAR(20) NOT NULL,
  `product_cnt` INT NOT NULL DEFAULT 1,
  `user_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `orders_product_code_fk_idx` (`product_code` ASC) VISIBLE,
  INDEX `orders_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `orders_product_code_fk`
    FOREIGN KEY (`product_code`)
    REFERENCES `hw04db`.`products` (`product_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orders_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `hw04db`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
