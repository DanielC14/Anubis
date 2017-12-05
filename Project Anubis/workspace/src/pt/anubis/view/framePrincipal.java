package pt.anubis.view;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import pt.anubis.controller.ConfigInstManager;
import pt.anubis.controller.ConfigObjetosManager;
import pt.anubis.controller.ConfigSalaManager;
import pt.anubis.controller.ConfigUtilizadorManager;
import pt.anubis.controller.DoarManager;
import pt.anubis.controller.ImportarManager;
import pt.anubis.controller.ListagemManager;
import pt.anubis.controller.LoadSave;
import pt.anubis.controller.LoginManager;
import pt.anubis.controller.ReclamarManager;
import pt.anubis.controller.RegistoManager;
import pt.anubis.controller.universalManager;

public class framePrincipal extends JFrame{

	private JPanel contentPane;
	public static JTextField txtEmail;
	public static JTextField txtNome;
	public static JTextPane txtDescricao;
	public static JTextField txtAddInst;
	public static JTextField txtEditInst;
	public static JTable tableReclamar;
	public static JTable tableDoar;
	public static JTable tableListagem;
	public static JTable tableConfigInst;
	public static JTextField txtAddSala;
	public static JTextField txtEditSala;
	public static JTable tableConfigSala;
	public static JTextField txtAdicionarTObj;
	public static JTextField txtEditarTObj;
	public static JTable tableConfigObjeto;
	private JTable tableConfigUtilizador;
	public static JTable tableConfigUtilizadorr;
	
	public static DefaultTableModel dtmConfigObj = new DefaultTableModel();
	public static DefaultTableModel dtmReclamar = new DefaultTableModel();
	public static DefaultTableModel dtmListagem = new DefaultTableModel();
	public static DefaultTableModel dtmDoar = new DefaultTableModel();
	public static DefaultTableModel dtmConfigInst = new DefaultTableModel();
	public static DefaultTableModel dtmConfigSala = new DefaultTableModel();
	public static DefaultTableModel dtmConfigUtilizador = new DefaultTableModel();
	public static DefaultTableModel dtmImportar = new DefaultTableModel();
	
	public static DefaultComboBoxModel<String> cbModelBloco = new DefaultComboBoxModel<String>();
	public static DefaultComboBoxModel<String> cbModelTObj = new DefaultComboBoxModel<String>();
	public static DefaultComboBoxModel<String> cbModelSala = new DefaultComboBoxModel<String>();
	public static DefaultComboBoxModel<String> cbModelInst = new DefaultComboBoxModel<String>();
	
	public static JLabel lblQuantidade;
	public static JLabel lblUser;
	public static JLabel lblMenu;
	public static JLabel lblEstado;
	public static JLabel lblAviso;
	public static JLabel lblObjReclamar;
	
	public static JComboBox<String> cBBloco;
	public static JComboBox<String> cBRecBloco;
	public static JComboBox<String> cBRecTObj;
	public static JComboBox<String> cBRecSala;
	public static JComboBox<String> cBListTObj;
	public static JComboBox<String> cBListBloco;
	public static JComboBox<String> cBListSala;
	public static JComboBox cBListCor;
	public static JComboBox cBListEstado;
	public static JComboBox cBDoarTObj;
	public static JComboBox cBEditTObj;
	public static framePrincipal mI;
	
	
	public static JPanel pMestre;
	public static JPanel pReclamar;
	public static JPanel pListagem;
	public static JPanel pRegistar;
	public static JPanel pDoar;
	public static JPanel pConfigInst;
	public static JPanel pConfigSala;
	public static JPanel pConfigUtilizador;
	public static JPanel pConfigObjeto;
	public static JPanel pImportar;
	public static JPanel pInicio;
	
	public static JButton btnRegistarObj;
	public static JButton btnReclamarObj;
	public static JButton btnListagem;
	public static JButton btnDoar;
	public static JButton btnImportar;
	public static JButton btnConfigurar;
	
	
	public static JTextField txtData;
	public static JTextField txtHora;
	public static JTextField txtAddBloco;
	public static JTextField txtEditBloco;
	
	public static Timer timer;
	public static JTable tableImportar;
	
