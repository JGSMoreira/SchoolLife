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

public class CadastrarAtividade extends JFrame implements MouseListener, ItemListener{
	
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
				   lblEstadodaAtividade = new JLabel("Estado da atividade");
	
	private JTextField txtNome = new JTextField(15),
					   txtValor = new JTextField(15),
					   txtEtapa = new JTextField(15),
					   txtCod = new JTextField(15);
	
	private JComboBox cbDia = new JComboBox(),
					  cbMes = new JComboBox(),
					  cbAno = new JComboBox(),
					  cbTipoAtividade = new JComboBox(),
					  cbEstadoAtividade = new JComboBox();
	
	private JPanel paInf = new JPanel(),
				   paCentral = new JPanel();
	
	private JLabel btnSalvar = new JLabel(new ImageIcon("img/geral/btn_Salvarmdpi.png"));
	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private JComboBox cbMateria = new JComboBox(),
					  cbPrioridade = new JComboBox();
	
	private String nomeDebug = "[CAD ATV]";
	
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
		paCentral.add(lblTipoAtividade);
		paCentral.add(cbTipoAtividade);
		paCentral.add(lblEstadodaAtividade);
		paCentral.add(cbEstadoAtividade);
		
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
		lblValor.setBounds(15, 120, 80, 30);
		txtValor.setBounds(125, 120, 50, 30);
		lblContinuaPontos.setBounds(180, 120, 125, 30);
		lblPrioridade.setBounds(15, 155, 80, 30);
		cbPrioridade.setBounds(125, 155, 240, 30);
		lblDataEntrega.setBounds(15, 190, 120, 30);
		cbDia.setBounds(125, 190, 40, 30);
		cbMes.setBounds(190, 190, 90, 30);
		cbAno.setBounds(305, 190, 60, 30);
		lblDe.setBounds(170, 190, 40, 30);
		lblDe2.setBounds(285, 190, 40, 30);
		lblTipoAtividade.setBounds(15, 225, 120, 30);
		cbTipoAtividade.setBounds(125, 225, 240, 30);
		lblEstadodaAtividade.setBounds(15, 260, 120, 30);
		cbEstadoAtividade.setBounds(125, 260, 240, 30);
		
	}
	
	public void estilizador() {
		paCentral.setBackground(new Color(16, 28, 28));
		paCentral.setLayout(null);
		paInf.setBackground(new Color(28, 49, 49));
		txtCod.setEditable(false);
		Font fonteOpenSans1 = new Font("Open Sans", Font.PLAIN, 12);
		txtCod.setHorizontalAlignment(JTextField.CENTER);
		
		lblContinuaPontos.setFont(fonteOpenSans1);
		lblEtapa.setFont(fonteOpenSans1);
		lblExemploEt.setFont(fonteOpenSans1);
		lblMateria.setFont(fonteOpenSans1);
		lblNome.setFont(fonteOpenSans1);
		lblPrioridade.setFont(fonteOpenSans1);
		lblDataEntrega.setFont(fonteOpenSans1);
		lblValor.setFont(fonteOpenSans1);
		cbDia.setFont(fonteOpenSans1);
		cbMes.setFont(fonteOpenSans1);
		cbAno.setFont(fonteOpenSans1);
		lblDe.setFont(fonteOpenSans1);
		lblDe2.setFont(fonteOpenSans1);
		lblTipoAtividade.setFont(fonteOpenSans1);
		cbTipoAtividade.setFont(fonteOpenSans1);
		lblEstadodaAtividade.setFont(fonteOpenSans1);
		cbEstadoAtividade.setFont(fonteOpenSans1);
		
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
			
			while(rs.next()) {
				cbTipoAtividade.addItem(rs.getString("nome"));
			}
			
			stm.close(); 
			revalidate();

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviaDados() {
		
		int dia = (int) cbDia.getSelectedItem();
		int mes = Integer.parseInt(converteMesNumero());
		int ano = (int) cbAno.getSelectedItem();
		String data = ano+"-"+mes+"-"+dia;
		
		String nome = txtNome.getText();
		String etapa = txtEtapa.getText();
		String pontuacao = txtValor.getText();
		String prioridade = (String) cbPrioridade.getSelectedItem();
		String situacao = (String) cbEstadoAtividade.getSelectedItem();
		int idTipoAtv = getIdTipoAtv();
		int idProf = getIdProfessor();
		int idMateria = getIdMateria();
		
		System.out.println(nomeDebug +"Prioridade: "+ prioridade);
		System.out.println(nomeDebug +"Situação: "+ situacao);
		System.out.println(nomeDebug +"ID Tipo Atv: "+ idTipoAtv);
		System.out.println(nomeDebug +"ID Prof: "+ idProf);
		System.out.println(nomeDebug +"ID Matéria: "+ idMateria);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			
			stm.executeUpdate("insert into atividade (nome, etapa, pontuacao, prioridade, data_entrega, situacao, idTipo_AtividadeFK, idProfessorFK, idMateriaFK) values"
					+ "('"+nome+"', "+etapa+","+pontuacao+",'"+prioridade+"','"+data+"','"+situacao+"',"+idTipoAtv+","+idProf+","+idMateria+");");
		
			basico.JanelaPergunta pe = new basico.JanelaPergunta("Dados gravados com sucesso!");
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
			String selecionado = String.valueOf(this.cbMateria.getSelectedItem());
			this.rs = stm.executeQuery("select idProfessorFK from materia where nome like " + "'" + selecionado + "';");
			while(rs.next()) {
				idProf = rs.getInt("idProfessorFK");
			}
			stm.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return(idProf);
	}
	public int getIdMateria() {
		int idMate = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			String selecionado = String.valueOf(this.cbMateria.getSelectedItem());
			this.rs = stm.executeQuery("select idMateria from materia where nome like " + "'" + selecionado + "';");
			while(rs.next()) {
				idMate = rs.getInt("idMateria");
			}
			stm.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return(idMate);
	}
	public int getIdTipoAtv() {
		int idMate = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			String selecionado = String.valueOf(this.cbTipoAtividade.getSelectedItem());
			this.rs = stm.executeQuery("select idTipo_Atividade from tipo_atividade where nome like " + "'" + selecionado + "';");
			while(rs.next()) {
				idMate = rs.getInt("idTipo_Atividade");
			}
			stm.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return(idMate);
	}
	
	public String converteMesNumero() {
		if (cbMes.getSelectedItem() == "Janeiro")
			return("01");
		else if (cbMes.getSelectedItem() == "Fevereiro")
			return("02");
		else if (cbMes.getSelectedItem() == "Março")
			return("03");
		else if (cbMes.getSelectedItem() == "Abril")
			return("04");
		else if (cbMes.getSelectedItem() == "Maio")
			return("05");
		else if (cbMes.getSelectedItem() == "Junho")
			return("06");
		else if (cbMes.getSelectedItem() == "Julho")
			return("07");
		else if (cbMes.getSelectedItem() == "Agosto")
			return("08");
		else if (cbMes.getSelectedItem() == "Setembro")
			return("09");
		else if (cbMes.getSelectedItem() == "Outubro")
			return("10");
		else if (cbMes.getSelectedItem() == "Novembro")
			return("11");
		else if (cbMes.getSelectedItem() == "Dezembro")
			return("12");
		else
			return("makonha");
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
		
		cbMes.addItem("---");
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
		
		cbEstadoAtividade.addItem("Feito");
		cbEstadoAtividade.addItem("A fazer");
		cbEstadoAtividade.addItem("Atrasado");
		
		adicionador();
		posicionador();
		estilizador();
		codigo();
		carregaMaterias();
		carregaTipoAtv();
		
		btnCancelar.addMouseListener(this);
		btnSalvar.addMouseListener(this);
		cbMes.addItemListener(this);
		
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

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		cbDia.removeAllItems();
		//MESES COM 31 DIAS
		if ((cbMes.getSelectedItem() == "Janeiro") || 
			(cbMes.getSelectedItem() == "Abril") || 
			(cbMes.getSelectedItem() == "Junho") ||
			(cbMes.getSelectedItem() == "Agosto") ||
			(cbMes.getSelectedItem() == "Outubro") ||
			(cbMes.getSelectedItem() == "Dezembro")
			) {
					for (int i=1; i<32; i++) {
						cbDia.addItem(i);
						revalidate();
						repaint();
					}
				}
		//APENAS FEVEREIRO
		else if (cbMes.getSelectedItem() == "Fevereiro") {
			for (int i=1; i<30; i++) {
				cbDia.addItem(i);	
				revalidate();
				repaint();
			}
		}
		//MESES COM 30 DIAS
		else if((cbMes.getSelectedItem() == "Março") ||
				(cbMes.getSelectedItem() == "Maio") ||
				(cbMes.getSelectedItem() == "Julho") ||
				(cbMes.getSelectedItem() == "Setembro") ||
				(cbMes.getSelectedItem() == "Novembro") 
				){
			for (int i=1; i<31; i++) {
				cbDia.addItem(i);
				revalidate();
				repaint();
			}
		}
		revalidate();
		repaint();
		}
}



