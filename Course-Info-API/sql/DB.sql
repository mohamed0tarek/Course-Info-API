-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema courseapi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema courseapi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `courseapi` DEFAULT CHARACTER SET latin1 ;
USE `courseapi` ;

-- -----------------------------------------------------
-- Table `courseapi`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courseapi`.`courses` (
  `ID` VARCHAR(60) NOT NULL,
  `NAME` VARCHAR(60) NOT NULL,
  `LENGTH` VARCHAR(60) NOT NULL,
  `LEVEL` VARCHAR(60) NOT NULL,
  `URL` VARCHAR(60) NOT NULL,
  `NOTES` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
