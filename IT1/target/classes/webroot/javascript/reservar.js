function reserva() {
    let form = document.getElementById('formularioReserva');
    let formdata = new FormData(form);
    fetch('/addReserva', {
        method: 'POST',
        body: formdata
    })
    .then((res) => {
        if (res.status === 200)
            return res.json();
        else
            throw Error("Erro no servidor!!");
        })
        .then((data) => {})
        .catch((err) => console.log(err))
}