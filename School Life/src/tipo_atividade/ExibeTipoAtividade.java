package tipo_atividade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ExibeTipoAtividade extends JFrame implements ActionListener, MouseListener{
	private ResultSet rs;
	private JTable tabela;
	private JScrollPane scroller;
	private JPanel paInferior = new JPanel();

	private JLabel btnCancelar = new JLabel(new ImageIcon("img/geral/btn_Cancelarmdpi.png"));
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 12);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public ExibeTipoAtividade() {
		
		setBounds(300, 300, 450, 300);
		setVisible(true);

		setTitle("School Life - Lista de Tipos de Atividades");
		paInferior.add(btnCancelar);
		add(paInferior, BorderLayout.SOUTH);
		setResizable(false);
		revalidate();
		
		btnCancelar.addMouseListener(this);
		btnCancelar.setFont(fonte);
		
		
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

	}
	
	public static void main(String[] args) {
		ExibeTipoAtividade ep = new ExibeTipoAtividade();
	}
	
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
