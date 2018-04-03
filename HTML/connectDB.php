
<?php
$servername = "games3dtest.ddns.net";
$username = "NJIT_CS684";
$password = "NJIT_CS684";
$dbname = "NJIT_CS684";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
?>