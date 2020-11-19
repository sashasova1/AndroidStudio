<?php

require 'config.php';
require 'db.php';
require 'model.php';

header('Content-type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');
header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token, Authorization');

$action = $_REQUEST['action'];
if ($action == 'get_authors_list') {
    $data = Model::getAuthorsList();
} elseif ($action == 'get_author' && isset($_REQUEST['author_id'])) {
    $author = Model::getAuthor($_REQUEST['author_id']);
    if (count ($author) > 0){
        $data = $author[0];
    } else {
        $data = ['err' => 'no author found'];
    }
} else {
    $data = ['err' => 'no action was sent'];
}
echo json_encode($data);
