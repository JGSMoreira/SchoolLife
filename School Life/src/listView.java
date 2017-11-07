import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class listView extends JFrame implements ActionListener{
	
	JPanel grandeInferior = new JPanel(),
		   navbar = new  JPanel();
	JScrollPane pa = new JScrollPane();
	JTextField txtPesq = new JTextField(30);
	JButton btnBusc = new JButton("Pesquisar");
	
	//DADOS DE LOGIN - BD
		private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
				   usuario = "root",
				   senha = "root";
		private Connection conexao;
		private Statement stm;
		private ResultSet rs;
		
	//BANCO DE DADOS
		
		public void getDados() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(url, usuario, senha);
				stm=conexao.createStatement();
				this.rs = stm.executeQuery("select * from professor;");
				
				int espacamento = 0;
				int primeiro = 0;
				
				while(rs.next()) {
					JLabel aa = new JLabel(),
						   cc = new JLabel();
					JSeparator se = new JSeparator();
					JButton btnEditar = new JButton("Editar"),
							btnExcluir = new JButton("Excluir"),
							btnVer = new JButton("Ver");
					
					aa.setText(rs.getString("nome"));
					cc.setText(rs.getString("idProfessor"));
					
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
					
					grandeInferior.add(aa);
					grandeInferior.add(cc);
					grandeInferior.add(se);
					grandeInferior.add(btnEditar);
					grandeInferior.add(btnVer);
					grandeInferior.add(btnExcluir);
					
					btnEditar.setBounds(510, espacamento - 5, 75, 35);
					btnVer.setBounds(595, espacamento - 5, 75, 35);
					btnExcluir.setBounds(680, espacamento - 5, 75, 35);
					
					se.setBounds(0, espacamento + 40, 1000, 1);
					
					espacamento = espacamento + 55;
				}
				
				stm.close();
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			this.add(grandeInferior, BorderLayout.CENTER);
		}
		
		public void getDadosPesquisa() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(url, usuario, senha);
				stm=conexao.createStatement();
				this.rs = stm.executeQuery("select * from professor where nome like '" + txtPesq.getText() + "';");
				
				int espacamento = 0;
				int primeiro = 0;
				
				this.remove(grandeInferior);
				while(rs.next()) {
					JLabel aa = new JLabel(),
						   cc = new JLabel();
					JSeparator se = new JSeparator();
					JButton btnEditar = new JButton("Editar"),
							btnExcluir = new JButton("Excluir"),
							btnVer = new JButton("Ver");
					
					aa.setText(rs.getString("nome"));
					cc.setText(rs.getString("idProfessor"));
					
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
					
					grandeInferior.add(aa);
					grandeInferior.add(cc);
					grandeInferior.add(se);
					grandeInferior.add(btnEditar);
					grandeInferior.add(btnVer);
					grandeInferior.add(btnExcluir);
					
					btnEditar.setBounds(510, espacamento - 5, 75, 35);
					btnVer.setBounds(595, espacamento - 5, 75, 35);
					btnExcluir.setBounds(680, espacamento - 5, 75, 35);
					
					se.setBounds(0, espacamento + 40, 1000, 1);
					
					espacamento = espacamento + 55;
				}
				
				stm.close();
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			this.add(grandeInferior, BorderLayout.CENTER);
			
		}
	
	//ELEMENTOS DA TABELA
		public void navBar() {
			
			txtPesq.setPreferredSize(new Dimension(20, 30));
			btnBusc.setPreferredSize(new Dimension(110, 30));
			navbar.add(txtPesq);
			navbar.add(btnBusc);
			navbar.setBounds(0, 0, 100, 100);
			this.add(navbar, BorderLayout.NORTH);
		}
		
		public listView() {
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setBounds(100, 100, 800, 600);
			setVisible(true);
			setResizable(false);
			this.setLayout(new BorderLayout());
			grandeInferior.setLayout(null);

			getDados();
			navBar();
			revalidate();
			repaint();
			
			btnBusc.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnBusc) {
				getDadosPesquisa();
				revalidate();
				repaint();
			}
			
		}
		
		public static void main (String [] args) {
			listView list = new listView();
		}

}
