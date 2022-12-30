function cambiarModoBusquedaNombre() {

    document.getElementById('buscarNombre').style.display = 'block'
    document.getElementById('buscarCodigo').style.display = 'none'

}

function cambiarModoBusquedaCodigo() {

    document.getElementById('buscarCodigo').style.display = 'block'
    document.getElementById('buscarNombre').style.display = 'none'
}

function cambio() {
    let entrada = document.getElementById('entrada').value
    let total = document.getElementById('total').value
    document.getElementById('entrega').value =  document.getElementById('entrada').value

    document.getElementById('cambio').value = parseFloat(entrada) - parseFloat(total)
}