package basico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javafx.scene.layout.Border;

public class JanelaPergunta extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626545031181805303L;

	private JPanel parteSuperior = new JPanel(),
				   parteInferior = new JPanel();
	
	private JLabel areaTexto = new JLabel();
	
	private JButton ok = new JButton("OK");
	
	private Font fonte = new Font ("Open Sans", Font.PLAIN, 16);
	
	//INTERFACE
	public void adicionaElementos() {
		this.add(parteInferior, BorderLayout.SOUTH);
		this.add(parteSuperior, BorderLayout.CENTER);
		
		parteSuperior.setLayout(new GridBagLayout());
		parteSuperior.add(areaTexto);
		
		parteInferior.add(ok);
		
		organizaElementos();
	}
	public void organizaElementos() {
		areaTexto.setBounds(10, 35, 400, 35);		
		estilizaElementos();
	}
	public void estilizaElementos() {
		parteSuperior.setBackground(new Color(16, 28, 28));
		parteInferior.setBackground(new Color(28, 49, 49));
		
		areaTexto.setFont(fonte);
		areaTexto.setForeground(Color.WHITE);
	}
	
	//CONSTRUTOR
	public JanelaPergunta(String texto) {
		setTitle("Janela de confirma��o");
		adicionaElementos();
		pack();
		setBounds(0, 0, 390, 180);
		this.setLocationRelativeTo(null);
		this.areaTexto.setText(texto);
		setVisible(true);
		revalidate();
		repaint();
		
		ok.addActionListener(this);
		
	}
	
	public static void main (String [] args) {
		JanelaPergunta del = new JanelaPergunta(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			dispose();
		}
		
	}

}
