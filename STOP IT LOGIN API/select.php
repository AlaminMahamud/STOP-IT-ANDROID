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

//$db = new PDO("mysql:host=" . DBHOST . ";dbname=" . DBNAME, DBUSER, DBPASS);
//$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
if (isset($_GET["district"])) {
    $district = $_GET["district"];
    $sql = "SELECT *
FROM events WHERE district = '" . $district . "'";
    $response["events"] = array();
    if ($result = mysql_query($sql, $con)) {

        $response["success"] = 1;
        while ($row = mysql_fetch_array($result)) {
            $user = array();
            $user["id"] = $row["id"];
            $user["event_name"] = $row["event_name"];
            $user["name"] = $row["name"];
            $user["working_place"] = $row["working_place"];
            $user["district"] = $row["district"];
            $user["upzilla"] = $row["upzilla"];
            $user["thana"] = $row["thana"];
            $user["description"] = $row["description"];
            $user["proof_link"] = $row["proof_link"];
            $user["created_by"] = $row["created_by"];
            $user["vote_yes"] = $row["vote_yes"];
            $user["vote_no"] = $row["vote_no"];
            array_push($response["events"], $user);
        }
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        echo json_encode($response);
    }
} else if (isset($_GET["type"])) {
    $type = $_GET["type"];
    if ($type == "most_voted") {
        $sql = "SELECT `id`, `event_name`, `name`, `working_place`, `district`, `upzilla`, `thana`, `description`, `proof_link`, `created_by`, `vote_yes`, `vote_no`, (vote_yes + vote_no) AS voted FROM  `events` ORDER BY voted DESC LIMIT 10";
        $response["events"] = array();
        if ($result = mysql_query($sql, $con)) {

            $response["success"] = 1;
            while ($row = mysql_fetch_array($result)) {
                $user = array();
                $user["id"] = $row["id"];
                $user["event_name"] = $row["event_name"];
                $user["name"] = $row["name"];
                $user["working_place"] = $row["working_place"];
                $user["district"] = $row["district"];
                $user["upzilla"] = $row["upzilla"];
                $user["thana"] = $row["thana"];
                $user["description"] = $row["description"];
                $user["proof_link"] = $row["proof_link"];
                $user["created_by"] = $row["created_by"];
                $user["vote_yes"] = $row["vote_yes"];
                $user["vote_no"] = $row["vote_no"];
                array_push($response["events"], $user);
            }
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            echo json_encode($response);
        }
    }
    else{
        $sql = "SELECT `id`, `event_name`, `name`, `working_place`, `district`, `upzilla`, `thana`, `description`, `proof_link`, `created_by`, `vote_yes`, `vote_no` FROM  `events` ORDER BY id DESC LIMIT 10";
        $response["events"] = array();
        if ($result = mysql_query($sql, $con)) {

            $response["success"] = 1;
            while ($row = mysql_fetch_array($result)) {
                $user = array();
                $user["id"] = $row["id"];
                $user["event_name"] = $row["event_name"];
                $user["name"] = $row["name"];
                $user["working_place"] = $row["working_place"];
                $user["district"] = $row["district"];
                $user["upzilla"] = $row["upzilla"];
                $user["thana"] = $row["thana"];
                $user["description"] = $row["description"];
                $user["proof_link"] = $row["proof_link"];
                $user["created_by"] = $row["created_by"];
                $user["vote_yes"] = $row["vote_yes"];
                $user["vote_no"] = $row["vote_no"];
                array_push($response["events"], $user);
            }
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            echo json_encode($response);
        }
    }
}