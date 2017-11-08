package atividade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import materia.CadastrarMateria;

public class CadastrarAtividade extends JFrame implements MouseListener{
	
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
				   lblProf = new JLabel("Professor"),
				   lblExemploEt = new JLabel("(Ex. 1, 2, 3, 4, etc.)"),
				   lblDataEntrega = new JLabel("Data de entrega"),
				   lblDe = new JLabel("de"),
				   lblDe2 = new JLabel("de"),
				   lblContinuaPontos = new JLabel("Pontos");
	
	private JTextField txtNome = new JTextField(15),
					   txtValor = new JTextField(15),
					   txtEtapa = new JTextField(15),
					   txtProf = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JComboBox cbDia = new JComboBox(),
					  cbMes = new JComboBox(),
					  cbAno = new JComboBox();
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JLabel btnSalvar = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private JComboBox cbMateria = new JComboBox(),
					  cbPrioridade = new JComboBox();
	
	//ORGANIZADORES DA INTERFACE
	public void adicionador() {
		this.add(paInf, BorderLayout.SOUTH);
		this.add(paCentral, BorderLayout.CENTER);
		
		paInf.add(btnSalvar);
		paInf.add(btnCancelar);
		
		paCentral.add(txtCod);
		paCentral.add(lblEtapa);
		paCentral.add(txtEtapa);
		paCentral.add(lblNome);
		paCentral.add(txtNome);
		paCentral.add(lblMateria);
		paCentral.add(cbMateria);
		paCentral.add(lblProf);
		paCentral.add(txtProf);
		paCentral.add(lblPrioridade);
		paCentral.add(cbPrioridade);
		paCentral.add(lblValor);
		paCentral.add(txtValor);
		paCentral.add(lblExemploEt);
		paCentral.add(lblContinuaPontos);
		paCentral.add(cbAno);
		paCentral.add(cbDia);
		paCentral.add(cbMes);
		paCentral.add(lblDataEntrega);
		paCentral.add(lblDe);
		paCentral.add(lblDe2);
		
	}
	
