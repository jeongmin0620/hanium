<?php

	session_start();

	include('DBconnected.php');

	// 쿠키값 지우기
	setCookie('id', '', time()-1000, '/');

	$_SESSION = array();

	session_destroy();
?>
<meta charset="utf-8">
<script>alert("로그아웃되었습니다."); top.location.href="login.html"; </script>
