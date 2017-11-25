package atividade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import materia.CadastrarMateria;

public class VisualizarAtividade extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//VARIÁVEIS
	private JLabel lblNome = new JLabel("Nome da atividade"),
				   lblValor = new JLabel("Valor"),
				   lblEtapa = new JLabel("Etapa"),
				   lblMateria = new JLabel("Matéria"),
				   lblPrioridade = new JLabel("Prioridade"),
				   lblExemploEt = new JLabel("(Ex. 1, 2, 3, 4, etc.)"),
				   lblDataEntrega = new JLabel("Data de entrega"),
				   lblDe = new JLabel("de"),
				   lblDe2 = new JLabel("de"),
				   lblContinuaPontos = new JLabel("Pontos"),
				   lblTipoAtividade = new JLabel("Tipo de atividade"),
				   lblEstadodaAtividade = new JLabel("Status");
	
	private JTextField txtNome = new JTextField(15),
					   txtValor = new JTextField(15),
					   txtEtapa = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JTextField txtDia = new JTextField(),
					   txtTipoAtividade = new JTextField(),
					   txtEstadoAtividade = new JTextField(),
					   txtMateria = new JTextField(),
					   txtPrioridade = new JTextField();
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private String nomeDebug = "[CAD ATV]";
	
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		
		paInf.add(btnCancelar);
		
		paCentral.add(txtCod);
		paCentral.add(lblEtapa);
		paCentral.add(txtEtapa);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblMateria);
		paCentral.add(txtMateria);
		paCentral.add(lblPrioridade);
		paCentral.add(txtPrioridade);
		paCentral.add(lblValor);
		paCentral.add(txtValor);
		paCentral.add(lblExemploEt);
		paCentral.add(lblContinuaPontos);
		paCentral.add(txtDia);
		paCentral.add(lblDataEntrega);
		paCentral.add(lblTipoAtividade);
		paCentral.add(txtTipoAtividade);
		paCentral.add(lblEstadodaAtividade);
		paCentral.add(txtEstadoAtividade);
		
	}
	
	public void posicionador() {
		
		lblEtapa.setBounds(15, 15, 125, 30);
		txtEtapa.setBounds(125, 15, 50, 30);
		lblExemploEt.setBounds(180, 15, 125, 30);
		txtCod.setBounds(125, 50, 50, 30);
		lblNome.setBounds(15, 50, 125, 30);
		txtNome.setBounds(180, 50, 185, 30);
		lblMateria.setBounds(15, 85, 80, 30);
		txtMateria.setBounds(125, 85, 240, 30);
		lblValor.setBounds(15, 120, 80, 30);
		txtValor.setBounds(125, 120, 50, 30);
		lblContinuaPontos.setBounds(180, 120, 125, 30);
		lblPrioridade.setBounds(15, 155, 80, 30);
		txtPrioridade.setBounds(125, 155, 240, 30);
		lblDataEntrega.setBounds(15, 190, 120, 30);
		txtDia.setBounds(125, 190, 240, 30);
		lblTipoAtividade.setBounds(15, 225, 120, 30);
		txtTipoAtividade.setBounds(125, 225, 240, 30);
		lblEstadodaAtividade.setBounds(15, 260, 120, 30);
		txtEstadoAtividade.setBounds(125, 260, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(new Color(16, 28, 28));
		paCentral.setLayout(null);
		paInf.setBackground(new Color(28, 49, 49));
		txtCod.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		
		txtCod.setHorizontalAlignment(JTextField.CENTER);
		txtEtapa.setHorizontalAlignment(JTextField.CENTER);
		txtValor.setHorizontalAlignment(JTextField.CENTER);
		
		lblContinuaPontos.setFont(fonteOpenSans1);
		lblEtapa.setFont(fonteOpenSans1);
		lblExemploEt.setFont(fonteOpenSans1);
		lblMateria.setFont(fonteOpenSans1);
		lblNome.setFont(fonteOpenSans1);
		lblPrioridade.setFont(fonteOpenSans1);
		lblDataEntrega.setFont(fonteOpenSans1);
		lblValor.setFont(fonteOpenSans1);
		txtDia.setFont(fonteOpenSans1);
		lblDe.setFont(fonteOpenSans1);
		lblDe2.setFont(fonteOpenSans1);
		lblTipoAtividade.setFont(fonteOpenSans1);
		txtTipoAtividade.setFont(fonteOpenSans1);
		lblEstadodaAtividade.setFont(fonteOpenSans1);
		txtEstadoAtividade.setFont(fonteOpenSans1);
		txtMateria.setFont(fonteOpenSans1);
		txtPrioridade.setFont(fonteOpenSans1);
		
		lblNome.setForeground(Color.WHITE);
		lblContinuaPontos.setForeground(Color.WHITE);
		lblEtapa.setForeground(Color.WHITE);
		lblExemploEt.setForeground(Color.WHITE);
		lblMateria.setForeground(Color.WHITE);
		lblPrioridade.setForeground(Color.WHITE);
		lblValor.setForeground(Color.WHITE);
		lblDataEntrega.setForeground(Color.WHITE);
		lblDe.setForeground(Color.WHITE);
		lblDe2.setForeground(Color.white);
		lblTipoAtividade.setForeground(Color.WHITE);
		lblEstadodaAtividade.setForeground(Color.WHITE);
		
		txtEtapa.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtValor.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
		txtDia.setEditable(false);
		txtEstadoAtividade.setEditable(false);
		txtEtapa.setEditable(false);
		txtMateria.setEditable(false);
		txtNome.setEditable(false);
		txtPrioridade.setEditable(false);
		txtTipoAtividade.setEditable(false);
		txtValor.setEditable(false);
		
	}
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
	public void carregaChave(int idMate, int idTipo) {
		//NOME DA MATERIA
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			this.rs = stm.executeQuery("select MAX(nome) from materia where idMateria = "+idMate);
			rs.next();
			
			txtMateria.setText(rs.getString("MAX(nome)"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		//TIPO DA ATIVIDADE
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			this.rs = stm.executeQuery("select MAX(nome) from tipo_Atividade where idtipo_atividade = "+idTipo);
			rs.next();
			
			txtTipoAtividade.setText(rs.getString("MAX(nome)"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void carregaDados(int codigo) {
		int idTipo = 0;
		int idMate = 0;
		
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT MAX(nome), MAX(etapa), MAX(pontuacao), MAX(data_entrega), MAX(prioridade), MAX(situacao), MAX(idTipo_atividadefk), MAX(idMateriaFK) FROM atividade where idAtividade = " + codigo + ";");
			rs.next();

			txtNome.setText(rs.getString("MAX(nome)"));
			txtEtapa.setText(rs.getString("MAX(etapa)"));
			txtValor.setText(rs.getString("MAX(pontuacao)"));
			txtDia.setText(rs.getString("MAX(data_entrega)"));
			txtEstadoAtividade.setText(rs.getString("MAX(situacao)"));
			txtPrioridade.setText(rs.getString("MAX(prioridade)"));
			
			idTipo = rs.getInt("MAX(idTipo_atividadeFK)");
			idMate = rs.getInt("MAX(idMateriaFK)");

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		carregaChave(idMate, idTipo);
	}
	
	public void codigo(String nomeAtv) {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm = conexao.createStatement();

			this.rs = stm.executeQuery("SELECT MAX(idAtividade) FROM atividade where nome like '"+nomeAtv+"';");
			rs.next();
			
			rs.getString("MAX(idAtividade)");
			this.codigo = ((Number) rs.getObject(1)).intValue();
			txtCod.setText(Integer.toString(codigo));
			stm.close();	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		carregaDados(codigo);
	}
	
	
	//CONSTRUTOR
	public VisualizarAtividade(String nomeAtv) {
		this.setIconImage(new ImageIcon("img/geral/icon.png").getImage());
		this.setTitle("School Life - Cadastrar Atividade");
		this.setBounds(0, 0, 400, 390);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		adicionador();
		posicionador();
		estilizador();
		codigo(nomeAtv);
		
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