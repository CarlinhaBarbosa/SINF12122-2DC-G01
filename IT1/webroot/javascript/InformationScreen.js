function openOption() {
    if (document.getElementById("myDropdown").value == "0") {
        document.getElementById("myDropdown").style.display = "block";
        document.getElementById("myDropdown").value = "1";
        console.log(document.getElementById("myDropdown").value);
    } else {
        document.getElementById("myDropdown").style.display = "none";
        document.getElementById("myDropdown").value = "0";
        console.log(document.getElementById("myDropdown").value);
    }
}
function InfoUser() {
    var pathname = window.location.pathname.split("/");
    let id = pathname[pathname.length - 1 ];
    let cliente = "";
    var d = document.getElementById("cliente");
    fetch('/cliente/' + id, {
        method: 'POST'
    })
            .then((res) => {

                if (res.status === 200)
                    return res.json();
                else
                    throw Error("Erro no servidor!!");
            })
            .then((data) => {
                console.log(data);
                var s = Object.keys(data.cliente).length;
                console.log(data.cliente.id);
                cliente = cliente + `
                    
                    <div class="profile tabShow">

                    <h1>Personal Info </h1>
                    <input type="hidden" class="input" name="numero" id="numero" value="${data.cliente.id}" >
                    <h2>Username </h2>
                    <input type="text" class="input" name="username" id="username"  value="${data.cliente.username}" >
                    <h2>Email </h2>
                    <input type="text" class="input" name="email" id="email"  value="${data.cliente.email}" >
                    <h2>Phone </h2>
                    <input type="text" class="input" name="phone" id="phone"  value="${data.cliente.telemovel}" >  
                    <h2>Nif </h2>
                    <input type="text" class="input" name="nif" id="nif"  value="${data.cliente.nif}" > 
                    <h2>password </h2>
                    <input type="text" class="input"  name="password" id="password"  value="${data.cliente.password}">  

                    <div class="Payment Info tabShow">
                        <h1>Other Info </h1>
                        <h2>Plan </h2>
                        <input type="text" class="input" name="plano" id="plano"  value="${data.cliente.plano}" >
                        <h2>Nome </h2>
                        <input type="text" class="input" name="nome" id="nome"  value="${data.cliente.nome}" >
                    </div>  
                    <div class="vehicle tabShow">
                        <h1>Vehicle Info </h1>
                        <h2>Brand </h2>
                        <input type="text" class="input"  name="marca" id="marca"  value="${data.cliente.marca}" >
                        <h2>Model </h2>
                        <input type="text" class="input" name="modelo" id="modelo"  value="${data.cliente.modelo}" >
                        <h2>Matricula </h2>
                        <input type="text" class="input"  name="matricula" id="matricula"  value="${data.cliente.matricula}" >
                        <h2>Lugar </h2>
                        <input type="text" class="input" name="lugar" id="lugar"  value="${data.cliente.lugar}" >
                        <p></p>
                        <button class="bt" onclick="alterarDados()">Alterar</button>
                    </div> 
                </div>`;


                d.innerHTML = cliente;


            })
            .catch((err) => console.log(err));
}
function OpenInfoUser() {
    var pathname = window.location.pathname.split("/");
    let id = pathname[pathname.length - 1 ];
    window.location.assign('/InfoUser/' + id);

}

function alterarDados() {
   
    let form = document.getElementById('cliente');
    let formdata = new FormData(form);
    fetch('/alterarUser', {
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
            console.log(err),);
            
}