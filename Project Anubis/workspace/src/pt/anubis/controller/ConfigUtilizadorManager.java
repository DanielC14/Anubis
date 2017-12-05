package pt.anubis.controller;

import javax.swing.JOptionPane;

import pt.anubis.model.Permissoes;
import pt.anubis.model.Utilizador;
import pt.anubis.view.framePrincipal;
import pt.anubis.view.frameSecundaria;


/**
 * 
 * Métodos que são usados na preparação e execução da Configuração de Utilizadores do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class ConfigUtilizadorManager {
	
	
	private static int indexUtilizador;
	
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Configuração de Utilizadores
	 * 
	 */
	public static void pressConfigUtilizador()
	{
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pConfigUtilizador);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Configurar Utilizadores");
		framePrincipal.mI.setTitle("ANUBIS - Configurar Utilizadores");
		tableConfigUtilizador();
	}
	
	/**
	 * faz a abertura da frameSecundaria que permite adicionar um Utilizador
	 */
	public static void openAddUser()
	{
		frameSecundaria.aU = new frameSecundaria();
		universalManager.setupComboBox();
		if(LoadSave.users.size() == 0)
		{
			frameSecundaria.cBModelTUser.removeAllElements();
			frameSecundaria.cBModelTUser.addElement("Administrador");
		}
		else
		{
			frameSecundaria.cBModelTUser.removeAllElements();
			frameSecundaria.cBModelTUser.addElement("Administrador");
			frameSecundaria.cBModelTUser.addElement("Instituição");
			frameSecundaria.cBModelTUser.addElement("Segurança");
		}
		frameSecundaria.aU.setVisible(true);
		
		universalManager.moveFramePrincipal();
		frameSecundaria.btnEditar.setVisible(false);
		setupPermissoes(frameSecundaria.cBTUser.getSelectedItem().toString());
		
	}
	
	
	/**
	 * importa alguns dados dos utilizadores para a tabela
	 */
	public static void tableConfigUtilizador()
	{
		framePrincipal.dtmConfigUtilizador.setRowCount(0);
		framePrincipal.dtmConfigUtilizador.setColumnCount(0);
		framePrincipal.dtmConfigUtilizador.addColumn("Tipo de Utilizador");
		framePrincipal.dtmConfigUtilizador.addColumn("Nome");
		framePrincipal.dtmConfigUtilizador.addColumn("Username");
		
		for(Utilizador user : LoadSave.users)
		{
			framePrincipal.dtmConfigUtilizador.addRow(new Object[]{user.getTipoUtilizador(),user.getNome(),user.getUtilizador()});
		}
	}
	
	/**
	 * recebe dados para a criação do utilizador
	 * verifica se as passwords coincidem e se o nome de utilizador ja nao existe
	 * cria uma nova conta
	 */
	public static boolean addUtilizador(String nomeCompleto, String nomeUtilizador, String palavraPasse, String rpalavraPasse, String tipoUtilizador,
			boolean registo, boolean reclamacao, boolean listagem, boolean doacoes, boolean configuracoes, boolean importacao)
	{
		boolean go = false;
		
		if(!(nomeCompleto.trim().isEmpty() || nomeUtilizador.trim().isEmpty() || palavraPasse.trim().isEmpty() || rpalavraPasse.trim().isEmpty()))
		{
			boolean existe = false;
			for(Utilizador utl : LoadSave.users)
			{
				if(utl.getUtilizador().equalsIgnoreCase(nomeUtilizador))
				{
					existe = true;
					break;
				}
			}
			if(existe == false)
			{
				if((palavraPasse.equals(rpalavraPasse)))
				{
					Utilizador u = new Utilizador(tipoUtilizador, nomeCompleto, nomeUtilizador, palavraPasse);
					u.getPerms().add(new Permissoes(registo, reclamacao, importacao, listagem, doacoes, configuracoes));
					LoadSave.users.add(u);
					JOptionPane.showMessageDialog(null,"Utilizador adicionado com sucesso!");
					frameSecundaria.aU.setVisible(false);
					tableConfigUtilizador();
					go = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Palavras-Passe diferentes!!!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Nome de Utilizador já existente!!!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Não pode deixar campos em branco!!!");
		}
		return go;
		
	}
	
	
	/**
	 * vai buscar os dados do utilizador escolhido na tabela
	 * preenche a frame com esses mesmos dados
	 * permite a edição de dados
	 */
	public static void frameEditUtilizador()
	{
		frameSecundaria.cBModelTUser.removeAllElements();
		frameSecundaria.cBModelTUser.addElement("Administrador");
		frameSecundaria.cBModelTUser.addElement("Instituição");
		frameSecundaria.cBModelTUser.addElement("Segurança");
		
		int selecionada = framePrincipal.tableConfigUtilizadorr.getSelectedRow();

		if (selecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione na tabela um Utilizador!");
		} else {
			frameSecundaria.aU = new frameSecundaria();
			frameSecundaria.aU.setVisible(true);
			universalManager.moveFramePrincipal();
			frameSecundaria.btnAdicionar.setVisible(false);
			String nomeC = framePrincipal.tableConfigUtilizadorr
					.getValueAt(framePrincipal.tableConfigUtilizadorr.getSelectedRow(), 1).toString();
			String nomeU = framePrincipal.tableConfigUtilizadorr
					.getValueAt(framePrincipal.tableConfigUtilizadorr.getSelectedRow(), 2).toString();
			frameSecundaria.lblAddUser.setText("Editar Utilizador");
			frameSecundaria.txtNomeCompleto.setText(nomeC);
			frameSecundaria.txtNomeUtilizador.setText(nomeU);
			frameSecundaria.aU.setTitle("Editar Utilizador");

			for (int i = 0; i < LoadSave.users.size(); i++) {
				Utilizador utl = LoadSave.users.get(i);
				Permissoes perm = Utilizador.perms.get(i);
				if (utl.getNome().equals(nomeC) && utl.getUtilizador().equals(nomeU)) {
					indexUtilizador = i;

					frameSecundaria.checkRegisto.setSelected(perm.isRegisto());
					frameSecundaria.checkReclamacao.setSelected(perm.isReclamacao());
					frameSecundaria.checkListagem.setSelected(perm.isListagens());
					frameSecundaria.checkImportacao.setSelected(perm.isImportacao());
					frameSecundaria.checkDoacoes.setSelected(perm.isDoacoes());
					frameSecundaria.checkConfiguracoes.setSelected(perm.isConfiguracoes());
					frameSecundaria.txtPalavraPasse.setText(utl.getPassword());
					frameSecundaria.txtrPalavraPasse.setText(utl.getPassword());
					frameSecundaria.cBTUser.setSelectedItem(utl.getTipoUtilizador());

					if ((!(LoginManager.tipoUtilizador.equals("Administrador"))&& utl.getTipoUtilizador().equals("Administrador")) || !(utl.getUtilizador().equalsIgnoreCase(LoginManager.nomeUtilizador)) && LoginManager.tipoUtilizador.equals("Administrador")&& utl.getTipoUtilizador().equals("Administrador")) {
						frameSecundaria.cBTUser.setEnabled(false);
						frameSecundaria.pPermissoes.setEnabled(false);
						frameSecundaria.checkRegisto.setEnabled(false);
						frameSecundaria.checkReclamacao.setEnabled(false);
						frameSecundaria.checkListagem.setEnabled(false);
						frameSecundaria.checkImportacao.setEnabled(false);
						frameSecundaria.checkDoacoes.setEnabled(false);
						frameSecundaria.checkConfiguracoes.setEnabled(false);
						frameSecundaria.txtPalavraPasse.setEnabled(false);
						frameSecundaria.txtrPalavraPasse.setEnabled(false);
						frameSecundaria.txtNomeCompleto.setEnabled(false);
						frameSecundaria.txtNomeUtilizador.setEnabled(false);
						frameSecundaria.btnEditar.setEnabled(false);
					}

					if (utl.getTipoUtilizador().equals("Administrador") || utl.getTipoUtilizador().equals("Instituição")) {
						frameSecundaria.cBTUser.setEnabled(false);
						frameSecundaria.pPermissoes.setEnabled(false);
						frameSecundaria.checkRegisto.setEnabled(false);
						frameSecundaria.checkReclamacao.setEnabled(false);
						frameSecundaria.checkListagem.setEnabled(false);
						frameSecundaria.checkImportacao.setEnabled(false);
						frameSecundaria.checkDoacoes.setEnabled(false);
						frameSecundaria.checkConfiguracoes.setEnabled(false);
					}

					break;
				}
			}

		}
		
	}
	
	/**
	 * depois de editados os dados na frame, é executado este método
	 * verifica se todos os campos estao preenchidos
	 * palavras passe coincidem
	 * novo nome de utilizador ja nao existe
	 * edita o que foi escolhido anteriormente
	 */
	public static void editUtilizador(String nomeC, String nomeU, String password, String rpassword, String tUser, boolean registo, boolean reclamacao, boolean importacao, boolean listagem, boolean doacoes, boolean configuracoes)
	{
		boolean continuar = true;
		
		if (!(password.trim().isEmpty() || rpassword.trim().isEmpty() || nomeC.trim().isEmpty() || nomeU.trim().isEmpty())) {
			if (password.equals(rpassword)) {

				Utilizador utl = LoadSave.users.get(indexUtilizador);
				Permissoes perm = Utilizador.perms.get(indexUtilizador);
				
				for(int i = 0;i<LoadSave.users.size();i++)
				{
					Utilizador u = LoadSave.users.get(i);
					if(i!=indexUtilizador)
					{
						if(u.getUtilizador().equalsIgnoreCase(nomeU))
						{
							JOptionPane.showMessageDialog(null, "Nome de Utilizador já pertence a outro utilizador!");
							continuar = false;
							break;
						}
					}
				}
				if(continuar == true)
				{
					utl.setNome(nomeC);
					if(LoginManager.nomeUtilizador.equals(LoadSave.users.get(indexUtilizador).getUtilizador()))
					{
						LoginManager.nomeUtilizador = nomeU;
						LoginManager.nomeUser = nomeC;
						framePrincipal.lblUser.setText(nomeC);
					}
					utl.setUtilizador(nomeU);
					utl.setPassword(password);
					utl.setTipoUtilizador(tUser);
					perm.setRegisto(registo);
					perm.setReclamacao(reclamacao);
					perm.setImportacao(importacao);
					perm.setListagens(listagem);
					perm.setDoacoes(doacoes);
					perm.setConfiguracoes(configuracoes);

					JOptionPane.showMessageDialog(null, "Utilizador editado com sucesso!");
					frameSecundaria.aU.dispose();
					tableConfigUtilizador();
				}
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Palavras-Passe diferentes!!!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não pode deixar campos em branco!!!");
		}
		
		
	}
	
	/**
	 * faz a remoção de um utilizador escolhido na tabela 
	*/
	public static void removerUtilizador(String nomeC, String nomeU)
	{
		if(LoadSave.users.size() !=1 )
		{
			if(!LoginManager.nomeUtilizador.equals(nomeU))
			{
				int selecionada = framePrincipal.tableConfigUtilizadorr.getSelectedRow();
				
				if(selecionada == -1)
				{
					JOptionPane.showMessageDialog(null,"Selecione na tabela um Utilizador!");
				}
				else
				{
				for(int i = 0; i < LoadSave.users.size();i++)
				{
					Utilizador utl = LoadSave.users.get(i);
					if(utl.getNome().equals(nomeC) && utl.getUtilizador().equals(nomeU))
					{
							LoadSave.users.remove(i);
							Utilizador.perms.remove(i);
							break;
					}
				}
				tableConfigUtilizador();
				framePrincipal.lblEstado.setText("Utilizador removido com sucesso!!!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Não pode remover se está com sessão iniciada!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Não pode remover o único utilizador!");
		}
			
		
		
	}
	
	public static void setupPermissoes(String tipoUser)
	{
		if(tipoUser.equals("Administrador"))
		{
			frameSecundaria.checkRegisto.setSelected(true);
			frameSecundaria.checkConfiguracoes.setSelected(true);
			frameSecundaria.checkDoacoes.setSelected(true);
			frameSecundaria.checkImportacao.setSelected(true);
			frameSecundaria.checkListagem.setSelected(true);
			frameSecundaria.checkReclamacao.setSelected(true);
			
			frameSecundaria.checkRegisto.setEnabled(false);
			frameSecundaria.checkConfiguracoes.setEnabled(false);
			frameSecundaria.checkDoacoes.setEnabled(false);
			frameSecundaria.checkImportacao.setEnabled(false);
			frameSecundaria.checkListagem.setEnabled(false);
			frameSecundaria.checkReclamacao.setEnabled(false);
			
			
		}
		else if(tipoUser.equals("Instituição"))
		{
			frameSecundaria.checkRegisto.setSelected(false);
			frameSecundaria.checkConfiguracoes.setSelected(false);
			frameSecundaria.checkDoacoes.setSelected(false);
			frameSecundaria.checkImportacao.setSelected(false);
			frameSecundaria.checkListagem.setSelected(true);
			frameSecundaria.checkReclamacao.setSelected(false);
			
			frameSecundaria.checkRegisto.setEnabled(false);
			frameSecundaria.checkConfiguracoes.setEnabled(false);
			frameSecundaria.checkDoacoes.setEnabled(false);
			frameSecundaria.checkImportacao.setEnabled(false);
			frameSecundaria.checkListagem.setEnabled(false);
			frameSecundaria.checkReclamacao.setEnabled(false);
		}
		else
		{
			
			
			frameSecundaria.checkRegisto.setEnabled(true);
			frameSecundaria.checkConfiguracoes.setEnabled(true);
			frameSecundaria.checkDoacoes.setEnabled(true);
			frameSecundaria.checkImportacao.setEnabled(true);
			frameSecundaria.checkListagem.setEnabled(true);
			frameSecundaria.checkReclamacao.setEnabled(true);
		}
	}
	
	
	

}
