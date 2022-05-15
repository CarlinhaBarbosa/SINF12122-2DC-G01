function loadEstat() {
    let info = "";
    var d = document.getElementById("stats");
    fetch('/ListarStats', {
        method: 'POST'
    })
            .then((res) => {

                if (res.status === 200)
                    return res.json();
                else
                    throw Error("Erro no servidor!!");
            })
            .then((data) => {
              
                info=`
                     <div>
                <div class="panel">
                    <div class="panel-header">
                        <h3 class="title1">Statistics</h3>


                    </div>

                    <div class="panel-body">
                        <div class="categories">
                            <div class="category">
                                <span>Histórico de Entradas e Saídas</span>
                                <span id="Historico" name=""></span>
                            </div>
                            <div class="category">
                                <span>Número de Modelos Iguais - Volvo</span>
                                <span>${data.stats.NumeroModelo}</span>
                            </div>
                            <div class="category">
                                <span>Número de Reservas Totais</span>
                                <span>${data.stats.NumeroReservas}</span>
                            </div>
                            <div class="category">
                                <div>Número de Viaturas no Parque em Tempo Real</div>
                                <span></span>
                            </div>
                        </div>
                       
                        <div class="chart"> <div class="titulo">Número de Planos Escolhidos</div>
                            <div class="operating-systems">
                                <span class="ios-os">
                                    <span></span>Ocasional
                                </span>
                                <span class="windows-os">
                                    <span></span>Diário
                                </span>
                                <span class="android-os">
                                    <span></span>Reservado
                                </span>
                            </div>
                            <div class="android-stats">
                                <span></span>
                            </div>
                            <div class="ios-stats">
                                <span></span>
                            </div>
                            <div class="windows-stats">
                                <span></span>
                            </div>


                        </div>
                    </div>
                </div>
            </div> `;

                
                d.innerHTML = info;
            })

            .catch((err) => console.log(err));

}
