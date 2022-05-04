function place(zona){
  var checkBox = document.getElementById(zona);
    if (checkBox.checked === true){
        switch(zona){
            case 'za':
                document.getElementById("lugarA").style.display = "block";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "none";
            case 'zb':
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "block";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "none";
            case 'zc':
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "block";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "none";
            case'zd':
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "block";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "none";
            case 'ze':
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "block";
                document.getElementById("lugarF").style.display = "none";
            case 'zf':
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "block";
            default:
                document.getElementById("lugarA").style.display = "none";
                document.getElementById("lugarB").style.display = "none";
                document.getElementById("lugarC").style.display = "none";
                document.getElementById("lugarD").style.display = "none";
                document.getElementById("lugarE").style.display = "none";
                document.getElementById("lugarF").style.display = "none";
        }
    }else {
        document.getElementById("lugarA").style.display = "none";
        document.getElementById("lugarB").style.display = "none";
        document.getElementById("lugarC").style.display = "none";
        document.getElementById("lugarD").style.display = "none";
        document.getElementById("lugarE").style.display = "none";
        document.getElementById("lugarF").style.display = "none";
    }
}