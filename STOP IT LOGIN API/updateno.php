<?php
/**
 * Created by PhpStorm.
 * User: AURANGO SABBIR
 * Date: 4/28/15
 * Time: 2:46 PM
 */

define('DBHOST', 'localhost');
define('DBUSER', 'ineedahe_aurango');
define('DBPASS', 'sabbir123');
define('DBNAME', 'ineedahe_ethereal');

$con = mysql_connect(DBHOST, DBUSER, DBPASS);
mysql_select_db(DBNAME, $con);

$name = $_POST["name"];
$user_id = $_POST["user_id"];
$event_id = $_POST["event_id"];
$vote_status = $_POST["vote_status"];
$device_id = $_POST["device_id"];
$response["events"] = array();
$sql = "SELECT * FROM events WHERE id = " . $event_id . " AND created_by='" . $name . "'";
if ($result = mysql_query($sql, $con)) {
    $row_number = mysql_num_rows($result);
    if ($row_number >= 1) {
        $response["success"] = 0;
        echo json_encode($response);
    } else {
        $sql = "SELECT * FROM voter WHERE event_id = " . $event_id . " AND user_id='" . $user_id . "' AND device_id='" . $device_id . "'";
        if ($result = mysql_query($sql, $con)) {
            $row_number = mysql_num_rows($result);
         
            if ($row_number >= 1) {
            
                $response["success"] = 0;
                echo json_encode($response);
            } else {
            
                $sql = "UPDATE events SET vote_no=vote_no+1 WHERE id =" . $event_id;
                if ($result = mysql_query($sql, $con)) {
                
                    $sql = "INSERT INTO `voter`(`event_id`, `user_id`, `vote_status`, `device_id`) VALUES (" . $event_id . ",'" . $user_id . "','no','".$device_id."')";
                    if($result=mysql_query($sql,$con)){
                    
                        $response["success"] = 1;
                        echo json_encode($response);
                    }
                    else{
                    
                        $response["success"] = 0;
                        echo json_encode($response);
                    }
                }else{
               
                    $response["success"] = 0;
                    echo json_encode($response);
                }
            }
        }
    }
} else {
	
    $response["success"] = 0;
    echo json_encode($response);
}