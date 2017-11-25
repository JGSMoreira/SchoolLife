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

public class CadastrarMateria extends JFrame implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5541980103611448459L;

	//VARIÁVEIS
	private JLabel lblNome = new JLabel("Nome da matéria"),
				   lblProf = new JLabel("Professor");
	
	private JTextField txtNome = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JLabel btnSalvar = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private JComboBox cbProf = new JComboBox();
	
	//DADOS DE LOGIN - BD
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	private Connection conexao;
	private Statement stm;
	private ResultSet rs;
	
	//VÁRIAVEIS - BD
	private int codigo;
	
	//BANCO DE DADOS
	public void codigo() {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm = conexao.createStatement();

			this.rs = stm.executeQuery("SELECT MAX(idmateria) FROM materia;");
			rs.next();
			
			rs.getString("MAX(idMateria)");
			if(rs.wasNull()) {
				codigo= 1;
			}
			else {
				this.codigo = ((Number) rs.getObject(1)).intValue();
				this.codigo = codigo + 1;
			}
			txtCod.setText(Integer.toString(codigo));
			stm.close();	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void carregaProfs() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			rs = stm.executeQuery("select nome from professor");			
			
			while(rs.next()) {
				cbProf.addItem(rs.getString("nome"));
			}
			stm.close(); 
			revalidate();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviaDados() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			stm.executeUpdate("insert into materia (nome, idProfessorFK) values" + ""
					+ "('"+txtNome.getText()+"', "+getIdProfessor()+");");
		
			basico.JanelaPergunta a = new basico.JanelaPergunta("Matéria cadastrada com sucesso!");
			stm.close();
			dispose();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getIdProfessor() {
		int idProf = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			String selecionado = String.valueOf(this.cbProf.getSelectedItem());
			this.rs = stm.executeQuery("select idProfessor from professor where nome like " + "'" + selecionado + "';");
			rs.next();
			
			idProf = rs.getInt("idProfessor");
			System.out.println("ID do professor "+selecionado+" carregado! ("+idProf+")");
			stm.close();
			dispose();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return(idProf);
	}
	
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		paInf.add(btnSalvar);
		paInf.add(btnCancelar);
		
		paCentral.add(txtCod);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblProf);
		paCentral.add(cbProf);
		
	}
	
	public void posicionador() {
		txtCod.setBounds(125, 15, 50, 30);
		lblNome.setBounds(15, 15, 125, 30);
		txtNome.setBounds(180, 15, 185, 30);
		lblProf.setBounds(15, 50, 80, 30);
		cbProf.setBounds(125, 50, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(new Color(16, 28, 28));
		paCentral.setLayout(null);
		paInf.setBackground(new Color(28, 49, 49));
		txtCod.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		
		txtCod.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
		lblNome.setFont(fonteOpenSans1);
		lblProf.setFont(fonteOpenSans1);
		cbProf.setFont(fonteOpenSans1);
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
		if (e.getSource() == btnSalvar) {
			enviaDados();
		}
		
	}
	
	//CONSTRUTOR
	public CadastrarMateria() {
		this.setIconImage(new ImageIcon("img/geral/icon.png").getImage());
		this.setTitle("School Life - Cadastrar Matéria");
		this.setBounds(0, 0, 400, 180);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		adicionador();
		posicionador();
		estilizador();
		carregaProfs();
		codigo();
		
		btnCancelar.addMouseListener(this);
		btnSalvar.addMouseListener(this);
		
		repaint();
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		if (e.getSource() == btnSalvar) {
			if (! txtNome.getText().equals("")) {
				enviaDados();
				dispose();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnSalvar) {
			btnSalvar.setIcon(new ImageIcon("img/geral/btn_Salvar_hovermdpi.png"));
		}
		if(e.getSource() == btnCancelar) {
			btnCancelar.setIcon(new ImageIcon("img/geral/btn_Cancelar_hovermdpi.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnSalvar) {
			btnSalvar.setIcon(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
		}
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
