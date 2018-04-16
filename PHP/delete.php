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
<div style="background:	#ffffff ;padding: 100px;" contextmenu="mymenu">
</head>
</body>

<ul>
  <li> 
 <button type = "submit"><a href="Favoritesadd.php" 
 style="background-color:#007acc;"> ADD </a></button></li>
 </ul>


<html>
<body>
<!-- -->



<div class="container"style="padding-top:70px;width:500px;">

<form  action ="delete.php" method="post">



<!--
    <div class="form-group">
    <label for="add"><br> Delete from Black List or Favorites: </br></label>
    <select name="favOrBl">
    <option value="W">Favorite</option>
    <option value="B">Black List</option>   
    </select><br>
--> 
  

  <div class="form-group">
    <label for="add"> <br>Restaurant's Food Type:</br></label>
    <input type="text" class="form-control" id="t" name="t">
  </div>


  <div class="form-group">
    <label for="add"><br>Restaurant's Address:</br></label>
    <input type="text" class="form-control" id="pl" name="pl">
  </div>

<div class="form-group">
    <label for="add"><br>Restaurant's Name:</br></label>
    <input type="text" class="form-control" id="fname" name="fname">
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
//$bl = $_POST['favOrBl'];
//echo $t;

   if (empty($pl) || empty($fname) || empty($t)){
	//echo $count;
    echo "<br>Error - All Fields are require </br>";
	die();
  }

$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND FAVPLACES = '$pl' AND FAVNAME ='$fname'";

//$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name'";
$results = mysqli_query($conn, $checkSQL);


	while($row = $results -> fetch_assoc())
{
	$BW = $row['BLACKLIST'];
}		 

$count = mysqli_num_rows($results);

if( $count < 1 )
	{
		echo "Error";
  die();
	}


  //if ($check)
$SQL = "DELETE FROM  Favorites  WHERE USERNAME ='$user_Name' AND FOODTYPE = '$t' AND FAVPLACES = '$pl' AND 
FAVNAME = '$fname' and BLACKLIST = '$BW'";

//$SQL = "DELETE FROM  Favorites WHERE USERNAME ='$user_Name' AND FOODTYPE = '$t' OR FAVPLACES = '$pl' OR FAVNAME = '$fname' OR BLACKLIST = '$bl'";

$result = $conn->multi_query($SQL);

if ( $result === TRUE) 
{
    echo "<br>Deleted successfully</br>";
} else {
    echo "Error: " . $SQL . "<br>" . $conn->error;
}

}

$conn->close();


?>