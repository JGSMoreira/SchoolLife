package Professor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ExibeProfessor extends JFrame implements ActionListener{
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	private JButton btnFechar = new JButton("Fechar");
	private ResultSet rs;
	private JTable tabela;
	private JScrollPane scroller;
	private JPanel painelSul = new JPanel();
	
	
	public ExibeProfessor() {
		
		setBounds(300, 300, 600, 300);
		setVisible(true);

		setTitle("Lista de Professores");
		painelSul.add(btnFechar);
		btnFechar.addActionListener(this);
		add(painelSul, BorderLayout.SOUTH);
		setResizable(false);
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			rs = stm.executeQuery("select * from professor");
			Vector cabecalhos = new Vector(),
				   linhas = new Vector();
			cabecalhos.addElement("Código");
			cabecalhos.addElement("Nome");
			
			
			while(rs.next()) {
				Vector linha = new Vector();
				linha.addElement(rs.getString("idprofessor"));
				linha.addElement(rs.getString("nome"));
				linhas.addElement(linha);
			}
			
			stm.close(); 
			
			tabela = new JTable(linhas, cabecalhos);
			scroller = new JScrollPane(tabela);
			this.add(scroller, BorderLayout.CENTER);
			revalidate();
			

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFechar)
			dispose();
	}
	
	public static void main(String[] args) {
		ExibeProfessor ep = new ExibeProfessor();
	}
}
