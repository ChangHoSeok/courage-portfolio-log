CREATE TABLE `TB_MEMBER` (
	`SNO` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`NAME` TEXT NOT NULL,
	`PASSWORD` TEXT NOT NULL,
	`SALT` TEXT NOT NULL,
	`EMAIL` TEXT NOT NULL );