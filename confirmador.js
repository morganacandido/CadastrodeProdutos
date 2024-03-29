/**
 * Confirmação de exclusão de um contato
 */
 
 function confirmar(idprod){
	let resposta = confirm("Confirma a exclusão desse produto?")
	if(resposta === true){
		window.location.href = "delete?idprod=" + idprod
	}
}