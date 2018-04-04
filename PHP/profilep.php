<?php include ('connectDB.php'); session_start();?>
<!DOCTYPE html>

<head>
<body>
	<link rel="stylesheet" type="text/css" href="CSS/Main.css">
	<link rel="stylesheet" type="text/css" href="CSS/Home.css">
	<link rel="stylesheet" type="text/css" href="header.css">
	
	<style>
      #right-panel {
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
        float: left;
        width: 70%;
        height: 100%;
      }
      #right-panel {
        margin: 20px;
        border-width: 2px;
        width: 20%;
        height: 400px;
        float: left;
        text-align: left;
        padding-top: 0;
      }
      #directions-panel {
        margin-top: 10px;
        background-color: #FFEE77;
        padding: 10px;
        overflow: scroll;
        height: 174px;
      }
    </style>

</head>

</body>

<?php include ( 'navbar_main.php');?>

<head>
<body>
<div style="background:	 #ffffff ;padding: 100px;" contextmenu="mymenu">
</head>
</body>

<?php
//$user_Name = $_SESSION["USERNAME"];
//$user_Name = $_SESSION['username'];
//$user_Name = 'sam90';
$user_Name = 'sahar';

$query = "SELECT * FROM User WHERE username = '$user_Name'";
$result = $conn->query($query); 


 while($row = $result -> fetch_assoc())
 {
	
	
	echo "<h2>". $row["username"]. "'s Profile Page</h2>";
	echo "<br><strong> user name: </strong>". $row["username"]."<br>";
    if ($row["firstname"]!= Null )
	echo"<br><strong> First name : </strong>". $row["firstname"]."<br>";
	if ($row["lastname"]!= Null )
	echo"<br> <strong>Last name: </strong>". $row["lastname"]."<br>";
	if ($row["email"]!= Null )
	echo"<br><strong> Email Address : </strong>". $row["email"]."<br>";
	if ($row["cellphone"]!= Null )
	echo"<br><strong> Phone: </strong>". $row["cellphone"]."<br>";
	if ($row["DOB"]!= Null )
	echo"<br> <strong>Date Of Birth : </strong>". $row["DOB"]."<br>";
if ($row["gender"]!= Null )
	echo"<br><strong> Gender:</strong> ". $row["gender"]."<br>";
	

	
 }

$_SESSION["username"] = $user_Name;

$conn->close();

?>
