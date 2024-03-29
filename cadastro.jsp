<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ page import="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList"%>
  <% ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produtos"); %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cadastro de Produtos</title>
<link rel="icon" href="Imagens/lista.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Cadastro de Produtos</h1>
	<a href= "novo.html" class="botao1">Novo produto</a>
	<a href= "report" class="botao2">Relatório</a>
	<table id="tabela">
	<thead>
		<tr>
		<th>ID</th>
		<th>NOME</th>
		<th>QUANTIDADE</th>
		<th>VALOR</th>
		<th>Opções</th>
		</tr>
	</thead>
	<tbody>
		<% for (int i = 0; i < lista.size(); i++) { %>
			<tr> 
				<td><%=lista.get(i).getIdprod()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getQuantidade()%></td>
				<td><%=lista.get(i).getValor()%></td>
				<td><a href="select?idprod=<%=lista.get(i).getIdprod()%>" class="botao1">Editar</a>
				<a href="javascript: confirmar(<%=lista.get(i).getIdprod()%>)" class="botao2">Excluir</a>
			</td>						
		</tr>
		<%} %>
	</tbody> 
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>