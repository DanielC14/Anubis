package pt.anubis.controller;

import javax.swing.JOptionPane;

import pt.anubis.view.loginAnubis;



public class anubisMain {
	
	public static loginAnubis log = new loginAnubis();
	
	public static void main(String[] args) {
		
		/**
		 * Execu��o de alguns m�todos de Load
		 * Principalmente o load dos utilizadores para permitir saber se os dados de login se encontram corretos
		 * In�cio do programa atrav�s da abertura da janela de login
		 */
		try
		{
			LoadSave.loadUtilizador();
			LoadSave.loadInstituicao();
			LoadSave.loadTipoObjeto();
			LoadSave.loadSala();
			
			if(LoadSave.users.size() == 0)
			{
				int resultado = JOptionPane.showConfirmDialog(null, "N�o existem contas no programa, crie uma!",
				        "Primeiro utilizador", JOptionPane.OK_CANCEL_OPTION);
				
				if(resultado == 2)
				{
					System.exit(0);
				}
				else
				{
					ConfigUtilizadorManager.openAddUser();
					
				}
				
				
				
			}
			else
			{
				
				
				
				log.setVisible(true);
			}
			
		}
		catch(Exception p)
		{
			System.out.println(p);
		}
		
		

	}
	
	

}
