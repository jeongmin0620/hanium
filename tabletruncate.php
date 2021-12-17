<?php
    $conn = mysqli_connect('host', 'user', 'pw');
    mysqli_select_db($conn, 'db');  
    $table = $_POST['table'];            
    
    $sql = "TRUNCATE $table";             
    $result = mysqli_query($conn, $sql);

    if (!$result) {
        echo "<script>alert('화분 내용 삭제 실패'); location.href='selecttable.php';</script>";   
 
    } else {
        echo "<script>alert('화분 내용 삭제 성공'); location.href='main.php';</script>"; 
    }


       
    mysqli_close($conn); 
?>
