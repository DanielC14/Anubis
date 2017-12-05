package pt.anubis.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pt.anubis.controller.LoginManager;

public class loginAnubis extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginAnubis frame = new loginAnubis();
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
	public loginAnubis() {
		setIconImage(new ImageIcon(getClass().getResource("/anubisicon.png")).getImage());
		setTitle("ANUBIS - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 401);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome de Utilizador");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(90, 205, 120, 14);
		contentPane.add(label);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(90, 230, 120, 20);
		contentPane.add(txtUsername);
		
		JLabel label_1 = new JLabel("Palavra-Passe");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(106, 261, 89, 14);
		contentPane.add(label_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(90, 283, 120, 20);
		contentPane.add(txtPassword);
		
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						LoginManager.setup(txtUsername.getText(),txtPassword.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(LoginManager.res == true)
					{
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nome de utilizador e/ou palavra-passe errados!!!");
						
					}
					
					
					
		        }
			}
		});
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(106, 314, 89, 23);
		contentPane.add(btnEntrar);
		Image icon = new ImageIcon (this.getClass().getResource("/anubisinicial198x.png")).getImage();
		
		
		
		
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 302, 362);
		contentPane.add(panel);
		
		JLabel lblIcon = new JLabel("");
		panel.add(lblIcon);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(icon));
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					LoginManager.setup(txtUsername.getText(),txtPassword.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(LoginManager.res == true)
				{
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Nome de utilizador e/ou palavra-passe errados!!!");
					
				}
				
			}
		});
	}
}
