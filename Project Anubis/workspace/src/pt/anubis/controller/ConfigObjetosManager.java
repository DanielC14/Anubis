package pt.anubis.controller;

import javax.swing.JOptionPane;

import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;

/**
 * 
 * Métodos que são usados na preparação e execução da Configuração de Tipos de Objetos do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class ConfigObjetosManager {
	
	public static String codigoTObj;
	
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Configuração de Tipos de Objetos
	 * 
	 */
	public static void pressConfigTObjecto()
	{
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pConfigObjeto);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Configurar Tipos de Objetos");
		framePrincipal.mI.setTitle("ANUBIS - Configurar Tipos de Objetos");
		tableConfigObjetos();
		
	}
	
	/**
	 * importa todos os tipos de objetos e as respetivas instituições associadas para a tabela
	 */
	public static void tableConfigObjetos()
	{
		framePrincipal.dtmConfigObj.setRowCount(0);
		framePrincipal.dtmConfigObj.setColumnCount(0);
		framePrincipal.dtmConfigObj.addColumn("Código");
		framePrincipal.dtmConfigObj.addColumn("Tipo de Objeto");
		framePrincipal.dtmConfigObj.addColumn("Instituição");
		
		for(TipoObjeto tobj: LoadSave.tipos)
		{
			framePrincipal.dtmConfigObj.addRow(new Object[]{tobj.getCodigo(), tobj.getNomeObjeto(), tobj.getAssociado()});
		}
	}
	
	
	/**
	 * permite adicionar um tipo de objeto e associar a uma instituição
	 * não permite adição de tipos de objetos com o mesmo nome
	 * @param nomeTObj
	 * @param instituicao
	 */
	public static void adicionarTipoObjeto(String nomeTObj, String instituicao)
	{
		codigoTObj = "";
		boolean adicionar = true;
		
		if(nomeTObj.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Nome de Tipo de Objeto inválido!!!");
		}
		else
		{
			for(TipoObjeto tobj:LoadSave.tipos)
			{
				if(nomeTObj.equalsIgnoreCase(tobj.getNomeObjeto()))
				{
					adicionar = false;
					JOptionPane.showMessageDialog(null,"Tipo de Objeto já existente!!!");
				}
				else
				{
					codigoTObj = tobj.getCodigo();
				}
			}
			if(adicionar == true)
			{
				for(TipoObjeto tobj: LoadSave.tipos)
				{
					codigoTObj = tobj.getCodigo();
				}
				
				if(codigoTObj.equals(""))
				{
					codigoTObj = "1";
				}
				else
				{
					int codigo = Integer.parseInt(codigoTObj) + 1;
					codigoTObj = Integer.toString(codigo);
				}
				
				TipoObjeto tobj = new TipoObjeto(codigoTObj,nomeTObj, instituicao);
				LoadSave.tipos.add(tobj);
				tableConfigObjetos();
				framePrincipal.txtAdicionarTObj.setText("");
				framePrincipal.lblEstado.setText("Tipo de Objeto adicionado com sucesso!");
				
			}
			universalManager.setupComboBox();
		}
		
		
		
	}
	
	/**
	 * permite a remoção de um tipo de objeto selecionado na tabela
	 * @param nomeTObj
	 */
	public static void removerTipoObjeto(String nomeTObj)
	{
		int selecionada = framePrincipal.tableConfigObjeto.getSelectedRow();
		
		if(selecionada == -1)
		{
			JOptionPane.showMessageDialog(null,"Selecione na tabela um Tipo de Objeto!");
		}
		else
		{
			for (int i=0; i < LoadSave.tipos.size(); i++) {
				TipoObjeto to = LoadSave.tipos.get(i);
				if (to.getNomeObjeto().equals(nomeTObj)) {
					if(to.getAssociado().equals("Não Associado"))
					{
						LoadSave.contNAssociados--;
						universalManager.checkTONaoAssociado();
					}
					LoadSave.tipos.remove(to);
				}
			}
			framePrincipal.lblEstado.setText("Tipo de Objeto removido com sucesso!");
			tableConfigObjetos();
		}
		universalManager.setupComboBox();
	}
	
	
	/**
	 * permite a edição de um tipo de objeto, mudando o seu nome ou instituição associada
	 * 
	 */
	public static void editarTipoObjeto(String nomeAntigo,String instituicaoAntigo, String nomeNovo,String instituicaoNova)
	{
		int selecionada = framePrincipal.tableConfigObjeto.getSelectedRow();
		
		int indexTObj = 0;
		boolean continuar = true;
		
		if(selecionada == -1)
		{
			JOptionPane.showMessageDialog(null,"Selecione na tabela um Tipo de Objeto!");
		}
		else
		{
		if(nomeNovo.trim().isEmpty())
		{
			for(TipoObjeto tobj : LoadSave.tipos)
			{
				if(tobj.getNomeObjeto().equals(nomeAntigo))
				{
					
					if(tobj.getAssociado().equals("Não Associado"))
					{
						LoadSave.contNAssociados--;
						universalManager.checkTONaoAssociado();
					}
					tobj.setAssociado(instituicaoNova);
					framePrincipal.lblEstado.setText("Tipo de Objeto editado com sucesso!");
					tableConfigObjetos();
				
				}
			}
			
		}
		else
		{
			
			for(int i = 0;i<LoadSave.tipos.size();i++)
			{
				TipoObjeto tobj = LoadSave.tipos.get(i);
				if(tobj.getNomeObjeto().equalsIgnoreCase(nomeAntigo))
				{
					indexTObj = i;
				}
				
				
			}
			
			for(int i = 0;i<LoadSave.tipos.size();i++)
			{
				TipoObjeto tobj = LoadSave.tipos.get(i);
				
				if(i!=indexTObj)
				{
					if(tobj.getNomeObjeto().equalsIgnoreCase(nomeNovo))
					{
						JOptionPane.showMessageDialog(null, "Tipo de Objeto já existente!");
						continuar = false;
						break;
						
					}
				}
				
			}
			
			if(continuar == true)
			{
				TipoObjeto tobj = LoadSave.tipos.get(indexTObj);
				tobj.setNomeObjeto(nomeNovo);
				if(!(instituicaoNova.equals("")))
				{
					tobj.setAssociado(instituicaoNova);
				}
				framePrincipal.txtEditarTObj.setText("");
				framePrincipal.lblEstado.setText("Tipo de Objeto editado com sucesso!");
				tableConfigObjetos();
				universalManager.setupComboBox();
			}
			
			}
		}
		
		
	}
	

}