	public static JSpinner spDataI;
	public static JSpinner spDataF;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					framePrincipal frame = new framePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public framePrincipal() {
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(mI, 
		            "Tem a certeza que quer sair?", "Confirmar Fecho", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						try {
							if(!LoginManager.tipoUtilizador.equals("Instituição"))
							{
								LoadSave.saveObjeto();
							}
						} catch (ParseException y) {
							y.printStackTrace();
						} catch (IOException y) {
							y.printStackTrace();
						}
						LoadSave.saveTipoObjeto();
						LoadSave.saveInstituicao();
						LoadSave.saveSala();
						LoadSave.saveUtilizador();
					} catch (FileNotFoundException q) {
						q.printStackTrace();
					}
		        	
		            System.exit(0);
		        }
		    }
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent arg0) {
				if(frameSecundaria.aU.isActive())
				{
					universalManager.hideFramePrincipal();
				}
				
				
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				universalManager.showFramePrincipal();
			}
		});
		
		
		setIconImage(new ImageIcon(getClass().getResource("/anubisicon.png")).getImage());
		setTitle("ANUBIS - Menu Inicial");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		setBounds(642, 251, 713, 548);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setForeground(Color.BLACK);
		lblIcon.setBackground(Color.WHITE);
		lblIcon.setBounds(20, 8, 254, 60);
		contentPane.add(lblIcon);
		Image icon = new ImageIcon (this.getClass().getResource("/anubis60xx.png")).getImage();
		lblIcon.setIcon(new ImageIcon(icon));
		
		lblMenu = new JLabel("Menu Inicial");
		lblMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMenu.setBounds(383, 8, 293, 60);
		contentPane.add(lblMenu);
		
		JPanel pMenuMestre = new JPanel();
		pMenuMestre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pMenuMestre.setBounds(10, 79, 135, 416);
		contentPane.add(pMenuMestre);
		pMenuMestre.setLayout(null);
		
		lblQuantidade = new JLabel("");
		lblQuantidade.setBounds(10, 354, 53, 14);
		pMenuMestre.add(lblQuantidade);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		JLabel label_4 = new JLabel("Sess\u00E3o Iniciada como:");
		label_4.setBounds(10, 376, 111, 14);
		pMenuMestre.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 9));
		
		lblUser.setBounds(10, 391, 94, 14);
		pMenuMestre.add(lblUser);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		lblObjReclamar = new JLabel("Objetos por Reclamar:");
		lblObjReclamar.setBounds(10, 339, 139, 14);
		pMenuMestre.add(lblObjReclamar);
		lblObjReclamar.setFont(new Font("Tahoma", Font.BOLD, 9));
		
		JPanel pMenus = new JPanel();
		pMenus.setBounds(2, 2, 130, 286);
		pMenuMestre.add(pMenus);
		pMenus.setLayout(new CardLayout(0, 0));
		
		JPanel pMTotal = new JPanel();
		pMenus.add(pMTotal, "name_871242602280255");
		pMTotal.setLayout(null);
		
		btnReclamarObj = new JButton("Reclamar Objeto");
		btnReclamarObj.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnReclamarObj.setBounds(8, 45, 113, 23);
		pMTotal.add(btnReclamarObj);
		
		btnListagem = new JButton("Listagem");
		btnListagem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnListagem.setBounds(8, 79, 113, 23);
		pMTotal.add(btnListagem);
		
		btnDoar = new JButton("Doar Objeto");
		btnDoar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnDoar.setBounds(8, 113, 113, 23);
		pMTotal.add(btnDoar);
		
		btnImportar = new JButton("Importar Lista");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(jDescricao.jD.isVisible())
					{
						jDescricao.jD.setVisible(false);
					}
				}catch(Exception p){}
				
				try {
					ImportarManager.pressImportar();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnImportar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnImportar.setBounds(8, 147, 113, 23);
		pMTotal.add(btnImportar);
		
		btnConfigurar = new JButton("Configura\u00E7\u00F5es");
		btnConfigurar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnConfigurar.setBounds(8, 181, 113, 23);
		pMTotal.add(btnConfigurar);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					if(jDescricao.jD.isVisible())
					{
						jDescricao.jD.setVisible(false);
					}
				}catch(Exception p){}
				
				try {
					try {
						if(!LoginManager.tipoUtilizador.equals("Instituição"))
						{
							LoadSave.saveObjeto();
						}
						
					} catch (ParseException y) {
						y.printStackTrace();
					} catch (IOException y) {
						y.printStackTrace();
					}
					LoadSave.saveTipoObjeto();
					LoadSave.saveInstituicao();
					LoadSave.saveSala();
					LoadSave.saveUtilizador();
				} catch (FileNotFoundException q) {
					q.printStackTrace();
				}
				
				dispose();
				loginAnubis lA = new loginAnubis();
				lA.setVisible(true);
				
			}
		});
		btnLogout.setBounds(8, 249, 113, 23);
		pMTotal.add(btnLogout);
		
		btnRegistarObj = new JButton("Registar Objeto");
		btnRegistarObj.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnRegistarObj.setBounds(8, 11, 113, 23);
		pMTotal.add(btnRegistarObj);
		
		JButton btnFecharPainel = new JButton("Menu Inicial");
		btnFecharPainel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					if(jDescricao.jD.isVisible())
					{
						jDescricao.jD.setVisible(false);
					}
				}catch(Exception p){}
				
				framePrincipal.pMestre.removeAll();
				framePrincipal.pMestre.repaint();
				framePrincipal.pMestre.revalidate();
				framePrincipal.pMestre.add(framePrincipal.pInicio);
				framePrincipal.pMestre.repaint();
				framePrincipal.pMestre.revalidate();
				framePrincipal.lblEstado.setText("");
				framePrincipal.lblMenu.setText("Menu Inicial");
				framePrincipal.mI.setTitle("ANUBIS - Menu Inicial");
			}
		});
		btnFecharPainel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFecharPainel.setBounds(8, 215, 113, 23);
		pMTotal.add(btnFecharPainel);
		
		
		
		
		
		JPanel pMConfig = new JPanel();
		pMenus.add(pMConfig, "name_871242593378287");
		pMConfig.setLayout(null);
		
		JButton button_3 = new JButton("Institui\u00E7\u00F5es");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		button_3.setBounds(8, 45, 113, 23);
		pMConfig.add(button_3);
		
		JButton button_4 = new JButton("Utilizadores");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		button_4.setBounds(8, 113, 113, 23);
		pMConfig.add(button_4);
		
		JButton button_5 = new JButton("Objetos");
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		button_5.setBounds(8, 147, 113, 23);
		pMConfig.add(button_5);
		
		JButton button_6 = new JButton("Salas");
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		button_6.setBounds(8, 79, 113, 23);
		pMConfig.add(button_6);
		
		JButton btnVoltar = new JButton("<- Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnVoltar.setBounds(8, 181, 111, 23);
		pMConfig.add(btnVoltar);
		
		JLabel label_25 = new JLabel("Configura\u00E7\u00F5es");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(8, 15, 111, 14);
		pMConfig.add(label_25);
		
		
		
		
		pMestre = new JPanel();
		
		pMestre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pMestre.setBounds(155, 79, 532, 416);
		contentPane.add(pMestre);
		pMestre.setLayout(new CardLayout(0, 0));
		
		pRegistar = new JPanel();
		pRegistar.setLayout(null);
		pMestre.add(pRegistar, "name_869153642670357");
		pRegistar.setVisible(false);
		
		JLabel label_6 = new JLabel("Nome:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(13, 14, 50, 14);
		pRegistar.add(label_6);
		
		JLabel label_7 = new JLabel("Email:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(13, 45, 50, 14);
		pRegistar.add(label_7);
		
		JLabel label_8 = new JLabel("Sala:");
		label_8.setBounds(203, 76, 37, 14);
		pRegistar.add(label_8);
		
		JLabel label_9 = new JLabel("Data:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(13, 107, 50, 14);
		pRegistar.add(label_9);
		
		JLabel label_11 = new JLabel("Cor:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(13, 169, 50, 14);
		pRegistar.add(label_11);
		
		JLabel label_12 = new JLabel("Estado:");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_12.setBounds(13, 205, 61, 14);
		pRegistar.add(label_12);
		
		JLabel label_13 = new JLabel("Descri\u00E7\u00E3o:");
		label_13.setBounds(13, 254, 61, 14);
		pRegistar.add(label_13);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(98, 42, 200, 20);
		pRegistar.add(txtEmail);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(98, 11, 200, 20);
		pRegistar.add(txtNome);
		
		
		
		cBBloco = new JComboBox<String>(cbModelBloco);
		cBBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				universalManager.comboBoxSalas();
			}
		});
		cBBloco.setToolTipText("Bloco");
		cBBloco.setBounds(98, 73, 43, 20);
		pRegistar.add(cBBloco);
		
		JComboBox<String> cBSala = new JComboBox<String>(cbModelSala);
		cBSala.setBounds(250, 73, 86, 20);
		pRegistar.add(cBSala);
		
		JLabel label_14 = new JLabel("Hora:");
		label_14.setBounds(203, 107, 37, 14);
		pRegistar.add(label_14);
		
		txtDescricao = new JTextPane();
		txtDescricao.setBounds(98, 254, 238, 113);
		pRegistar.add(txtDescricao);
		
		JButton btnRegistar = new JButton("Registar");
		btnRegistar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnRegistar.setBounds(250, 378, 86, 23);
		pRegistar.add(btnRegistar);
		
		JComboBox<String> cBTipoObjeto = new JComboBox<String>(cbModelTObj);
		cBTipoObjeto.setBounds(98, 135, 117, 20);
		pRegistar.add(cBTipoObjeto);
		
		JLabel label_15 = new JLabel("Bloco:");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_15.setBounds(13, 76, 50, 14);
		pRegistar.add(label_15);
	

		
		JComboBox cBCor = new JComboBox();
		cBCor.setModel(new DefaultComboBoxModel(new String[] {"Amarelo", "Azul", "Bege", "Branco", "Castanho", "Cinza", "Dourado", "Laranja", "Preto", "P\u00FArpura", "Rosa", "Verde", "Vermelho"}));
		cBCor.setBounds(98, 166, 117, 20);
		pRegistar.add(cBCor);
		
		JComboBox cBEstado = new JComboBox();
		cBEstado.setModel(new DefaultComboBoxModel(new String[] {"Bom", "Mau", "Razo\u00E1vel"}));
		cBEstado.setBounds(98, 202, 117, 20);
		pRegistar.add(cBEstado);
		
		JTextPane txtpnTipo = new JTextPane();
		txtpnTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnTipo.setEditable(false);
		txtpnTipo.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnTipo.setText("Tipo de\r\nObjeto :");
		txtpnTipo.setBounds(9, 128, 63, 41);
		pRegistar.add(txtpnTipo);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(98, 104, 86, 20);
		pRegistar.add(txtData);
		txtData.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setColumns(10);
		txtHora.setBounds(250, 104, 86, 20);
		pRegistar.add(txtHora);
		
		btnRegistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegistoManager.registar(txtNome.getText(),txtEmail.getText(),cBBloco.getSelectedItem().toString(), cBSala.getSelectedItem().toString(), txtData.getText(),txtHora.getText(),cBTipoObjeto.getSelectedItem().toString(),cBCor.getSelectedItem().toString(),cBEstado.getSelectedItem().toString(),txtDescricao.getText());
				
			}
		});
		
		pReclamar = new JPanel();
		pMestre.add(pReclamar, "name_869121203301840");
		pReclamar.setLayout(null);
		pReclamar.setVisible(false);
		
		
		JButton btnReclamar = new JButton("Reclamar");
		btnReclamar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReclamarManager.reclamacaoSucedida();
				
			}
		});
		btnReclamar.setBounds(429, 382, 89, 23);
		pReclamar.add(btnReclamar);
		
		cBRecTObj = new JComboBox<String>(cbModelTObj);
		cBRecTObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReclamarManager.sortTableReclamar();
			}
		});
		cBRecTObj.setBounds(106, 11, 143, 20);
		pReclamar.add(cBRecTObj);
		
		JLabel label = new JLabel("Tipo de Objeto:");
		label.setBounds(10, 11, 98, 20);
		pReclamar.add(label);
		
		JScrollPane scrollPaneReclamar = new JScrollPane();
		scrollPaneReclamar.setBounds(10, 42, 508, 329);
		pReclamar.add(scrollPaneReclamar);
		
		pListagem = new JPanel();
		pListagem.setLayout(null);
		pMestre.add(pListagem, "name_196146218576699");
		
		JLabel label_23 = new JLabel("Tipo de Objeto:");
		label_23.setBounds(10, 11, 100, 20);
		pListagem.add(label_23);
		
		cBListTObj = new JComboBox(cbModelTObj);
		cBListTObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListagemManager.sortTableListagem();
			}
		});
		cBListTObj.setBounds(106, 11, 143, 20);
		pListagem.add(cBListTObj);
		
		JScrollPane scrollPaneListagem = new JScrollPane();
		scrollPaneListagem.setBounds(10, 98, 508, 303);
		pListagem.add(scrollPaneListagem);
		
		tableListagem = new JTable(dtmListagem)
		{public boolean isCellEditable(int row, int column){return false;}};
		tableListagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListagemManager.showDescricao();
			}
		});
		tableListagem.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneListagem.setViewportView(tableListagem);
		tableListagem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JLabel label_24 = new JLabel("Estado:");
		label_24.setBounds(259, 14, 43, 14);
		pListagem.add(label_24);
		
		cBListEstado = new JComboBox();
		cBListEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemManager.sortTableListagem();
			}
		});
		cBListEstado.setModel(new DefaultComboBoxModel(new String[] {"", "Bom", "Razo\u00E1vel", "Mau"}));
		cBListEstado.setBounds(310, 11, 125, 20);
		pListagem.add(cBListEstado);
		
		JLabel label_10 = new JLabel("Bloco:");
		label_10.setBounds(10, 42, 40, 14);
		pListagem.add(label_10);
		
		cBListBloco = new JComboBox<String>(cbModelBloco);
		cBListBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemManager.sortTableListagem();
			}
		});
		cBListBloco.setBounds(50, 42, 40, 20);
		pListagem.add(cBListBloco);
		
		JLabel label_27 = new JLabel("Sala:");
		label_27.setBounds(106, 42, 29, 14);
		pListagem.add(label_27);
		
		cBListSala = new JComboBox<String>(cbModelSala);
		cBListSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemManager.sortTableListagem();
			}
		});
		cBListSala.setBounds(141, 42, 108, 20);
		pListagem.add(cBListSala);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(259, 45, 46, 14);
		pListagem.add(lblCor);
		
		cBListCor = new JComboBox();
		cBListCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemManager.sortTableListagem();
			}
		});
		cBListCor.setModel(new DefaultComboBoxModel(new String[] {"", "Amarelo", "Azul", "Bege", "Branco", "Castanho", "Cinza", "Dourado", "Laranja", "Preto", "P\u00FArpura", "Rosa", "Verde", "Vermelho"}));
		cBListCor.setBounds(292, 41, 143, 20);
		pListagem.add(cBListCor);
		
		JLabel lblDataInicial = new JLabel("Data Inicial:");
		lblDataInicial.setBounds(10, 73, 80, 14);
		pListagem.add(lblDataInicial);
		
		spDataI = new JSpinner();
		spDataI.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ListagemManager.sortTableListagem();
			}
		});
		Date datenowI = Calendar.getInstance().getTime();
		spDataI.setModel(new SpinnerDateModel(datenowI, null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor de_spDataI = new JSpinner.DateEditor(spDataI,"dd/MM/yyyy");
		spDataI.setEditor(de_spDataI);
		spDataI.setBounds(80, 70, 88, 20);
		pListagem.add(spDataI);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setBounds(259, 73, 61, 14);
		pListagem.add(lblDataFinal);
		
		spDataF = new JSpinner();
		spDataF.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ListagemManager.sortTableListagem();
			}
		});
		Date datenowF = Calendar.getInstance().getTime();
		spDataF.setModel(new SpinnerDateModel(datenowF,null,null,Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor de_spDataF = new JSpinner.DateEditor(spDataF,"dd/MM/yyyy");
		spDataF.setEditor(de_spDataF);
		spDataF.setBounds(322, 70, 88, 20);
		pListagem.add(spDataF);
		
		pDoar = new JPanel();
		pMestre.add(pDoar, "name_196009536555795");
		pDoar.setLayout(null);
		
		JScrollPane scrollPaneDoar = new JScrollPane();
		scrollPaneDoar.setBounds(10, 42, 508, 359);
		pDoar.add(scrollPaneDoar);
		
		tableDoar = new JTable(dtmDoar)
		{public boolean isCellEditable(int row, int column){return false;}};
		tableDoar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoarManager.showDescricao();
			}
		});
		scrollPaneDoar.setViewportView(tableDoar);
		tableDoar.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		JLabel label_21 = new JLabel("Tipo de Objeto:");
		label_21.setBounds(10, 11, 89, 20);
		pDoar.add(label_21);
		
		cBDoarTObj = new JComboBox(cbModelTObj);
		cBDoarTObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					DoarManager.sortTableDoar();
				
				
			}
		});
		cBDoarTObj.setBounds(109, 13, 143, 20);
		pDoar.add(cBDoarTObj);
		
		JButton button_7 = new JButton("Doar");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DoarManager.Doar();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		button_7.setBounds(429, 8, 89, 23);
		pDoar.add(button_7);
		
		tableReclamar = new JTable(dtmReclamar)
		{public boolean isCellEditable(int row, int column){return false;}};
		
		tableReclamar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					ReclamarManager.showDescricao();
				
			}
		});
		scrollPaneReclamar.setViewportView(tableReclamar);
		tableReclamar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cBRecSala = new JComboBox<String>(cbModelSala);
		cBRecSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReclamarManager.sortTableReclamar();
			}
		});
		cBRecSala.setBounds(410, 11, 108, 20);
		pReclamar.add(cBRecSala);
		
		JLabel label_1 = new JLabel("Sala:");
		label_1.setBounds(375, 11, 29, 14);
		pReclamar.add(label_1);
		
		cBRecBloco = new JComboBox<String>(cbModelBloco);
		cBRecBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReclamarManager.sortTableReclamar();
				universalManager.comboBoxSalas();
			}
		});
		cBRecBloco.setBounds(319, 11, 40, 20);
		pReclamar.add(cBRecBloco);
		
		JLabel label_3 = new JLabel("Bloco:");
		label_3.setBounds(279, 11, 40, 14);
		pReclamar.add(label_3);
		
		
		
		pConfigInst = new JPanel();
		pConfigInst.setLayout(null);
		pMestre.add(pConfigInst, "name_870422338469553");
		
		JScrollPane scrollPaneConfigInst = new JScrollPane();
		scrollPaneConfigInst.setBounds(283, 11, 235, 329);
		pConfigInst.add(scrollPaneConfigInst);
		
		tableConfigInst = new JTable(dtmConfigInst)
		{public boolean isCellEditable(int row, int column){return false;}};
		scrollPaneConfigInst.setViewportView(tableConfigInst);
		tableConfigInst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton button = new JButton("Remover*");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(framePrincipal.tableConfigInst.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null,"Selecione na tabela uma instituição!!!");
				}
				else
				{
					ConfigInstManager.removerInst(tableConfigInst.getValueAt(tableConfigInst.getSelectedRow(), 0).toString());
				}
			}
		});
		button.setBounds(283, 351, 102, 23);
		pConfigInst.add(button);
		
		JLabel label_20 = new JLabel("*Selecione a Institui\u00E7\u00E3o que deseja editar e/ou remover");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_20.setBounds(10, 391, 313, 14);
		pConfigInst.add(label_20);
		
		JPanel pAddInst = new JPanel();
		pAddInst.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pAddInst.setBounds(10, 11, 263, 97);
		pConfigInst.add(pAddInst);
		pAddInst.setLayout(null);
		
		JButton btnAddInst = new JButton("Adicionar");
		btnAddInst.setBounds(10, 66, 89, 23);
		pAddInst.add(btnAddInst);
		
		JLabel label_17 = new JLabel("Nome:");
		label_17.setBounds(20, 39, 41, 14);
		pAddInst.add(label_17);
		
		txtAddInst = new JTextField();
		txtAddInst.setBounds(83, 36, 164, 20);
		pAddInst.add(txtAddInst);
		txtAddInst.setColumns(10);
		
		JLabel label_16 = new JLabel("Adicionar Institui\u00E7\u00E3o:");
		label_16.setBounds(10, 11, 134, 14);
		pAddInst.add(label_16);
		label_16.setHorizontalAlignment(SwingConstants.LEFT);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel pEditInst = new JPanel();
		pEditInst.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pEditInst.setBounds(10, 119, 263, 97);
		pConfigInst.add(pEditInst);
		pEditInst.setLayout(null);
		
		JLabel label_18 = new JLabel("Editar Institui\u00E7\u00E3o: *");
		label_18.setBounds(10, 11, 134, 14);
		pEditInst.add(label_18);
		label_18.setHorizontalAlignment(SwingConstants.LEFT);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_19 = new JLabel("Novo nome:");
		label_19.setBounds(10, 41, 75, 14);
		pEditInst.add(label_19);
		
		txtEditInst = new JTextField();
		txtEditInst.setBounds(83, 38, 164, 20);
		pEditInst.add(txtEditInst);
		txtEditInst.setColumns(10);
		
		JButton btnEditInst = new JButton("Editar");
		btnEditInst.setBounds(10, 66, 89, 23);
		pEditInst.add(btnEditInst);
		btnEditInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ConfigInstManager.editarInst(tableConfigInst.getValueAt(tableConfigInst.getSelectedRow(), 0).toString(),txtEditInst.getText());
				}catch(Exception p){
					JOptionPane.showMessageDialog(null,"Selecione uma Instituição da tabela!!!");
				}
				
			}
		});
		btnAddInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfigInstManager.adicionarInst(txtAddInst.getText().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		pConfigSala = new JPanel();
		pConfigSala.setLayout(null);
		pMestre.add(pConfigSala, "name_199396532804750");
		
		JScrollPane scrollPaneConfigSala = new JScrollPane();
		scrollPaneConfigSala.setBounds(333, 11, 185, 329);
		pConfigSala.add(scrollPaneConfigSala);
		
		tableConfigSala = new JTable(dtmConfigSala)
		{public boolean isCellEditable(int row, int column){return false;}};
		scrollPaneConfigSala.setViewportView(tableConfigSala);
		tableConfigSala.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton button_10 = new JButton("Remover*");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				ConfigSalaManager.removerSala(tableConfigSala.getValueAt(tableConfigSala.getSelectedRow(), 0).toString(), tableConfigSala.getValueAt(tableConfigSala.getSelectedRow(), 1).toString());
				}catch(Exception p)
				{
					JOptionPane.showMessageDialog(null,"Selecione uma Sala da tabela!!!");
				}
				}
		});
		button_10.setBounds(429, 351, 89, 23);
		pConfigSala.add(button_10);
		
		JLabel label_30 = new JLabel("*Selecione a Sala que deseja editar e/ou remover");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_30.setBounds(10, 391, 313, 14);
		pConfigSala.add(label_30);
		
		JComboBox cBConfigSala = new JComboBox(cbModelBloco);
		cBConfigSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigSalaManager.sortCBSalas();
			}
		});
		cBConfigSala.setBounds(362, 352, 57, 20);
		pConfigSala.add(cBConfigSala);
		
		JLabel lblBloco_1 = new JLabel("Bloco:");
		lblBloco_1.setBounds(306, 355, 46, 14);
		pConfigSala.add(lblBloco_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 246, 135);
		pConfigSala.add(panel);
		panel.setLayout(null);
		
		JLabel label_26 = new JLabel("Adicionar Sala:");
		label_26.setBounds(10, 11, 134, 14);
		panel.add(label_26);
		label_26.setHorizontalAlignment(SwingConstants.LEFT);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblBloco = new JLabel("Bloco:");
		lblBloco.setBounds(22, 48, 46, 14);
		panel.add(lblBloco);
		
		txtAddBloco = new JTextField();
		txtAddBloco.setBounds(73, 45, 41, 20);
		panel.add(txtAddBloco);
		txtAddBloco.setColumns(10);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(22, 73, 41, 14);
		panel.add(lblSala);
		
		txtAddSala = new JTextField();
		txtAddSala.setBounds(73, 70, 143, 20);
		panel.add(txtAddSala);
		txtAddSala.setColumns(10);
		
		JButton btnAddSala = new JButton("Adicionar");
		btnAddSala.setBounds(10, 101, 89, 23);
		panel.add(btnAddSala);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 157, 246, 135);
		pConfigSala.add(panel_1);
		
		JLabel label_28 = new JLabel("Editar Sala: *");
		label_28.setBounds(10, 11, 134, 14);
		panel_1.add(label_28);
		label_28.setHorizontalAlignment(SwingConstants.LEFT);
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_5 = new JLabel("Bloco:");
		label_5.setBounds(22, 46, 46, 14);
		panel_1.add(label_5);
		
		txtEditBloco = new JTextField();
		txtEditBloco.setBounds(73, 43, 41, 20);
		panel_1.add(txtEditBloco);
		txtEditBloco.setColumns(10);
		
		JLabel lblNovaSala = new JLabel("Sala:");
		lblNovaSala.setBounds(22, 74, 41, 14);
		panel_1.add(lblNovaSala);
		
		txtEditSala = new JTextField();
		txtEditSala.setBounds(73, 71, 164, 20);
		panel_1.add(txtEditSala);
		txtEditSala.setColumns(10);
		
		JButton button_12 = new JButton("Editar");
		button_12.setBounds(10, 102, 89, 23);
		panel_1.add(button_12);
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ConfigSalaManager.editarSala(tableConfigSala.getValueAt(tableConfigSala.getSelectedRow(), 0).toString(), tableConfigSala.getValueAt(tableConfigSala.getSelectedRow(), 1).toString(), txtEditBloco.getText(), txtEditSala.getText());
				}catch(Exception p)
				{
					JOptionPane.showMessageDialog(null,"Selecione uma Sala da tabela!!!");
				}
				
			}
		});
		btnAddSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigSalaManager.adicionarSala(txtAddBloco.getText().toString(), txtAddSala.getText().toString());
			}
		});
		
		pConfigObjeto = new JPanel();
		pConfigObjeto.setLayout(null);
		pMestre.add(pConfigObjeto, "name_199648455546333");
		
		JScrollPane scrollPaneConfigObjeto = new JScrollPane();
		scrollPaneConfigObjeto.setBounds(238, 11, 280, 356);
		pConfigObjeto.add(scrollPaneConfigObjeto);
		
		tableConfigObjeto = new JTable(dtmConfigObj)
		{public boolean isCellEditable(int row, int column){return false;}};
		scrollPaneConfigObjeto.setViewportView(tableConfigObjeto);
		tableConfigObjeto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton button_13 = new JButton("Remover*");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfigObjetosManager.removerTipoObjeto(tableConfigObjeto.getValueAt(tableConfigObjeto.getSelectedRow(), 1).toString());
			}
		});
		button_13.setBounds(414, 378, 104, 23);
		pConfigObjeto.add(button_13);
		
		JLabel label_31 = new JLabel("*Selecione o Objeto que deseja editar e/ou remover");
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_31.setBounds(10, 391, 313, 14);
		pConfigObjeto.add(label_31);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 11, 218, 153);
		pConfigObjeto.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_32 = new JLabel("Adicionar Tipo de Objeto:");
		label_32.setBounds(10, 11, 183, 14);
		panel_2.add(label_32);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_33 = new JLabel("Nome:");
		label_33.setBounds(10, 39, 46, 14);
		panel_2.add(label_33);
		
		txtAdicionarTObj = new JTextField();
		txtAdicionarTObj.setBounds(55, 36, 153, 20);
		panel_2.add(txtAdicionarTObj);
		txtAdicionarTObj.setColumns(10);
		
		JLabel lblAssociarAUma = new JLabel("Associar a uma IS:");
		lblAssociarAUma.setBounds(10, 65, 171, 14);
		panel_2.add(lblAssociarAUma);
		
		JComboBox cBAddTObj = new JComboBox(cbModelInst);
		cBAddTObj.setBounds(10, 90, 198, 20);
		panel_2.add(cBAddTObj);
		
		JButton btnAddTObj = new JButton("Adicionar");
		btnAddTObj.setBounds(10, 119, 89, 23);
		panel_2.add(btnAddTObj);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setLayout(null);
		panel_3.setBounds(10, 175, 218, 153);
		pConfigObjeto.add(panel_3);
		
		JLabel label_35 = new JLabel("Editar Tipo de Objeto:*");
		label_35.setBounds(10, 11, 183, 14);
		panel_3.add(label_35);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_36 = new JLabel("Nome:");
		label_36.setBounds(10, 39, 46, 14);
		panel_3.add(label_36);
		
		txtEditarTObj = new JTextField();
		txtEditarTObj.setBounds(55, 36, 153, 20);
		panel_3.add(txtEditarTObj);
		txtEditarTObj.setColumns(10);
		
		JLabel lblMudarIsAssociada = new JLabel("Mudar IS associada:");
		lblMudarIsAssociada.setBounds(10, 65, 213, 14);
		panel_3.add(lblMudarIsAssociada);
		
		cBEditTObj = new JComboBox(cbModelInst);
		cBEditTObj.setBounds(10, 90, 198, 20);
		panel_3.add(cBEditTObj);
		
		JButton btnEditTObj = new JButton("Editar");
		btnEditTObj.setBounds(10, 121, 89, 23);
		panel_3.add(btnEditTObj);
		btnEditTObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					ConfigObjetosManager.editarTipoObjeto(tableConfigObjeto.getValueAt(tableConfigObjeto.getSelectedRow(), 1).toString(),tableConfigObjeto.getValueAt(tableConfigObjeto.getSelectedRow(), 2).toString(),txtEditarTObj.getText(),cBEditTObj.getSelectedItem().toString());
					
				}catch(Exception p){JOptionPane.showMessageDialog(null, "Adicione uma Instituição de Solidariedade primeiro!");}
				
			}
		});
		
		btnAddTObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					ConfigObjetosManager.adicionarTipoObjeto(txtAdicionarTObj.getText(), cBAddTObj.getSelectedItem().toString());
				}
				catch(Exception p)
				{
					JOptionPane.showMessageDialog(null, "Adicione uma Instituição de Solidariedade primeiro!");
				}
				
				
			}
		});
		
		pConfigUtilizador = new JPanel();
		pConfigUtilizador.setLayout(null);
		pMestre.add(pConfigUtilizador, "name_201146832559534");
		
		JScrollPane scrollPaneConfigUtilizador = new JScrollPane();
		scrollPaneConfigUtilizador.setBounds(10, 11, 508, 335);
		pConfigUtilizador.add(scrollPaneConfigUtilizador);
		
		tableConfigUtilizadorr = new JTable(dtmConfigUtilizador)
		{public boolean isCellEditable(int row, int column){return false;}};
		scrollPaneConfigUtilizador.setViewportView(tableConfigUtilizadorr);
		tableConfigUtilizadorr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JButton button_16 = new JButton("Remover*");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					ConfigUtilizadorManager.removerUtilizador(tableConfigUtilizadorr.getValueAt(tableConfigUtilizadorr.getSelectedRow(), 1).toString(), tableConfigUtilizadorr.getValueAt(tableConfigUtilizadorr.getSelectedRow(), 2).toString());
				}catch(Exception p)
				{
					JOptionPane.showMessageDialog(null,"Selecione uma Sala da tabela!!!");
				}
				
			}
		});
		button_16.setBounds(418, 357, 100, 23);
		pConfigUtilizador.add(button_16);
		
		JButton button_17 = new JButton("Adicionar Utilizador");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
					
				}catch(Exception p){}
				ConfigUtilizadorManager.openAddUser();
			}
		});
		button_17.setBounds(10, 357, 175, 23);
		pConfigUtilizador.add(button_17);
		
		JLabel label_38 = new JLabel("*Selecione o Utilizador que deseja editar e/ou remover");
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_38.setBounds(10, 391, 313, 14);
		pConfigUtilizador.add(label_38);
		
		JButton button_18 = new JButton("Editar*");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
					
				}catch(Exception p){}
				try{
					
					ConfigUtilizadorManager.frameEditUtilizador();
				}catch(Exception p)
				{
					p.printStackTrace();
				}
				
			}
		});
		button_18.setBounds(195, 357, 89, 23);
		pConfigUtilizador.add(button_18);
		
		pImportar = new JPanel();
		pMestre.add(pImportar, "name_95338888382129");
		pImportar.setLayout(null);
		
		JScrollPane scrollPaneImportar = new JScrollPane();
		scrollPaneImportar.setBounds(10, 11, 508, 356);
		pImportar.add(scrollPaneImportar);
		
		tableImportar = new JTable(dtmImportar)
		{public boolean isCellEditable(int row, int column){return false;}};
		scrollPaneImportar.setViewportView(tableImportar);
		
		JButton btnImportar_1 = new JButton("Importar");
		btnImportar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportarManager.importar();
			}
		});
		btnImportar_1.setBounds(429, 378, 89, 23);
		pImportar.add(btnImportar_1);
		
		JLabel lblVerifiqueSeEstes = new JLabel("Verifique se estes s\u00E3o os objetos que deseja importar");
		lblVerifiqueSeEstes.setBounds(10, 382, 346, 14);
		pImportar.add(lblVerifiqueSeEstes);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportarManager.removerImport();
			}
		});
		btnRemover.setBounds(330, 378, 89, 23);
		pImportar.add(btnRemover);
		
		pInicio = new JPanel();
		pMestre.add(pInicio, "name_80571598671237");
		pInicio.setLayout(null);
		
		JLabel lblBemvindo = new JLabel("BEM-VINDO");
		lblBemvindo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBemvindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemvindo.setBounds(10, 61, 508, 31);
		pInicio.add(lblBemvindo);
		
		JLabel lblAoPrograma = new JLabel("ao programa");
		lblAoPrograma.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAoPrograma.setHorizontalAlignment(SwingConstants.CENTER);
		lblAoPrograma.setBounds(10, 103, 508, 25);
		pInicio.add(lblAoPrograma);
		
		JLabel lblAnubis = new JLabel("ANUBIS");
		lblAnubis.setFont(new Font("Calibri Light", Font.BOLD, 71));
		lblAnubis.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnubis.setIcon(new ImageIcon("C:\\Users\\WORKSPACE\\Desktop\\relatorio final anubis\\st4.png"));
		lblAnubis.setBounds(10, 148, 508, 80);
		pInicio.add(lblAnubis);
		
		JLabel label_22 = new JLabel("");
		Image initial_icon = new ImageIcon (this.getClass().getResource("/AN22.png")).getImage();
		label_22.setIcon(new ImageIcon(initial_icon));
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(10, 11, 508, 390);
		pInicio.add(label_22);
		
		JLabel lblParaMaisInformaes = new JLabel("Para mais informa\u00E7\u00F5es consulte o manual de utilizador");
		lblParaMaisInformaes.setBounds(10, 387, 508, 14);
		pInicio.add(lblParaMaisInformaes);
		
		lblEstado = new JLabel("");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(155, 62, 532, 14);
		contentPane.add(lblEstado);
		
		lblAviso = new JLabel("AVISO: Alguns Tipos de Objeto n\u00E3o se encontram associados a nenhuma institui\u00E7\u00E3o!!!");
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAviso.setBounds(10, 497, 677, 14);
		lblAviso.setVisible(false);
		contentPane.add(lblAviso);
		
		if(LoadSave.contNAssociados>0)
		{
			lblAviso.setVisible(true);
		}
		
		pMestre.removeAll();
		pMestre.repaint();
		pMestre.revalidate();
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
				}catch(Exception p){}
				
				pMenus.removeAll();
				pMenus.repaint();
				pMenus.revalidate();
				pMenus.add(pMTotal);
				pMenus.repaint();
				pMenus.revalidate();
				
			}
		});
		
		btnReclamarObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReclamarManager.checkObjetosReclamar();
				
				
			}
		});
		
		btnRegistarObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(jDescricao.jD.isVisible())
					{
						jDescricao.jD.setVisible(false);
					}
				}catch(Exception p){}
				
				
				RegistoManager.pressRegistar();
			}
		});
		
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					if(jDescricao.jD.isVisible())
					{
						jDescricao.jD.setVisible(false);
					}
				}catch(Exception p){}
				
				pMenus.removeAll();
				pMenus.repaint();
				pMenus.revalidate();
				pMenus.add(pMConfig);
				pMenus.repaint();
				pMenus.revalidate();
			}
		});
		
		btnListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ListagemManager.checkObjetosListagem();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnDoar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoarManager.checkObjetosDoar();
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
				}catch(Exception p){}
				ConfigInstManager.pressConfigInst();
			}
		});
		
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
				}catch(Exception p){}
				ConfigSalaManager.pressConfigSala();
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigUtilizadorManager.pressConfigUtilizador();
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(frameSecundaria.aU.isVisible())
					{
						frameSecundaria.aU.setVisible(false);
					}
				}catch(Exception p){}
				
				ConfigObjetosManager.pressConfigTObjecto();
				
			}
		});
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistoManager.currentDate();
				
			}
		});
		
		
		
	}
}
