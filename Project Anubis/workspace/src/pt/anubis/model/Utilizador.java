package pt.anubis.model;

import java.util.ArrayList;

public class Utilizador {
	
	private String tipoUtilizador;
	private String nome;
	private String utilizador;
	private String password;
	private Permissoes registo;
	private Permissoes reclamacao;
	private Permissoes importacao;
	private Permissoes listagem;
	private Permissoes doacoes;
	private Permissoes configuracoes;
	
	public static ArrayList<Permissoes> perms = new ArrayList<Permissoes>();
	
	
	
	public static ArrayList<Permissoes> getPerms() {
		return perms;
	}
	public static void setPerms(ArrayList<Permissoes> perms) {
		Utilizador.perms = perms;
	}
	public Permissoes getRegisto() {
		return registo;
	}
	public void setRegisto(Permissoes registo) {
		this.registo = registo;
	}
	public Permissoes getReclamacao() {
		return reclamacao;
	}
	public void setReclamacao(Permissoes reclamacao) {
		this.reclamacao = reclamacao;
	}
	public Permissoes getImportacao() {
		return importacao;
	}
	public void setImportacao(Permissoes importacao) {
		this.importacao = importacao;
	}
	public Permissoes getListagem() {
		return listagem;
	}
	public void setListagem(Permissoes listagem) {
		this.listagem = listagem;
	}
	public Permissoes getDoacoes() {
		return doacoes;
	}
	public void setDoacoes(Permissoes doacoes) {
		this.doacoes = doacoes;
	}
	public Permissoes getConfiguracoes() {
		return configuracoes;
	}
	public void setConfiguracoes(Permissoes configuracoes) {
		this.configuracoes = configuracoes;
	}

	
	
	
	public String getTipoUtilizador() {
		return tipoUtilizador;
	}
	public void setTipoUtilizador(String tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUtilizador() {
		return utilizador;
	}
	public void setUtilizador(String utilizador) {
		this.utilizador = utilizador;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Utilizador(String tipoUtilizador, String nome, String utilizador, String password) {
		super();
		this.tipoUtilizador = tipoUtilizador;
		this.nome = nome;
		this.utilizador = utilizador;
		this.password = password;
		
	}
	public Utilizador(String tipoUtilizador, String nome, String utilizador, String password, Permissoes registo,
			Permissoes reclamacao, Permissoes importacao, Permissoes listagem, Permissoes doacoes,
			Permissoes configuracoes) {
		super();
		this.tipoUtilizador = tipoUtilizador;
		this.nome = nome;
		this.utilizador = utilizador;
		this.password = password;
		this.registo = registo;
		this.reclamacao = reclamacao;
		this.importacao = importacao;
		this.listagem = listagem;
		this.doacoes = doacoes;
		this.configuracoes = configuracoes;
	}
	
	
	
	
	

}