	public void verifica_data() {
		
		if (cbMes.getSelectedItem() == "Janeiro") {
			for (int i=1; i<32; i++) {
				cbDia.addItem(i);
				revalidate();
				repaint();
				}
			}
		
			else if (cbMes.getSelectedItem() == "Fevereiro") {
				for (int i=1; i<30; i++) {
					cbDia.addItem(i);	
					revalidate();
					repaint();
				}
			}
				else if(cbMes.getSelectedItem() == "Março") {
					for (int i=1; i<31; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
			
				else if(cbMes.getSelectedItem() == "Abril") {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Maio") {
					for (int i=1; i<31; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Junho") {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Julho") {
					for (int i=1; i<31; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Agosto") {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Setembro") {
					for (int i=1; i<31; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Outubro") {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Novembro") {
					for (int i=1; i<31; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
		
				else if(cbMes.getSelectedItem() == "Dezembro") {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
						}
				}
			
			revalidate();
			repaint();
		
	}
	
	public void posicionador() {
		
		lblEtapa.setBounds(15, 15, 125, 30);
		txtEtapa.setBounds(125, 15, 50, 30);
		lblExemploEt.setBounds(180, 15, 125, 30);
		txtCod.setBounds(125, 50, 50, 30);
		lblNome.setBounds(15, 50, 125, 30);
		txtNome.setBounds(180, 50, 185, 30);
		lblMateria.setBounds(15, 85, 80, 30);
		cbMateria.setBounds(125, 85, 240, 30);
		lblProf.setBounds(15, 120, 80, 30);
		txtProf.setBounds(125, 120, 240, 30);
		lblValor.setBounds(15, 155, 80, 30);
		txtValor.setBounds(125, 155, 50, 30);
		lblContinuaPontos.setBounds(180, 155, 125, 30);
		lblPrioridade.setBounds(15, 190, 80, 30);
		cbPrioridade.setBounds(125, 190, 240, 30);
		lblDataEntrega.setBounds(15, 140, 200, 200);
		cbDia.setBounds(125, 225, 40, 30);
		cbMes.setBounds(190, 225, 90, 30);
		cbAno.setBounds(305, 225, 60, 30);
		lblDe.setBounds(170, 225, 40, 30);
		lblDe2.setBounds(285, 225, 40, 30);



		
	}
	
	public void estilizador() {
		paCentral.setBackground(new Color(16, 28, 28));
		paCentral.setLayout(null);
		paInf.setBackground(new Color(28, 49, 49));
		txtCod.setEditable(false);
		txtProf.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		txtCod.setHorizontalAlignment(JTextField.CENTER);
		
		lblContinuaPontos.setFont(fonteOpenSans1);
		lblEtapa.setFont(fonteOpenSans1);
		lblExemploEt.setFont(fonteOpenSans1);
		lblMateria.setFont(fonteOpenSans1);
		lblNome.setFont(fonteOpenSans1);
		lblPrioridade.setFont(fonteOpenSans1);
		lblDataEntrega.setFont(fonteOpenSans1);
		lblProf.setFont(fonteOpenSans1);
		lblValor.setFont(fonteOpenSans1);
		cbDia.setFont(fonteOpenSans1);
		cbMes.setFont(fonteOpenSans1);
		cbAno.setFont(fonteOpenSans1);
		lblDe.setFont(fonteOpenSans1);
		lblDe2.setFont(fonteOpenSans1);
		
		lblNome.setForeground(Color.WHITE);
		lblProf.setForeground(Color.WHITE);
		lblContinuaPontos.setForeground(Color.WHITE);
		lblEtapa.setForeground(Color.WHITE);
		lblExemploEt.setForeground(Color.WHITE);
		lblMateria.setForeground(Color.WHITE);
		lblPrioridade.setForeground(Color.WHITE);
		lblValor.setForeground(Color.WHITE);
		lblDataEntrega.setForeground(Color.WHITE);
		lblDe.setForeground(Color.WHITE);
		lblDe2.setForeground(Color.white);
		
		txtEtapa.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtProf.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		txtValor.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.WHITE));
		
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
	
	public void codigo() {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm = conexao.createStatement();

			this.rs = stm.executeQuery("SELECT MAX(idAtividade) FROM atividade;");
			rs.next();
			
			rs.getString("MAX(idAtividade)");
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
	
	public void carregaMaterias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			rs = stm.executeQuery("select nome from materia");
			Vector linhas = new Vector();			
			
			while(rs.next()) {
				cbMateria.addItem(rs.getString("nome"));
			}
			
			stm.close(); 
			revalidate();
			

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregaTipoAtv() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			rs = stm.executeQuery("select nome from tipo_atividade");
			Vector linhas = new Vector();			
			
			while(rs.next()) {
				cbMateria.addItem(rs.getString("nome"));
			}
			
			stm.close(); 
			revalidate();
			

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviaDados() {
		
	}
	
	//CONSTRUTOR
	public CadastrarAtividade() {
		
		this.setTitle("School Life - Cadastrar Atividade");
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		cbPrioridade.addItem("Baixa");
		cbPrioridade.addItem("Média");
		cbPrioridade.addItem("Alta");
		
		cbMes.addItem("Janeiro");
		cbMes.addItem("Fevereiro");
		cbMes.addItem("Março");
		cbMes.addItem("Abril");
		cbMes.addItem("Maio");
		cbMes.addItem("Junho");
		cbMes.addItem("Julho");
		cbMes.addItem("Agosto");
		cbMes.addItem("Setembro");
		cbMes.addItem("Outubro");
		cbMes.addItem("Novembro");
		cbMes.addItem("Dezembro");		
		
		cbAno.addItem(2017);
		
		adicionador();
		posicionador();
		estilizador();
		codigo();
		carregaMaterias();
		carregaTipoAtv();
		verifica_data();
		
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



