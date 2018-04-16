<?php include ('connectDB.php');  session_start();?>

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
<div style="background:	#ffffff ;padding: 100px;" contextmenu="mymenu">
</head>
</body>


<ul>
  <li> <button type = "submit"> <a href="Favoritesadd.php" style="background-color:#007acc;"> Add </a></button>
 <button  type = "submit"><a href="delete.php" style="background-color:#007acc;"> Delete </a></button></li>
</ul>
<div class="container"style="padding-top:70px;width:500px;">


<?php

$user_Name = $_SESSION['username'];
//$user_Name = 'sahar_e';

$query = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND BLACKLIST = 'W'";

$result = $conn->query($query); 
	
	echo "<h2>". $user_Name ."'s Favorites </h2>";
$counter = 1;

$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND BLACKLIST = 'W'";

//$checkSQL = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name'";
$result = mysqli_query($conn, $checkSQL);
$count = mysqli_num_rows($result);
	
	if($count === 0)
	{
	echo "You have not added any Favorites!";
	}
	else{
while($row = $result -> fetch_assoc())
{
	if (!empty($row['FOODTYPE'] ) )
	{
//	echo "<br><strong>" - </strong>";

echo "<strong> *Favorit Food Type : </strong>". $row["FOODTYPE"]."<br>";
	}


	if(!empty($row['FAVPLACES']))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Favorit Place: </strong>". $row["FAVPLACES"]."<br>";
    }
	
	
	if(!empty($row["FAVNAME"]))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Place Name: </strong>". $row["FAVNAME"]."<br>";
    }


	//$counter++;

	echo "<br>";	
}
	}
	
?>
	
<?php include"blacklist.php"?>
