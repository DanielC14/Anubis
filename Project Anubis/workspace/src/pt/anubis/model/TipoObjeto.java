package pt.anubis.model;


public class TipoObjeto {
	
	private String codigo;
	private String nomeObjeto;
	private String associado;
	private Instituição is;
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeObjeto() {
		return nomeObjeto;
	}
	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}
	

	public String getAssociado() {
		return associado;
	}
	public void setAssociado(String associado) {
		this.associado = associado;
	}
	
	
	public TipoObjeto(String codigo, String nomeObjeto, String associado) {
		super();
		this.codigo = codigo;
		this.nomeObjeto = nomeObjeto;
		this.associado = associado;
	}
	public TipoObjeto(String nomeObjeto) {
		super();
		this.nomeObjeto = nomeObjeto;
	}
	
	
	
	
	
	
	
	
	
	
	

}
