package atividade;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;


public class CadastroTipoAtividade extends JFrame implements ActionListener{

	private JLabel lblNomeT = new JLabel("Nome:"),
				   lblCodigo = new JLabel("Código:");
	private JTextField txtNomeT = new JTextField(),
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
	

	public CadastroTipoAtividade() {
		//txtNomeT.setDocument(new CustomerForm(30));      
		
		setLayout(null);

		setBounds(100,100,400,300);
		setVisible(true);

		lblNomeT.setBounds(50, 100, 100, 20);
		lblNomeT.setFont(fonte);
		add(lblNomeT);
		
		lblCodigo.setBounds(50, 20, 80, 50);
		lblCodigo.setFont(fonte);
		add(lblCodigo);
		
		txtCodigo.setBounds(120, 30, 50, 30);
		txtCodigo.setFont(fonte);
		txtCodigo.setEditable(false);
		add(txtCodigo);
		
		txtNomeT.setBounds(120, 98, 200, 30);
		txtNomeT.setFont(fonte);
		add(txtNomeT);
		
		btnSalvar.setBounds(50, 200, 100, 40);
		add(btnSalvar);
		btnSalvar.setFont(fonte);
		
		btnFechar.setBounds(250, 200, 100, 40);
		add(btnFechar);
		btnFechar.setFont(fonte);
		
		setTitle("School Life - Cadastro de Tipo de Atividade");
		
		btnFechar.addActionListener(this);
		btnSalvar.addActionListener(this);

	}
	

	
	public void limpar() {
		txtNomeT.setText("");
		txtNomeT.requestFocus();
	}
	
	public void salvarTipoAtividade() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			stm.executeUpdate("insert into tipo_atividade (nome) values" + "('"+txtNomeT.getText()+"');");
		
			limpar();
			JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
			stm.close();		
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	



	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFechar) {
			dispose();
		}
		else if (e.getSource()==btnSalvar && ! txtNomeT.getText().equals("")) {
			salvarTipoAtividade();
		}
		
	}
	
	public static void main(String[] args) {
		CadastroTipoAtividade c = new CadastroTipoAtividade();
	}
}
