document.addEventListener('keydown', function (event) {
	if (event.code.startsWith('F')) {
		event.preventDefault();
	}
});

document.addEventListener('keydown', function (event) {

	if (event.code === 'F1') {
		document.getElementById('btncambiarModoBusquedaCodigo').click()
		event.preventDefault();

	}
	if (event.code === 'F2') {
		document.getElementById('btncambiarModoBusquedaNombre').click();
		event.preventDefault();
	}
	if (event.code === 'F5') {
		location.reload();
		event.preventDefault();
	}
});