package pt.anubis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import pt.anubis.model.Objeto;
import pt.anubis.view.framePrincipal;
import pt.anubis.view.jDescricao;

/**
 * 
 * Métodos que são usados na preparação e execução da Listagem do programa
 * 
 * @author Daniel Carneiro
 *
 */

public class ListagemManager {
	
	private static ArrayList<String> sortObjetos = new ArrayList<String>();
	
	
	/**
	 * Verifica se existem objetos para apresentar na lis tagem
	 * Se nao houver objetos para apresentar, o painel da listagem não é aberto
	 * @throws ParseException
	 */
	public static void checkObjetosListagem() throws ParseException
	{
		if(LoadSave.contObjetos == 0 && !(LoginManager.tipoUtilizador.equals("Instituição")))
		{
			JOptionPane.showMessageDialog(null,"Não existem objetos a mostrar!!!");
		}
		else
		{
			pressListagem();
			tableListagem();
			spinnerDate();
		}
	}
	
	
	/**
	 * Verifica as datas de todos os objetos e determina que valor por na spinner da data inicial e que valor por na spinner da data final
	 * O objeto com a data mais antiga vai dar o valor à data inicial
	 * O objeto com a data mais recente vai dar o valor à data final
	 * 
	 * @throws ParseException
	 */
	public static void spinnerDate() throws ParseException
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataI = null;
		Date dataCheck = null;
		Date dataF = null;
		
		for(int i = 0;i<LoadSave.objetos.size();i++)
		{
			Objeto obj = LoadSave.objetos.get(i);
			if(i==0)
			{
				dataI = formato.parse(obj.getData());
				dataF = formato.parse(obj.getData());
			}
			else
			{
				dataCheck = formato.parse(obj.getData());
				if(dataCheck.before(dataI))
				{
					dataI = formato.parse(obj.getData());
				}
				else if (dataCheck.after(dataF))
				{
					dataF = formato.parse(obj.getData());
				}
				
			}
			
		}
		
