create table if not exists  `users`  (
	`id` bigint primary key auto_increment,
	`username` varchar(255),
	`password` varchar(255),
	`email` varchar(255) unique,
	`first_name` varchar(255),
	`middle_name` varchar(255),
	`last_name` varchar(255),
	`business_id` bigint null,
	`user_type` varchar(50) null default 'user',
	`created_at` timestamp default current_timestamp,
	`updated_at` timestamp default current_timestamp on update current_timestamp,
	`deleted_at` timestamp null
);