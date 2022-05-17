
function registarCliente() {
//    if (validarRegistoCliente() !== false) {
        adicionarUtilizador();
//    }
}


function adicionarUtilizador() {
    let form = document.getElementById('registo');
    let formdata = new FormData(form);
    fetch('/addUtilizador', {
        method: 'POST',
        body: formdata
    })
            .then((res) => {
                if (res.status === 201)
                    return res.json();
                else
                 window.alert("O username ou email do utilizador inserido já existe!");
                throw Error("Erro no servidor!!");
            })
            .then((data) => {
                document.getElementById("registo").submit();
           
            })
            .catch((err) => 
            console.log(err),
            document.getElementById("email").value="",
            document.getElementById("nome").value="",
            document.getElementById("password").value="",
            document.getElementById("username").value="",
            document.getElementById("password").value="",
            document.getElementById("phone").value="",
            document.getElementById("nif").value="",);   
}
function registarViatura() {
    let form = document.getElementById('registoV');
    let formdata = new FormData(form);
    fetch('/addViatura', {
        method: 'POST',
        body: formdata
    })
            .then((res) => {
                if (res.status === 201)
                    return res.json();
                else
                 window.alert("A matrícula do utilizador inserida já existe!");
                throw Error("Erro no servidor!!");
            })
            .then((data) => {
                document.getElementById("registoV").submit();
                window.location.href='index.html';
            })
            .catch((err) => 
            console.log(err),
            document.getElementById("modelo").value="",
            document.getElementById("marca").value="",
            document.getElementById("licenseplate").value="",);   
}

//function validarRegistoCliente() {
//    var tamanhoMinimoUserName = 5;
//    var tamanhoMinimoPassword = 7;
//    var qtdMinimaNumerosPassword = 1;
//    var qtdMinimaLetrasMaiusculas = 2;
//    var numero = 0;
//    var letraMaiscula = 0;
//    var userName = document.getElementById("usernameRegisto").value;
//    var password = document.getElementById("passwordRegisto").value;
//
//    if (userName.length < tamanhoMinimoUserName) {
//        alert("Insira o nome de usuário com pelo menos " + tamanhoMinimoUserName + " caracteres");
//        return false;
//    }
//
//    if (password.length < tamanhoMinimoPassword) {
//
//        alert("Password demasiado pequena! Mínimo de " + tamanhoMinimoPassword + " caracteres");
//        return false;
//    }
//    for (var i = 0; i < password.length; i++) {
//        var valorAscii = password.charCodeAt(i);
//
//
//        if (valorAscii >= 65 && valorAscii <= 90)
//            letraMaiscula++;
//
//
//        if (valorAscii >= 48 && valorAscii <= 57)
//            numero++;
//    }
//    if (numero < qtdMinimaNumerosPassword) {
//        alert("Número mínimo de número na palavra passe: " + qtdMinimaNumerosPassword);
//        document.getElementById("passwordRegisto").value = "";
//        document.getElementById("passwordConfirmacao").value = '';
//        return false;
//    }
//    if (letraMaiscula < qtdMinimaLetrasMaiusculas) {
//        alert("Número mínimo de maiúsculas na palavra passe: " + qtdMinimaLetrasMaiusculas);
//        document.getElementById("passwordConfirmacao").value = '';
//        document.getElementById("passwordRegisto").value = "";
//        return false;
//    }
//    if (password !== document.getElementById("passwordConfirmacao").value) {
//        document.getElementById("passwordConfirmacao").value = '';
//        document.getElementById("passwordRegisto").value = '';
//        alert("Password não coincide com confirmação");
//        return false;
//    }
//}