package atividade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import materia.CadastrarMateria;

public class CadastrarAtividade extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//VARIÁVEIS
	private JLabel lblNome = new JLabel("Nome da atividade"),
				   lblValor = new JLabel("Valor"),
				   lblEtapa = new JLabel("Etapa"),
				   lblMateria = new JLabel("Matéria"),
				   lblPrioridade = new JLabel("Prioridade"),
				   lblProf = new JLabel("Professor"),
				   lblExemploEt = new JLabel("(Ex. 1, 2, 3, 4, etc.)"),
				   lblContinuaPontos = new JLabel("Pontos");
	
	private JTextField txtNome = new JTextField(15),
					   txtValor = new JTextField(15),
					   txtEtapa = new JTextField(15),
					   txtProf = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JButton btnSalvar = new JButton("Salvar"),
					btnCancelar = new JButton("Cancelar");
	
	private JComboBox cbMateria = new JComboBox(),
					  cbPrioridade = new JComboBox();
	
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		
		paInf.add(btnSalvar);
		paInf.add(btnCancelar);
		
		paCentral.add(txtCod);
		paCentral.add(lblEtapa);
		paCentral.add(txtEtapa);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblMateria);
		paCentral.add(cbMateria);
		paCentral.add(lblProf);
		paCentral.add(txtProf);
		paCentral.add(lblPrioridade);
		paCentral.add(cbPrioridade);
		paCentral.add(lblValor);
		paCentral.add(txtValor);
		paCentral.add(lblExemploEt);
		paCentral.add(lblContinuaPontos);
		
		
	}
	
	public void posicionador() {
		
		lblEtapa.setBounds(15, 15, 125, 30);
		txtEtapa.setBounds(125, 15, 50, 30);
		lblExemploEt.setBounds(180, 15, 125, 30);
		txtCod.setBounds(125, 50, 50, 30);
		lblNome.setBounds(15, 50, 125, 30);
		txtNome.setBounds(180, 50, 185, 30);
		lblMateria.setBounds(15, 85, 80, 30);
		cbMateria.setBounds(125, 85, 240, 30);
		lblProf.setBounds(15, 120, 80, 30);
		txtProf.setBounds(125, 120, 240, 30);
		lblValor.setBounds(15, 155, 80, 30);
		txtValor.setBounds(125, 155, 50, 30);
		lblContinuaPontos.setBounds(180, 155, 125, 30);
		lblPrioridade.setBounds(15, 190, 80, 30);
		cbPrioridade.setBounds(125, 190, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(Color.white);
		paCentral.setLayout(null);
		paInf.setBackground(new Color(0, 206, 209));
		txtCod.setEditable(false);
		txtProf.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		
		lblContinuaPontos.setFont(fonteOpenSans1);
		lblEtapa.setFont(fonteOpenSans1);
		lblExemploEt.setFont(fonteOpenSans1);
		lblMateria.setFont(fonteOpenSans1);
		lblNome.setFont(fonteOpenSans1);
		lblPrioridade.setFont(fonteOpenSans1);
		lblProf.setFont(fonteOpenSans1);
		lblValor.setFont(fonteOpenSans1);
		
	}
	
	public void addCoisasCB() {
		cbMateria.addItem("Nenhum");
		cbMateria.addItem("Ednaldo Pereira");
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
	public CadastrarAtividade() {
		
		this.setTitle("School Life - Cadastrar Atividade");
		this.setBounds(0, 0, 400, 300);
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

}
