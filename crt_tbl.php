<?php

	$host = 'db-82rkr.cdb.ntruss.com';
	$user = 'plant';
	$pw = '12345678!a';
	$dbName = 'plantdb';
	$mysqli = new mysqli($host, $user, $pw, $dbName);

	session_start();
	$uid = $_SESSION['id'];

	$input = $_POST['input'];

	$sql = "CREATE TABLE $input";

	$sql = $sql. "(
		".$uid." int primary key auto_increment,
		input_time DATETIME DEFAULT NULL,
		temp float DEFAULT NULL,
		humidity float DEFAULT NULL,
                           level float DEFAULT NULL,
                           soil varchar(20) DEFAULT NULL			
		)ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	if($mysqli->query($sql)){ 
		echo "<script>alert('화분 등록 성공'); top.location.href='main.php';</script>"; 
	} else {
		echo "<script>alert('화분 등록 실패'); top.location.href='main.php';</script>";

	}

	mysqli_close($mysqli);

?>
