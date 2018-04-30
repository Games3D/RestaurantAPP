<?php include ('connectDB.php');  session_start();

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
$user_Name = $_SESSION['USER'];?>

<!DOCTYPE html>
<?php
$query = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND BLACKLIST = 'B'";

$result = $conn->query($query); 
	
	echo "<h2>". $user_Name ."'s Black List </h2>";


$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND BLACKLIST = 'B'";

//$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name'";
$result = mysqli_query($conn, $checkSQL);
$count = mysqli_num_rows($result);
	
	if($count === 0)
	{
	echo "You have not added any information in your Black List!";
	}
	else{
while($row = $result -> fetch_assoc())
{
	if (!empty($row['FOODTYPE'] ) )
	{
//	echo "<br><strong>" - </strong>";

echo "<strong> *Food Type : </strong>". $row["FOODTYPE"]."<br>";
	}


	if(!empty($row['FAVPLACES']))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Restaurant's Address: </strong>". $row["FAVPLACES"]."<br>";
    }
	
	
	if(!empty($row["FAVNAME"]))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Restaurant's Name: </strong>". $row["FAVNAME"]."<br>";
    }


	//$counter++;

	echo "<br>";	
}
	}
?>
