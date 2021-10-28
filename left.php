<?php include('DBconnected.php'); ?>
<!DOCTYPE html>
<html lang="ko">
<head><title> target </title>
 <meta charset="utf-8">
 <script language="javascript">
    function Input()
    {
        if (document.data.input.value == "") {
            alert("등록할 화분의 이름을 입력해 주세요");
            document.data.input.focus();
            return false;
        }
        document.data.submit();
    }
    function InputDel()
    {
        if (document.data_del.del.value == "") {
            alert("삭제할 화분의 이름을 입력해 주세요");
            document.data_del.del.focus();
            return false;
        }
        document.data_del.submit();
    }
</script>
<style>
 img{
     position : absolute;
     left:0px;
     top:480px;
 }
</style>
</head>
<body bgcolor="#EAEAEA">
    <?php
    if(isset($_SESSION['name'])){
        echo "<h3>{$_SESSION['name']}님 환영합니다.</h3>";
    }else{
        echo "<script>alert('잘못된 접근입니다.'); history.back();</script>";
    } 
    ?>
    <div>
        <form name="data" action="crt_tbl.php" method="post">
            <label for="namecre"><b>화분 등록</b></label><br>
            <input type="text" name="input" id="input" style="ime-mode:inactive;width:140px;height:20px;" ><br>

            <button type="button" onclick="Input()">등록하기</button>
            <br><Br>
        </form>
    </div>
    <div>
        <form name="data_del" action="delete.php" method="post">
            <label for="namedel"><b>화분 삭제</b></label><br>
            <input type="text" name="del" id="del" style="ime-mode:inactive;width:140px;height:20px;" ><br>
            <button type="button" onclick="InputDel()">삭제하기</button>
            <br><Br><br>
        </form>
    </div>

    <button type="button" id="btnfq" onclick="top.location.href='fq.html'">
        <span>F&Q</span>
    </button>
    <div>
        <a href="logout.php"><input type="button" value="로그아웃" /></a>
    </div>
    
    <div>
        <img src="plantcare3.png" align="botton">
    </div>
</body>
</html>