package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Bin.Fornecedor;
import Persistence.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CadastroFornecedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtRazao;
	private JTextField txtInsc;
	private JTextField txtCnpj;
	private JTextField txtnomefant;
	
	
	
	String[] estestadoCivil = { "Solteiro(a)", "Casado(a)", "Divorciado(a)",
			"Viuvo(a)", "Separado(a)" };
	String[] estsexo = { "Masculino", "Feminino" };
	private JTextField txtTelefone;
	private JTextField txtEmail;
	
	private Dao banco = new Dao();
	private Fornecedor fornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroFornecedor dialog = new CadastroFornecedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroFornecedor() {
		fornecedor = new Fornecedor();
		setTitle("Cadastro de Fornecedor");
		setBounds(100, 100, 601, 324);
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
		
		txtRazao = new JTextField();
		txtRazao.setColumns(10);
		txtRazao.setBounds(10, 92, 232, 20);
		contentPanel.add(txtRazao);
		
		JLabel lblNome = new JLabel("RazaoSocial");
		lblNome.setBounds(10, 67, 232, 14);
		contentPanel.add(lblNome);
		
		txtInsc = new JTextField();
		txtInsc.setColumns(10);
		txtInsc.setBounds(10, 148, 232, 20);
		contentPanel.add(txtInsc);
		
		JLabel lblRg = new JLabel("InscEst");
		lblRg.setBounds(10, 123, 232, 14);
		contentPanel.add(lblRg);
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(267, 148, 249, 20);
		contentPanel.add(txtCnpj);
		
		JLabel lblCpf = new JLabel("CNPJ");
		lblCpf.setBounds(267, 123, 249, 14);
		contentPanel.add(lblCpf);
		
		txtnomefant = new JTextField();
		txtnomefant.setColumns(10);
		txtnomefant.setBounds(267, 92, 249, 20);
		contentPanel.add(txtnomefant);
		
		JLabel lblSobrenome = new JLabel("NomeFanta");
		lblSobrenome.setBounds(267, 67, 249, 14);
		contentPanel.add(lblSobrenome);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(10, 204, 232, 20);
		contentPanel.add(txtTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone ");
		lblTelefone.setBounds(10, 179, 249, 14);
		contentPanel.add(lblTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(267, 204, 249, 20);
		contentPanel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(267, 179, 249, 14);
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
		
		String cnpj=txtCnpj.getText();
		String inscEstadual=txtInsc.getText();
		String nomeFantasia=txtnomefant.getText();
		String razaoSocial=txtRazao.getText();
		Integer fone=Integer.parseInt(txtTelefone.getText());
		String email=txtEmail.getText();
		Fornecedor Fornecedor = new Fornecedor(email, fone, razaoSocial, nomeFantasia, inscEstadual, cnpj);
		
		
		banco.salvarObjeto(Fornecedor);
		
		dispose();
		
	}

	public boolean inserir(Fornecedor fornecedor) {
		// TODO Au
		try {
		this.fornecedor = fornecedor;
		setTitle("Alteração de dados fornecedor");
		txtid.setText(String.valueOf(fornecedor.getId()));
		txtCnpj.setText(String.valueOf(fornecedor.getCnpj()));
		txtEmail.setText(String.valueOf(fornecedor.getEmail()));
		txtInsc.setText(String.valueOf(fornecedor.getInscEstadual()));
		txtnomefant.setText(String.valueOf(fornecedor.getNomeFantasia()));
		txtRazao.setText(String.valueOf(fornecedor.getRazaoSocial()));
		txtTelefone.setText(String.valueOf(fornecedor.getFone()));
		return true;
	} catch (Exception e) {
		JOptionPane.showMessageDialog(contentPanel, "Erro no sistema");
		dispose();
		return false;
	}

		
	}
}
