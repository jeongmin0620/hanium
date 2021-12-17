<!DOCTYPE html>
<html>
<head>
  <title>화분 조회</title>
  <meta charset="UTF-8">
  <script type="text/javascript">
    $(function() {
      $(document).ready(function() {
        $('#select').DataTable();
      });
    });
  </script>
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" type="text/Javascript"charset="UTF-8"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js" type="text/Javascript"charset="UTF-8"></script>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/Javascript"charset="UTF-8"></script>
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
  <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js" type="text/Javascript"charset="UTF-8"></script>
  <link rel="stylesheet" href="selecttable.css">
  <script type="text/javascript">
    $(function() {
      $(document).ready(function() {
        $('#select').DataTable();
      });
    });
  </script>

</head>
<body>
  <?php
  $conn = mysqli_connect('host', 'user', 'pw');
  mysqli_select_db($conn, 'db');
  $table = $_GET['TABLE_NAME'];
  ?>
  <div class="container py-5">
    <header class="text-center text-white">
      <h1 class="display-4">화분 조회</h1>
      <p class="lead mb-0"><?php echo "$table"; ?></p>
      <div>
        <button type="button" class="button" onclick="location.href='main.php'">메인으로</button>
        <form style="display: inline;" action="tabletruncate.php" method="post">
          <input type="hidden" name="table" value="<?php echo "$table"; ?>">
          <button type="submit" class="button">화분 내용 삭제</button>
        </form>
      </div>
    </header>
    <div class="row py-5">
      <div class="col-lg-10 mx-auto">
        <div class="card rounded shadow border-0">
          <div class="card-body p-5 bg-white rounded">
            <div class="table-responsive">
              <table id="select" style="width:100%" class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th>번호</th>
                    <th>시간</th>
                    <th>온도</th>
                    <th>습도</th>
                    <th>물높이</th>
                    <th>토양습도</th>
                  </tr>
                </thead>
                <tbody>
                  <?php      

                  $sql = "select * from $table";                                
                  $result = mysqli_query($conn, $sql); 
                  $count = 1;
                  while ($row = mysqli_fetch_array($result)){
                    $input_time = $row['input_time'];
                    $temp = $row['temp'];
                    $humidity = $row['humidity'];
                    $level = $row['level'];
                    $soil = $row['soil'];
                    echo("<tr><td>".$count."</td>");
                    echo("<td>".$input_time."</td>");
                    echo("<td>".$temp."</td>");
                    echo("<td>".$humidity."</td>");  
                    echo("<td>".$level."</td>");    
                    echo("<td>".$soil."</td></tr>");                  
                    $count++; 
                  }

                  mysqli_close($conn); 
                  ?>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
