package View;

import javax.swing.JPanel;

import View.Pesquisa.PesquisaCliente;
import View.Pesquisa.PesquisaFornecedor;
import View.Pesquisa.PesquisaProduto;



import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Menu() {
		setBounds(0, 0, 1350, 90);
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1350, 21);
		add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCadastroProduto = new JMenuItem("Cadastro Produto");
		mntmCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto cp = new CadastroProduto();
				cp.setVisible(true);
				cp.setAlwaysOnTop(true);
				cp.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmCadastroProduto);
		
		JMenuItem mntmCadastroTransporte = new JMenuItem("Cadastro Cliente");
		mntmCadastroTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCliente ct = new CadastroCliente();
				ct.setVisible(true);
				ct.setAlwaysOnTop(true);
				ct.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmCadastroTransporte);
		
		JMenuItem mntmCadastroFuncionario = new JMenuItem("Cadastro Fornecedor");
		mntmCadastroFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFornecedor cf = new CadastroFornecedor();
				cf.setVisible(true);
				cf.setAlwaysOnTop(true);
				cf.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmCadastroFuncionario);
		
		JMenu mnPesquisa = new JMenu("Pesquisa");
		menuBar.add(mnPesquisa);
		
		JMenuItem mntmPesquisaProduto = new JMenuItem("Pesquisa Produto");
		mntmPesquisaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaProduto pp = new PesquisaProduto();
				pp.setVisible(true);
				pp.setAlwaysOnTop(true);
				pp.setLocationRelativeTo(null);
			}
		});
		mnPesquisa.add(mntmPesquisaProduto);
		
		JMenuItem mntmPesquisaTransporte = new JMenuItem("Pesquisa Fornecedor");
		mntmPesquisaTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaFornecedor pt = new PesquisaFornecedor();
				pt.setVisible(true);
				pt.setAlwaysOnTop(true);
				pt.setLocationRelativeTo(null);
			}
		});
		mnPesquisa.add(mntmPesquisaTransporte);
		
		JMenuItem mntmPesquisaFuncionario = new JMenuItem("Pesquisa Cliente");
		mntmPesquisaFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaCliente pf = new PesquisaCliente();
				pf.setVisible(true);
				pf.setAlwaysOnTop(true);
				pf.setLocationRelativeTo(null);
			}
		});
		mnPesquisa.add(mntmPesquisaFuncionario);

	}
}