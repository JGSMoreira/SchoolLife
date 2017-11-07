package testes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class listView extends JFrame implements ActionListener{
	
	JPanel gradeInferior = new JPanel(),
		   navbar = new  JPanel();
	JScrollPane pa = new JScrollPane();
	JTextField txtPesq = new JTextField(30);
	JButton btnBusc = new JButton("Pesquisar"),
			btnAtualizar = new JButton("Atualizar");
	
	
	
	JScrollPane scroll = new JScrollPane(gradeInferior);
	
	//DADOS DE LOGIN - BD
		private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
				   usuario = "root",
				   senha = "root";
		private Connection conexao;
		private Statement stm;
		private ResultSet rs;
		
	//BANCO DE DADOS
		public void getDados() {
			String comando = "select * from professor;";
			carregaDados(comando);
		}
		
		public void getDadosPesquisa() {
			String comando = "select * from professor where nome like '%" + txtPesq.getText() + "%';";
			carregaDados(comando);
		}
	
		public void carregaDados(String comando) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(url, usuario, senha);
				stm=conexao.createStatement();
				this.rs = stm.executeQuery(comando);
				
				int espacamento = 0;
				int primeiro = 0;
						
				gradeInferior.removeAll();
				
				while(rs.next()) {
					JLabel aa = new JLabel(),
						   cc = new JLabel();
					JSeparator se = new JSeparator();
					JButton btnEditar = new JButton("Editar"),
							btnExcluir = new JButton("Excluir"),
							btnVer = new JButton("Ver");					
					
					aa.setText(rs.getString("nome"));
					cc.setText(rs.getString("idProfessor"));
					
					rs.getInt("idProfessor");
					if (rs.wasNull()) {
						JLabel lblNaoEncont = new JLabel("NENHUM DADO ENCONTRADO!");
						gradeInferior.add(lblNaoEncont, BorderLayout.CENTER);
						revalidate();
					}
					else {
						if (primeiro ==  0) {
							espacamento = 15;
							cc.setBounds(25, espacamento, 1000, 25);
							aa.setBounds(75, espacamento, 1000, 25);
							primeiro = 1;
						}
						else {
							cc.setBounds(25, espacamento, 1000, 25);
							aa.setBounds(75, espacamento, 1000, 25);	
						}
						
						gradeInferior.add(aa);
						gradeInferior.add(cc);
						gradeInferior.add(se);
						gradeInferior.add(btnEditar);
						gradeInferior.add(btnVer);
						gradeInferior.add(btnExcluir);
						
						aa.setForeground(Color.white);
						cc.setForeground(Color.white);
						
						btnEditar.setBounds(510, espacamento - 5, 75, 35);
						btnVer.setBounds(595, espacamento - 5, 75, 35);
						btnExcluir.setBounds(680, espacamento - 5, 75, 35);
						
						se.setBounds(0, espacamento + 40, 1000, 1);
						
						espacamento = espacamento + 55;
					}
					
					btnEditar.addActionListener(e->{professor.EditaProfessor edita = new professor.EditaProfessor(aa.getText());});
				}
				stm.close();
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			this.add(scroll, BorderLayout.CENTER);
		}
	//ELEMENTOS DA TABELA
		public void navBar() {
			txtPesq.setPreferredSize(new Dimension(20, 30));
			btnBusc.setPreferredSize(new Dimension(110, 30));
			btnAtualizar.setPreferredSize(new Dimension(110, 30));
			navbar.add(btnAtualizar);
			navbar.add(txtPesq);
			navbar.add(btnBusc);
			navbar.setBounds(0, 0, 100, 100);
			this.add(navbar, BorderLayout.NORTH);
		}
		
		public void estilizar() {
			navbar.setBackground(new Color(47,79,79));
			gradeInferior.setBackground(new Color(16, 28, 28));
		}
		
		public listView() {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setBounds(100, 100, 800, 600);
			this.setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			this.setLayout(new BorderLayout());
			gradeInferior.setLayout(null);

			getDados();
			navBar();
			estilizar();
			revalidate();
			repaint();
			
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			btnBusc.addActionListener(this);
			btnAtualizar.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnBusc) {
				getDadosPesquisa();
				this.revalidate();
				this.repaint();
			}
			if (e.getSource() == btnAtualizar) {
				getDadosPesquisa();
				this.revalidate();
				this.repaint();
			}
		}
		
		public static void main (String [] args) {
			listView list = new listView();
		}

}
