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

$event_name = $_POST["event_name"];
$name = $_POST["name"];
$working_place = $_POST["working_place"];
$district = $_POST["district"];
$upzilla = $_POST["upzilla"];
$thana = $_POST["thana"];
$description = $_POST["description"];
$proof_link = $_POST["proof_link"];
$created_by = $_POST["created_by"];
$sql = "INSERT INTO `events`
(`event_name`, `name`, `working_place`, `district`, `upzilla`, `thana`, `description`, `proof_link`, `created_by`) 
VALUES ('".$event_name."','".$name."','".$working_place."','".$district."','".$upzilla."','".$thana."','".$description."','','".$created_by."')";
$response["events"] = array();
if ($result = mysql_query($sql, $con)) {
    $response["success"] = 1;
    echo json_encode($response);
} else {
    $response["success"] = 0;
    echo json_encode($response);
}