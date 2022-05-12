function loadInfo() {
    let info = "";
    var d = document.getElementById("cliente");
    fetch('/ContaUtilizador', {
        method: 'POST'
    })
            .then((res) => {

                if (res.status === 200)
                    return res.json();
                else
                    throw Error("Erro no servidor!!");
            })
            .then((data) => {
                var s = Object.keys(data.cliente).length;
                for (j = 1; j <= s; j++) {
                    info = info + `
                    <input id="email" name="email" type="email" placeholder="${data.cliente[j].email}" disabled/>
                    <input id="nome" name="nome" type="text" placeholder="${data.cliente[j].nome}" disabled/>                       
                    <input id="password" name="password" type="password" placeholder="${data.cliente[j].password}" disabled/>                       
                    <input id="username" name="username" type="text"  placeholder="${data.cliente[j].username}" disabled/>
                    <input id="phone" name="phone" type="tel" placeholder="${data.cliente[j].phone}" disabled />
                    <input id="nif" name="nif" type="number" placeholder="${data.cliente[j].nif}" disabled/>
                    <input id="modelo" name="modelo" type="text" placeholder="${data.cliente[j].modelo}" disabled/>
                    <input id="marca" name="marca" type="text" placeholder="${data.cliente[j].marca}" disabled/>                       
                    <input id="licenseplate" type="text" name="licenseplate" class="licenseplate" placeholder="${data.cliente[j].matricula}" disabled/>
                    <input id="plano" name="plano" type="text" placeholder="${data.cliente[j].plano}" disabled/>                       
                    <input id="lugar" name="lugar" type="text" placeholder="${data.cliente[j].lugar}" disabled/>                       
                    <input id="titular" name="titular" type="text"  placeholder="${data.cliente[j].titular}" disabled/> 
                    <input id="nrCartao" name="nrCartao" type="number" placeholder="${data.cliente[j].nrCartao}" disabled/> 
                    <input type="button" class="btn" value="Alterar dados" />`

                }
                d.innerHTML = info;
            })

            .catch((err) => console.log(err));

}
function verLocalidade() {

    if (document.getElementById("localidade").value === "Default") {
        listarEventos();
    } else {

        let form = document.getElementById('verAsLocalidades');
        let formdata = new FormData(form);
        var evento = "";
        fetch('/verLocalidade', {
            method: 'POST',
            body: formdata
        })
                .then((res) => {
                    if (res.status === 200)
                        return res.json();
                    else
                        throw Error("Erro no servidor!!");
                })
                .then((data) => {
                    document.getElementById("AllEvents").innerHTML = null;
                    var s = Object.keys(data).length;
                    for (j = 1; j <= s; j++) {

                        evento = evento + `
                         <article class='evento1'>
                             <div class='descricaoEvento'>
                            <div id="botaoColecoes" class="botaoColecoes" onclick="abrirFormulario(${data[j].nomeEvento},${data[j].colecoes})"> Colecões </div> 
                                   <br> 
                                    <h3 id='nomeEvento1'>${data[j].nomeEvento}</h3> <br>                        
                                 <b>Data</b>  <div id='data1'>${data[j].dataEvento}</div>
                                 <br><b>Hora</b><div id='hora1'>${data[j].horaEvento}</div>
                                 <br><b>Local</b> <div id='local1'>${data[j].localEvento}</div>
                                 <br><b>Recomendações:</b>${data[j].recomendacao}<br> 
                                 <b></b>
                                 </div> 
                                 </article>`;
                    }

                    document.getElementById('AllEvents').innerHTML = evento;
                })
                .catch((err) => console.log(err));
    }
}
function getPeca() {
    var pathname = window.location.pathname.split("/");
    let idPeca = pathname[pathname.length - 1 ];
    let peca = "";
    fetch('/peca/' + idPeca, {
        method: 'POST'
    })
            .then((res) => {
                
                if (res.status === 200)
                    return res.json();
                else
                    throw Error("Erro no servidor!!");
            })
            .then((data) => {
                var str = data.peca.imagemPeca;
                var res = str.replace("webrootimages", "images/");
                peca = `
                  <div class="left">                
                <div class='box4'>
                    <div class='Miniatura1'> 
                        <h1 class="titulos tituloPos"> ${data.peca.nomePeca} </h1>
                        <img src="../${res}" alt="" class="img tituloPos" />
                    </div>
                </div>
            </div>

            <div class="right">
                <p class="textos">
                    <b> Coleção:</b> ${data.peca.nomeColecao}
                    <br/><br/>
                    <b> Colecionador:</b>${data.peca.proprietarioColecao}
                    <br/><br/>
                    <b>Data da Peça:</b> ${data.peca.dataPeca}
                    <br/><br/>
                    <b>Data de Aquisição da Peça:</b> ${data.peca.dataAquisicaoPeca}
                    <br/><br/>
                    <b>Importância:</b> ${data.peca.importanciaPeca}
                    <br/><br/>
                    <b>Peso:</b> ${data.peca.pesoPeca} kg
                    <br/><br/>
                    <b>Valor:</b> ${data.peca.valorPeca} euros
                    <br/><br/>
                </p>
            </div>`;

            document.getElementById("container").innerHTML=peca;

            })
            .catch((err) => console.log(err));
}