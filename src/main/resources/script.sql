
delete from bank.currency_course where id = 5;

-- -----------------------------------------------------
-- Schema Bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Bank
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Bank`; 
CREATE SCHEMA IF NOT EXISTS `Bank` DEFAULT CHARACTER SET utf8 ;
USE `Bank` ;

-- -----------------------------------------------------
-- Table `Bank`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`user_role` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`role_name` VARCHAR(255) NULL,
	CONSTRAINT `pk_user_role` PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Bank`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`client` (
   `id` BIGINT AUTO_INCREMENT NOT NULL,
   `login` VARCHAR(255) NULL,
   `password` VARCHAR(255) NULL,
   `name` VARCHAR(255) NULL,
   `surname` VARCHAR(255) NULL,
   `date_of_birth` date NULL,
   `phone_number` VARCHAR(255) NULL,
   `status` INT NULL,
   `email` VARCHAR(255) NULL,
   `user_role_id` BIGINT NULL,
   CONSTRAINT `pk_clients` PRIMARY KEY (`id`));
ALTER TABLE `client` ADD CONSTRAINT `FK_CLIENTS_ON_USER_ROLE` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`account` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`sum` BIGINT NOT NULL,
	`date_of_issue` date NULL,
	`currency` INT NULL,
	`status` INT NULL,
	`client_id` BIGINT NULL,
   CONSTRAINT `pk_account` PRIMARY KEY (`id`));
ALTER TABLE `account` ADD CONSTRAINT `FK_ACCOUNT_ON_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`card` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`holder_name` VARCHAR(255) NULL,
	`date_of_expire` date NULL,
	`cvv` VARCHAR(255) NULL,
	`pin` VARCHAR(255) NULL,
	`status` INT NULL,
	`card_issuer` INT NULL,
	`type` INT NULL,
	`account_id` BIGINT NULL,
   CONSTRAINT `pk_card` PRIMARY KEY (`id`));
ALTER TABLE `card` ADD CONSTRAINT `FK_CARD_ON_ACCOUNT` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`deposit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`deposit` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`interest` DOUBLE NOT NULL,
	`month_to_expire` BIGINT NOT NULL,
	`currency` INT NULL,
   CONSTRAINT `pk_deposit` PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Bank`.`deposit_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`deposit_order` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`status` INT NULL,
	`sum` BIGINT NOT NULL,
	`date_of_issue` date NULL,
	`date_of_end` date NULL,
	`client_id` BIGINT NULL,
	`deposit_id` BIGINT NULL,
   CONSTRAINT `pk_deposit_order` PRIMARY KEY (`id`));
ALTER TABLE `deposit_order` ADD CONSTRAINT `FK_DEPOSIT_ORDER_ON_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);
ALTER TABLE `deposit_order` ADD CONSTRAINT `FK_DEPOSIT_ORDER_ON_DEPOSIT` FOREIGN KEY (`deposit_id`) REFERENCES `deposit` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`loan` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`interest` DOUBLE NOT NULL,
	`months_to_return` BIGINT NOT NULL,
	`currency` INT NULL,
   CONSTRAINT `pk_loan` PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Bank`.`loan_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`loan_order` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`date_of_issue` date NULL,
	`sum` BIGINT NOT NULL,
	`status` INT NULL,
	`date_of_end` date NULL,
	`client_id` BIGINT NULL,
	`loan_id` BIGINT NULL,
   CONSTRAINT `pk_loan_order` PRIMARY KEY (`id`));
ALTER TABLE `loan_order` ADD CONSTRAINT `FK_LOAN_ORDER_ON_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);
ALTER TABLE `loan_order` ADD CONSTRAINT `FK_LOAN_ORDER_ON_LOAN` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`operation_of_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`operation_of_user` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`type` INT NULL,
	`description` VARCHAR(255) NULL,
	`client_id` BIGINT NULL,
   CONSTRAINT `pk_operation_of_user` PRIMARY KEY (`id`));
ALTER TABLE `operation_of_user` ADD CONSTRAINT `FK_OPERATION_OF_USER_ON_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);


-- -----------------------------------------------------
-- Table `Bank`.`currency_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bank`.`currency_course` (
	`id` BIGINT AUTO_INCREMENT NOT NULL,
	`date` date NULL,
	`cost_usd` DOUBLE NOT NULL,
	`cost_eur` DOUBLE NOT NULL,
	`cost_rub` DOUBLE NOT NULL,
   CONSTRAINT `pk_currency_course` PRIMARY KEY (`id`));
   
drop table bank.currency_course;

select * from bank.currency_course;

insert into bank.deposit (interest, month_to_expire, currency) values (12.5, 3, 0);
insert into bank.deposit (interest, month_to_expire, currency) values (4.5, 9, 1);

insert into bank.loan (interest, months_to_return, currency) values (19, 36, 1);
insert into bank.loan (interest, months_to_return, currency) values (15, 12, 0);

select * from bank.currency_course;

insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("04-12-22","%d-%m-%y"), 2.53, 2.62, 4.04);
insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("03-12-22","%d-%m-%y"), 2.5, 2.6, 4);
insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("02-12-22","%d-%m-%y"), 2.48, 2.57, 4.02);
insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("01-12-22","%d-%m-%y"), 2.55, 2.58, 4.01);
insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("30-11-22","%d-%m-%y"), 2.54, 2.59, 3.98);
insert into bank.currency_course (date, cost_usd, cost_eur, cost_rub) values (STR_TO_DATE("29-11-22","%d-%m-%y"), 2.51, 2.47, 3.92);