<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Main.css">
<link rel="stylesheet" type="text/css" href="CSS/Home.css">

 </head>
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

<div class="container"style="padding-top:70px;width:500px;">

<form  action ="Favoritesadd.php" method="post">



  <div class="form-group">
    <label for="add"><br> Favorite Food Type:</br></label>
    <input type="text" class="form-control" id="t" name="t">
  </div>
  <div>


  <div class="form-group">
    <label for="add"><br> Favorite Food Places:</br></label>
    <input type="text" class="form-control" id="pl" name="pl">
  </div>


  <div class="form-group">
    <label for="add"><br> Favorite Food Places Name:</br></label>
    <input type="text" class="form-control" id="fname" name="fname">
  </div>
  <div>

  <div class="form-group">
    <label for="add"><br> Black List:</br></label>
    <input type="text" class="form-control" id="bl" name="bl">
  </div>
  <div>
  
  

  <button type="submit" name="submit">Add</button>
  </div>


   
</form>
</div>


<?php  $user_Name = $_SESSION['username'];  // get user name ?>


<?php

 // echo $user_Name ;    //test username


// execute the query 

if(isset($_POST['submit']))  
{  
$user_Name = $_SESSION['username'];

$t = $_POST['t'];

$pl = $_POST['pl'];

$fname = $_POST['fname'];

$bl = $_POST['bl'];

echo $user_Name;
//echo $t;
//echo $pl;

$SQL = "INSERT INTO Favorites (USERNAME, FOODTYPE, FAVPLACES,FAVNAME, BLACKLIST) 
VALUES ('$user_Name','$t','$pl', '$fname', '$bl')";


if ($conn->multi_query($SQL) === TRUE) {
    echo "<br>New records created successfully</br>";
} else {
    echo "Error: " . $SQL . "<br>" . $conn->error;
}

$conn->close();
}


//header("refresh:2, url =Favorites.php")

?>