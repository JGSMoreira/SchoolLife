package basico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import atividade.CadastrarAtividade;
import materia.CadastrarMateria;
import sun.applet.Main;

public class Menu extends JFrame implements ActionListener, MouseListener{
	
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
	
	private JLabel lblLogo = new JLabel(new ImageIcon("img/menu/logo.png"));
	private JLabel lblEspaco = new JLabel("       ");
	
	private Font ftLogo = new Font("Product Sans", Font.PLAIN, 30);
	
	URL urlCadProf = Menu.class.getResource("menu/btn_CadProf.png");
	URL urlCadMat = Menu.class.getResource("menu/btn_CadMat.png");
	URL urlAddAtv = Menu.class.getResource("menu/btn_AddAtv.png");
	URL urldegrade = Menu.class.getResource("menu/degradê.png");
	
	private JLabel btnCadProf = new JLabel(new ImageIcon(urlCadProf));
	private JLabel btnCadMat = new JLabel(new ImageIcon(urlCadMat));
	private JLabel btnAddAtv = new JLabel(new ImageIcon(urlAddAtv));
	private JLabel degrade = new JLabel(new ImageIcon(urldegrade));
	
	//BANCO DE DADOS VARÁVEIS

	
	private Menu() {
		this.setTitle("School Life - Menu Principal");
		this.setBounds(10, 10, 800, 600);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		adicionador();
		posicionador();
		estilizador();
		adicionaListeners();
		
		this.setVisible(true);
	}
	
	//BANCO DE DADOS

	
	//ACTION LISTENER
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == miAddAtividade) {
			atividade.CadastrarAtividade a = new atividade.CadastrarAtividade();
		}
		if (e.getSource() == miAddProfessor) {
			professor.CadastroProfessor a = new professor.CadastroProfessor();
		}
		if (e.getSource() == miAddMateria) {
			materia.CadastrarMateria a = new CadastrarMateria();
		}
		if(e.getSource() == miVerProf) {
			professor.ListarProfessores vp = new professor.ListarProfessores();
		}
		if(e.getSource() == miAddTipoAtividade) {
			tipo_atividade.CadastrarTipoAtividade ta = new  tipo_atividade.CadastrarTipoAtividade();
		}
	}
	
	//ISTO ADICIONA OS ELEMENTOS NA TELA
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
		paEsquerdo.add(btnCadProf);
		paEsquerdo.add(btnCadMat);
		paEsquerdo.add(btnAddAtv);
		paCentro.add(degrade);
			
		System.out.println("Objetos adicionados na tela.");
	}
	
	//ISTO POSICIONA OS ELEMENTOS NA TELA
	public void posicionador() {
		paCentro.setLayout(null);
		degrade.setBounds(-20, -275, 820, 600);
		System.out.println("Objetos posicionados na tela.");
	}
	
	public void estilizador() {
		lblLogo.setFont(ftLogo);
		lblLogo.setForeground(Color.white);
		paEsquerdo.setBackground(new Color(47,79,79));
		paCentro.setBackground(new Color(16, 28, 28));
		
		mnbMenu.setBackground(new Color(47,79,79));
		mnAdicionar.setForeground(Color.white);
		mnVisualizar.setForeground(Color.white);	
		mnbMenu.setBorder(null);
		mnbMenu.setFont(new Font("Open Sans", 12, Font.PLAIN));
		}
	
	public void adicionaListeners() {
		
		//ACTION LISTENERS
		miAddAtividade.addActionListener(this);
		miAddMateria.addActionListener(this);
		miAddProfessor.addActionListener(this);
		miAddTipoAtividade.addActionListener(this);
		miVerAtv.addActionListener(this);
		miVerMat.addActionListener(this);
		miVerProf.addActionListener(this);
		miVerTipoAtividade.addActionListener(this);
		
		//MOUSE LISTENERS
		btnCadProf.addMouseListener(this);
		btnCadMat.addMouseListener(this);
		btnAddAtv.addMouseListener(this);
		
	}
	
	public static void main (String [] args) {
		Menu mn = new Menu();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCadProf) {
			professor.CadastroProfessor cp = new professor.CadastroProfessor();
		}
		if (e.getSource() == btnCadMat) {
			materia.CadastrarMateria ab = new materia.CadastrarMateria();
		}
		if (e.getSource() == btnAddAtv) {
			atividade.CadastrarAtividade ca = new atividade.CadastrarAtividade();
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCadProf) {
			btnCadProf.setIcon(new ImageIcon("img/menu/btn_CadProf_hover.png"));
		}
		if (e.getSource() == btnCadMat) {
			btnCadMat.setIcon(new ImageIcon("img/menu/btn_CadMat_hover.png"));
		}
		if (e.getSource() == btnAddAtv) {
			btnAddAtv.setIcon(new ImageIcon("img/menu/btn_AddAtv_hover.png"));
		}
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCadProf) {
			btnCadProf.setIcon(new ImageIcon("img/menu/btn_CadProf.png"));
		}
		if (e.getSource() == btnCadMat) {
			btnCadMat.setIcon(new ImageIcon("img/menu/btn_CadMat.png"));
		}
		if (e.getSource() == btnAddAtv) {
			btnAddAtv.setIcon(new ImageIcon("img/menu/btn_AddAtv.png"));
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
