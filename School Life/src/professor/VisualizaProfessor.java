package professor;
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
import java.util.Vector;

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

public class VisualizaProfessor extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4248697972014390077L;
	
	private JLabel lblNome = new JLabel("Nome"),
				   lblEmail = new JLabel("E-mail");
	private JTextField txtNome = new JTextField(),
					   txtCodigo = new JTextField(),
					   txtEmail = new JTextField();
	
	private JPanel paCentral = new JPanel(),
				   paInferior = new JPanel();
	
	private int codigo;
	private ResultSet rs;
	
	private JLabel btnOk = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 12);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public VisualizaProfessor (String nomeProf) {
		setBounds(100,100,400,185);
		setTitle("School Life - Visualizar Professor");
		setVisible(true);
		setResizable(false);
		setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		add(paCentral, BorderLayout.CENTER);
		add(paInferior, BorderLayout.SOUTH);
		
		paCentral.setLayout(null);
		
		btnOk.addMouseListener(this);
	
		lblNome.setBounds(15, 15, 100, 30);
		paCentral.add(lblNome);
		lblNome.setFont(fonte);
		
		lblEmail.setFont(fonte);
		lblEmail.setForeground(Color.WHITE);
		paCentral.add(lblEmail);
		lblEmail.setBounds(15, 65, 100, 30);
		
		txtEmail.setFont(fonte);
		paCentral.add(txtEmail);
		txtEmail.setBounds(125, 65, 240, 30);
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtEmail.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtEmail.setEditable(false);
		
		txtNome.setBounds(180, 15, 185, 30);
		paCentral.add(txtNome);
		txtNome.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtNome.requestFocus();
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setFont(fonte);
		txtNome.setEditable(false);
		
		txtCodigo.setBounds(125, 15, 50, 30);
		paCentral.add(txtCodigo);
		txtCodigo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		txtCodigo.setHorizontalAlignment(JTextField.CENTER);
		txtCodigo.setEditable(false);
		txtCodigo.setFont(fonte);
		
		paInferior.add(btnOk);
		btnOk.setFont(fonte);
		
		paCentral.setBackground(new Color(16, 28, 28));
		lblNome.setForeground(Color.WHITE);
		paInferior.setBackground(new Color(28, 49, 49));
		
		codigo(nomeProf);
	}
	
	public void carregaDados(int codigo) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(nome), MAX(email) FROM professor where idProfessor = " + codigo + ";");
			rs.next();

			txtNome.setText(rs.getString("MAX(nome)"));
			txtEmail.setText(rs.getString("MAX(email)"));

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
			
			txtCodigo.setText(Integer.toString(codigo));

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		carregaDados(codigo);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			if (! txtNome.getText().equals("")) {
				dispose();
			}
		}
	}
	
	public void VisualizarProfessor() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		stm=conexao.createStatement();
		
		stm.executeQuery("select * from professor where idProfessor = '"+"'");
		
			txtNome.setText(rs.getString("idprofessor"));
			stm.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOk) {
			dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnOk) {
			btnOk.setIcon(new ImageIcon("img/geral/btn_Salvar_hovermdpi.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnOk) {
			btnOk.setIcon(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
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
