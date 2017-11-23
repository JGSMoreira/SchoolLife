package tipo_atividade;
import com.mysql.jdbc.Driver;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.layout.Border;

public class CadastrarTipoAtividade extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4248697972014390077L;
	
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField(),
					   txtCodigo = new JTextField();
	
	private JPanel paCentral = new JPanel(),
				   paInferior = new JPanel();
	
	private int codigo;
	private ResultSet rs;
	
	private JLabel btnSalvar = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 12);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public CadastrarTipoAtividade () {
		
		setBounds(100,100,400,143);
		setTitle("School Life - Cadastro de Tipo de Atividade");
		setVisible(true);
		setResizable(false);
		setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		add(paCentral, BorderLayout.CENTER);
		add(paInferior, BorderLayout.SOUTH);
		
		paCentral.setLayout(null);
		
		btnCancelar.addMouseListener(this);
		btnSalvar.addMouseListener(this);
	
		lblNome.setBounds(15, 15, 100, 30);
		paCentral.add(lblNome);
		lblNome.setFont(fonte);

		txtNome.setBounds(180, 15, 185, 30);
		paCentral.add(txtNome);
		txtNome.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtNome.requestFocus();
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setFont(fonte);
		
		txtCodigo.setBounds(125, 15, 50, 30);
		paCentral.add(txtCodigo);
		txtCodigo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtCodigo.setHorizontalAlignment(JTextField.CENTER);
		txtCodigo.setEditable(false);
		txtCodigo.setFont(fonte);
		
		paInferior.add(btnSalvar);
		btnSalvar.setFont(fonte);
		
		paInferior.add(btnCancelar);
		
		paCentral.setBackground(new Color(16, 28, 28));
		lblNome.setForeground(Color.WHITE);
		paInferior.setBackground(new Color(28, 49, 49));
		
		codigo();

	}
	
	public void codigo() {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(idtipo_atividade) FROM tipo_atividade;");
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
		if (e.getSource() == btnSalvar) {
			if (! txtNome.getText().equals("")) {
				salvarProfessor();
			}
			else if (e.getSource() == btnCancelar) {
				dispose();
			}
		}
	}
	
	public void salvarProfessor() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		stm=conexao.createStatement();
		
		stm.executeUpdate("insert into tipo_atividade (nome) values" + "('"+txtNome.getText()+"');");
		
		basico.JanelaPergunta a = new basico.JanelaPergunta("Tipo de Atividade cadastrado com sucesso!");
		stm.close();		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		if (e.getSource() == btnSalvar) {
			if (! txtNome.getText().equals("")) {
				salvarProfessor();
				txtNome.setText("");
				txtNome.requestFocus();
				codigo();
				revalidate();
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

