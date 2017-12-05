package pt.anubis.controller;

import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Date;

import pt.anubis.model.Instituição;
import pt.anubis.model.Sala;
import pt.anubis.model.TipoObjeto;
import pt.anubis.view.framePrincipal;
import pt.anubis.view.frameSecundaria;
import pt.anubis.view.jDescricao;

/**
 * Class com metodos que nao se encaixam em nenhuma outra e que podem ser usados quando necessario
 * 
 * @author Daniel Carneiro
 *
 */
public class universalManager {
	
	public static SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	public static String formatData = data.format(new Date()).toString();
	public static SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
	public static String formatHora = hora.format(new Date()).toString();
	
	/**
	 * poe a frame secundaria do lado direito da frame principal
	 */
	public static void moveFramePrincipal()
	{
		try
		{
			frameSecundaria.aU.setLocation((int) (Math.round(framePrincipal.mI.getLocationOnScreen().getX()) + framePrincipal.mI.getBounds().getWidth()),(int)Math.round(framePrincipal.mI.getLocationOnScreen().getY()));
		}catch(Exception e){
			
		}
		
	}
	
	/**
	 * poe a descrição do lado direito da frame principal
	 */
	public static void moveDescricao()
	{
		jDescricao.jD.setLocation((int) (Math.round(framePrincipal.mI.getLocationOnScreen().getX()) + framePrincipal.mI.getBounds().getWidth()),(int)Math.round(framePrincipal.mI.getLocationOnScreen().getY()));
	}
	
	/**
	 * minimiza a frame secundaria se a principal tambem se encontrar minimizada
	 */
	public static void hideFramePrincipal()
	{
		try
		{
			if(frameSecundaria.aU.isActive())
			{
				frameSecundaria.aU.setState(Frame.ICONIFIED);
			}
		}catch(Exception p){}
		
		
	}
	
	/**
	 * minimiza a frame principal se a secundaria tambem se encontrar minimizada
	 */
	public static void hideFrameSecundaria()
	{
		
			framePrincipal.mI.setState(Frame.ICONIFIED);
		
	}
	
	/**
	 * retoma ao estado normal a frame secundaria se a frame primaria tambem retomar
	 */
	public static void showFramePrincipal()
	{
		try
		{
		if(frameSecundaria.aU.isActive())
		{
			frameSecundaria.aU.setState(Frame.NORMAL);
		}
		}catch(Exception p){}
	}
	
	/**
	 * retoma ao estado normal a frame primaria se a frame secundaria tambem retomar
	 */
	public static void showFrameSecundaria()
	{
		framePrincipal.mI.setState(Frame.NORMAL);
	}
	
	public static void comboBoxTipoUser()
	{
		frameSecundaria.cBModelTUser.removeAllElements();
		frameSecundaria.cBModelTUser.addElement("Administrador");
		frameSecundaria.cBModelTUser.addElement("Instituição");
		frameSecundaria.cBModelTUser.addElement("Segurança");
	}
	
	/**
	 * carrega os modelos das comboBox com o tipo de objeto, blocos, salas e instituicoes
	 */
	public static void setupComboBox()
	{
		
		
		//INSTITUICAO
		
		framePrincipal.cbModelInst.removeAllElements();
		if(LoadSave.instituicoes.size()>0)
		{
			
			
			for(Instituição inst : LoadSave.instituicoes)
			{
				framePrincipal.cbModelInst.addElement(inst.getNome().toString());
			}
		}
		
		
		framePrincipal.cbModelBloco.removeAllElements();
		if(LoadSave.salas.size()>0)
		{
			
			// Setup comboBox para os Blocos
			framePrincipal.cbModelBloco.addElement("");
			for (Sala blc : LoadSave.salas) {
				boolean existe = false;
				if (framePrincipal.cbModelBloco.getSize() == 1) {
					framePrincipal.cbModelBloco.addElement(blc.getBloco());
				} else {
					for (int i = 0; i < framePrincipal.cbModelBloco.getSize(); i++) {
						if (blc.getBloco().equals(
								framePrincipal.cbModelBloco.getElementAt(i))) {
							existe = true;
						}
					}
					if (existe == false) {
						framePrincipal.cbModelBloco.addElement(blc.getBloco());
					}
				}
			}
		}
		
		
		framePrincipal.cbModelTObj.removeAllElements();
		if(LoadSave.tipos.size()>0)
		{
			// Setup comboBox para os Tipos de Objeto
			
			framePrincipal.cbModelTObj.addElement("");
			for (TipoObjeto tobj : LoadSave.tipos) {
				framePrincipal.cbModelTObj.addElement(tobj.getNomeObjeto());
			}
		}
		
		
	}
	
	/**
	 * carrega o modelo das comboBox para as salas dependendo do bloco escolhido
	 */
	public static void comboBoxSalas()
	{
		
		framePrincipal.cbModelSala.removeAllElements();
		if(LoadSave.salas.size()>0)
		{
			
			
			if(framePrincipal.cBBloco.getSelectedIndex() != -1)
			{
				framePrincipal.cbModelSala.removeAllElements();
				framePrincipal.cbModelSala.addElement("");
				for(Sala sl:LoadSave.salas)
				{
					if(framePrincipal.cBBloco.getSelectedItem().toString().equals(sl.getBloco()))
					{
						framePrincipal.cbModelSala.addElement(sl.getSala());
					}
				}
			}
		}
		
	}
	
	/**
	 * verifica se a variavel que conta o numero de tipos de objetos que nao tem instituiçao associada é maior que 0
	 * para emitir um aviso
	 */
	public static void checkTONaoAssociado()
	{
		if(LoadSave.contNAssociados>0)
		{
			framePrincipal.lblAviso.setVisible(true);
		}
		else{
			framePrincipal.lblAviso.setVisible(false);
		}
	}

}
