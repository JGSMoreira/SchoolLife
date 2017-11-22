package materia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VisualizarMateria extends JFrame implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5541980103611448459L;

	//VARIÁVEIS
	private JLabel lblNome = new JLabel("Nome da matéria"),
				   lblProf = new JLabel("Professor");
	
	private JTextField txtNome = new JTextField(15),
					   txtCod = new JTextField(15),
					   txtProf = new JTextField(15);
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	//DADOS DE LOGIN - BD
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	private Connection conexao;
	private Statement stm;
	private ResultSet rs;
	
	//VÁRIAVEIS - BD
	private int codigoMate;
	private int codigoProf;
	
	//BANCO DE DADOS
	public void codigo(String nomeMate) {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm = conexao.createStatement();

			this.rs = stm.executeQuery("SELECT MAX(idmateria) FROM materia where nome like '"+nomeMate+"';");
			rs.next();
			
			rs.getString("MAX(idMateria)");
			this.codigoMate = ((Number) rs.getObject(1)).intValue();
			txtCod.setText(Integer.toString(codigoMate));
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm = conexao.createStatement();
			
			this.rs = stm.executeQuery("select MAX(idprofessorfk) FROM materia where nome like '"+nomeMate+"';");
			rs.next();
			
			rs.getString("MAX(idProfessorfk)");
			this.codigoProf = ((Number) rs.getObject(1)).intValue();
			stm.close();
			System.out.println(codigoProf);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		carregaDados(codigoMate, codigoProf);
	}
	
	public void carregaDados(int codigoMate, int codigoProf) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(nome) FROM materia where idmateria = " + codigoMate + ";");
			rs.next();
			txtNome.setText(rs.getString("MAX(nome)"));

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			this.rs=stm.executeQuery("SELECT MAX(nome) FROM professor where idprofessor = " + codigoProf + ";");
			rs.next();
			txtProf.setText(rs.getString("MAX(nome)"));
			
			System.out.println(rs.getString("MAX(nome)"));
			
			stm.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		paInf.add(btnCancelar);
		
		paCentral.add(txtCod);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblProf);
		paCentral.add(txtProf);
		
	}
	
	public void posicionador() {
		txtCod.setBounds(125, 15, 50, 30);
		lblNome.setBounds(15, 15, 125, 30);
		txtNome.setBounds(180, 15, 185, 30);
		lblProf.setBounds(15, 50, 80, 30);
		txtProf.setBounds(125, 50, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(new Color(16, 28, 28));
		paCentral.setLayout(null);
		paInf.setBackground(new Color(28, 49, 49));
		txtCod.setEditable(false);
		txtProf.setEditable(false);
		txtNome.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		
		txtCod.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
		lblNome.setFont(fonteOpenSans1);
		lblProf.setFont(fonteOpenSans1);
		txtProf.setFont(fonteOpenSans1);
		txtCod.setFont(fonteOpenSans1);
		txtNome.setFont(fonteOpenSans1);
		
		txtCod.setHorizontalAlignment(JTextField.CENTER);
		
		lblNome.setForeground(Color.WHITE);
		lblProf.setForeground(Color.WHITE);
		
		
	}
	
	//EXECUTA AÇÕES
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
	
	//CONSTRUTOR
	public VisualizarMateria(String nomeMate) {
		
		this.setTitle("School Life - Visualizar Matéria");
		this.setBounds(0, 0, 400, 180);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		adicionador();
		posicionador();
		estilizador();
		codigo(nomeMate);
		
		btnCancelar.addMouseListener(this);
		
		repaint();
		this.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnCancelar) {
			btnCancelar.setIcon(new ImageIcon("img/geral/btn_Cancelar_hovermdpi.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnCancelar) {
			btnCancelar.setIcon(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
