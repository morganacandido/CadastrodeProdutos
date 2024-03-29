<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<title>Cadastro de Produtos</title>
<link rel="icon" href="Imagens/lista.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Produto</h1>
	<form name="formProduto" action="update">
		<table>
			<tr>
			<td><input type="text" name="idprod"
					id="caixa3" readonly value="<%out.print(request.getAttribute("idprod"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome"
					class="caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="number" name="quantidade"
					 class="caixa1" value="<%out.print(request.getAttribute("quantidade"));%>"></td>
			</tr>
			<tr>
				<td><input type="number" name="valor" 
					class="caixa1" value="<%out.print(request.getAttribute("valor"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="botao1"
			onclick="validar()">
	</form>
	<script src="script/validador.js"></script>
</body>
</html>

