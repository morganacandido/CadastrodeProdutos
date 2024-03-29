/**
 * Validação de formulário
 */
 
 function validar(){
	let nome = formProduto.nome.value
	if(nome === ""){
		alert('Preencha o campo nome')
		formProduto.nome.focus()
		return false
	}else{
		document.forms["formProduto"].submit()
	}
}