
<?php
$user_Name = $_SESSION['username'];
//$user_Name = 'sahar_e';

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
