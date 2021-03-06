<html>
<head>
	<title>Welcome Home</title>
	<link rel="stylesheet" type="text/css" href="CSS/Main.css">
	<link rel="stylesheet" type="text/css" href="CSS/Home.css">
	
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
 
 //get request for stock info
		$urlFirst = 'https://web.njit.edu/~jp834/webapps8/Restaurant.jsp?PARAMS=1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|jared';//.$_GET['symbol'];
		$contentsFirst = file_get_contents($urlFirst);
		//If $contents is not a boolean FALSE value.
		if($contentsFirst == false){
			$_SESSION["ERROR"] = 'Get request error';
			header('Location: Error.php');
			return;
		}
		$DATA= explode("~", $contentsFirst);
	//echo $DATA[0];
  ?>
 
<body>

<?php include 'Functions/navbar_main.php';?>

<body>
<br>
	<br>
	<br>
	<div id="map"></div>
    <div id="right-panel">
    <div>
    <b>Start:</b>
    <select id="start">
      <option value="<?php echo $_GET["start"];?>"><?php echo $_GET["start"]; ?></option>
    </select>
    <br>
      
    <b>Waypoints:</b> <br>
    <i>(Ctrl+Click or Cmd+Click for multiple selection)</i> <br>
    <select multiple id="waypoints">
    
     <?php 
		//echo $DATA[0];
	
		foreach($DATA as $line){
			$DATAR= explode("`", $line);
		//	echo "d";//$DATAR[0];			
		  echo('<option value="'.$DATAR[3].'">'.$DATAR[0].'</option>');
		//}
     ?>
     
      <!--<option value="montreal, quebec">Montreal, QBC</option>
      <option value="toronto, ont">Toronto, ONT</option>
      <option value="chicago, il">Chicago</option>
      <option value="winnipeg, mb">Winnipeg</option>
      <option value="fargo, nd">Fargo</option>
      <option value="calgary, ab">Calgary</option>
      <option value="spokane, wa">Spokane</option>-->
      
      
    </select>
    <br>
    <b>End:</b>
    <select id="end">
      <option value="<?php echo $_GET["end"]; ?>"><?php echo $_GET["end"];?></option>
      
    </select>
    <br>
      <input type="submit" id="submit">
    </div>
    <div id="directions-panel"></div>
    </div>
    <script>
      function initMap() {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: {lat: 41.85, lng: -87.65}
        });
        directionsDisplay.setMap(map);
        document.getElementById('submit').addEventListener('click', function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        });
      }
      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        var waypts = [];
        var checkboxArray = document.getElementById('waypoints');
        for (var i = 0; i < checkboxArray.length; i++) {
          if (checkboxArray.options[i].selected) {
            waypts.push({
              location: checkboxArray[i].value,
              stopover: true
            });
          }
        }
        directionsService.route({
          origin: document.getElementById('start').value,
          destination: document.getElementById('end').value,
          waypoints: waypts,
          optimizeWaypoints: true,
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
              var routeSegment = i + 1;
              summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment +
                  '</b><br>';
              summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
              summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
              summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCS-aIwl2FrJkJGwfO0K-DllGxnVkMvMmg&callback=initMap">
    </script>
  </body>
  </body>
</html>