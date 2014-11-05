<?php
header('Content-type: application/json');
header("access-control-allow-origin: *");
$ch = curl_init();
$url = 'http://pictaculous.com/api/1.0/';

error_log($_GET["name"]);

$fields = array('image'=>file_get_contents($_GET["name"]));

# Set some default CURL options
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
curl_setopt($ch, CURLOPT_HEADER, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $fields);
curl_setopt($ch, CURLOPT_URL, $url);

$json = curl_exec($ch);
echo json_encode($json);
