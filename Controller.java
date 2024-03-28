package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;



@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans produto = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/insert")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}


	// listar produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CRiando um objeto que ira receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarProdutos();

		// Encaminhar a lista ao documento cadastro.jsp
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	// Novo produto
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("quantidade"));
		System.out.println(request.getParameter("valor"));
		// setar as variáveis JavaBeans
		produto.setNome(request.getParameter("nome"));
		produto.setQuantidade(request.getParameter("quantidade"));
		produto.setValor(request.getParameter("valor"));
		// invocar o metodo inserirProduto passando o objeto produto
		dao.inserirProduto(produto);
		// redirecionar para o documento cadastro.jsp
		response.sendRedirect("main");
	}

	// Editar produto
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do produto que sera editado
		String idprod = request.getParameter("idprod");
		System.out.println(idprod);

		// Setar a variavel JavaBeans
		produto.setIdprod(idprod);

		// Executar o métdodo selecionarProduto (DAO)
		dao.selecionarProduto(produto);

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idprod", produto.getIdprod());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("quantidade", produto.getQuantidade());
		request.setAttribute("valor", produto.getValor());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis JavaBeans
		produto.setIdprod(request.getParameter("idprod"));
		produto.setNome(request.getParameter("nome"));
		produto.setQuantidade(request.getParameter("quantidade"));
		produto.setValor(request.getParameter("valor"));
		
		// executar o método alterarProduto
		dao.alterarProduto(produto);

		// redirecionar para o documento cadastro.jsp (atualizando as alterações)
		response.sendRedirect("main");

	}
	
	// Remover um produto
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//recebimento de id do produto a ser excluido(validador.js)
		String idprod = request.getParameter("idprod");
		//setar a variavel idprod JavaBeans
		produto.setIdprod(idprod);
		//executar o método deletarProduto (DAO) passando o objeto produto
		dao.deletarProduto(produto);
		// redirecionar para o documento cadastro.jsp (atualizando as alterações)
				response.sendRedirect("main");
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo de conteúdo
			response.setContentType("apllication/pdf");
			//nome do documento
			response.addHeader("content-Disposition", "inline; filename="
					+ "produtos.pdf");
			//criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Relatório Cadastro de produtos:"));
			documento.add(new Paragraph(" "));
			//criar uma tabela
			PdfPTable tabela = new PdfPTable(4);
			//cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Quantidade em estoque"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Preço Venda"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			//preencher a tabela com os produtos
			ArrayList<JavaBeans> lista = dao.listarProdutos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getIdprod());
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getQuantidade());
				tabela.addCell(lista.get(i).getValor());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			documento.close();
		}
	}
	
	/*public String loginUsuario(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// se tentar acessar direto, sem passar pelo form, manda pro form de login.
		
		if(req.getParameter("usuario")==null && req.getParameter("senha")==null) {
			return "login.jsp";
		}
		// se o usuario e senha estao certos, redireciona pra logica de exibir Index.
		if(req.getParameter("usuario").equals("morgana") && req.getParameter("senha").equals("1234")) {
			HttpSession hs = req.getSession(); // pega a sessao ou cria uma nova, caso nao exista
			hs.setAttribute("user", req.getParameter("usuario").toUpperCase());
			return "cadastro.jsp";
		}else {
			req.setAttribute("msg", "Login/senha invalido");
			return "login.jsp";
		}
		
	}*/

}

	