function openOption() {
  if (document.getElementById("myDropdown").value == "0") {
    document.getElementById("myDropdown").style.display ="block";
    document.getElementById("myDropdown").value = "1";
    console.log(document.getElementById("myDropdown").value);
  }
  else {
    document.getElementById("myDropdown").style.display ="none";
    document.getElementById("myDropdown").value="0";
    console.log(document.getElementById("myDropdown").value);
  }
}



