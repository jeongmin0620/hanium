CREATE TABLE member (

    `idx` int primary key auto_increment,
    `id` varchar(50) NOT NULL,
    `pw` varchar(80) NOT NULL,
    `name` varchar(50) NOT NULL,
    `tel` int(80) NOT NULL,
    `email` varchar(100) NOT NULL,
    `job` varchar(70) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;