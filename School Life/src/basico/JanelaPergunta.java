package basico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPanel;

public class JanelaPergunta extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626545031181805303L;

	private JPanel parteSuperior = new JPanel(null),
				   parteInferior = new JPanel();
	
	private JLabel texto = new JLabel();
	
	private JButton sim = new JButton("Sim"),
					nao = new JButton("Não");
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 16);
	
	//INTERFACE
	public void adicionaElementos() {
		this.add(parteInferior, BorderLayout.SOUTH);
		this.add(parteSuperior, BorderLayout.CENTER);
		
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
	public JanelaPergunta(String texto) {
		setTitle("Janela de Confirmação");
		adicionaElementos();
		pack();
		setBounds(0, 0, 390, 180);
		this.setLocationRelativeTo(null);
		this.texto.setText(texto);
		setVisible(true);
		revalidate();
		repaint();
		
		sim.addActionListener(this);
		nao.addActionListener(this);
		
	}
	
	public static void main (String [] args) {
		JanelaPergunta del = new JanelaPergunta(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nao) {
			dispose();
		}
		if (e.getSource() == sim) {

		}
		
	}

}
