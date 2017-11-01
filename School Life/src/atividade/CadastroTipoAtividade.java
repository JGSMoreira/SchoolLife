import javax.swing.JFrame;

public class CadastroTipoAtividade extends JFrame implements ActionListener{

	private JLabel lblNome = new JLabel("Nome:"),
				   lblCodigo = new JLabel("Código:");
	private JTextField txtNome = new JTextField(),
					   txtCodigo = new JTextField();
	private JButton btnSalvar = new JButton("Salvar"),
					btnFechar = new JButton("Fechar");
	
	private int codigo;
	private ResultSet rs;
	
	private Font fonte = new Font ("Open Sans", Font.TYPE1_FONT, 16);
	
	private String url = "jdbc:mysql://localhost:3306/school_life?useSSL=false",
			   usuario = "root",
			   senha = "root";
	
	private Connection conexao;
	private Statement stm;
	
	public CadastroTipoAtividade() {
		setBounds(300,300,300);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		CadastroTipoAtividade c = new CadastroTipoAtividade();
	}
}
