package pt.anubis.model;


public class Permissoes {
	
	private boolean registo;
	private boolean reclamacao;
	private boolean importacao;
	private boolean listagens;
	private boolean doacoes;
	private boolean configuracoes;
	
	
	public boolean isRegisto() {
		return registo;
	}
	public void setRegisto(boolean registo) {
		this.registo = registo;
	}
	public boolean isReclamacao() {
		return reclamacao;
	}
	public void setReclamacao(boolean reclamacao) {
		this.reclamacao = reclamacao;
	}
	public boolean isImportacao() {
		return importacao;
	}
	public void setImportacao(boolean importacao) {
		this.importacao = importacao;
	}
	public boolean isListagens() {
		return listagens;
	}
	public void setListagens(boolean listagens) {
		this.listagens = listagens;
	}
	public boolean isDoacoes() {
		return doacoes;
	}
	public void setDoacoes(boolean doacoes) {
		this.doacoes = doacoes;
	}
	public boolean isConfiguracoes() {
		return configuracoes;
	}
	public void setConfiguracoes(boolean configuracoes) {
		this.configuracoes = configuracoes;
	}
	
	public Permissoes(boolean registo, boolean reclamacao, boolean importacao,
			boolean listagens, boolean doacoes, boolean configuracoes) {
		super();
		this.registo = registo;
		this.reclamacao = reclamacao;
		this.importacao = importacao;
		this.listagens = listagens;
		this.doacoes = doacoes;
		this.configuracoes = configuracoes;
	}
	
	
	
	
	

}
