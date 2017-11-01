package professor;

import java.awt.BorderLayout;
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

public class CadastroProfessor extends JFrame implements ActionListener{
	
	private JLabel lblNome = new JLabel("Nome:"),
				   lblCodigo = new JLabel("Código:");
	private JTextField txtNome = new JTextField(),
					   txtCodigo = new JTextField();
	private JButton btnSalvar = new JButton("Salvar"),
					btnFechar = new JButton("Fechar");
	
	private int codigo;
	private ResultSet rs;

	
	private Font fonte = new Font ("Open Sans", Font.TYPE1_FONT, 16);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public CadastroProfessor () {
		setBounds(100,100,400,300);
		setTitle("Cadastro de Professor");
		setVisible(true);
		setResizable(false);
		setLayout(null);
		
		
		btnFechar.addActionListener(this);
		btnSalvar.addActionListener(this);
		

		lblNome.setBounds(50, 100, 100, 40);
		add(lblNome);
		lblNome.setFont(fonte);
		
		txtNome.setBounds(120, 105, 230, 30);
		add(txtNome);
		txtNome.setFont(fonte);
		
		lblCodigo.setBounds(50, 20, 100, 40);
		add(lblCodigo);
		lblCodigo.setFont(fonte);
		
		txtCodigo.setBounds(120, 25, 50, 30);
		add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setFont(fonte);
		
		btnSalvar.setBounds(50, 200, 100, 40);
		add(btnSalvar);
		
		btnFechar.setBounds(250, 200, 100, 40);
		add(btnFechar);
		codigo();

	}
	
	public void codigo() {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			this.rs=stm.executeQuery("select max(idprofessor) from professor;");
			this.codigo=((Number) rs.getObject(1)).intValue();
			this.codigo=+1;
			
			System.out.println(stm.executeQuery("select max(idprofessor) from professor;"));
			txtCodigo.setText(Integer.toString(codigo));
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFechar) {
			dispose();
		}
		else if (e.getSource() == btnSalvar) {
			if (! txtNome.getText().equals("")) {
			salvarProfessor();
			}
		}
	}
	
	public void salvarProfessor() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		stm=conexao.createStatement();
		
		stm.executeUpdate("insert into professor (nome) values" + "('"+txtNome.getText()+"');");
	
		limpar();
		JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
		stm.close();		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		txtNome.setText("");
		txtNome.requestFocus();
	}

	
	public static void main(String[] args) {
		CadastroProfessor c = new CadastroProfessor();
	}

	
}
