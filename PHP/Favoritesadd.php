<?php include ('connectDB.php'); 
?>
<?php session_start();
?>


<!DOCTYPE html>
<html>
<head>

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
<div style="background:	#f ;padding: 100px;" >
</head>
</body>

<ul>
  <li> 
 <button type = "submit"><a href="delete.php" 
 style="background-color:#007acc;"> Delete </a></button></li>
 </ul>


<html>
<body>
<!-- -->


<div class="container"style="padding-top:70px;width:500px;">

<form  action ="Favoritesadd.php" method="post">


<!-- <br><strong>Favorit Food Radius: </strong><input type="text" name="places"><br>
<br><strong>Favorit meal: </strong><input type="text" name="places"><br>
--> 


  <div class="form-group">
    <label for="add"><br> Please choose what do you want to add: </br></label>
    <select name="favOrBl">
    <option value="W">Favorite</option>
    <option value="B">Black List</option>   
    </select><br>
  

  <div class="form-group">
    <label for="add"><br> Restaurant's Food Type:</br></label>
    <input type="text" class="form-control" id="t" name="t">
  </div>
  <div>


  <div class="form-group">
    <label for="add"><br> Restaurant's Address:</br></label>
    <input type="text" class="form-control" id="pl" name="pl">
  </div>


  <div class="form-group">
    <label for="add"><br> Restaurant's Name:</br></label>
    <input type="text" class="form-control" id="fname" name="fname">
  </div>
  <div>

  
  
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

$fl = $_POST['favOrBl'];

//echo $user_Name;
//echo $t;
//echo $pl;
$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND FAVPLACES = '$pl' AND FAVNAME ='$fname'";

//$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name'";
$results = mysqli_query($conn, $checkSQL);
$count = mysqli_num_rows($results);


   if (empty($pl) || empty($fname) || empty($t)){
	//echo $count;
    echo "<br>Error - All Fields are require </br>";
	die();
  }

  $SQL = "INSERT INTO Favorites(USERNAME, FOODTYPE, FAVPLACES,FAVNAME, BLACKLIST) 
VALUES ('$user_Name','$t','$pl', '$fname', '$fl')";
    $resultss = mysqli_query($conn, $SQL);
 
 if( $count != 0 )
	{
		  echo $count;
	  echo "Error - Duplicate Value";
  die();
	}

 
if ( $resultss === TRUE) 
{
    echo "<br>Added successfully</br>";
} 
else {
    echo "Error: " . $SQL . "<br>" . $conn->error;
}


$conn->close();


}
//header("refresh:2, url =Favoritesp.php")

?>
