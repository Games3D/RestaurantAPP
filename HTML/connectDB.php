
<?php
$servername = "sql.njit.edu";
$username = "jp834";
$password = "despot77";
$dbname = "jp834";
/*$servername = "localhost";
$username = "se269";
$password = "";
$dbname = "se269";
*/


// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
?>