package tipo_atividade;

import java.awt.BorderLayout;
import java.awt.Font;
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


public class ExibeTipoAtividade extends JFrame implements ActionListener{
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	private Font fonte = new Font ("Open Sans", Font.TYPE1_FONT, 16);
	
	private Connection conexao;
	private Statement stm;
	private JButton btnFechar = new JButton("Fechar");
	private ResultSet rs;
	private JTable tabela;
	private JScrollPane scroller;
	private JPanel painelSul = new JPanel();
	
	
	public ExibeTipoAtividade() {
		
		setBounds(300, 300, 450, 300);
		setVisible(true);

		setTitle("School Life - Lista de Tipos de Atividades");
		painelSul.add(btnFechar);
		btnFechar.addActionListener(this);
		add(painelSul, BorderLayout.SOUTH);
		setResizable(false);
		revalidate();
		
		btnFechar.setFont(fonte);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();
			rs = stm.executeQuery("select * from tipo_atividade");
			Vector cabecalhos = new Vector(),
				   linhas = new Vector();
			cabecalhos.addElement("Código");
			cabecalhos.addElement("Tipo");
			
			
			
			while(rs.next()) {
				Vector linha = new Vector();
				linha.addElement(rs.getString("idtipo_atividade"));
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
		ExibeTipoAtividade ep = new ExibeTipoAtividade();
	}
}
