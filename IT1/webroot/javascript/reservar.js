function myFunction() {
  var checkBox = document.getElementById("ZonaA");
  var lugarA = document.getElementById("lugarA");
  if (checkBox.checked == true){
    lugarA.style.display = "block";
  } else {
     lugarA.style.display = "none";
  }
}