function login() {
    var username=document.getElementById("userlogin").value;
     var password=document.getElementById("passwordlogin").value;
     console.log("user:"+ username);
      console.log("pass:"+ password);
                if(username==="admin" && password==="admin"){
                    window.location.assign("../GestorScreen");
                    }else if(username==="geronimosousa" && password==="stilton"){
                        window.location="../HTML/ClienteScreen.html";
                    }
                    
                
            };