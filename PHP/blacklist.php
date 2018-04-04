
<?php

$user_Name = $_SESSION['username'];
//$user_Name = 'sahar_e';

$query = "SELECT * FROM Favorites WHERE USERNAME = '$user_Name' AND BLACKLIST = 'bl'";

$result = $conn->query($query); 
	
	echo "<h2>". $user_Name ."'s Black List </h2>";
$counter = 1;

while($row = $result -> fetch_assoc())
{
	if (!empty($row["FOODTYPE"] ) )
	{
//	echo "<br><strong>" - </strong>";

echo "<strong> *Favorit Food Type : </strong>". $row["FOODTYPE"]."<br>";
	}


	if(!empty($row["FAVPLACES"]))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Favorit Place: </strong>". $row["FAVPLACES"]."<br>";
    }
	
	
	if(!empty($row["FAVNAME"]))
	{	
//	echo "<br><strong>". $counter. " - </strong>";

echo "<strong> *Place Name: </strong>". $row["FAVNAME"]."<br>";
    }


	$counter++;

	echo "<br>";	

	}
	

	
	if($counter === 1)
	{
	echo "You have not added any Favorites!";
	}
	
	
	
?>
	
