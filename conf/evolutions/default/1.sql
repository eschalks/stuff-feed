# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `feed_post` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`feed_id` VARCHAR(254) NOT NULL,`user_id` BIGINT NOT NULL,`title` VARCHAR(254) NOT NULL,`link_url` VARCHAR(254) NOT NULL,`posted_at` TIMESTAMP NOT NULL);
create table `feed` (`id` VARCHAR(254) NOT NULL PRIMARY KEY,`name` VARCHAR(254) NOT NULL);
create table `user` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`username` VARCHAR(254) NOT NULL,`password` CHAR(60) NOT NULL,`email` VARCHAR(254) NOT NULL,`registered_at` TIMESTAMP NOT NULL);
create unique index `idx_username` on `user` (`username`);
alter table `feed_post` add constraint `fk_user` foreign key(`user_id`) references `user`(`id`) on update NO ACTION on delete NO ACTION;
alter table `feed_post` add constraint `fk_feed` foreign key(`feed_id`) references `feed`(`id`) on update NO ACTION on delete NO ACTION;

# --- !Downs

ALTER TABLE feed_post DROP FOREIGN KEY fk_user;
ALTER TABLE feed_post DROP FOREIGN KEY fk_feed;
drop table `feed_post`;
drop table `feed`;
drop table `user`;

