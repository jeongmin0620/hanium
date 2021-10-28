<?php
	include('DBconnected.php');

	$uid = $_GET["UserID"];
	$sql = mq("select * from member where id='".$uid."'");

	$member = $sql->fetch_array();

	if($member==0) {
?>
		
	<div style='font-family:"malgun gothic"; margin-top: 90px; text-align: center;' >
		<?php echo $uid." 는 사용 가능한 아이디입니다.";?>
	</div>

<?php 
	}else{
?>
	<div style='font-family:"malgun gothic"; margin-top: 90px; text-align: center; color:red;'>
		<?php echo "이미 사용 중인 ID 입니다.";?>
	</div>
<?php
	}
?>
	<div style="margin-top: 20px; margin-left: 170px;">
	<button value="확인"  onclick="window.close()">확인</button>
	</div>