package tipo_atividade;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;


public class CadastroTipoAtividade extends JFrame implements ActionListener, MouseListener{

	private JLabel lblNomeT = new JLabel("Nome:");
	private JTextField txtNomeT = new JTextField(),
					   txtCodigo = new JTextField();

	private JPanel 	paCentral = new JPanel(),
					paInferior = new JPanel();
	
	private JLabel btnSalvar = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private int codigo;
	private ResultSet rs;
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 12);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	

	public CadastroTipoAtividade() {
		//txtNomeT.setDocument(new CustomerForm(30));      
		
		add(paCentral, BorderLayout.CENTER);
		add(paInferior, BorderLayout.SOUTH);

		setBounds(100,100,400,100);
		setVisible(true);
		setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		setResizable(false);
		paCentral.setLayout(null);
		paInferior.setLayout(new FlowLayout());
		
		lblNomeT.setBounds(15, 15, 100, 30);
		lblNomeT.setFont(fonte);
		lblNomeT.setForeground(Color.WHITE);
		add(lblNomeT);
		
		txtCodigo.setBounds(125, 15, 50, 30);
		txtCodigo.setFont(fonte);
		txtCodigo.setEditable(false);
		add(txtCodigo);
		
		txtNomeT.setBounds(180, 15, 185, 30);
		txtNomeT.setFont(fonte);
		add(txtNomeT);
		
		add(btnSalvar);
		btnSalvar.setFont(fonte);
		
		add(btnCancelar);
		btnCancelar.setFont(fonte);
		
		setTitle("School Life - Cadastro de Tipo de Atividade");
		
		txtCodigo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtCodigo.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
		txtNomeT.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtNomeT.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
		btnCancelar.addMouseListener(this);
		btnSalvar.addMouseListener(this);
		
		paCentral.setBackground(new Color(16, 28, 28));
		paInferior.setBackground(new Color(28, 49, 49));
		
		paCentral.add(txtCodigo);
		paCentral.add(lblNomeT);
		paCentral.add(txtNomeT);
		
		paInferior.add(btnSalvar);
		paInferior.add(btnCancelar);
		
		codigo();
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
	
	public void codigo() {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(idtipo_atividade) FROM tipo_atividade");
			rs.next();

			rs.getString("MAX(idtipo_atividade)");
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
		if(e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource()==btnSalvar && ! txtNomeT.getText().equals("")) {
			salvarTipoAtividade();
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		CadastroTipoAtividade c = new CadastroTipoAtividade();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		if (e.getSource() == btnSalvar) {
			if (! txtNomeT.getText().equals("")) {
				salvarTipoAtividade();
				txtNomeT.setText("");
				txtNomeT.requestFocus();
				codigo();
				revalidate();
			}
		}
		
	}
}
