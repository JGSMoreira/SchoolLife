package materia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeletaMateria extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626545031181805303L;

	private JPanel parteSuperior = new JPanel(null),
				   parteInferior = new JPanel();
	
	private JLabel texto = new JLabel("Tem certeza que deseja deletar esta matéria?");
	
	private JButton sim = new JButton("Sim"),
					nao = new JButton("Não");
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 16);
	
	//BANCO DE DADOS VARÁVEIS
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	private ResultSet rs;
	private int codigo;
	private String nomeMateria;
	
	//BANCO DE DADOS
	public void deletaDados(int codigo) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			stm.executeUpdate("delete from materia where idMateria = " + codigo +";");

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void codigo(String nomeMateria) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT idMateria FROM materia where nome like '" + nomeMateria + "';");
			rs.next();

			rs.getString("idMateria");
			this.codigo=((Number) rs.getObject(1)).intValue();

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deletaDados(codigo);
	}
	
	//INTERFACE
	public void adicionaElementos() {
		this.add(parteInferior, BorderLayout.SOUTH);
		this.add(parteSuperior, BorderLayout.CENTER);
		
		parteSuperior.add(texto);
		
		parteInferior.add(sim);
		parteInferior.add(nao);
		
		organizaElementos();
	}
	public void organizaElementos() {
		texto.setBounds(10, 35, 400, 35);
		
		estilizaElementos();
	}
	public void estilizaElementos() {
		parteSuperior.setBackground(new Color(16, 28, 28));
		parteInferior.setBackground(new Color(28, 49, 49));
		
		texto.setFont(fonte);
		texto.setForeground(Color.WHITE);
	}
	
	//CONSTRUTOR
	public DeletaMateria(String nomeMateria) {
		setTitle("Janela de Confirmação");
		adicionaElementos();
		pack();
		setBounds(0, 0, 390, 180);
		this.setLocationRelativeTo(null);
		this.nomeMateria = nomeMateria;
		setVisible(true);
		revalidate();
		repaint();
		
		testes.listView aa = new testes.listView();
		aa.setAtualizar();
		
		sim.addActionListener(this);
		nao.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nao) {
			dispose();
		}
		if (e.getSource() == sim) {
			codigo(nomeMateria);
			testes.listView aa = new testes.listView();
			aa.getDados();
			basico.JanelaPergunta a = new basico.JanelaPergunta("Matéria deletada com sucesso!");
			dispose();
		}
		
	}

}
