<?php
	include('DBconnected.php');

	$userid = $_POST['UserID'];
	$userpw = $_POST['UserPWConfirm'];
	$username = $_POST['UserName'];
	$usertel = $_POST['UserCellPhone'];
	$useremail = $_POST['UserEmail'].'@'.$_POST['email'];
	$userjob = $_POST['job'];


	$id_check = mq("select * from member where id='$userid'");
	$id_check = $id_check->fetch_array();
	if($id_check >= 1){
		echo "<script>alert('아이디가 중복됩니다.'); history.back();</script>";
	}else{
		$sql = mq("insert into member (id,pw,name,tel,email,job) values('".$userid."','".$userpw."','".$username."','".$usertel."','".$useremail."','".$userjob."')");
		
?>
		<meta charset="utf-8" />
		<script type="text/javascript">alert('회원가입이 완료되었습니다.');</script>
		<script>location.href = "login.html";</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<?php
	}

?>

