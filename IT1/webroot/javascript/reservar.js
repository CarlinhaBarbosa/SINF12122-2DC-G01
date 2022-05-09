function place(zona){
  var checkBox = document.getElementById(zona);
  lugarA = document.getElementById("lugarA");
  lugarB = document.getElementById("lugarB");
  lugarC = document.getElementById("lugarC");
  lugarD = document.getElementById("lugarD");
  lugarE = document.getElementById("lugarD");
  lugarF = document.getElementById("lugarD");
    if (checkBox.checked === true){
        switch(zona){
            case 'za':
                lugarA.style.display = "block";
                lugarB.style.display = "none";
                lugarC.style.display = "none";
                lugarD.style.display = "none";
                lugarE.style.display = "none";
                lugarF.style.display = "none";
            case 'zb':
                lugarA.style.display = "none";
                lugarB.style.display = "block";
                lugarC.style.display = "none";
                lugarD.style.display = "none";
                lugarE.style.display = "none";
                lugarF.style.display = "none";
            case 'zc':
                lugarA.style.display = "none";
                lugarB.style.display = "none";
                lugarC.style.display = "block";
                lugarD.style.display = "none";
                lugarE.style.display = "none";
                lugarF.style.display = "none";
            case 'zd':
                lugarA.style.display = "none";
                lugarB.style.display = "none";
                lugarC.style.display = "none";
                lugarD.style.display = "block";
                lugarE.style.display = "none";
                lugarF.style.display = "none";
            case 'ze':
                lugarA.style.display = "none";
                lugarB.style.display = "none";
                lugarC.style.display = "none";
                lugarD.style.display = "none";
                lugarE.style.display = "block";
                lugarF.style.display = "none";
            case 'zf':
                lugarA.style.display = "none";
                lugarB.style.display = "none";
                lugarC.style.display = "none";
                lugarD.style.display = "none";
                lugarE.style.display = "none";
                lugarF.style.display = "block";
            default:
                lugarA.style.display = "none";
                lugarB.style.display = "none";
                lugarC.style.display = "none";
                lugarD.style.display = "none";
                lugarE.style.display = "none";
                lugarF.style.display = "none";
        }
    }else {
        lugarA.style.display = "none";
        lugarB.style.display = "none";
        lugarC.style.display = "none";
        lugarD.style.display = "none";
        lugarE.style.display = "none";
        lugarF.style.display = "none";
    }
}