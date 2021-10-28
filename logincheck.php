<meta charset="utf-8" />
<?php	
	include('DBconnected.php');
	
	if($_POST["id"] == "" || $_POST["pass"] == ""){
		echo '<script> alert("아이디와 비밀번호를 입력하세요"); history.back(); </script>';
	}else{

	$password = $_POST['pass'];
	$sql = mq("select * from member where id='".$_POST['id']."'");
	$member = $sql->fetch_array();
	$pw = $member['pw']; 

	if($password==$pw) 
	{
		$_SESSION['id'] = $member["id"];
		$_SESSION['pass'] = $member["pw"];
		$_SESSION['name'] = $member["name"];

		echo "<script>alert('로그인되었습니다.'); location.href='main.php';</script>";
	}else{ 
		echo "<script>alert('아이디 혹은 비밀번호를 확인하세요.'); history.back();</script>";
	}
}
?>

