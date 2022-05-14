function loadInfo() {
    let info = "";
    var pathname = window.location.pathname.split("/");
    let nome = pathname[pathname.length - 1 ];
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
                  
                    <div class="profile tabShow">
                        <h1>Personal Info </h1>
                        <h2>Username </h2>
                        <input type="text" class="input"  id="1" disabled value="${data.cliente[j].username}" >
                        <h2>Email </h2>
                        <input type="text" class="input"  id="2" disabled value="${data.cliente[j].email}" >
                        <h2>Phone </h2>
                        <input type="text" class="input"  id="3" disabled value="${data.cliente[j].phone}" >  
                        <h2>Nif </h2>
                        <input type="text" class="input"  id="4" disabled value="${data.cliente[j].nif}" > 
                        <h2>password </h2>
                        <input type="text" class="input"  id="5" disabled value="${data.cliente[j].password}">  
                       
                    <div class="Payment Info tabShow">
                        <h1>Payment Info </h1>
                        <h2>Plan </h2>
                        <input type="text" class="input"  id="6" disabled value="${data.cliente[j].plano}" >

                    </div>  
                    <div class="vehicle tabShow">
                        <h1>Vehicle Info </h1>
                        <h2>Brand </h2>
                        <input type="text" class="input"  id="9" disabled value="${data.cliente[j].marca}" >
                        <h2>Model </h2>
                        <input type="text" class="input"  id="10" disabled value="${data.cliente[j].modelo}" >
                        <h2>Matricula </h2>
                        <input type="text" class="input"  id="11" disabled value="${data.cliente[j].matricula}" >
                        <h2>Lugar </h2>
                        <input type="text" class="input"  id="12" disabled value="${data.cliente[j].lugar}" >
                        <button class="bt">Update</button>
            <script src="jquery/jquery.js"></script>
                <script>
                        const tabBtn = document.querySelectorAll(".tab");
                        const tabBtn = document.querySelectorAll(".tabShow");
                        function tabs(panelIndex) {
                            tab.forEach(function (node) {
                                node.AMinhaConta.display = "none";

                            });
                            tab[panelIndex].AMinhaConta.display = "block";
                        }
                        tabs(0);
                </script>
                    `; //                        <h2>Name </h2>
//                        <input type="text" class="input"  id="7" disabled value="${data.cliente[j].nome}" >
//                        <h2>Nºcartão </h2>
//                        <input type="text" class="input"  id="8" disabled value="${data.cliente[j].nrCartao}" >  
//                        <button class="bt">Update</button>


                }
                d.innerHTML = info;
            })

            .catch((err) => console.log(err));

}
//function getColecao() {
//    var pathname = window.location.pathname.split("/");
//    let nomeColecao = pathname[pathname.length - 1 ];
//    let pecas = "";
//    fetch('/colecao/' + nomeColecao, {
//        method: 'POST'
//    })
//            .then((res) => {
//                if (res.status === 200)
//                    return res.json();
//                else
//                    throw Error("Erro no servidor!!");
//            })
//            .then((data) => {
//                document.getElementById("nomeColecaoId").innerHTML = data.colecao[1].nomeColecao;
//                document.getElementById("TipoDeColecao").innerHTML = data.colecao[1].tipoColecao;
//                document.getElementById("dataInicioColecao").innerHTML = data.colecao[1].data_inicioColecao;
//                document.getElementById("DescricaoColecao").innerHTML = data.colecao[1].descricaoColecao;
//                document.getElementById("Proprietario").innerHTML = data.colecao[1].proprietarioColecao;
//                if (data.colecao[1].ratingColecao === null) {
//                    document.getElementById("rating").innerHTML = "Sem avaliações";
//                } else {
//                    document.getElementById("rating").innerHTML = data.colecao[1].ratingColecao;
//                }
//                var s = Object.keys(data.colecao).length;
//
//                for (j = 2; j <= s; j++) {
//                    var str = data.colecao[j].imagem;
//                    var res = str.replace("webrootimages", "images/");
//                    pecas = pecas +
//                            `<div class="pecas1">
//                    <div class="imge">
//                         <img src="../${res}" alt="" class="img2" >
//                     </div>
//
//                  <div class="margem">
//
//                <b> Nome:</b>${data.colecao[j].nome_peca}
//                <br/><br/>
//                <b>Data da Peça:</b> ${data.colecao[j].data_peca}
//                <br/><br/>
//                <b>Data de Aquisição da Peça:</b> ${data.colecao[j].data_aquisicao}
//                <br/><br/>
//                <b>Importância:</b> ${data.colecao[j].importancia}
//                <br/><br/>
//                <b>Peso:</b>${data.colecao[j].peso} kg
//                <br/><br/>
//                <b>Valor:</b>${data.colecao[j].valor} Euros
//                <br/><br/>
//
//            </div>`;
//                    if (data.colecao[1].permissao === true) {
//                        document.getElementById("botaoNovoEvento").style.display = "block";
//
//                        pecas = pecas +
//                                `<div class="botoes">
//                <div id="editarinfo" > <a class ='botaoEditarPeca' onclick="AlterarPeca(${data.colecao[j].id_peca},'${data.colecao[j].nome_peca}');"> Editar informações </a>
//                </div>
//
//                <input class ='botaoEliminarPeca' type="button" onclick="Eliminar(${data.colecao[j].id_peca},${nomeColecao});" value="Eliminar" />
//            </div>`;
//
//                    }
//
//                    pecas = pecas + `</div>`;
//                }
//
//
//                document.getElementById("addcolecao").value = data.colecao[1].nomeColecao;
//                document.getElementById("pecas").innerHTML = pecas;
//
//            })
//            .catch((err) => console.log(err));
//}
//
//function getPeca() {
//    var pathname = window.location.pathname.split("/");
//    let idPeca = pathname[pathname.length - 1 ];
//    let peca = "";
//    fetch('/peca/' + idPeca, {
//        method: 'POST'
//    })
//            .then((res) => {
//
//                if (res.status === 200)
//                    return res.json();
//                else
//                    throw Error("Erro no servidor!!");
//            })
//            .then((data) => {
//                var str = data.peca.imagemPeca;
//                var res = str.replace("webrootimages", "images/");
//                peca = `
//                  <div class="left">                
//                <div class='box4'>
//                    <div class='Miniatura1'> 
//                        <h1 class="titulos tituloPos"> ${data.peca.nomePeca} </h1>
//                        <img src="../${res}" alt="" class="img tituloPos" />
//                    </div>
//                </div>
//            </div>
//
//            <div class="right">
//                <p class="textos">
//                    <b> Coleção:</b> ${data.peca.nomeColecao}
//                    <br/><br/>
//                    <b> Colecionador:</b>${data.peca.proprietarioColecao}
//                    <br/><br/>
//                    <b>Data da Peça:</b> ${data.peca.dataPeca}
//                    <br/><br/>
//                    <b>Data de Aquisição da Peça:</b> ${data.peca.dataAquisicaoPeca}
//                    <br/><br/>
//                    <b>Importância:</b> ${data.peca.importanciaPeca}
//                    <br/><br/>
//                    <b>Peso:</b> ${data.peca.pesoPeca} kg
//                    <br/><br/>
//                    <b>Valor:</b> ${data.peca.valorPeca} euros
//                    <br/><br/>
//                </p>
//            </div>`;
//
//                document.getElementById("container").innerHTML = peca;
//
//            })
//            .catch((err) => console.log(err));
//}