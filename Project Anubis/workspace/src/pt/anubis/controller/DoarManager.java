package pt.anubis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import pt.anubis.model.Objeto;
import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;
import pt.anubis.view.jDescricao;


/**
 * 
 * Métodos que são usados na preparação e execução das Doações do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class DoarManager {
	
	private static ArrayList<String> sortObjetos = new ArrayList<String>();
	private static String[] codigo = new String[LoadSave.objetos.size()];
	
	/**
	 * verifica se existem objetos
	 */
	public static void checkObjetosDoar()
	{
		if(LoadSave.contObjetos == 0)
		{
			JOptionPane.showMessageDialog(null,"Não existem objetos a doar!!!");
		}
		else
		{
			pressDoar();
			tableDoar();
		}
	}
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Doações
	 */
	public static void pressDoar()
	{
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pDoar);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Doar Objetos");
		framePrincipal.mI.setTitle("ANUBIS - Doar Objetos");
		jDescricao.txtDescricao.setText("");
		framePrincipal.cBDoarTObj.setSelectedItem("");
		
		if(!(jDescricao.jD.isVisible()))
		{
			jDescricao.jD.setVisible(true);
		}
		
		universalManager.moveDescricao();
	}
	
	/**
	 * abre uma frame de lado que permite obter a descrição dos objetos selecionados na tabela
	 */
	public static void showDescricao()
	{
		
		
		for(int i = 0; i<LoadSave.objetos.size();i++)
		{
			Objeto obj = LoadSave.objetos.get(i);
			if((obj.getCodigoObjeto().equals(framePrincipal.tableDoar.getValueAt(framePrincipal.tableDoar.getSelectedRow(), 0))))
					{
						jDescricao.txtDescricao.setText(obj.getDescricao());
						break;
					}
		}
	}
	
	/**
	 * importa para a tabela todos os objetos que possam ser doados
	 */
	public static void tableDoar()
	{
		framePrincipal.dtmDoar.setRowCount(0);
		framePrincipal.dtmDoar.setColumnCount(0);
		framePrincipal.dtmDoar.addColumn("Código");
		framePrincipal.dtmDoar.addColumn("Data");
		framePrincipal.dtmDoar.addColumn("Tipo Objeto");
		framePrincipal.dtmDoar.addColumn("Instituição Associada");
		
		objetosDoar();
		
		
	}
	
	/**
	 * verifica quais são os objetos que possam ser doados
	 * os objetos que podem ser doados têm de ter sido registados à mais de 1 mês
	 * lê as datas dos objetos e verifica com a data de sistema se já passou 1 mês
	 * 
	 */
	public static void objetosDoar()
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		int cont = 0;
		
		for(int i = 0;i<LoadSave.objetos.size();i++)
		{
			Objeto obj = LoadSave.objetos.get(i);
			try {
				String dataFinal = "";
				Calendar c = Calendar.getInstance();
				c.setTime(formato.parse(obj.getData()));
				c.add(Calendar.MONTH,1);
				dataFinal = formato.format(c.getTime());
				Date dataF = formato.parse(dataFinal);
				
				Date currentDate = formato.parse(universalManager.formatData);
				if(currentDate.after(dataF) && (obj.getEstado().equals("Bom") || obj.getEstado().equals("Razoável")))
				{
					cont++;
					for(TipoObjeto tObj : LoadSave.tipos)
					{
						
						if(obj.getTipoDeObjeto().equals(tObj.getNomeObjeto()) && !(tObj.getAssociado().equals("Não Associado")))
						{
							framePrincipal.dtmDoar.addRow(new Object[]{obj.getCodigoObjeto(), obj.getData(), obj.getTipoDeObjeto(), tObj.getAssociado()});
							codigo[i] = obj.getCodigoObjeto();
							
						}
							
					}
					
				}
				
				
				
				
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
			
		if(cont == 0)
		{
			JOptionPane.showMessageDialog(null,"Não existem objetos a doar!!!");
		}
		
		
	}
	
	/**
	 * permite ordenar a tabela dos objetos que possam ser doados
	 * ordena por tipo de objeto, bloco e sala
	 */
	public static void sortTableDoar()
	{
		if(framePrincipal.pDoar.isVisible())
		{
			try{
				tableDoar();
				
				if(!(framePrincipal.cBListTObj.getSelectedItem().toString().equals("")))
				{
					sortObjetos.clear();
					
					for(int i = 0; i<framePrincipal.dtmDoar.getRowCount();i++)
					{
						String s1 = new String(framePrincipal.dtmDoar.getValueAt(i, 0).toString() + "#" + framePrincipal.dtmDoar.getValueAt(i, 1).toString() + "#" + framePrincipal.dtmDoar.getValueAt(i, 2).toString() + "#" + framePrincipal.dtmDoar.getValueAt(i, 3).toString());
						sortObjetos.add(s1);
					}
					
					framePrincipal.dtmDoar.setRowCount(0);
					
					for (int i = 0; i< sortObjetos.size();i++)
					{
						String Sobj = sortObjetos.get(i);
						String[] fields = Sobj.toString().split("#");
						if(fields[2].equals(framePrincipal.cBDoarTObj.getSelectedItem().toString()))
						{
							
							
							framePrincipal.dtmDoar.addRow(new String[]{fields[0], fields[1], fields[2], fields[3]});
						}
						
						
					}
				}
			}catch(Exception p){}
		}
		
		
	}
	
	/**
	 * executa a doação de um objeto para a instituição associada ao tipo de objeto
	 * 
	 */
	public static void Doar() throws FileNotFoundException
	{
		if(!(framePrincipal.tableDoar.getSelectedRow() == -1))
		{
			String path = "instituicaoFicheiros/" + framePrincipal.dtmDoar.getValueAt(framePrincipal.tableDoar.getSelectedRow(), 3).toString() + ".txt";
			File instituicaoFile = new File(path);
			FileOutputStream fos = new FileOutputStream(instituicaoFile,true);
			PrintWriter out = new PrintWriter(fos);
			
			for(int i = 0; i<LoadSave.objetos.size();i++)
			{
				Objeto obj = LoadSave.objetos.get(i);
				if(framePrincipal.dtmDoar.getValueAt(framePrincipal.tableDoar.getSelectedRow(), 0).toString().equals(obj.getCodigoObjeto()))
				{
					out.println(obj.getCodigoObjeto() + "#" + obj.getNome() + "#" + obj.getEmail() + "#" + obj.getBloco() + "#" + obj.getSala() + "#" + obj.getData() + "#" + obj.getHora() + "#" + obj.getTipoDeObjeto() + "#" + obj.getCor() + "#" + obj.getEstado() + "#" + obj.getDescricao());
					LoadSave.objetos.remove(i);
				}
			}
			out.close();
			framePrincipal.lblEstado.setText("Objeto doado com sucesso!");
			framePrincipal.cbModelTObj.setSelectedItem("");
			jDescricao.txtDescricao.setText("");
			tableDoar();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Não existe nenhum objeto selecionado!");
		}
		
	}


}
