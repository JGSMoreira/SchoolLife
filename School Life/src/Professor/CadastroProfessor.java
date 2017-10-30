package Professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;

public class CadastroProfessor extends JFrame implements ActionListener{
	
	private JLabel lblNome = new JLabel("Nome:"),
				   lblCodigo = new JLabel("Código");
	private JTextField txtNome = new JTextField(),
					   txtCódigo = new JTextField();
	private JButton btnSalvar = new JButton("Salvar"),
					btnFechar = new JButton("Fechar");
	
	
	
	public CadastroProfessor () {
		setBounds(100,100,500,500);
		setTitle("Cadastro de Professor");
		setVisible(true);
		setResizable(false);
		
		btnFechar.addActionListener(this);
		btnSalvar.addActionListener(this);
		

		lblNome.setBounds(10, 10, 10, 10);
		add(lblNome);
		lblCodigo.setBounds(30, 10, 20, 10);
		add(lblCodigo);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		CadastroProfessor c = new CadastroProfessor();
	}

	
}
