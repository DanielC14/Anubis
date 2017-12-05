package pt.anubis.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pt.anubis.model.Objeto;
import pt.anubis.view.framePrincipal;
import pt.anubis.view.jDescricao;

/**
 * 
 * Métodos que são usados na preparação e execução das Reclamações do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class ReclamarManager {
	
	
	private static ArrayList<String> sortObjetos = new ArrayList<String>();
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de reclamações
	 */
	public static void pressReclamar()
	{
		if(framePrincipal.pDoar.isVisible())
		{
			framePrincipal.pDoar.setVisible(false);
		}
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pReclamar);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Reclamar Objeto");
		framePrincipal.mI.setTitle("ANUBIS - Reclamar Objeto");
		framePrincipal.cbModelBloco.setSelectedItem("");
		framePrincipal.cbModelSala.setSelectedItem("");
		framePrincipal.cbModelTObj.setSelectedItem("");
		jDescricao.txtDescricao.setText("");
		if(!(jDescricao.jD.isVisible()))
		{
			jDescricao.jD.setVisible(true);
		}
		
		universalManager.moveDescricao();
		
		
	}
	
	/**
	 * importa todos os objetos para a tabela
	 */
	public static void tableReclamar()
	{
		framePrincipal.dtmReclamar.setRowCount(0);
		framePrincipal.dtmReclamar.setColumnCount(0);
		framePrincipal.dtmReclamar.addColumn("Código");
		framePrincipal.dtmReclamar.addColumn("Data");
		framePrincipal.dtmReclamar.addColumn("Hora");
		framePrincipal.dtmReclamar.addColumn("Bloco");
		framePrincipal.dtmReclamar.addColumn("Sala");
		framePrincipal.dtmReclamar.addColumn("Tipo Objeto");
		framePrincipal.dtmReclamar.addColumn("Cor");
		framePrincipal.dtmReclamar.addColumn("Estado");
		
		
		
		for(Objeto obj: LoadSave.objetos)
		{
			framePrincipal.dtmReclamar.addRow(new Object[]{obj.getCodigoObjeto(),obj.getData(), obj.getHora(), obj.getBloco(), obj.getSala(), obj.getTipoDeObjeto(), obj.getCor(), obj.getEstado()});
		}
		
	}
	
	/**
	 * Através da frame jDescrição, este metodo sabe qual a linha da tabela que esta selecionada e escreve a descriçao no textPane da jDescriçao
	 */
	public static void showDescricao()
	{
		
		for(Objeto obj: LoadSave.objetos)
		{
			if((obj.getCodigoObjeto().equals(framePrincipal.tableReclamar.getValueAt(framePrincipal.tableReclamar.getSelectedRow(), 0))))
					{
						jDescricao.txtDescricao.setText(obj.getDescricao());
						break;
					}
		}
	}
	
	/**
	 * reclama o objeto, apagando do arrayList
	 */
	public static void reclamacaoSucedida()
	{
		
		for (int i=0; i < LoadSave.objetos.size(); i++) {
			Objeto obj = LoadSave.objetos.get(i);
			if((obj.getCodigoObjeto().equals(framePrincipal.tableReclamar.getValueAt(framePrincipal.tableReclamar.getSelectedRow(), 0))))
			{
				LoadSave.objetos.remove(obj);
			}
			
		}
		LoadSave.contObjetos--;
		framePrincipal.lblQuantidade.setText(Integer.toString(LoadSave.contObjetos));
		framePrincipal.lblEstado.setText("Objeto reclamado com sucesso!");
		framePrincipal.cbModelTObj.setSelectedItem("");
		framePrincipal.cbModelBloco.setSelectedItem("");
		jDescricao.txtDescricao.setText("");
		tableReclamar();
		
	}
	
	/**
	 * verifica se existem objetos por reclamar
	 */
	public static void checkObjetosReclamar()
	{
		if(LoadSave.contObjetos == 0)
		{
			JOptionPane.showMessageDialog(null,"Não existem objetos a reclamar!!!");
		}
		else
		{
			pressReclamar();
			tableReclamar();
		}
	}
	
	/**
	 * ordena a tabela de reclamar pelos parametros que o utilizador escolher
	 */
	public static void sortTableReclamar()
	{
		try
		{
			tableReclamar();
			
			if(!(framePrincipal.cBRecTObj.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmReclamar.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmReclamar.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 6).toString()  + "#" + framePrincipal.dtmReclamar.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				framePrincipal.dtmReclamar.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					if(fields[5].equals(framePrincipal.cBRecTObj.getSelectedItem().toString()))
					{
						
						
						framePrincipal.dtmReclamar.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
					}
					
					
				}
			}
			
			if(!(framePrincipal.cBRecBloco.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmReclamar.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmReclamar.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				framePrincipal.dtmReclamar.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[3].equals(framePrincipal.cBRecBloco.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmReclamar.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
					}
					
					
				}
			}
			
			if(!(framePrincipal.cBRecSala.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmReclamar.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmReclamar.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmReclamar.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				
				framePrincipal.dtmReclamar.setRowCount(0);
				
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[4].equals(framePrincipal.cBRecSala.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmReclamar.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
					}
					
					
				}
			}
			
		}
		catch(Exception p)
		{
			
		}
		
		
	}
	
	

}
