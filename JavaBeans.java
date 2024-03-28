package model;

public class JavaBeans {
	private String idprod;
	private String nome;
	private String quantidade;
	private String valor;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String idprod, String nome, String quantidade, String valor) {
		super();
		this.idprod = idprod;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
