package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Persistence.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class CadastroProduto extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtDescricao;
	private JTextField txtPreco;
	private JTextField txtEstMin;
	private JLabel msn;
	private Produto produto;
	
	private Dao banco = new Dao();
	private JTextField txtCor;
	private JTextField txtPeso;
	private JTextField txtLocal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroProduto dialog = new CadastroProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroProduto() {
		produto=new Produto();
		setTitle("Cadastro de Produto");
		setBounds(100, 100, 308, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("C\u00F3digo");
			lblNewLabel.setBounds(10, 10, 86, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtID = new JTextField();
			txtID.setEnabled(false);
			txtID.setBounds(10, 30, 86, 20);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		{
			JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
			lblDescrio.setBounds(10, 60, 144, 14);
			contentPanel.add(lblDescrio);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setColumns(10);
			txtDescricao.setBounds(10, 80, 237, 20);
			contentPanel.add(txtDescricao);
		}
		{
			JLabel lblPreo = new JLabel("Pre\u00E7o");
			lblPreo.setBounds(10, 110, 115, 14);
			contentPanel.add(lblPreo);
		}
		{
			txtPreco = new JTextField();
			txtPreco.setColumns(10);
			txtPreco.setBounds(10, 130, 86, 20);
			contentPanel.add(txtPreco);
		}
		{
			txtEstMin = new JTextField();
			txtEstMin.setColumns(10);
			txtEstMin.setBounds(10, 181, 86, 20);
			contentPanel.add(txtEstMin);
		}
		{
			JLabel lblEstoqueMin = new JLabel("Estoque Min");
			lblEstoqueMin.setBounds(10, 161, 115, 14);
			contentPanel.add(lblEstoqueMin);
		}
		{
			 msn = new JLabel("Preenchimento Obrigatorio em Todos Campos");
			msn.setVisible(false);
			msn.setForeground(Color.RED);
			msn.setBounds(10, 281, 271, 14);
			contentPanel.add(msn);
		}
		{
			txtCor = new JTextField();
			txtCor.setColumns(10);
			txtCor.setBounds(147, 131, 100, 20);
			contentPanel.add(txtCor);
		}
		{
			JLabel lblCor = new JLabel("Cor");
			lblCor.setBounds(147, 111, 115, 14);
			contentPanel.add(lblCor);
		}
		{
			txtPeso = new JTextField();
			txtPeso.setColumns(10);
			txtPeso.setBounds(147, 181, 100, 20);
			contentPanel.add(txtPeso);
		}
		{
			JLabel lblPeso = new JLabel("Peso");
			lblPeso.setBounds(147, 161, 115, 14);
			contentPanel.add(lblPeso);
		}
		{
			txtLocal = new JTextField();
			txtLocal.setColumns(10);
			txtLocal.setBounds(10, 232, 237, 20);
			contentPanel.add(txtLocal);
		}
		{
			JLabel lblLocalizao = new JLabel("localiza\u00E7\u00E3o");
			lblLocalizao.setBounds(10, 212, 237, 14);
			contentPanel.add(lblLocalizao);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		
		System.out.println(acao);
		switch (acao) {
		case "OK":
			salvar();
			break;
			
		case"Cancel":
			dispose();
			break;

		default:
			break;
		}
		
	}

	private void salvar() {
		try {
			
		
		String descricao=txtDescricao.getText();
		float estoqueMin=Float.parseFloat(txtEstMin.getText());
		float preco=Float.parseFloat(txtPreco.getText());
		String cor = txtCor.getText();
		String localizacao= txtLocal.getText();
		float peso=Float.parseFloat(txtPeso.getText().replace(",", "."));
		
		if (descricao.length()>0) {
			boolean salvou=false;
			produto.setDescricao(descricao);
			produto.setEstoqueMin(estoqueMin);
			produto.setPreco(preco);
			produto.setCor(cor);
			produto.setPeso(peso);
			produto.setLocalizacao(localizacao);
			if (txtID.getText().length() > 0) {
				produto.setId(Integer.parseInt(txtID.getText()));
				salvou=banco.salvarOuAtualizarObjeto(produto);
			}else {
				salvou=banco.salvarObjeto(produto);
			}
			
			if (!salvou) {
				JOptionPane.showMessageDialog(contentPanel, "Sistema Não Conseguiu salvar Produto no banco.");
			}
			if (salvou) {
				JOptionPane.showMessageDialog(contentPanel, "Produto Criado no banco com sucesso.");
				dispose();
			}
		}else {
			msn.setVisible(true);
		}
		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, "Inserir valores validos nos campos numericos");
		}
	}
	
	//função utilizada de fora da classe para inserir produtos que serão alterados 
	public boolean inserir(Produto produto) {
		try {
			this.produto = produto;
			setTitle("Alteração de dados Produto");
			txtID.setText(String.valueOf(produto.getId()));
			txtEstMin.setText(String.valueOf(produto.getEstoqueMin()));
			txtDescricao.setText(produto.getDescricao());
			txtPreco.setText(String.valueOf(produto.getPreco()));
			txtCor.setText(produto.getCor());
			txtPeso.setText(String.valueOf(produto.getPeso()));
			txtLocal.setText(produto.getLocalizacao());
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro no sistema");
			dispose();
			return false;
		}
	}
}
