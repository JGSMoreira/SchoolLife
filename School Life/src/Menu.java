import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame implements ActionListener{
	
	private JMenuBar mnbMenu = new JMenuBar();
	private JMenu mnAdicionar = new JMenu("Adicionar");
	private JMenuItem miAddProfessor = new JMenuItem("Professor");
	private JMenuItem miAddMateria = new JMenuItem("Matéria");
	private JMenuItem miAddAtividade = new JMenuItem("Atividade");
	
	private JLabel lblLogo = new JLabel("SCHOOL LIFE"); 
	
	private Menu() {
		this.setTitle("School Life - Menu Principal");
		this.setBounds(10, 10, 800, 600);
		this.setLayout(null);
		
		adicionador();
		posicionador();
		
		this.setVisible(true);
	}
	
	public void adicionador () {
		this.add(lblLogo);
		
		mnbMenu.add(mnAdicionar);
		mnAdicionar.add(miAddAtividade);
		mnAdicionar.add(miAddMateria);
		mnAdicionar.add(miAddProfessor);
		setJMenuBar(mnbMenu);
		
		System.out.println("Objetos adicionados á tela.");
	}
	public void posicionador() {
		lblLogo.setBounds(10, 10, 100, 10);
		
		System.out.println("Objetos posicionados na tela.");
	}
	
	public static void main (String [] args) {
		Menu mn = new Menu();
	}
	
}
