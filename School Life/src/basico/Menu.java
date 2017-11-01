package basico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	
	/**
	 *  Eu realmente não sei o que é isso, mas acho o erro irritante.
	 */
	private static final long serialVersionUID = 915657901997428142L;

	private JMenuBar mnbMenu = new JMenuBar();
	
	private JMenu mnAdicionar = new JMenu("Cadastrar");
	private JMenuItem miAddProfessor = new JMenuItem("Professor");
	private JMenuItem miAddMateria = new JMenuItem("Matéria");
	private JMenuItem miAddAtividade = new JMenuItem("Atividade");
	private JMenuItem miAddTipoAtividade = new JMenuItem("Tipo de Atividade");
	
	private JMenu mnVisualizar = new JMenu("Visualizar");
	private JMenuItem miVerProf = new JMenuItem("Professores");
	private JMenuItem miVerMat = new JMenuItem("Matérias");
	private JMenuItem miVerAtv = new JMenuItem("Atividades");
	private JMenuItem miVerTipoAtividade = new JMenuItem("Tipo de Atividade");
	
	private JPanel paEsquerdo = new JPanel();
	private JPanel paCentro = new JPanel();
	
	private JButton btnCadProf = new JButton("Cadastrar Professor");
	private JButton btnCadMat = new JButton("Cadastrar Matéria");
	private JButton btnAddAtv = new JButton("Adicionar Atividade"); 
	
	private JLabel lblLogo = new JLabel("School Life");
	private JLabel lblEspaco = new JLabel("       ");
	
	private Font ftLogo = new Font("Segoe UI", Font.PLAIN, 30);
	
	private Menu() {
		this.setTitle("School Life - Menu Principal");
		this.setBounds(10, 10, 800, 600);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		adicionador();
		posicionador();
		estilizador();
		
		this.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		
	}
	
	//ISTO ADICIONA OS ALEMENTOS NA TELA
	public void adicionador () {
		
		mnbMenu.add(mnAdicionar);
		mnbMenu.add(mnVisualizar);
		
		mnAdicionar.add(miAddAtividade);
		mnAdicionar.addSeparator();
		mnAdicionar.add(miAddTipoAtividade);
		mnAdicionar.add(miAddMateria);
		mnAdicionar.add(miAddProfessor);
		
		mnVisualizar.add(miVerAtv);
		mnVisualizar.addSeparator();
		mnVisualizar.add(miVerTipoAtividade);
		mnVisualizar.add(miVerMat);
		mnVisualizar.add(miVerProf);
		
		setJMenuBar(mnbMenu);
		
		this.add(paEsquerdo, BorderLayout.NORTH);
		this.add(paCentro, BorderLayout.CENTER);
		
		paEsquerdo.add(lblLogo);
		paEsquerdo.add(lblEspaco);
		paEsquerdo.add(btnCadProf);
		paEsquerdo.add(btnCadMat);
		paEsquerdo.add(btnAddAtv);
	
		System.out.println("Objetos adicionados á tela.");
	}
	
	//ISTO POSICIONA OS ELEMENTOS NA TELA
	public void posicionador() {
		
		System.out.println("Objetos posicionados na tela.");
	}
	
	public void estilizador() {
		lblLogo.setFont(ftLogo);
		lblLogo.setForeground(Color.white);
		paEsquerdo.setBackground(new Color(0, 206, 209));
		paCentro.setBackground(Color.WHITE);
	}
	
	public static void main (String [] args) {
		Menu mn = new Menu();
	}
	
}
