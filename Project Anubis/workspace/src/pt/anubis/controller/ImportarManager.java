package pt.anubis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import pt.anubis.model.Objeto;
import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;

/**
 * 
 * Métodos que são usados na preparação e execução da Importação do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class ImportarManager {
	
	private static JFileChooser fc = new JFileChooser();
	private static ArrayList<Objeto> imports = new ArrayList<Objeto>();
	private static int result;
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel de Importações
	 * 
	 */
	public static void pressImportar() throws FileNotFoundException
	{
		openFC();
		
		if(!(result == JFileChooser.CANCEL_OPTION))
		{
			imports.clear();
			
			framePrincipal.lblEstado.setText("");
			framePrincipal.pMestre.removeAll();
			framePrincipal.pMestre.repaint();
			framePrincipal.pMestre.revalidate();
			framePrincipal.pMestre.add(framePrincipal.pImportar);
			framePrincipal.pMestre.repaint();
			framePrincipal.pMestre.revalidate();
			framePrincipal.lblMenu.setText("Importar");
			framePrincipal.mI.setTitle("ANUBIS - Importar");
			
			
			framePrincipal.dtmImportar.setRowCount(0);
			framePrincipal.dtmImportar.setColumnCount(0);
			framePrincipal.dtmImportar.addColumn("Data");
			framePrincipal.dtmImportar.addColumn("Hora");
			framePrincipal.dtmImportar.addColumn("Bloco");
			framePrincipal.dtmImportar.addColumn("Sala");
			framePrincipal.dtmImportar.addColumn("Tipo Objeto");
			framePrincipal.dtmImportar.addColumn("Cor");
			framePrincipal.dtmImportar.addColumn("Estado");
			framePrincipal.dtmImportar.addColumn("Descrição");
			
			File iFicheiro = fc.getSelectedFile();
			
			
			Scanner inFile = new Scanner(iFicheiro);
			String tipoObjeto =  "";
			
			
			while(inFile.hasNextLine())
			{
				String line = inFile.nextLine();
				String[] fields = line.split("#");
				String[] dataHora = fields[0].split(" ");
				String[] dados = fields[1].split(";");
				
				for(TipoObjeto tObj: LoadSave.tipos)
				{
					if(dados[4].equals(tObj.getCodigo()))
					{
						tipoObjeto = tObj.getNomeObjeto();
					}
				}
				
				framePrincipal.dtmImportar.addRow(new Object[]{dataHora[0], dataHora[1], dados[2], dados[3], tipoObjeto, dados[5], dados[6], dados[7] });
				Objeto objeto = new Objeto("",dados[0], dados[1], dados[2], dados[3], dataHora[0], dataHora[1], tipoObjeto, dados[5], dados[6], dados[7]);
				imports.add(objeto);
				
				
			}
			inFile.close();
		}
		
		
	}
	
	/**
	 * permite remover um objeto importado do ficheiro de importações
	 * antes de este passar para o arrayList dos objetos
	 */
	public static void removerImport()
	{
		for(int i = 0; i<imports.size();i++)
		{
			Objeto iObj = imports.get(i);
			if(iObj.getData().equals(framePrincipal.tableImportar.getValueAt(framePrincipal.tableImportar.getSelectedRow(), 0).toString()) && iObj.getHora().equals(framePrincipal.tableImportar.getValueAt(framePrincipal.tableImportar.getSelectedRow(), 1).toString()))
					{
						imports.remove(i);
					}
			
		}
		
		framePrincipal.dtmImportar.setRowCount(0);
		
		for(Objeto iObj : imports)
		{
			
			framePrincipal.dtmImportar.addRow(new Object[]{iObj.getData(), iObj.getHora(), iObj.getBloco(),iObj.getSala(),iObj.getTipoDeObjeto(),iObj.getCor(),iObj.getEstado(), iObj.getDescricao()});
		}
		
		
		
	}
	
	/**
	 * passa todos os objetos importados para o arrayList principal
	 */
	public static void importar()
	{
		int codigoI = 0;
		String codigoS = null;
		
		for(Objeto obj : LoadSave.objetos)
		{
			codigoI = Integer.parseInt(obj.getCodigoObjeto());
		}
		
		for(Objeto iObj : imports)
		{
			
			codigoI++;
			codigoS = String.valueOf(codigoI);
			Objeto obj = new Objeto(codigoS, iObj.getNome(), iObj.getEmail(), iObj.getBloco(), iObj.getSala(), iObj.getData(), iObj.getHora(), iObj.getTipoDeObjeto(), iObj.getCor(), iObj.getEstado(), iObj.getDescricao());
			LoadSave.objetos.add(obj);
			
			codigoI = Integer.parseInt(codigoS);
		}
		
		framePrincipal.lblEstado.setText("Objetos importados com sucesso!");
		framePrincipal.dtmImportar.setRowCount(0);
		
	}
	
	/**
	 * abre o fileChooser que permite ao utilizador escolher o ficheiro de texto com as importações que deseja fazer
	 */
	public static void openFC()
	{
		fc.setDialogTitle("Importar Ficheiro");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		result = fc.showSaveDialog(null);
		fc.setVisible(true);
		
	}

}
