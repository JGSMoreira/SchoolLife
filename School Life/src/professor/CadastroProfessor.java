package professor;
import com.mysql.jdbc.Driver;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.layout.Border;

public class CadastroProfessor extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4248697972014390077L;
	
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField(),
					   txtCodigo = new JTextField();
	private JButton btnSalvar = new JButton("Salvar"),
					btnFechar = new JButton("Fechar");
	
	private JPanel paCentral = new JPanel(),
				   paInferior = new JPanel();
	
	private int codigo;
	private ResultSet rs;

	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 12);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public CadastroProfessor () {
		setBounds(100,100,400,130);
		setTitle("School Life - Cadastro de Professor");
		setVisible(true);
		setResizable(false);
		setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		add(paCentral, BorderLayout.CENTER);
		add(paInferior, BorderLayout.SOUTH);
		
		paCentral.setLayout(null);
		
		btnFechar.addActionListener(this);
		btnSalvar.addActionListener(this);
		

		lblNome.setBounds(15, 15, 100, 30);
		paCentral.add(lblNome);
		lblNome.setFont(fonte);
		
		txtNome.setBounds(180, 15, 185, 30);
		paCentral.add(txtNome);
		txtNome.setFont(fonte);
		
		txtCodigo.setBounds(125, 15, 50, 30);
		paCentral.add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setFont(fonte);
		
		paInferior.add(btnSalvar);
		btnSalvar.setFont(fonte);
		
		paInferior.add(btnFechar);
		btnFechar.setFont(fonte);
		
		paCentral.setBackground(Color.white);
		paCentral.setLayout(null);
		paInferior.setBackground(new Color(0, 206, 209));
		
		codigo();

	}
	
	public void codigo() {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(idprofessor) FROM professor;");
			rs.next();

			rs.getString("MAX(idProfessor)");
			if(rs.wasNull()) {
				codigo = 1;
			}
			else {
				this.codigo=((Number) rs.getObject(1)).intValue();
				this.codigo=codigo + 1;
			}
			
			txtCodigo.setText(Integer.toString(codigo));

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFechar) {
			dispose();
		}
		else if (e.getSource() == btnSalvar) {
			if (! txtNome.getText().equals("")) {
			salvarProfessor();
			dispose();
			}
		}
	}
	
	public void salvarProfessor() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		stm=conexao.createStatement();
		
		stm.executeUpdate("insert into professor (nome) values" + "('"+txtNome.getText()+"');");
	
		JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
		stm.close();		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String []args) {
		CadastroProfessor cp = new CadastroProfessor();
	}
}
