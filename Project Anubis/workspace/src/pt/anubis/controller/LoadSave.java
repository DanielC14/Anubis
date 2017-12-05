package pt.anubis.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.anubis.model.Instituição;
import pt.anubis.model.Objeto;
import pt.anubis.model.Permissoes;
import pt.anubis.model.Sala;
import pt.anubis.model.TipoObjeto;
import pt.anubis.model.Utilizador;
import pt.anubis.view.frameSecundaria;

public class LoadSave {
	
	public static String tipoUser;
	public static String nomeUser;
	public static boolean res = false;
	public static String codigoObjeto = "";
	public static String codigoTipoObjeto;
	public static ArrayList<Utilizador> users = new ArrayList<Utilizador>();
	
	static File pastaFiles = new File("ficheiros");
	
	static File usersFile = new File ("ficheiros/user.txt");
	static ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	static File objetosFile = new File ("ficheiros/objetos.txt");
	static File tipoObjetoFile = new File("ficheiros/tipoObjeto.txt");
	static File instituicaoFile = new File("ficheiros/instituicao.txt");
	static ArrayList<TipoObjeto> tipos = new ArrayList<TipoObjeto>();
	static File salaFile = new File("ficheiros/salas.txt");
	public static ArrayList<Sala> salas = new ArrayList<Sala>();
	public static ArrayList<Instituição> instituicoes = new ArrayList<Instituição>();
	public static int contObjetos;
	public static int contNAssociados;
	public static File pastaFicheiros = new File("objetos");
	public static File listaFicheiros[];
	
	
	
	
	
	
	//LOAD
	/**
	 * Load dos utilizadores presentes no ficheiro de texto respetivo
	 */
	public static void loadUtilizador() throws Exception
	{
		if(!pastaFiles.exists())
		{
			pastaFiles.mkdir();
		}
		if(usersFile.exists()==false)
		{
			
			usersFile.createNewFile();
			
		}
		
		Scanner inFile = new Scanner(usersFile);
		
		
		while(inFile.hasNextLine())
		{
			String line = inFile.nextLine();
			String[] fields = line.split("#");
			
			Utilizador u = new Utilizador(fields[0], fields[1],fields[2],fields[3]);
			u.getPerms().add(new Permissoes(Boolean.parseBoolean(fields[4]),Boolean.parseBoolean(fields[5]), Boolean.parseBoolean(fields[6]), Boolean.parseBoolean(fields[7]), Boolean.parseBoolean(fields[8]), Boolean.parseBoolean(fields[9])));
			users.add(u);
			
		}
		
		
		
		inFile.close();
	}
	
	/**
	 * Load das instituições presentes no ficheiro de texto repetivo
	 * 
	 */
	public static void loadInstituicao() throws Exception
	{
		if(instituicaoFile.exists()==false)
		{
			instituicaoFile.createNewFile();
		}
		Scanner inFile = new Scanner(instituicaoFile);
		while(inFile.hasNextLine())
		{
			String line = inFile.nextLine();
			String[] fields = line.split("#");
			Instituição i = new Instituição(fields[0], fields[1]);
			instituicoes.add(i);
		}
		inFile.close();
	}
	
