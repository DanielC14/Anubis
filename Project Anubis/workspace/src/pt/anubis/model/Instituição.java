package pt.anubis.model;


public class Instituição{
	
	
	private String nome;
	private String codigo;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Instituição(String codigo, String nome) {
		super();
		this.nome = nome;
		this.codigo = codigo;
	}

	
	
	
	
	
	

}
