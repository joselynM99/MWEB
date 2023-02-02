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
	document.getElementById('entrega').value = document.getElementById('entrada').value
	document.getElementById('cambio').value = parseFloat(entrada) - parseFloat(total)
}

function contarEfectivo() {
	let b100 = parseInt(document.getElementById('100').value)
	let b50 = parseInt(document.getElementById('50').value)
	let b20 = parseInt(document.getElementById('20').value)
	let b10 = parseInt(document.getElementById('10').value)
	let b5 = parseInt(document.getElementById('5').value)
	let b2 = parseInt(document.getElementById('2').value)
	let b1 = parseInt(document.getElementById('1').value)

	let m100 = parseInt(document.getElementById('1.00').value)
	let m050 = parseInt(document.getElementById('0.50').value)
	let m025 = parseInt(document.getElementById('0.25').value)
	let m010 = parseInt(document.getElementById('0.10').value)
	let m005 = parseInt(document.getElementById('0.05').value)
	let m001 = parseInt(document.getElementById('0.01').value)

	let cb100 = b100 * 100;
	let cb50 = b50 * 50;
	let cb20 = b20 * 20;
	let cb10 = b10 * 10;
	let cb5 = b5 * 5;
	let cb2 = b2 * 2;
	let cb1 = b1 * 1;

	let cm1 = m100 * 1
	let cm50 = m050 * 0.50
	let cm25 = m025 * 0.25
	let cm10 = m010 * 0.10
	let cm05 = m005 * 0.05
	let cm01 = m001 * 0.01

	console.log(cb100 + cb50 + cb20 + cb10 + cb5 + cb2 + cb1 + cm1 + cm50 + cm25 + cm10 + cm05 + cm01)
	document.getElementById('valorContable').value = cb100 + cb50 + cb20 + cb10 + cb5 + cb2 + cb1 + cm1 + cm50 + cm25 + cm10 + cm05 + cm01;
	calcularValorFaltante()
}

function calcularValorFaltante() {
	let saldoFinal = parseFloat(document.getElementById('saldoFinal').textContent)
	let efectivo = parseFloat(document.getElementById('valorContable').value)
	let valor = saldoFinal - efectivo

	console.log(saldoFinal)

	if (valor < 0) {
		document.getElementById('valorFS').innerHTML = 'Valor Sobrante'
		document.getElementById('valorFaltante').value = -valor
	} else {
		document.getElementById('valorFS').innerHTML = 'Valor Faltante'
		document.getElementById('valorFaltante').value = valor
	}

	document.getElementById('pValCont').value = efectivo
	document.getElementById('pValCierre').value = saldoFinal
	document.getElementById('pValDif').value = valor


}

function descuento() {
	let selected = document.getElementById('porDes').checked

	let total = parseFloat(document.getElementById('total').value)
	let descuento = parseFloat(document.getElementById('valDes').value)

	if (selected == true) {
		total = total - (total * descuento / 100)
	} else {
		total = total - descuento
	}

	document.getElementById('total').value = total


}

function cliente() {
	console.log(document.getElementById("cliente").value)
	document.getElementById("cliente").value = document.getElementById("seC").value
}


function calcularPrecioVenta(){
	
	let impuesto = document.getElementById("list_impuesto").value
	let costo = document.getElementById("costo").value
	console.log(impuesto)
	console.log(costo)
	if(impuesto==1){
		document.getElementById("precioV").value= parseFloat(costo) + parseFloat(costo*12/100)
		
		console.log(costo*12/100)
	}else{
		document.getElementById("precioV").value=costo
	}
	
}

function calcularPrecioSubProducto(){
	let costoProducto= document.getElementById("costoProd").value
	let cantidadR = document.getElementById("cantidadR").value
	console.log("cantidadR")
	console.log("costoProd")
	if(costoProducto!=null){
		document.getElementById("costo").value=parseFloat(costoProducto)/parseFloat(cantidadR)
	}
	
	calcularPrecioVenta()
}
