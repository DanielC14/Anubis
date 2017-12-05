package pt.anubis.model;


public class Objeto {
	
	private String codigoObjeto;
	private String nome;
	private String email;
	private String bloco;
	private String sala;
	private String data;
	private String hora;
	private String tipoDeObjeto;
	private TipoObjeto tipoObjeto;
	private Sala ssala;
	private Sala bbloco;
	
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	private String cor;
	private String estado;
	private String descricao;
	
	
	public String getCodigoObjeto() {
		return codigoObjeto;
	}
	public void setCodigoObjeto(String codigoObjeto) {
		this.codigoObjeto = codigoObjeto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getTipoDeObjeto() {
		return tipoDeObjeto;
	}
	public void setTipoDeObjeto(String codigoTipoObjeto) {
		this.tipoDeObjeto = codigoTipoObjeto;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Objeto(String codigoObjeto, String nome, String email, String bloco, String sala, String data, String hora,
			String tipoDeObjeto, String cor, String estado, String descricao) {
		super();
		this.codigoObjeto = codigoObjeto;
		this.nome = nome;
		this.email = email;
		this.bloco = bloco;
		this.sala = sala;
		this.data = data;
		this.hora = hora;
		this.tipoDeObjeto = tipoDeObjeto;
		this.cor = cor;
		this.estado = estado;
		this.descricao = descricao;
	}
	
	
	
	
	
	
	
	
	

}
