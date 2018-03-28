<?php require_once 'connectDB.php';
	session_start();
	
	//resets error vars
	unset($_SESSION['ERROR']);
	unset($_SESSION['ERROR_PATH']);
	
	if ($_SESSION["authenticated"] == "" or (isset($_SESSION['LAST_ACTIVITY']) && (time() - $_SESSION['LAST_ACTIVITY'] > 1800))){
		session_unset(); 
		session_destroy(); 
		header("Location: index.php"); /* Redirect browser */
		exit();
	}
	$_SESSION['LAST_ACTIVITY'] = time(); // update last activity time stamp
	?>
<?php include 'Functions/navbar_main.php';?>
<?php
//$user_Name = $_SESSION["USERNAME"];
$user_Name = $_SESSION['USER'];
//$user_Name = 'sahar_e';
$query = "SELECT * FROM User WHERE username = '".$user_Name."'";

$result = $conn->query($query); 


 while($row = $result -> fetch_assoc())
 {
	if ($row["firstname"] != Null ||$row["lastname"]!= Null ||$row["email"]!= Null ||
	$row["cellphone"]!= Null || $row["DOB"]!= Null || $row["gender"]!= Null)
	{
	echo "<h2>". $row["username"]. "'s Profile Page</h2>";
	echo "<strong> user name: </strong>". $row["username"]."<br>";
    echo"<br><strong> First name : </strong>". $row["firstname"]."<br>";
	echo"<br> <strong>Last name: </strong>". $row["lastname"]."<br>";
	echo"<br><strong> Email Address : </strong>". $row["email"]."<br>";
	echo"<br><strong> Phone: </strong>". $row["cellphone"]."<br>";
	echo"<br> <strong>Date Of Birth : </strong>". $row["DOB"]."<br>";
	echo"<br><strong> Gender:</strong> ". $row["gender"]."<br>";
	}

 }

$_SESSION["username"] = $user_Name;

$conn->close();

?>