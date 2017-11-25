package tipo_atividade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeletaTipoAtividade extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626545031181805303L;

	private JPanel parteSuperior = new JPanel(null),
				   parteInferior = new JPanel();
	
	private JLabel texto = new JLabel("Tem certeza que deseja deletar isto?");
	
	private JLabel sim = new JLabel(new ImageIcon("img/geral/btnSim.png")),
				   nao = new JLabel(new ImageIcon("img/geral/btnNão.png"));
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 16);
	
	//BANCO DE DADOS VARÁVEIS
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	private ResultSet rs;
	private int codigo;
	private String nomeTipoAtv;
	
	//BANCO DE DADOS
	public void deletaDados(int codigo) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			stm.executeUpdate("delete from tipo_atividade where idTipo_atividade = " + codigo +";");

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void codigo(String nomeTipoAtv) {
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			stm=conexao.createStatement();

			this.rs=stm.executeQuery("SELECT idTipo_atividade FROM tipo_atividade where nome like '%" + nomeTipoAtv + "%';");
			rs.next();

			rs.getString("idTipo_atividade");
			this.codigo=((Number) rs.getObject(1)).intValue();

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deletaDados(codigo);
	}
	
	//INTERFACE
	public void adicionaElementos() {
		this.add(parteInferior, BorderLayout.SOUTH);
		this.add(parteSuperior, BorderLayout.CENTER);
		
		parteSuperior.setLayout(new GridBagLayout());
		parteSuperior.add(texto);
		
		parteInferior.add(sim);
		parteInferior.add(nao);
		
		organizaElementos();
	}
	public void organizaElementos() {
		texto.setBounds(10, 35, 400, 35);
		
		estilizaElementos();
	}
	public void estilizaElementos() {
		parteSuperior.setBackground(new Color(16, 28, 28));
		parteInferior.setBackground(new Color(28, 49, 49));
		
		texto.setFont(fonte);
		texto.setForeground(Color.WHITE);
	}
	
	//CONSTRUTOR
	public DeletaTipoAtividade(String nomeTipoAtv) {
		this.setIconImage(new ImageIcon("img/geral/icon.png").getImage());
		setTitle("Janela de Confirmação");
		adicionaElementos();
		pack();
		setBounds(0, 0, 390, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.nomeTipoAtv = nomeTipoAtv;
		setVisible(true);
		revalidate();
		repaint();
		
		testes.listView aa = new testes.listView();
		aa.setAtualizar();
		
		sim.addMouseListener(this);
		nao.addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == nao) {
			dispose();
		}
		if (e.getSource() == sim) {
			codigo(nomeTipoAtv);
			basico.JanelaPergunta a = new basico.JanelaPergunta("Tipo de Atividade deletado com sucesso!");
			dispose();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == nao) {
			nao.setIcon(new ImageIcon("img/geral/btnNãohover.png"));
		}
		if(e.getSource() == sim) {
			sim.setIcon(new ImageIcon("img/geral/btnSimhover.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == nao) {
			nao.setIcon(new ImageIcon("img/geral/btnNão.png"));
		}
		if(e.getSource() == sim) {
			sim.setIcon(new ImageIcon("img/geral/btnSim.png"));
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
