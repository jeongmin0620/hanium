<?php

	$host = '';
	$user = '';
	$pw = '';
	$dbName = '';
	$mysqli = mysqli_connect($host, $user, $pw, $dbName);

	$temp = $_GET['temp'];
	$humidity = $_GET['humidity'];
	$level = $_GET['level'];
	$soil = $_GET['soil'];
	
	if((string)$soil == '1'){
		$a = "'매우 건조'";
	}else if ((string)$soil == '0') {
		$a = "'충분'";
	}

	$sql = "INSERT INTO test2(input_time, temp, humidity, level, soil) VALUES(now(), $temp, $humidity, $level, $a)";

	mysqli_query($mysqli, $sql);
	mysqli_close($mysqli);

?>
