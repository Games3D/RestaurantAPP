<ul>
	<li><a href="search.php" style="background-color:#333;">Home</a></li>
  <li><a href="profile.php" style="background-color:#333;">Profile</a></li>
  <li><a href="Favorites.php" style="background-color:#333;">Favorites</a></li>
  <li><a href="index.php" style="background-color:#333;">Sign out</a></li>

</ul>
<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {
    var myDropdown = document.getElementById("myDropdown");
      if (myDropdown.classList.contains('show')) {
        myDropdown.classList.remove('show');
      }
  }
}
</script>
