package materia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;

public class ListarMaterias extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel navbar = new JPanel();
	private JPanel gradeInferior = new JPanel();
	private JXTextField txtPesq = new JXTextField("Digite o nome de um professor");
	private JLabel btnBusc = new JLabel(new ImageIcon("img/tabela/btnSearch.png")),
				   btnAtualizar = new JLabel(new ImageIcon("img/tabela/btnRefresh.png")),
				   logo = new JLabel(new ImageIcon("img/menu/logo.png"));
	
	private JLabel addProf = new JLabel("+ ADICIONAR PROFESSOR");
	
	private JScrollPane scroll = new JScrollPane();
	private int contaTamanho = 0;
	private boolean dadosEncont = true;
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 14);
	private Font fonteNegrito = new Font ("Open Sans", Font.BOLD, 12);
	private Font fonteItalico = new Font ("Open Sans", Font.ITALIC, 12);
	
	private Color gradeInferiorCor = new Color(16,28,28);
	private Color navBarCor = new Color(47,79,79);
	private Color textoCor1 = new Color(255,255,255);
	private Color separadorCor = new Color(51,51,51);
	
	//DADOS DE LOGIN - BD
		private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
				   usuario = "root",
				   senha = "root";
		private Connection conexao;
		private Statement stm;
		private ResultSet rs;
	
	//GET E SET
		public void setAtualizar() {
		}
		
	//BANCO DE DADOS
		//BOT�O ATUALIZAR
		public void getDados() {
			carregaDados("select * from materia;");
		}
		//BOT�O PESQUISAR
		public void getDadosPesquisa() {
			if (!txtPesq.getText().equals("")) {
				carregaDados("select * from materia where nome like '%" + txtPesq.getText() + "%';");
			}
		}
		//CARREGA DADOS
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
					JLabel btnEditar = new JLabel(new ImageIcon("img/tabela/btnEditar.png")),
						   btnExcluir = new JLabel(new ImageIcon("img/tabela/btnExcluir.png")),
						   btnVer = new JLabel(new ImageIcon("img/tabela/btnVisualizar.png"));
					
					nomeProf.setText(rs.getString("nome"));
					codProf.setText(rs.getString("idMateria"));
					
					rs.getInt("idMateria");
					if (rs.wasNull()) {
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
						
						nomeProf.setForeground(textoCor1);
						codProf.setForeground(textoCor1);
						
						btnEditar.setBounds(545, espacamento - 5, 75, 35);
						btnVer.setBounds(615, espacamento - 5, 75, 35);
						btnExcluir.setBounds(685, espacamento - 5, 75, 35);
						
						separador.setBounds(0, espacamento + 40, 1000, 1);
						
						espacamento = espacamento + 55;
						contaTamanho = espacamento + 30;
					}
					separador.setForeground(separadorCor);
					
					codProf.setFont(fonteNegrito);
					nomeProf.setFont(fonte);
					
					btnEditar.addMouseListener(new MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnEditar) {
								EditarMateria edita = new EditarMateria(nomeProf.getText());
							}
						}
						public void mouseEntered(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnEditar) {
								btnEditar.setIcon(new ImageIcon("img/tabela/btnEditarhover.png"));
							}
						}
						public void mouseExited(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnEditar) {
								btnEditar.setIcon(new ImageIcon("img/tabela/btnEditar.png"));
							}
						}
						
					});
					btnVer.addMouseListener(new MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnVer) {
								VisualizarMateria visualizar = new VisualizarMateria(nomeProf.getText());
							}
						}
						public void mouseEntered(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnVer) {
								btnVer.setIcon(new ImageIcon("img/tabela/btnVisualizarhover.png"));
							}
						}
						public void mouseExited(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnVer) {
								btnVer.setIcon(new ImageIcon("img/tabela/btnVisualizar.png"));
							}
						}
						
					});
					btnExcluir.addMouseListener(new MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnExcluir) {
								DeletaMateria deletar = new DeletaMateria(nomeProf.getText());
								getDados();
							}
						}
						public void mouseEntered(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnExcluir) {
								btnExcluir.setIcon(new ImageIcon("img/tabela/btnExcluirhover.png"));
							}
						}
						public void mouseExited(java.awt.event.MouseEvent e) {
							if (e.getSource() == btnExcluir) {
								btnExcluir.setIcon(new ImageIcon("img/tabela/btnExcluir.png"));
							}
						}
						
					});
				}
				stm.close();
				
				if (primeiro == 0) {
					JLabel lblNaoEncont = new JLabel(new ImageIcon("img/tabela/naoEncont.png"));
					gradeInferior.add(lblNaoEncont);
					lblNaoEncont.setForeground(textoCor1);
					lblNaoEncont.setFont(fonteNegrito);
					lblNaoEncont.setBounds(240, espacamento + 30, 300, 300);
					
					addProf.setText(null);
					addProf.setIcon(new ImageIcon("img/tabela/btnAddAlgo.png"));
					gradeInferior.add(addProf);
					addProf.setBounds(258, espacamento + 260, 263, 33);
					addProf.setForeground(textoCor1);
					addProf.setFont(fonteNegrito);
					dadosEncont = false;
				}
				else {
					dadosEncont = true;
					addProf.setIcon(null);
					addProf.setText("+ ADICIONAR MATÉRIA");
					gradeInferior.add(addProf);
					addProf.setBounds(605, espacamento - 5, 300, 25);
					addProf.setForeground(textoCor1);
					addProf.setFont(fonteNegrito);
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			gradeInferior.setPreferredSize(new Dimension(610, contaTamanho));
			scroll.setViewportView(gradeInferior);
			add(scroll, BorderLayout.CENTER);
			System.out.println("Dados Carregados!");
			revalidate();
			repaint();
		}
	//ELEMENTOS DA TABELA
		public void navBar() {
			FlowLayout flow = new FlowLayout();
			flow.setHgap(-8);
			navbar.setLayout(flow);
			navbar.add(logo);
			txtPesq.setPreferredSize(new Dimension(22, 30));
			btnAtualizar.setPreferredSize(new Dimension(80, 40));
			navbar.add(txtPesq);
			
			navbar.setBounds(0, 0, 100, 100);
			this.add(navbar, BorderLayout.NORTH);
			btnBusc.setPreferredSize(new Dimension(80, 40));
			
			navbar.add(btnBusc);
			navbar.add(btnAtualizar);
		}
		
		public void estilizar() {
			ImageIcon ico = new ImageIcon("img/download.jpg");
			this.setIconImage(ico.getImage());
			navbar.setBackground(navBarCor);
			gradeInferior.setBackground(gradeInferiorCor);
			navbar.setBackground(navBarCor);
			gradeInferior.setBackground(gradeInferiorCor);
			gradeInferior.setLayout(null);
			
			txtPesq.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(41,171,226)));
			txtPesq.setPreferredSize(new Dimension(300, 33));
			txtPesq.setFont(fonte);
			
			btnBusc.setPreferredSize(new Dimension(80, 33));
			
			gradeInferior.setBorder(null);
			navbar.setBorder(null);
			scroll.setBorder(null);
			
		}
		
		public ListarMaterias() {
			this.setIconImage(new ImageIcon("img/geral/icon.png").getImage());
			this.setTitle("School Life - Lista de Matérias");
			
			setBounds(100, 100, 800, 600);
			this.setLocationRelativeTo(null);
			setResizable(false);
			this.setLayout(new BorderLayout());
			this.setLocationRelativeTo(null);

			getDados();
			navBar();
			estilizar();
			revalidate();
			repaint();
			
			this.setVisible(true);
			
			btnBusc.addMouseListener(this);
			btnAtualizar.addMouseListener(this);
			addProf.addMouseListener(this);
		}
		
		public static void main (String [] args) {
			ListarMaterias list = new ListarMaterias();
			list.setVisible(true);
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getSource() == addProf) {
				CadastrarMateria cad = new CadastrarMateria();
			}
			if (e.getSource() == btnBusc) {
				getDadosPesquisa();
			}
			if (e.getSource() == btnAtualizar) {
				getDados();
			}
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			if (e.getSource() == btnBusc) {
				btnBusc.setIcon(new ImageIcon("img/tabela/btnSearchhover.png"));;
			}
			if (e.getSource() == btnAtualizar) {
				btnAtualizar.setIcon(new ImageIcon("img/tabela/btnRefreshhover.png"));;
			}
			if (e.getSource() == addProf) {
				if (dadosEncont == true)
					addProf.setForeground(Color.LIGHT_GRAY);
				if (dadosEncont == false)
					addProf.setIcon(new ImageIcon("img/tabela/btnAddAlgohover.png"));
			}
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			if (e.getSource() == btnBusc) {
				btnBusc.setIcon(new ImageIcon("img/tabela/btnSearch.png"));;
			}
			if (e.getSource() == btnAtualizar) {
				btnAtualizar.setIcon(new ImageIcon("img/tabela/btnRefresh.png"));;
			}
			if (e.getSource() == addProf) {
				if (dadosEncont == true)
					addProf.setForeground(textoCor1);
				if (dadosEncont == false)
					addProf.setIcon(new ImageIcon("img/tabela/btnAddAlgo.png"));
			}
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
