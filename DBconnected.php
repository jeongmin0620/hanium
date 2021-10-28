 <?php
  session_start();
  
  $db = new mysqli('db-82rkr.cdb.ntruss.com', 'plant', '12345678!a', 'plantdb');
  $db->set_charset("utf8");

  function mq($sql){
    global $db;
    return $db->query($sql);
  }

  ?>