<?php
$host = "localhost";
$user = "APCSUSER";
$password = "apcs";
$db = "apcs";
$con = mysqli_connect($host, $user, $password, $db);
if (!$con) {
    die("Error in Connection " . mysqli_connect_error());
} else {
    echo "<br/><h3>Connection succeful</h3>";
}
?>