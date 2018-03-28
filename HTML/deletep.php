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
<body>
<!-- -->
<?php include 'Functions/navbar_main.php';?>

<!-- -->


<div class="container"style="padding-top:70px;width:500px;">

<form  action ="deletep.php" method="post">


<!-- <br><strong>Favorit Food Radius: </strong><input type="text" name="places"><br>
<br><strong>Favorit meal: </strong><input type="text" name="places"><br>
--> 

  <div class="form-group">
    <label for="add"> <br>Delete Favorite Food Type:</br></label>
    <input type="text" class="form-control" id="t" name="t">
  </div>


  <div class="form-group">
    <label for="add"><br>Delete Favorite Food Places:</br></label>
    <input type="text" class="form-control" id="pl" name="pl">
  </div>

<div class="form-group">
    <label for="add"><br>Delete Favorite Food Places Name:</br></label>
    <input type="text" class="form-control" id="fname" name="fname">
  </div>

  
<div class="form-group">
    <label for="add"><br>Delete Black List:</br></label>
    <input type="text" class="form-control" id="bl" name="bl">
  </div>
  
  <div>
  <button type="submit" name="submit">DELETE</button>
  </div>
   
</form>
</div>


<?php


$user_Name = $_SESSION['username'];
//echo $user_Name;
if(isset($_POST['submit']))  
{  
$user_Name = $_SESSION['username'];
//echo $user_Name;
// get value
$t = $_POST['t'];
$pl = $_POST['pl'];
$fname = $_POST['fname'];
$bl = $_POST['bl'];
//echo $t;

$SQL = "UPDATE Favorites SET FOODTYPE= NULL WHERE USERNAME ='$user_Name' AND FOODTYPE = '$t'";
$SQL = "UPDATE Favorites SET FAVPLACES = NULL WHERE USERNAME ='$user_Name' AND FOODTYPE = '$pl'";
$SQL = "UPDATE Favorites SET FAVNAME= NULL WHERE USERNAME ='$user_Name' AND FOODTYPE = '$fname'";
$SQL = "UPDATE Favorites SET BLACKLIST= NULL WHERE USERNAME ='$user_Name' AND FOODTYPE = '$bl'";


$result = $conn->multi_query($SQL);

if ( $result === TRUE) 
{
    echo "<br>Deleted successfully</br>";
} else {
    echo "Error: " . $SQL . "<br>" . $conn->error;
}

$conn->close();
}



?>