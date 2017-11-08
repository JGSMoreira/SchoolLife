package professor;

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

public class DeletaProfessor extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626545031181805303L;

	private JPanel parteSuperior = new JPanel(null),
				   parteInferior = new JPanel();
	
	private JLabel texto = new JLabel("Tem certeza que deseja deletar este professor?");
	
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
	private String nomeProf;
	
	//BANCO DE DADOS
	public void deletaDados(int codigo) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			stm.executeUpdate("delete from professor where idProfessor = " + codigo +";");

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void codigo(String nomeProf) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT idProfessor FROM professor where nome like '%" + nomeProf + "%';");
			rs.next();

			rs.getString("idProfessor");
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
	public DeletaProfessor(String nomeProf) {
		setTitle("Janela de Confirmação");
		adicionaElementos();
		pack();
		setBounds(0, 0, 390, 180);
		this.setLocationRelativeTo(null);
		this.nomeProf = nomeProf;
		setVisible(true);
		revalidate();
		repaint();
		
		sim.addActionListener(this);
		nao.addActionListener(this);
		
	}
	
	public static void main (String [] args) {
		DeletaProfessor del = new DeletaProfessor(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nao) {
			dispose();
		}
		if (e.getSource() == sim) {
			codigo(nomeProf);
			dispose();
		}
		
	}

}
