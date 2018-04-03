<?php
require_once("connectDB.php");

if(!empty($_POST["register-user"])) {
	/* Form Required Field Validation */
	foreach($_POST as $key=>$value) {
		if(empty($_POST[$key])) {
		$error_message = "All Fields are required";
		break;
		}
	}
	/* Password Matching Validation */
	if($_POST['password'] != $_POST['confirm_password']){ 
	$error_message = 'Passwords should be same<br>'; 
	}

	/* Email Validation */
	if(!isset($error_message)) {
		if (!filter_var($_POST["userEmail"], FILTER_VALIDATE_EMAIL)) {
		$error_message = "Invalid Email Address";
		}
	}
	
	/* Validation to check if gender is selected */
	if(!isset($error_message)) {
	if(!isset($_POST["gender"])) {
	$error_message = " All Fields are required";
	}
	}
	

	/* Validation to check if Terms and Conditions are accepted */
	if(!isset($error_message)) {
		if(!isset($_POST["terms"])) {
		$error_message = "Accept Terms and Conditions to Register";
		}
	}

	//to use a hashes on the passwords use this:  md5($_POST["password"])
	
	if(!isset($error_message)) {
		$query = "INSERT INTO User (username, firstname, lastname, password, email, gender, cellphone, DOB) VALUES('" . strtolower($_POST["userName"]) . "', '" . $_POST["firstName"] . "', '" . $_POST["lastName"] . "', '" . strtolower($_POST["password"]) . "', '" . strtolower($_POST["userEmail"]) . "', '" . $_POST["gender"] . "', '" . $_POST["userPhone"] . "', '" . $_POST["userDOB"] . "')";
		$result = $conn->query($query);
		if(!empty($result)) {
			$error_message = "";
			$success_message = "You have registered successfully!";	
			header('Location: index.php');
			unset($_POST);
		} else {
			echo $query;
			$error_message = "Problem in registration. Try Again!";	
		}
	}
}
?>
<html>
<head>
<title>User Registration</title>
<link rel="stylesheet" type="text/css" href="CSS/RegisterUser.css">
</head>
<body>
<header>
	<h1 class="mainheader" align="center"><font color="white">Sign Up with Us!</font></h1>
</header>

<article>
<section>
<h1 style="text-align: center"><font color="white">All new users will be activated upon admin approval only</font></h1>
<form name="RegisterAccount" method="post" action="">
<table border="0" width="500" align="center" class="demo-table">
<?php if(!empty($success_message)) { ?>	
<div class="success-message"><?php if(isset($success_message)) echo $success_message; ?></div>
<?php } ?>
<?php if(!empty($error_message)) { ?>	
<div class="error-message"><?php if(isset($error_message)) echo $error_message; ?></div>
<?php } ?>
<tr>
<td>User Name</td>
<td><input type="text" class="demoInputBox" name="userName" value="<?php if(isset($_POST['userName'])) echo $_POST['userName']; ?>"></td>
</tr>

<tr>
<td>First Name</td>
<td><input type="text" class="demoInputBox" name="firstName" value="<?php if(isset($_POST['firstName'])) echo $_POST['firstName']; ?>"></td>
</tr>

<tr>
<td>Last Name</td>
<td><input type="text" class="demoInputBox" name="lastName" value="<?php if(isset($_POST['lastName'])) echo $_POST['lastName']; ?>"></td>
</tr>

<tr>
<td>Password</td>
<td><input type="password" class="demoInputBox" name="password" value=""></td>
</tr>

<tr>
<td>Confirm Password</td>
<td><input type="password" class="demoInputBox" name="confirm_password" value=""></td>
</tr>

<tr>
<td>Email</td>
<td><input type="text" class="demoInputBox" name="userEmail" value="<?php if(isset($_POST['userEmail'])) echo $_POST['userEmail']; ?>"></td>
</tr>

<tr>
<td>Cell Phone Number (xxx-xxx-xxxx)</td>
<td><input type="tel" pattern="^\d{10}$" class="demoInputBox" name="userPhone" value="<?php if(isset($_POST['userPhone'])) echo $_POST['userPhone']; ?>"></td>
</tr>

<tr>
<td>Date of Birth</td>
<td><input type="date" class="demoInputBox" name="userDOB" value="<?php if(isset($_POST['userDOB'])) echo $_POST['userDOB']; ?>"></td>
</tr>

<tr>
<td>Gender</td>
<td><input type="radio" name="gender" value="M" <?php if(isset($_POST['gender']) && $_POST['gender']=="M") { ?>checked<?php  } ?>> Male
<input type="radio" name="gender" value="F" <?php if(isset($_POST['gender']) && $_POST['gender']=="F") { ?>checked<?php  } ?>> Female
</td>
</tr>

<tr>
<td colspan=2>
<input type="checkbox" name="terms"> I accept Terms and Conditions <input type="submit" name="register-user" value="Register" class="btnRegister"></td>
</tr>
</table>
</form>
</section>
</article>
<footer>
	<h2 align="center"><font color="white">Created by Chris, Jared, Sahar, Barath</font></h2>

</footer>
</body>
</html>