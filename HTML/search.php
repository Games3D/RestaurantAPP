<html>
<head>
	<title>Welcome To Search</title>
	<link rel="stylesheet" type="text/css" href="CSS/Main.css">
	<link rel="stylesheet" type="text/css" href="CSS/Home.css">
	<link rel="stylesheet" type="text/css" href="CSS/search.css">
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

<?php include 'Functions/navbar_main.php';?>

<body>
<font size="8px">
<div class="search-area-wrapper" align="center">
            <div class="search-area container" align="center">
                                <h3 class="search-header" align="center"><font color="white">Start Your Journey Today!</font></h3>
                <p class="search-tag-line" align="center"><font color="white">Search a starting and an ending point to begin the search!</font></p>
<br>
                <form action="Home.php" method="get" align="center">
	Starting Point: <input type="text" name="start"><br>
Ending Point: <input type="text" name="end"><br>
<input type="submit">
</form>

            </div>
            <br>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3022.8950644970128!2d-74.18152958436995!3d40.742334479328676!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c2537d98c396f9%3A0xb97c287a2ef95f43!2sNew+Jersey+Institute+of+Technology!5e0!3m2!1sen!2sus!4v1521501511048" 
            width="800" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        </font>
	<br>
	<br>


</body>
</html>