CREATE TABLE `books` (
    `id` int(10) AUTO_INCREMENT primary key,
    `author` longtext,
    `launch_date` datetime(6) not null,
    `price` decimal (65,2) not null,
    `title` longtext  
) engine = InnoDB default CHARSET=latin1;