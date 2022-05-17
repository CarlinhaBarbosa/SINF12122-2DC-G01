
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

