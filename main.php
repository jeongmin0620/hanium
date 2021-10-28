<?php include('DBconnected.php'); ?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<head>
	<meta charset="utf-8" />
	<title>메인페이지</title>
</head>
	<frameset cols="25%, *">
        <frame src="left.php">
        <frame src="right.php">
         </frameset> 
<body>
	<?php
    if(isset($_SESSION['name'])){
        echo "";
    }else{
        echo "<script>alert('잘못된 접근입니다.'); history.back();</script>";
    } 
    ?>
</body>

</html>