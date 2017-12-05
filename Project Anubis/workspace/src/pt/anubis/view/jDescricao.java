package pt.anubis.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;

public class jDescricao extends JDialog {

	public static JTextPane txtDescricao;
	public static jDescricao jD = new jDescricao();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jDescricao dialog = new jDescricao();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public jDescricao() {
		setIconImage(new ImageIcon(getClass().getResource("/anubisicon.png")).getImage());
		setBounds(100, 100, 316, 316);
		getContentPane().setLayout(null);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescrio.setBounds(10, 11, 66, 14);
		getContentPane().add(lblDescrio);
		
		txtDescricao = new JTextPane();
		txtDescricao.setBounds(10, 36, 280, 230);
		getContentPane().add(txtDescricao);
		txtDescricao.setEditable(false);

	}
}
