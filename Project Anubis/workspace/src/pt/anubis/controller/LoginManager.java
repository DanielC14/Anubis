package pt.anubis.controller;

import javax.swing.JLabel;

import pt.anubis.model.Permissoes;
import pt.anubis.model.Utilizador;
import pt.anubis.view.framePrincipal;

/**
 * 
 * Métodos que são usados na preparação e execução do Login do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class LoginManager {
	
	public static String nomeUser;
	public static boolean res = false;
	public static String tipoUtilizador;
	public static String nomeUtilizador;
	
	
	
	
	
	/**
	 * Verifica se o nome de utilizador e a palavra passe coincidem com algum utilizador
	 * para dar inicio ao login, senao diz dados incorretos
	 * e dependendo do tipo de utilizador que inicia sessao
	 * havera diferenças na interação com o programa
	 */
	public static void setup(String username, String password) throws Exception
	{
		for(int i = 0; i < LoadSave.users.size();i++)
		{
			Utilizador u = LoadSave.users.get(i);
			if(u.getUtilizador().equals(username) && u.getPassword().equals(password))
			{
				if(u.getTipoUtilizador().equals("Instituição"))
				{
					LoadSave.loadObjetoInstituicao(u.getNome());
				}
				else
				{
					LoadSave.loadObjeto();
				}
				universalManager.setupComboBox(); 
				
				Permissoes p = Utilizador.perms.get(i);
				res = true;
				nomeUser = u.getNome();
				tipoUtilizador = u.getTipoUtilizador();
				nomeUtilizador = u.getUtilizador();
				framePrincipal.lblUser = new JLabel(nomeUser);
			
				framePrincipal.mI = new framePrincipal();
				framePrincipal.mI.setVisible(true);
				framePrincipal.pMestre.removeAll();
				framePrincipal.pMestre.repaint();
				framePrincipal.pMestre.revalidate();
				framePrincipal.pMestre.add(framePrincipal.pInicio);
				framePrincipal.pMestre.repaint();
				framePrincipal.pMestre.revalidate();
				framePrincipal.btnRegistarObj.setEnabled(p.isRegisto());
				framePrincipal.btnReclamarObj.setEnabled(p.isReclamacao());
				framePrincipal.btnListagem.setEnabled(p.isListagens());
				framePrincipal.btnImportar.setEnabled(p.isImportacao());
				framePrincipal.btnDoar.setEnabled(p.isDoacoes());
				framePrincipal.btnConfigurar.setEnabled(p.isConfiguracoes());
				if(tipoUtilizador.equalsIgnoreCase("Instituição"))
				{
					framePrincipal.lblObjReclamar.setText("Objetos Doados:");
				}
				
				framePrincipal.lblQuantidade.setText(Integer.toString(LoadSave.contObjetos));
				
				
				break;
				
			}
			
			
		}
	
	}
	
	
	
	
	
	
	
	
	
	

}
