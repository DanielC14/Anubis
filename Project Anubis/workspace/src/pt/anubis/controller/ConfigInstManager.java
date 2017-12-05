package pt.anubis.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import pt.anubis.model.Instituição;
import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;


/**
 * 
 * Métodos que são usados na preparação e execução da Configuração de Instituições do programa
 * 
 * @author Daniel Carneiro
 *
 */


public class ConfigInstManager {
	
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Configuração de Instituições
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
		framePrincipal.lblMenu.setText("Configurar Instituições");
		framePrincipal.mI.setTitle("ANUBIS - Configurar Instituições");
		
		tableConfigInst();
	}
	/**
	 * importa todas as Instituições para a tabela existente no painel
	 */
	public static void tableConfigInst()
	{
		framePrincipal.dtmConfigInst.setRowCount(0);
		framePrincipal.dtmConfigInst.setColumnCount(0);
		framePrincipal.dtmConfigInst.addColumn("Nome");
		
		for(Instituição inst : LoadSave.instituicoes)
		{
			framePrincipal.dtmConfigInst.addRow(new Object[]{inst.getNome()});
		}
	}
	
	/**
	 * permite a adição de uma instituição ao programa
	 * verifica se esta já não existe
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
			JOptionPane.showMessageDialog(null,"Insira o nome da Instituição!!!");
		}
		else
		{
			for (Instituição inst : LoadSave.instituicoes) {
				if(inst.getNome().equalsIgnoreCase(nomeInst))
				{
					adicionar = false;
					JOptionPane.showMessageDialog(null,"Instituição já existente!!!");
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
				
				Instituição inst = new Instituição(codigoInst, nomeInst);
				LoadSave.instituicoes.add(inst);
				tableConfigInst();
				framePrincipal.txtAddInst.setText("");
				framePrincipal.lblEstado.setText("Instituição adicionada com sucesso!");
				File addinst = new File("instituicaoFicheiros/" + nomeInst + ".txt");
				addinst.createNewFile();
			}
			universalManager.setupComboBox();
		}
		
		
		
	}
	/**
	 * Permite a remoção de uma instiuição que esteja selecionada na tabela
	 * @param nomeInst
	 */
	public static void removerInst(String nomeInst)
	{
		boolean associado = false;
		
		
		int selecionada = framePrincipal.tableConfigInst.getSelectedRow();
		
		if(selecionada == -1)
		{
			JOptionPane.showMessageDialog(null,"Selecione na tabela uma instituição!!!");
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
					Instituição inst = LoadSave.instituicoes.get(i);
					if (inst.getNome().equalsIgnoreCase(nomeInst)) {
						LoadSave.instituicoes.remove(inst);
						framePrincipal.lblEstado.setText("Instituição removida com sucesso!");
						break;
					}
				}
				tableConfigInst();
				universalManager.setupComboBox();
			}
			else
			{
				int resposta = JOptionPane.showConfirmDialog(null, "Esta instituição está associada a um tipo de objeto, tem a certeza que deseja remover?", "Remover Instituição", JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION)
				{
					for (int i=0; i < LoadSave.instituicoes.size(); i++) {
						Instituição inst = LoadSave.instituicoes.get(i);
						if (inst.getNome().equals(nomeInst)) {
							for(TipoObjeto tObj : LoadSave.tipos)
							{
								if(tObj.getAssociado().equals(nomeInst))
								{
								tObj.setAssociado("Não Associado");
								LoadSave.contNAssociados++;
								}
							}
							LoadSave.instituicoes.remove(inst);
							framePrincipal.lblEstado.setText("Instituição removida com sucesso!");
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
	 * Permite editar uma instituição, tendo esta que ser primeiro selecionada na tabela
	 * Se já existir uma outra instituição com uma nome igual não deixa mudar para esse nome
	 * muda tambem o nome do ficheiro da instituiçao
	 */
	public static void editarInst(String nomeAntigo,String nomeNovo)
	{
		int indexInst = 0;
		boolean continuar = true;
		
			if(nomeNovo.trim().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Insira o nome da Instituição!!!");
			}
			else
			{
				for(int i = 0; i<LoadSave.instituicoes.size();i++)
				{
					Instituição inst = LoadSave.instituicoes.get(i);
					if(inst.getNome().equalsIgnoreCase(nomeAntigo))
					{
						indexInst = i;
					}
				}
				
				for(int i = 0; i<LoadSave.instituicoes.size();i++)
				{
					Instituição inst = LoadSave.instituicoes.get(i);
					if(i!=indexInst)
					{
						if(inst.getNome().equalsIgnoreCase(nomeNovo))
						{
							JOptionPane.showMessageDialog(null, "Instituição já existente!");
							continuar = false;
							break;
						}
					}
				}
				
				if(continuar == true)
				{
					
					Instituição inst = LoadSave.instituicoes.get(indexInst);
					File fnomeAntigo = new File("instituicaoFicheiros/" + inst.getNome() + ".txt");
					File fnomeNovo = new File("instituicaoFicheiros/" + nomeNovo + ".txt");
					fnomeAntigo.renameTo(fnomeNovo);
					inst.setNome(nomeNovo);
					tableConfigInst();
					framePrincipal.txtEditInst.setText("");
					framePrincipal.lblEstado.setText("Instituição editada com sucesso!");
					universalManager.setupComboBox();
					
				}
				
			}
	}
	
	

}
