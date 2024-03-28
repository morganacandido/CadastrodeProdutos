package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	// **Modulo de conexão
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.01:3306/dbcadastro?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "mo751*Can!d1d0";

	// método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD CREATE **/
	public void inserirProduto(JavaBeans produto) {
		String create = "insert into produtos (nome, quantidade, valor) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros pelos conteudos das variaveis JavaBeans
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getQuantidade());
			pst.setString(3, produto.getValor());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o Banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READY **/
	public ArrayList<JavaBeans> listarProdutos() {
		// criando um objeto para acessar a classe Javabeans
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from produtos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver produtos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idprod = rs.getString(1);
				String nome = rs.getString(2);
				String quantidade = rs.getString(3);
				String valor = rs.getString(4);
				// populando o ArrayList
				produtos.add(new JavaBeans(idprod, nome, quantidade, valor));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// CRUD UPDATE
	//SELECIONAR PRODUTO
	public void selecionarProduto(JavaBeans produto) {
		String read2 = "select * from produtos where idprod =?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, produto.getIdprod());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				produto.setIdprod(rs.getString(1));
				produto.setNome(rs.getString(2));
				produto.setQuantidade(rs.getString(3));
				produto.setValor(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//Editar produto
	public void alterarProduto(JavaBeans produto) {
		String create  = "update produtos set nome=?,quantidade=?,valor=? where idprod=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getQuantidade());
			pst.setString(3, produto.getValor());
			pst.setString(4, produto.getIdprod());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarProduto(JavaBeans produto) {
		String delete = "delete from produtos  where idprod=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, produto.getIdprod());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
}
