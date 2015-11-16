package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Bin.Cliente;
import Persistence.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CadastroCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtNome;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtSobrenome;
	
	
	
	String[] estestadoCivil = { "Solteiro(a)", "Casado(a)", "Divorciado(a)",
			"Viuvo(a)", "Separado(a)" };
	String[] estsexo = { "Masculino", "Feminino" };
	private JDateChooser dtData;
	private JComboBox boxEstCivil;
	private JComboBox boxsexo;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	
	private Dao banco = new Dao();
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroCliente dialog = new CadastroCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroCliente() {
		cliente = new Cliente();
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 601, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 11, 232, 14);
		contentPanel.add(lblCodigo);
		
		txtid = new JTextField();
		txtid.setEnabled(false);
		txtid.setBounds(10, 36, 86, 20);
		contentPanel.add(txtid);
		txtid.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(10, 92, 232, 20);
		contentPanel.add(txtNome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 67, 232, 14);
		contentPanel.add(lblNome);
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		txtRg.setBounds(10, 148, 232, 20);
		contentPanel.add(txtRg);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(10, 123, 232, 14);
		contentPanel.add(lblRg);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(267, 148, 249, 20);
		contentPanel.add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(267, 123, 249, 14);
		contentPanel.add(lblCpf);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(267, 92, 249, 20);
		contentPanel.add(txtSobrenome);
		
		JLabel lblSobrenome = new JLabel("SobreNome");
		lblSobrenome.setBounds(267, 67, 249, 14);
		contentPanel.add(lblSobrenome);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil");
		lblEstadoCivil.setBounds(10, 179, 232, 14);
		contentPanel.add(lblEstadoCivil);
		
		JLabel lblDataNasc = new JLabel("Data Nasc");
		lblDataNasc.setBounds(10, 235, 232, 14);
		contentPanel.add(lblDataNasc);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(267, 179, 249, 14);
		contentPanel.add(lblSexo);
		
		 boxEstCivil = new JComboBox(estestadoCivil);
		boxEstCivil.setBounds(10, 204, 232, 20);
		contentPanel.add(boxEstCivil);
		
		 boxsexo = new JComboBox(estsexo);
		boxsexo.setBounds(267, 204, 145, 20);
		contentPanel.add(boxsexo);
		dtData = new JDateChooser(new java.util.Date());
		dtData.setBounds(10, 260, 140, 20);
		contentPanel.add(dtData);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(267, 260, 249, 20);
		contentPanel.add(txtTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone ");
		lblTelefone.setBounds(267, 235, 249, 14);
		contentPanel.add(lblTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(267, 36, 249, 20);
		contentPanel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(267, 11, 249, 14);
		contentPanel.add(lblEmail);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salvar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void salvar(){
		try {
			
		
		Date dataNasc= dtData.getDate();
		String sexo= (String) boxsexo.getSelectedItem();
		String estadoCivil=(String) boxEstCivil.getSelectedItem();
		String cpf=txtCpf.getText();
		String rg=txtRg.getText();
		String sobrenome=txtSobrenome.getText();
		String nome=txtNome.getText();
		Integer fone=Integer.parseInt(txtTelefone.getText());
		String email=txtEmail.getText();
		Cliente cliente = new Cliente(email,  fone, nome, sobrenome, rg, cpf, estadoCivil, sexo, dataNasc);
		
		
		boolean salvou = banco.salvarObjeto(cliente);
		
		if (salvou) {
			JOptionPane.showMessageDialog(contentPanel, "Salvol con sucesso!!");
		}else {
			JOptionPane.showMessageDialog(contentPanel, "Verifique algo deu errado");
		}
		
		dispose();
		
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}

	public boolean inserir(Cliente cliente) {
		try {
			this.cliente = cliente;
			setTitle("Alteração de dados cliente");
			txtid.setText(String.valueOf(cliente.getId()));
			txtCpf.setText(String.valueOf(cliente.getCpf()));
			txtEmail.setText(String.valueOf(cliente.getEmail()));
			txtNome.setText(String.valueOf(cliente.getNome()));
			txtRg.setText(String.valueOf(cliente.getRg()));
			txtSobrenome.setText(String.valueOf(cliente.getSobrenome()));
			txtTelefone.setText(String.valueOf(cliente.getFone()));
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro no sistema");
			dispose();
			return false;
		}

		
	}
}
