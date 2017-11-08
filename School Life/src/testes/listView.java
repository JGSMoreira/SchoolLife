package testes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
import javax.swing.Scrollable;
import javax.swing.SpringLayout;

import org.w3c.dom.events.MouseEvent;
import org.jdesktop.swingx.*;

import professor.CadastroProfessor;

public class listView extends JFrame implements ActionListener, MouseListener{
	
	private JPanel navbar = new  JPanel(),
				   gradeInferior = new JXPanel();
	private JTextField txtPesq = new JTextField(30);
	private JButton btnBusc = new JButton("Pesquisar"),
			        btnAtualizar = new JButton("Atualizar");
	private JLabel addProf = new JLabel("+ ADICIONAR PROFESSOR");
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 14);
	private Font fonteNegrito = new Font ("Open Sans", Font.BOLD, 12);
	
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
					JLabel nomeProf = new JLabel(),
						   codProf = new JLabel();						   
					JSeparator separador = new JSeparator();
					JButton btnEditar = new JButton("Editar"),
							btnExcluir = new JButton("Excluir"),
							btnVer = new JButton("Ver");					
					
					nomeProf.setText(rs.getString("nome"));
					codProf.setText(rs.getString("idProfessor"));
					
					rs.getInt("idProfessor");
					if (rs.wasNull()) {
						JLabel lblNaoEncont = new JLabel("NENHUM DADO ENCONTRADO!");
						gradeInferior.add(lblNaoEncont);
						lblNaoEncont.setBounds(50, 50, 100, 100);
						revalidate();
					}
					else {
						if (primeiro ==  0) {
							espacamento = 15;
							codProf.setBounds(25, espacamento, 1000, 25);
							nomeProf.setBounds(75, espacamento, 1000, 25);
							primeiro = 1;
						}
						else {
							codProf.setBounds(25, espacamento, 1000, 25);
							nomeProf.setBounds(75, espacamento, 1000, 25);	
						}
						
						gradeInferior.add(nomeProf);
						gradeInferior.add(codProf);
						gradeInferior.add(separador);
						gradeInferior.add(btnEditar);
						gradeInferior.add(btnVer);
						gradeInferior.add(btnExcluir);
						
						nomeProf.setForeground(Color.white);
						codProf.setForeground(Color.white);
						
						btnEditar.setBounds(510, espacamento - 5, 75, 35);
						btnVer.setBounds(595, espacamento - 5, 75, 35);
						btnExcluir.setBounds(680, espacamento - 5, 75, 35);
						
						separador.setBounds(0, espacamento + 40, 1000, 1);
						
						espacamento = espacamento + 55;
					}
					separador.setForeground(new Color(47,79,79));
					
					codProf.setFont(fonte);
					nomeProf.setFont(fonte);
					
					
					btnEditar.addActionListener(e->{professor.EditaProfessor edita = new professor.EditaProfessor(nomeProf.getText());});
					btnVer.addActionListener(e->{professor.VisualizaProfessor deletar = new professor.VisualizaProfessor(nomeProf.getText());});
					btnExcluir.addActionListener(e->{professor.DeletaProfessor deletar = new professor.DeletaProfessor(nomeProf.getText());});
				}
				stm.close();
				gradeInferior.add(addProf);
				addProf.setBounds(605, espacamento - 5, 300, 25);
				addProf.setForeground(Color.white);
				addProf.setFont(fonteNegrito);
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			gradeInferior.setSize(1000,1000); 
			JScrollPane scroll = new JScrollPane(gradeInferior);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.add(scroll, BorderLayout.CENTER);
			revalidate();
			repaint();
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
			
			btnBusc.addActionListener(this);
			btnAtualizar.addActionListener(this);
			addProf.addMouseListener(this);
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

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getSource() == addProf) {
				CadastroProfessor cad = new CadastroProfessor();
			}
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



}
