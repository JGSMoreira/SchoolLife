package Matéria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastrarMateria extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5541980103611448459L;

	//VARIÁVEIS
	private JLabel lblNome = new JLabel("Nome da matéria"),
				   lblProf = new JLabel("Professor"),
				   lblCod = new JLabel("Código");
	
	private JTextField txtNome = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JButton btnSalvar = new JButton("Salvar"),
					btnCancelar = new JButton("Cancelar");
	
	private JComboBox cbProf = new JComboBox();
	
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		paInf.add(btnSalvar);
		paInf.add(btnCancelar);
		
		paCentral.add(lblCod);
		paCentral.add(txtCod);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblProf);
		paCentral.add(cbProf);
		
	}
	
	public void posicionador() {
		lblCod.setBounds(15, 15, 125, 30);
		txtCod.setBounds(125, 15, 50, 30);
		lblNome.setBounds(15, 50, 125, 30);
		txtNome.setBounds(125, 50, 240, 30);
		lblProf.setBounds(15, 85, 80, 30);
		cbProf.setBounds(125, 85, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(Color.white);
		paCentral.setLayout(null);
		paInf.setBackground(new Color(0, 206, 209));
		txtCod.setEditable(false);
		
		
	}
	
	public void addCoisasCB() {
		cbProf.addItem("Nenhum");
		cbProf.addItem("Ednaldo Pereira");
	}
	
	public void enviaDados() {
		
	}
	
	//EXECUTA AÇÕES
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		if (e.getSource() == btnSalvar) {
			enviaDados();
		}
		
	}
	
	//CONSTRUTOR
	public CadastrarMateria() {
		
		this.setTitle("School Life - Cadastrar Matéria");
		this.setBounds(0, 0, 400, 200);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		adicionador();
		posicionador();
		estilizador();
		addCoisasCB();
		
		btnCancelar.addActionListener(this);
		btnSalvar.addActionListener(this);
		
		repaint();
		this.setVisible(true);
	}
	
	public static void main (String []args) {
		CadastrarMateria cad = new CadastrarMateria();
	}
}