		framePrincipal.spDataI.setValue(dataI);
		framePrincipal.spDataF.setValue(dataF);
		
	}
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel listagem, tambem tornando visivel a jDescriçao se esta se encontrar escondida
	 * ou se ja estiver visivel o seu texto é apagado
	 */
	public static void pressListagem()
	{
		if(framePrincipal.pDoar.isVisible())
		{
			framePrincipal.pDoar.setVisible(false);
		}
		
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pListagem);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Listagem");
		framePrincipal.mI.setTitle("ANUBIS - Listagem");
		framePrincipal.cbModelBloco.setSelectedItem("");
		framePrincipal.cbModelSala.setSelectedItem("");
		framePrincipal.cbModelTObj.setSelectedItem("");
		framePrincipal.cBListEstado.setSelectedItem("");
		framePrincipal.cBListCor.setSelectedItem("");
		
		if(!jDescricao.jD.isVisible())
		{
			jDescricao.jD.setVisible(true);
		}
		else
		{
			jDescricao.txtDescricao.setText("");
		}
		universalManager.moveDescricao();
	}
	
	
	/**
	 * Através da frame jDescrição, este metodo sabe qual a linha da tabela que esta selecionada e escreve a descriçao no textPane da jDescriçao
	 */
	public static void showDescricao()
	{
		for(Objeto obj: LoadSave.objetos)
		{
			if(obj.getCodigoObjeto().equals(framePrincipal.tableListagem.getValueAt(framePrincipal.tableListagem.getSelectedRow(), 0)))
					{
						jDescricao.txtDescricao.setText(obj.getDescricao());
						break;
					}
		}
	}
	
	
	/**
	 * importa todos os objetos para a tabela da listagem
	 */
	public static void tableListagem()
	{
		framePrincipal.dtmListagem.setRowCount(0);
		framePrincipal.dtmListagem.setColumnCount(0);
		framePrincipal.dtmListagem.addColumn("Código");
		framePrincipal.dtmListagem.addColumn("Data");
		framePrincipal.dtmListagem.addColumn("Hora");
		framePrincipal.dtmListagem.addColumn("Bloco");
		framePrincipal.dtmListagem.addColumn("Sala");
		framePrincipal.dtmListagem.addColumn("Tipo Objeto");
		framePrincipal.dtmListagem.addColumn("Cor");
		framePrincipal.dtmListagem.addColumn("Estado");
		
		
		for(Objeto obj: LoadSave.objetos)
		{
			framePrincipal.dtmListagem.addRow(new Object[]{obj.getCodigoObjeto(),obj.getData(), obj.getHora(), obj.getBloco(), obj.getSala(), obj.getTipoDeObjeto(), obj.getCor(), obj.getEstado()});
		}
		
	}
	
	
	/**
	 * Permite ordenar a tabela da listagem utilizando os requisitos impostos pelo utilizador nas comboBox e nas spinners
	 */
	public static void sortTableListagem()
	{
		tableListagem();
		try
		{
			
			
			
			
			//DATA INICIAL E FINAL
			
			framePrincipal.dtmListagem.setRowCount(0);
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataI = (Date) framePrincipal.spDataI.getValue();
			Date dataCheck = null;
			Date dataF = (Date) framePrincipal.spDataF.getValue();
			
			for (Objeto obj : LoadSave.objetos) 
			{
				dataCheck = formato.parse(obj.getData());
				if ((dataCheck.before(dataF) || dataCheck.equals(dataF)) && (dataCheck.after(dataI) || dataCheck.equals(dataI))) 
				{
					framePrincipal.dtmListagem.addRow(new Object[]{obj.getCodigoObjeto(),obj.getData(), obj.getHora(), obj.getBloco(), obj.getSala(), obj.getTipoDeObjeto(), obj.getCor(), obj.getEstado()});
				}
			}
			
			
			
			
			//TIPO DE OBJETO
			
			if(!(framePrincipal.cBListTObj.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmListagem.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmListagem.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				
				
				framePrincipal.dtmListagem.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					if(fields[5].equals(framePrincipal.cBListTObj.getSelectedItem().toString()))
					{
						
						
						framePrincipal.dtmListagem.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],fields[7]});
					}
					
					
				}
			}
			
			//BLOCO
			
			if(!(framePrincipal.cBListBloco.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmListagem.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmListagem.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				framePrincipal.dtmListagem.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[3].equals(framePrincipal.cBListBloco.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmListagem.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],fields[7]});
					}
					
					
				}
			}
			
			//SALA
			
			if(!(framePrincipal.cBListSala.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmListagem.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmListagem.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				
				framePrincipal.dtmListagem.setRowCount(0);
				
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[4].equals(framePrincipal.cBListSala.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmListagem.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],fields[7]});
					}
					
					
				}
			}
			
			//COR
			
			if(!(framePrincipal.cBListCor.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmListagem.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmListagem.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				framePrincipal.dtmListagem.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[6].equals(framePrincipal.cBListCor.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmListagem.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
					}
					
					
				}
			}
			
			//ESTADO
			
			if(!(framePrincipal.cBListEstado.getSelectedItem().toString().equals("")))
			{
				sortObjetos.clear();
				
				for(int i = 0; i<framePrincipal.dtmListagem.getRowCount();i++)
				{
					String s1 = new String(framePrincipal.dtmListagem.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 3).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 4).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 5).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 6).toString() + "#" + framePrincipal.dtmListagem.getValueAt(i, 7).toString());
					sortObjetos.add(s1);
				}
				
				framePrincipal.dtmListagem.setRowCount(0);
				
				for (int i = 0; i< sortObjetos.size();i++)
				{
					String Sobj = sortObjetos.get(i);
					String[] fields = Sobj.toString().split("#");
					
					if(fields[7].equals(framePrincipal.cBListEstado.getSelectedItem().toString()))
					{
						
						framePrincipal.dtmListagem.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]});
					}
					
					
				}
			}
			
		}
		catch(Exception p)
		{
			
		}
		
		
	}

}
