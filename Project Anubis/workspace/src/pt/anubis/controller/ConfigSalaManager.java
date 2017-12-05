package pt.anubis.controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import pt.anubis.model.Sala;
import pt.anubis.view.framePrincipal;

/**
 * 
 * Métodos que são usados na preparação e execução da Configuração de Salas do programa
 * 
 * @author Daniel Carneiro
 *
 */

public class ConfigSalaManager {
	
	private static boolean existe;
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Configuração de Salas
	 * 
	 */
	public static void pressConfigSala()
	{
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pConfigSala);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Configurar Salas");
		framePrincipal.mI.setTitle("ANUBIS - Configurar Salas");
		framePrincipal.cbModelBloco.setSelectedItem("");
		tableConfigSalas();
		
	}
	/**
	 * importa todas as salas para a tabela
	 */
	public static void tableConfigSalas()
	{
		framePrincipal.dtmConfigSala.setRowCount(0);
		framePrincipal.dtmConfigSala.setColumnCount(0);
		framePrincipal.dtmConfigSala.addColumn("Bloco");
		framePrincipal.dtmConfigSala.addColumn("Sala");
		
		for(Sala sl : LoadSave.salas)
		{
			framePrincipal.dtmConfigSala.addRow(new Object[]{sl.getBloco(),sl.getSala()});
		}
	}
	
	/**
	 * permite a ordenação da tabela para só apresentar as salas presentes no bloco escolhido
	 */
	public static void sortCBSalas()
	{
		try{
			if(!(framePrincipal.cbModelBloco.getSelectedItem().toString().equals("")))
			{
				framePrincipal.dtmConfigSala.setRowCount(0);
				for(int i = 0; i<LoadSave.salas.size();i++)
				{
					Sala sl = LoadSave.salas.get(i);
					if(sl.getBloco().contains(framePrincipal.cbModelBloco.getSelectedItem().toString()))
					{
						framePrincipal.dtmConfigSala.addRow(new Object[]{sl.getBloco(), sl.getSala()});
					}
				}
			}
			else
			{
				tableConfigSalas();
			}
		}catch(Exception p){}
		
	}
	
	
	/**
	 * permite adicionar um bloco e a sua respetiva sala
	 */
	public static void adicionarSala(String blocoA, String salaA)
	{
		if(blocoA.trim().isEmpty() || salaA.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Insira dados válidos!!!");
		}
		else
		{
			for(Sala sl: LoadSave.salas)
			{
				existe = false;
				if(sl.getBloco().equalsIgnoreCase(blocoA) && sl.getSala().equalsIgnoreCase(salaA))
				{
					existe = true;
					break;
				}
			}
			
			if(existe == false)
			{
				Sala sl = new Sala(blocoA.toUpperCase(), salaA);
				LoadSave.salas.add(sl);
				sortSalas();
				framePrincipal.cbModelSala.setSelectedItem("");
				framePrincipal.lblEstado.setText("Sala adicionada com sucesso!");
				tableConfigSalas();
				framePrincipal.txtAddSala.setText("");
				framePrincipal.txtAddBloco.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Sala já existente!!!");
			}
		}
		universalManager.setupComboBox();
		universalManager.comboBoxSalas();
	}
	
	
	/**
	 * permite a ordenação da arrayList salas para apresentar os seus valores por ordem alfabetica e numerica
	 */
	public static void sortSalas()
	{
		ArrayList<String> salaOrdenada = new ArrayList<String>();
		for(Sala sl:LoadSave.salas)
		{
			salaOrdenada.add(sl.getBloco() + "#" + sl.getSala());
		}
		Collections.sort(salaOrdenada);
		LoadSave.salas.clear();
		for(String slOrd: salaOrdenada)
		{
			String line = slOrd;
			String[] fields = line.split("#");
			Sala s = new Sala(fields[0], fields[1]);
			LoadSave.salas.add(s);
		}
	}
	
	
	/**
	 * permite a remoção de uma sala escolhida na tabela pelo utilizador
	 */
	public static void removerSala(String blocoR, String salaR)
	{
		
			for(int i = 0; i< LoadSave.salas.size();i++)
			{
				Sala sl = LoadSave.salas.get(i);
				if(blocoR.equals(sl.getBloco().toString()) && salaR.equals(sl.getSala().toString()))
				{
					LoadSave.salas.remove(i);
					framePrincipal.lblEstado.setText("Sala removida com sucesso!");
					tableConfigSalas();
				}
			}
		
			universalManager.setupComboBox();
			universalManager.comboBoxSalas();
	}
	
	/**
	 * permite a edição de um bloco e a sua respetiva sala
	 * o utilizador escolhe uma sala na tabela
	 * preenche a secção de edição com os valores pretendidos
	 */
	public static void editarSala(String blocoE, String salaE, String blocoNovo, String salaNova)
	{
		
		int indexSala = 0;
		boolean continuar = true;
		
		if(blocoNovo.trim().isEmpty() || salaNova.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Insira dados válidos!!!");
		}
		else
		{
			for(int i = 0;i<LoadSave.salas.size();i++)
			{
				Sala sl = LoadSave.salas.get(i);
				if(sl.getBloco().equalsIgnoreCase(blocoE) && sl.getSala().equalsIgnoreCase(salaE))
				{
					indexSala = i;
				}
			}
			
			for(int i = 0;i<LoadSave.salas.size();i++)
			{
				Sala sl = LoadSave.salas.get(i);
				
				if(indexSala != i)
				{
					if(sl.getBloco().equalsIgnoreCase(blocoNovo) && sl.getSala().equalsIgnoreCase(salaNova))
					{
						JOptionPane.showMessageDialog(null, "Sala já existente!");
						continuar = false;
						break;
					}
				}
				
				
			}
			
			if(continuar == true)
			{
				Sala sl = LoadSave.salas.get(indexSala);
				sl.setBloco(blocoNovo);
				sl.setSala(salaNova);
				sortSalas();
				tableConfigSalas();
				framePrincipal.txtEditBloco.setText("");
				framePrincipal.txtEditSala.setText("");
				universalManager.setupComboBox();
				universalManager.comboBoxSalas();
				framePrincipal.lblEstado.setText("Sala editada com sucesso!");
			}
			
		}
		
		
		
	}

}
