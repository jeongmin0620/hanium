 <?php
  session_start();
  
  $db = new mysqli('host', 'user', 'pw', 'db');
  $db->set_charset("utf8");

  function mq($sql){
    global $db;
    return $db->query($sql);
  }

  ?>
