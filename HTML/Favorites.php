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

$user_Name = $_SESSION['USER'];
//$user_Name = 'sahar_e';

$query = "SELECT * FROM Favorites WHERE USERNAME = '".$user_Name."'";

$result = $conn->query($query); 

include 'Functions/navbar_main.php';

	echo "<h2>". $user_Name ."'s Favorites </h2>";

while($row = $result -> fetch_assoc())
{
	if (!empty($row["FOODTYPE"] ) )
	{
	echo "<br><strong> Favorite Food Type : </strong>". $row["FOODTYPE"]."<br>";
	}


	if(!empty($row["FAVPLACES"]))
	{	
	echo "<br><strong> Favorite Places: </strong>". $row["FAVPLACES"]."<br>";
    }
	
	
	if(!empty($row["FAVNAME"]))
	{	
	echo "<br><strong> Favorite Places: </strong>". $row["FAVNAME"]."<br>";
    }
	
	
	if(!empty($row["BLACKLIST"]))
	{
		echo "<br><strong> Black List : </strong>". $row["BLACKLIST"]."<br>";
	}
	echo "<br>";
}
?>

<form action="Favoritesadd.php" method="get">
    <input type="submit" value="Add Favorites" 
         name="Submit" id="frm1_submit" />
</form>
