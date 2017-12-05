package pt.anubis.view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import pt.anubis.controller.ConfigUtilizadorManager;
import pt.anubis.controller.LoadSave;
import pt.anubis.controller.anubisMain;
import pt.anubis.controller.universalManager;

public class frameSecundaria extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNomeUtilizador;
	public static frameSecundaria aU;
	public static JTextField txtNomeCompleto;
	public static JPasswordField txtPalavraPasse;
	public static JPasswordField txtrPalavraPasse;
	public static JPanel pMestre;
	public static JPanel pAddUser;
	public static JLabel lblAddUser;
	public static JComboBox cBTUser;
	public static JLabel lblReintroduza;
	public static JLabel lblPalavrapasse;
	public static JButton btnAdicionar;
	public static JButton btnEditar;
	public static JPanel pPermissoes;
	public static DefaultComboBoxModel<String> cBModelTUser = new DefaultComboBoxModel<String>();

	public static JCheckBox checkRegisto;
	public static JCheckBox checkReclamacao;
	public static JCheckBox checkListagem;
	public static JCheckBox checkDoacoes;
	public static JCheckBox checkConfiguracoes;
	public static JCheckBox checkImportacao;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameSecundaria frame = new frameSecundaria();
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
	public frameSecundaria() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeiconified(WindowEvent e) {
				universalManager.showFrameSecundaria();
			}
			@Override
			public void windowIconified(WindowEvent e) {
				universalManager.hideFrameSecundaria();
			}
		});
		
		
		setTitle("ANUBIS - Adicionar Utilizador");
		setIconImage(new ImageIcon(getClass().getResource("/anubisicon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 445, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		contentPane.setLayout(null);
		
		pMestre = new JPanel();
		pMestre.setBounds(0, 55, 442, 213);
		contentPane.add(pMestre);
		pMestre.setLayout(new CardLayout(0, 0));
		
		pAddUser = new JPanel();
		pMestre.add(pAddUser, "name_647895336360088");
		pAddUser.setLayout(null);
		
		pPermissoes = new JPanel();
		pPermissoes.setBounds(230, 50, 206, 117);
		pAddUser.add(pPermissoes);
		pPermissoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pPermissoes.setLayout(null);
		
		JLabel lblPermisses = new JLabel("Permiss\u00F5es:");
		lblPermisses.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPermisses.setBounds(7, 11, 69, 14);
		pPermissoes.add(lblPermisses);
		
		checkRegisto = new JCheckBox("Registo");
		checkRegisto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkRegisto.setBounds(7, 32, 83, 23);
		pPermissoes.add(checkRegisto);
		
		checkListagem = new JCheckBox("Listagem");
		checkListagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkListagem.setBounds(104, 32, 95, 23);
		pPermissoes.add(checkListagem);
		
		checkReclamacao = new JCheckBox("Reclama\u00E7\u00E3o");
		checkReclamacao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkReclamacao.setBounds(7, 58, 95, 23);
		pPermissoes.add(checkReclamacao);
		
		checkDoacoes = new JCheckBox("Doa\u00E7\u00F5es");
		checkDoacoes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkDoacoes.setBounds(104, 58, 95, 23);
		pPermissoes.add(checkDoacoes);
		
		checkConfiguracoes = new JCheckBox("Configura\u00E7\u00F5es");
		checkConfiguracoes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkConfiguracoes.setBounds(104, 84, 96, 23);
		pPermissoes.add(checkConfiguracoes);
		
		checkImportacao = new JCheckBox("Importa\u00E7\u00E3o");
		checkImportacao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkImportacao.setBounds(7, 84, 95, 23);
		pPermissoes.add(checkImportacao);
		
		txtNomeUtilizador = new JTextField();
		txtNomeUtilizador.setBounds(10, 79, 198, 20);
		pAddUser.add(txtNomeUtilizador);
		txtNomeUtilizador.setColumns(10);
		
		JTextPane txtpnTipoDeUtilizador = new JTextPane();
		txtpnTipoDeUtilizador.setEditable(false);
		txtpnTipoDeUtilizador.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtpnTipoDeUtilizador.setBounds(230, 10, 64, 34);
		pAddUser.add(txtpnTipoDeUtilizador);
		txtpnTipoDeUtilizador.setText("Tipo de \r\nUtilizador:");
		txtpnTipoDeUtilizador.setBackground(SystemColor.menu);
		
		cBTUser = new JComboBox(cBModelTUser);
		
		cBTUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(aU.isVisible())
				{
					ConfigUtilizadorManager.setupPermissoes(cBTUser.getSelectedItem().toString());
				}
				
			}
		});
		cBTUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cBTUser.setBounds(295, 13, 97, 20);
		pAddUser.add(cBTUser);
		
		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setBounds(10, 30, 200, 20);
		pAddUser.add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);
		
		txtPalavraPasse = new JPasswordField();
		txtPalavraPasse.setBounds(10, 130, 129, 20);
		pAddUser.add(txtPalavraPasse);
		
		txtrPalavraPasse = new JPasswordField();
		txtrPalavraPasse.setBounds(10, 179, 129, 20);
		pAddUser.add(txtrPalavraPasse);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(10, 10, 150, 14);
		pAddUser.add(lblNomeCompleto);
		
		JLabel lblNomeDeUtilizador = new JLabel("Nome de utilizador:");
		lblNomeDeUtilizador.setBounds(10, 59, 108, 14);
		pAddUser.add(lblNomeDeUtilizador);
		
		lblPalavrapasse = new JLabel("Palavra-Passe:");
		lblPalavrapasse.setBounds(10, 110, 97, 14);
		pAddUser.add(lblPalavrapasse);
		
		lblReintroduza = new JLabel("Reintroduza a Palavra-Passe:");
		lblReintroduza.setBounds(10, 159, 157, 14);
		pAddUser.add(lblReintroduza);
		
		
		lblAddUser = new JLabel("Adicionar Utilizador");
		lblAddUser.setBounds(88, 11, 254, 33);
		lblAddUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblAddUser);
		
		
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ConfigUtilizadorManager.addUtilizador(txtNomeCompleto.getText(), txtNomeUtilizador.getText(), txtPalavraPasse.getText().toString(), txtrPalavraPasse.getText().toString(), cBTUser.getSelectedItem().toString(), checkRegisto.isSelected(), checkReclamacao.isSelected(), checkListagem.isSelected(), checkConfiguracoes.isSelected(), checkDoacoes.isSelected(), checkImportacao.isSelected()) == true && LoadSave.users.size() == 1)
				{
					anubisMain.log.setVisible(true);
				}
			}
		});
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigUtilizadorManager.editUtilizador(txtNomeCompleto.getText(), txtNomeUtilizador.getText(), txtPalavraPasse.getText(), txtrPalavraPasse.getText(), cBTUser.getSelectedItem().toString(), checkRegisto.isSelected(), checkReclamacao.isSelected(), checkImportacao.isSelected(), checkListagem.isSelected(), checkDoacoes.isSelected(), checkConfiguracoes.isSelected());
			}
		});
		btnEditar.setBounds(340, 279, 89, 23);
		contentPane.add(btnEditar);
		btnAdicionar.setBounds(340, 279, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnVoltar = new JButton("Fechar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aU.setVisible(false);
				//dispose();
			}
		});
		btnVoltar.setBounds(10, 279, 89, 23);
		contentPane.add(btnVoltar);
		
		
		
	}
}
