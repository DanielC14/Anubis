package pt.anubis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import pt.anubis.model.Objeto;
import pt.anubis.view.framePrincipal;

/**
 * 
 * Métodos que são usados na preparação e execução da Listagem do programa
 * 
 * @author Daniel Carneiro
 *
 */
public class RegistoManager {
	
	public static String codigoObjeto = "";
	public static String codigoTipoObjeto;
	
	/**
	 * remove todos os elementos do painel Mestre e coloca o painel registar
	 */
	public static void pressRegistar()
	{
		if(framePrincipal.pDoar.isVisible())
		{
			framePrincipal.pDoar.setVisible(false);
		}
		
		framePrincipal.timer.start();
		framePrincipal.lblEstado.setText("");
		framePrincipal.pMestre.removeAll();
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.pMestre.add(framePrincipal.pRegistar);
		framePrincipal.pMestre.repaint();
		framePrincipal.pMestre.revalidate();
		framePrincipal.lblMenu.setText("Registar Objeto");
		framePrincipal.mI.setTitle("ANUBIS - Registar Objeto");
		framePrincipal.cbModelBloco.setSelectedItem("");
		framePrincipal.cbModelSala.setSelectedItem("");
		framePrincipal.cbModelTObj.setSelectedItem("");
		framePrincipal.txtNome.setText("");
		framePrincipal.txtEmail.setText("");
		framePrincipal.txtDescricao.setText("");
		currentDate();
	}
	/**
	 * junta todos os dados fornecidos pelo utilizador e regista o objeto
	 */
	public static void registar(String nome, String email,String bloco, String sala,
			String data, String hora, String tipoObjeto, String cor, String estado, String descricao)
	{
		if(!(nome.trim().isEmpty() || email.trim().isEmpty() || bloco.trim().isEmpty() || sala.trim().isEmpty() || tipoObjeto.trim().isEmpty() || descricao.trim().isEmpty()))
		{
			for(Objeto obj: LoadSave.objetos)
			{
				codigoObjeto = obj.getCodigoObjeto();
				
			}
			
			if(codigoObjeto.equals(""))
			{
				codigoObjeto = "1";
			}
			else
			{
				
				int codigo = Integer.parseInt(codigoObjeto) + 1;
				codigoObjeto = Integer.toString(codigo);
			}
			
			
			
			
			Objeto obj = new Objeto(codigoObjeto, nome, email,bloco, sala, data, hora, tipoObjeto, cor, estado, descricao);
			LoadSave.objetos.add(obj);
			LoadSave.contObjetos++;
			framePrincipal.lblQuantidade.setText(Integer.toString(LoadSave.contObjetos));
			framePrincipal.lblEstado.setText("Objeto registado com sucesso!");
			framePrincipal.txtNome.setText("");
			framePrincipal.txtEmail.setText("");
			framePrincipal.txtDescricao.setText("");
			framePrincipal.cbModelBloco.setSelectedItem("");
			framePrincipal.cbModelSala.setSelectedItem("");
			framePrincipal.cbModelTObj.setSelectedItem("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		}
		
	}
	
	/**
	 * permite obter a data e hora atual para aparecer no registo
	 */
	public static void currentDate()
	{
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String formatData = data.format(new Date()).toString();
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String formatHora = hora.format(new Date()).toString();
		
		framePrincipal.txtData.setText(formatData);
		framePrincipal.txtHora.setText(formatHora);
		
	}
	
	
}
