<?php

    include_once 'db_functions.php';

    $db = new DB_Functions();

    $response = array();

    $newMsgs = $db->getNewMessages();

    if ($newMsgs) {
        $new_msgs = mysql_num_rows($newMsgs);
		$response["new_msgs"] = $new_msgs;
	}

    echo json_encode($response);