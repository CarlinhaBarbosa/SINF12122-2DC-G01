function loadInfo() {
    let info = "";
    var d = document.getElementById("users");
    fetch('/ListarClientes', {
        method: 'POST'
    })
            .then((res) => {

                if (res.status === 200)
                    return res.json();
                else
                    throw Error("Erro no servidor!!");
            })
            .then((data) => {
                var s = Object.keys(data.clientes).length;
                for (j = 1; j <= s; j++) {
                    info = info + `
                     <tr>
                        <td>${data.clientes[j].nome}</td>
                        <td>${data.clientes[j].matricula}</td>
                        <td>${data.clientes[j].telemovel}</td>
                        <td>${data.clientes[j].email}</td>
                        <td>${data.clientes[j].nif}</td>
                        <td>${data.clientes[j].lugar}</td>
                        <td>${data.clientes[j].plano}</td>
                    </tr>`

                }
                d.innerHTML = info;
            })

            .catch((err) => console.log(err));

}
function SendFile(){
    let info = "";
    
    var form = document.getElementById("Leitor");
    let formdata = new FormData(form);
    fetch('/SendFile', {
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
                 document.getElementById("Leitor").submit();
            })

            .catch((err) => 
            console.log(err));
    }
    