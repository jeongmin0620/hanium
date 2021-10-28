<?php
	
	$conn = mysqli_connect('db-82rkr.cdb.ntruss.com', 'plant', '12345678!a');
	mysqli_select_db($conn, "plantdb"); 


	$dtable = $_POST['del'];

	$sql = "drop table $dtable";
	if (mysqli_query($conn, $sql)) {

		echo "<script>alert('화분 삭제 성공'); top.location.href='main.php';</script>";
		
	} else {
		echo "<script>alert('존재하지 않는 테이블입니다.'); history.back();</script>";
	}
	mysqli_close($conn);

?>
