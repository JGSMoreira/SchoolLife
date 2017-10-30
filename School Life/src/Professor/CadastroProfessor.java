package Professor;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;

public class CadastroProfessor extends JFrame implements ActionListener, MouseListener{
	
	private JLabel lblNome = new JLabel("Nome:"),
				   lblCodigo = new JLabel("Código:");
	private JTextField txtNome = new JTextField(),
					   txtCódigo = new JTextField();
	private JButton btnSalvar = new JButton("Salvar"),
					btnFechar = new JButton("Fechar");
	
	
	
	public CadastroProfessor () {
		
		//Definições da janela
		setLayout(null);
		setBounds(100,100,300,400);
		setTitle("Cadastro de Professor");
		setVisible(true);
		setResizable(false);
		
		btnFechar.addActionListener(this);
		btnSalvar.addActionListener(this);

		lblNome.setBounds(50, 50, 50, 20);
		add(lblNome);
		
		lblCodigo.setBounds(50, 100, 50, 20);
		add(lblCodigo);
		
		txtNome.setBounds(50, 60, 100, 20);
		
		btnFechar.setBounds(30, 300,  80, 30);
		add(btnFechar);
		btnSalvar.setBounds(180, 300, 80, 30);
		add(btnSalvar);
		
		btnSalvar.setBackground(new Color(240, 255, 240));
		btnSalvar.setOpaque(true);
		
		btnSalvar.addMouseListener(this);
	

	

		}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		CadastroProfessor c = new CadastroProfessor();
	}


	public void mouseClicked(MouseEvent e) {
		btnSalvar.setBackground(Color.WHITE);
		
	}


	public void mouseEntered(MouseEvent e) {
		btnSalvar.setBackground(Color.YELLOW);
		
	}


	public void mouseExited(MouseEvent e) {
		btnSalvar.setBackground(Color.PINK  );
		
	}


	public void mousePressed(MouseEvent e) {
		btnSalvar.setBackground(Color.GREEN);
		
	}

	public void mouseReleased(MouseEvent e) {
		btnSalvar.setBackground(Color.RED);
		
	}

	
}
