package pt.anubis.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import pt.anubis.model.Institui��o;
import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;


/**
 * 
 * M�todos que s�o usados na prepara��o e execu��o da Configura��o de Institui��es do programa
 * 
 * @author Daniel Carneiro
 *
 */


public class ConfigInstManager {
	
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Configura��o de Institui��es
	 */
	public static void pressConfigInst()
	{
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pConfigInst);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Configurar Institui��es");
		framePrincipal.mI.setTitle("ANUBIS - Configurar Institui��es");
		
		tableConfigInst();
	}
	/**
	 * importa todas as Institui��es para a tabela existente no painel
	 */
	public static void tableConfigInst()
	{
		framePrincipal.dtmConfigInst.setRowCount(0);
		framePrincipal.dtmConfigInst.setColumnCount(0);
		framePrincipal.dtmConfigInst.addColumn("Nome");
		
		for(Institui��o inst : LoadSave.instituicoes)
		{
			framePrincipal.dtmConfigInst.addRow(new Object[]{inst.getNome()});
		}
	}
	
	/**
	 * permite a adi��o de uma institui��o ao programa
	 * verifica se esta j� n�o existe
	 * cria o ficheiro da mesma para mais tarde mover o registo de objetos doados
	 * @param nomeInst
	 * @throws IOException
	 */
	public static void adicionarInst(String nomeInst) throws IOException
	{
		File instituicaoFile = new File("instituicaoFicheiros");
		if(!instituicaoFile.exists())
		{
			instituicaoFile.mkdir();
		}
		
		String codigoInst = "";
		boolean adicionar = true;
		if(nomeInst.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Insira o nome da Institui��o!!!");
		}
		else
		{
			for (Institui��o inst : LoadSave.instituicoes) {
				if(inst.getNome().equalsIgnoreCase(nomeInst))
				{
					adicionar = false;
					JOptionPane.showMessageDialog(null,"Institui��o j� existente!!!");
				}
				else
				{
					codigoInst = inst.getCodigo();
					
				}
				
			}
			if(adicionar == true)
			{
				if(codigoInst.equals(""))
				{
					codigoInst = "1";
				}
				else
				{
					int codigo = Integer.parseInt(codigoInst) + 1;
					codigoInst = Integer.toString(codigo);
				}
				
				Institui��o inst = new Institui��o(codigoInst, nomeInst);
				LoadSave.instituicoes.add(inst);
				tableConfigInst();
				framePrincipal.txtAddInst.setText("");
				framePrincipal.lblEstado.setText("Institui��o adicionada com sucesso!");
				File addinst = new File("instituicaoFicheiros/" + nomeInst + ".txt");
				addinst.createNewFile();
			}
			universalManager.setupComboBox();
		}
		
		
		
	}
	/**
	 * Permite a remo��o de uma instiui��o que esteja selecionada na tabela
	 * @param nomeInst
	 */
	public static void removerInst(String nomeInst)
	{
		boolean associado = false;
		
		
		int selecionada = framePrincipal.tableConfigInst.getSelectedRow();
		
		if(selecionada == -1)
		{
			JOptionPane.showMessageDialog(null,"Selecione na tabela uma institui��o!!!");
		}
		else
		{
			for(TipoObjeto tObj : LoadSave.tipos)
			{
				if(tObj.getAssociado().equals(nomeInst))
				{
					associado = true;
					break;
				}
			}
			
			if(associado == false)
			{
				for (int i=0; i < LoadSave.instituicoes.size(); i++) {
					Institui��o inst = LoadSave.instituicoes.get(i);
					if (inst.getNome().equalsIgnoreCase(nomeInst)) {
						LoadSave.instituicoes.remove(inst);
						framePrincipal.lblEstado.setText("Institui��o removida com sucesso!");
						break;
					}
				}
				tableConfigInst();
				universalManager.setupComboBox();
			}
			else
			{
				int resposta = JOptionPane.showConfirmDialog(null, "Esta institui��o est� associada a um tipo de objeto, tem a certeza que deseja remover?", "Remover Institui��o", JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION)
				{
					for (int i=0; i < LoadSave.instituicoes.size(); i++) {
						Institui��o inst = LoadSave.instituicoes.get(i);
						if (inst.getNome().equals(nomeInst)) {
							for(TipoObjeto tObj : LoadSave.tipos)
							{
								if(tObj.getAssociado().equals(nomeInst))
								{
								tObj.setAssociado("N�o Associado");
								LoadSave.contNAssociados++;
								}
							}
							LoadSave.instituicoes.remove(inst);
							framePrincipal.lblEstado.setText("Institui��o removida com sucesso!");
							break;
						}
					}
					
				}
				universalManager.checkTONaoAssociado();
				tableConfigInst();
				universalManager.setupComboBox();
			}
			
		}
		
	}
	
	/**
	 * Permite editar uma institui��o, tendo esta que ser primeiro selecionada na tabela
	 * Se j� existir uma outra institui��o com uma nome igual n�o deixa mudar para esse nome
	 * muda tambem o nome do ficheiro da institui�ao
	 */
	public static void editarInst(String nomeAntigo,String nomeNovo)
	{
		int indexInst = 0;
		boolean continuar = true;
		
			if(nomeNovo.trim().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Insira o nome da Institui��o!!!");
			}
			else
			{
				for(int i = 0; i<LoadSave.instituicoes.size();i++)
				{
					Institui��o inst = LoadSave.instituicoes.get(i);
					if(inst.getNome().equalsIgnoreCase(nomeAntigo))
					{
						indexInst = i;
					}
				}
				
				for(int i = 0; i<LoadSave.instituicoes.size();i++)
				{
					Institui��o inst = LoadSave.instituicoes.get(i);
					if(i!=indexInst)
					{
						if(inst.getNome().equalsIgnoreCase(nomeNovo))
						{
							JOptionPane.showMessageDialog(null, "Institui��o j� existente!");
							continuar = false;
							break;
						}
					}
				}
				
				if(continuar == true)
				{
					
					Institui��o inst = LoadSave.instituicoes.get(indexInst);
					File fnomeAntigo = new File("instituicaoFicheiros/" + inst.getNome() + ".txt");
					File fnomeNovo = new File("instituicaoFicheiros/" + nomeNovo + ".txt");
					fnomeAntigo.renameTo(fnomeNovo);
					inst.setNome(nomeNovo);
					tableConfigInst();
					framePrincipal.txtEditInst.setText("");
					framePrincipal.lblEstado.setText("Institui��o editada com sucesso!");
					universalManager.setupComboBox();
					
				}
				
			}
	}
	
	

}