	/**
	 * Caso seja iniciada sessao por uma instituição
	 * faz o load dos objetos doados para essa instituição
	 * para serem apresentados na listagem
	 */
	public static void loadObjetoInstituicao(String instituicao) throws Exception
	{
		
		File pastaInstituicao = new File("instituicaoFicheiros");
		if(!pastaInstituicao.exists())
		{
			pastaInstituicao.mkdir();
		}
			contObjetos = 0;
			
			objetos.clear();
		
			File listagemInstituicao = new File("instituicaoFicheiros/" + instituicao + ".txt");
			
			Scanner inFile = new Scanner(listagemInstituicao);
			while(inFile.hasNextLine())
			{
				String line = inFile.nextLine();
				String[] fields = line.split("#");
				
				Objeto obj = new Objeto(fields[0], fields[1],fields[2],fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9],fields[10]);
				objetos.add(obj);
				contObjetos++;
			}
			inFile.close();
			
			
		}
	
	
	public static File[] getFilesSortByDate(File folder) {
        if (!folder.isDirectory()) return null;
        
        File files[] = folder.listFiles();
        Arrays.sort( files, new Comparator<Object>()
        {
            public int compare(final Object o1, final Object o2) {
                return new Long(((File)o1).lastModified()).compareTo
                     (new Long(((File) o2).lastModified()));
            }
        }); 
        return files;
    }  
		
	/**
	 * Load de todos os objetos nao reclamados nos ficheiros de texto de datas
	 * 
	 */
	public static void loadObjeto() throws Exception
	{
		if(!pastaFicheiros.exists())
		{
			pastaFicheiros.mkdir();
		}
		
		contObjetos = 0;
		objetos.clear();
		listaFicheiros = getFilesSortByDate(pastaFicheiros);
		for(int i = 0; i < listaFicheiros.length;i++)
		{
			File objetoData = new File(listaFicheiros[i].toString());
			
			Scanner inFile = new Scanner(objetoData);
			while(inFile.hasNextLine())
			{
				String tipoDeObjeto = "";
				String line = inFile.nextLine();
				String[] fields = line.split("#");
				for(TipoObjeto tObj : tipos)
				{
					if(fields[7].equals(""))
					{
						tipoDeObjeto = "Apagado";
					}
					if(fields[7].equals(tObj.getCodigo()))
					{
						tipoDeObjeto = tObj.getNomeObjeto();
					}
				}
				Objeto obj = new Objeto(fields[0], fields[1],fields[2],fields[3], fields[4], fields[5], fields[6], tipoDeObjeto, fields[8], fields[9],fields[10]);
				objetos.add(obj);
				contObjetos++;
			}
			inFile.close();
			
			
		}
		
	}
	
	/**
	 * Load dos tipos de objetos presentes no ficheiro de texto respetivo
	 * 
	 */
	public static void loadTipoObjeto() throws Exception
	{
		contNAssociados = 0;
		
		if(tipoObjetoFile.exists()==false)
		{
			tipoObjetoFile.createNewFile();
		}
		Scanner inFile = new Scanner(tipoObjetoFile);
		while(inFile.hasNextLine())
		{
			
			String associado = null;
			
			String line = inFile.nextLine();
			String[] fields = line.split("#");
			if(!fields[2].equals("Não Associado"))
			{
				for(Instituição inst: instituicoes)
				{
					if(fields[2].equals(inst.getCodigo()))
					{
						associado = inst.getNome();
					}
				}
				TipoObjeto tobj = new TipoObjeto(fields[0], fields[1], associado);
				tipos.add(tobj);
			}
			else
			{
				TipoObjeto tobj = new TipoObjeto(fields[0], fields[1], "Não Associado");
				tipos.add(tobj);
				contNAssociados++;
			}
			
			
			
			
		}
		inFile.close();
		
	}
	
	/**
	 * Load dos blocos e das salas presentes no ficheiro de texto respetivo
	 * 
	 */
	public static void loadSala() throws Exception
	{
		
		if(salaFile.exists()==false)
		{
			salaFile.createNewFile();
		}
		Scanner inFile = new Scanner(salaFile);
		while(inFile.hasNextLine())
		{
			String line = inFile.nextLine();
			String[] fields = line.split("#");
			Sala s = new Sala(fields[0], fields[1]);
			salas.add(s);
		}
		inFile.close();
		
	}
		
	
	
	
	//SAVE
	
	/**
	 * Limpa os ficheiros onde vao ser guardados os objetos
	 * para em saveObjeto guardar por datas
	 */
	private static void clearFicheiros() throws ParseException, IOException
	{
		
		for(int i = 0; i < listaFicheiros.length;i++)
		{
			File objetoData = new File(listaFicheiros[i].toString());
			PrintWriter out = new PrintWriter(objetoData);
			out.print("");
			out.close();
			
		}
	}
	
	/**
	 * verifica os objetos a guardar e guarda no ficheiro dependendo da sua data de registo
	 */
	public static void saveObjeto() throws ParseException, IOException
	{
			clearFicheiros();
		
		String path = "objetos/";
		
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoFicheiro = new SimpleDateFormat("MM-yyyy");
		
		
			for (int i = 0; i<objetos.size();i++)
			{
				Objeto obj = objetos.get(i);
				String codigoTObjeto = "";
				String dataFinal = "";
				
				for(TipoObjeto tObjeto : tipos)
				{
					
					if(obj.getTipoDeObjeto().equals(tObjeto.getNomeObjeto()))
					{
						codigoTObjeto = tObjeto.getCodigo();
						break;
					}		
				}
				
				Calendar c = Calendar.getInstance();
				c.setTime(formato.parse(obj.getData()));
				dataFinal = formatoFicheiro.format(c.getTime());
				File objetoData = new File(path + dataFinal + ".txt");
				if(objetoData.exists()==false)
				{
					objetoData.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(objetoData,true);
				PrintWriter out = new PrintWriter(fos);
				out.println(obj.getCodigoObjeto() + "#" + obj.getNome() + "#" + obj.getEmail() + "#" + obj.getBloco() + "#" + obj.getSala() + "#" + obj.getData() + "#" + obj.getHora() + "#" + codigoTObjeto + "#" + obj.getCor() + "#" + obj.getEstado() + "#" + obj.getDescricao());
				out.close();
			}
		
		
	}
	
	/**
	 * guarda os tipos de objeto no ficheiro de texto 
	 */
	public static void saveTipoObjeto() throws FileNotFoundException
	{
		String codigo = null;
		
		PrintWriter out = new PrintWriter(tipoObjetoFile);
		if(tipos.size()>0)
		{
			for (TipoObjeto tobj: tipos)
			{
				if(!tobj.getAssociado().equals("Não Associado"))
				{
					for(Instituição inst: instituicoes)
					{
						if(tobj.getAssociado().equals(inst.getNome()))
						{
							codigo = inst.getCodigo();
							break;
						}
					}
					out.println(tobj.getCodigo() + "#" + tobj.getNomeObjeto() + "#" + codigo);
				}
				else
				{
					out.println(tobj.getCodigo() + "#" + tobj.getNomeObjeto() + "#" + "Não Associado");
				}
				
			}
			out.close();
		}
		
	}
	
	/**
	 * guarda os blocos e salas no ficheiro de texto
	 */
	public static void saveSala() throws FileNotFoundException
	{
		if(salas.size()>0)
		{
			PrintWriter out = new PrintWriter(salaFile);
			for (Sala sl: salas)
			{
				out.println(sl.getBloco() + "#" + sl.getSala());
			}
			out.close();
		}
		
	}
	
	/**
	 * guarda as instituiçoes no ficheiro de texto
	 */
	public static void saveInstituicao() throws FileNotFoundException
	{
		if(instituicoes.size()>0)
		{
			PrintWriter out = new PrintWriter("ficheiros/instituicao.txt");
			for (Instituição inst: instituicoes)
			{
				out.println(inst.getCodigo() + "#" + inst.getNome());
			}
			out.close();
		}
		
	}
	
	/**
	 * guarda todos os dados dos utilizadores no ficheiro de texto
	 */
	public static void saveUtilizador() throws FileNotFoundException
	{
		if(users.size()>0)
		{
			PrintWriter out = new PrintWriter(usersFile);
			for(int i = 0; i<users.size();i++	)
			{
				Utilizador utl = users.get(i);
				Permissoes perm = Utilizador.perms.get(i);
				out.println(utl.getTipoUtilizador() + "#" + utl.getNome() + "#" + utl.getUtilizador() + "#" + utl.getPassword() + "#" + Boolean.toString(perm.isRegisto()) + "#" + Boolean.toString(perm.isReclamacao()) + "#" + Boolean.toString(perm.isImportacao()) + "#" + Boolean.toString(perm.isListagens()) + "#" + Boolean.toString(perm.isDoacoes()) + "#" + Boolean.toString(perm.isConfiguracoes()) );
			}
			out.close();
		}
		
	}
	

}
