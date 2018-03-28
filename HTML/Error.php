<html>
<head>
<title>ERROR...</title>
<link rel="stylesheet" type="text/css" href="CSS/login.css">
<script>
	$("#nav").addClass("js").before('<div id="menu">&#9776;</div>');
	$("#menu").click(function(){
		$("#nav").toggle();
	});
	$(window).resize(function(){
		if(window.innerWidth > 700) {
			$("#nav").removeAttr("style");
		}
	});
	</script>
<?php
	require_once 'DBconnect.php';
	session_start();
?>
</head>

<body>
<table border="0" width="500" align="center" class="demo-table">
 <tbody>
<tr>
<td align="center">SERVER ERROR</td>
		</tr>
<tr>
<td align="center">
	<?php 
		session_start(); 
		if (isset($_SESSION["ERROR"]))
			echo $_SESSION["ERROR"];
		else
			echo "No errors found, please return home.";

	?>
	</table>
<footer>
</footer>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>


