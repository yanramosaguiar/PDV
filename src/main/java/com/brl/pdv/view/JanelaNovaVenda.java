package com.brl.pdv.view;

import static com.brl.pdv.util.FormatadorDinheiro.formata;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.brl.pdv.controller.NovaVendaController;
import com.brl.pdv.model.Cliente;
import com.brl.pdv.model.Localidade;
import com.brl.pdv.model.Produto;
import com.brl.pdv.model.Venda;

public class JanelaNovaVenda extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelCliente;
	private JPanel panelProdutos;
	private JPanel panelTabelaVenda;
	private JPanel panelTotalCompra;
	private JScrollPane scrollTabelaVenda;

	private JLabel lblCliente;
	private JLabel lblLocalidade;
	private JLabel lblCodigoProduto;
	private JLabel lblQuantidade;
	private JLabel lblDescricao;
	private JLabel lblGetDescricao;
	private JLabel lblTotalDaCompra;
	private JLabel lblValorTotal;
	private JLabel lblBugs;

	private JComboBox<Cliente> comboBoxCliente;
	private JComboBox<Localidade> comboBoxLocalidade;
	private JComboBox<Produto> comboBoxProduto;

	private JSpinner spinnerQtd;

	private GridBagLayout gbl_panelCliente;
	private GridBagConstraints gbc_lblCliente;
	private GridBagConstraints gbc_comboBoxCliente;
	private GridBagConstraints gbc_lblLocalidade;
	private GridBagConstraints gbc_comboBoxLocalidade;

	private JButton btnInserir;
	private JButton btnExcluir;
	private JButton btnFinalizarCompra;

	private JTable tabelaVenda;
	private NovaVendaController novaVendaController;
	private Cliente clienteSelecionado;
	private Localidade localidadeSelecionada;
	private Produto produtoSelecionado;
	private List<Venda> vendas;
	private BigDecimal totalParcial;

	public JanelaNovaVenda() {
		setTitle("PDV - Cadastro de Vendas (v. 1.0 beta)");
		novaVendaController = new NovaVendaController();
		vendas = new ArrayList<>();
		totalParcial = new BigDecimal(0.0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelCliente = new JPanel();
		panelCliente.setBackground(new Color(255, 250, 250));
		panelCliente.setBounds(10, 76, 719, 45);
		panelCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelCliente);
		gbl_panelCliente = new GridBagLayout();
		gbl_panelCliente.columnWidths = new int[] { 60, 300, 90, 245, 20, 0 };
		gbl_panelCliente.rowHeights = new int[] { 40, 0 };
		gbl_panelCliente.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelCliente.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelCliente.setLayout(gbl_panelCliente);

		lblCliente = new JLabel("Cliente:");
		gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.insets = new Insets(0, 0, 0, 5);
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		panelCliente.add(lblCliente, gbc_lblCliente);

		comboBoxCliente = new JComboBox<>();
		comboBoxCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		novaVendaController.popularComboBoxClientes(comboBoxCliente);
		comboBoxCliente.setEditable(false);
		gbc_comboBoxCliente = new GridBagConstraints();
		gbc_comboBoxCliente.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCliente.gridx = 1;
		gbc_comboBoxCliente.gridy = 0;
		panelCliente.add(comboBoxCliente, gbc_comboBoxCliente);

		lblLocalidade = new JLabel("Local da venda:");
		gbc_lblLocalidade = new GridBagConstraints();
		gbc_lblLocalidade.anchor = GridBagConstraints.EAST;
		gbc_lblLocalidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblLocalidade.gridx = 2;
		gbc_lblLocalidade.gridy = 0;
		panelCliente.add(lblLocalidade, gbc_lblLocalidade);

		comboBoxLocalidade = new JComboBox<>();
		comboBoxLocalidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		novaVendaController.popularComboBoxLocalidades(comboBoxLocalidade);
		comboBoxLocalidade.setEditable(false);
		gbc_comboBoxLocalidade = new GridBagConstraints();
		gbc_comboBoxLocalidade.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxLocalidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLocalidade.gridx = 3;
		gbc_comboBoxLocalidade.gridy = 0;
		panelCliente.add(comboBoxLocalidade, gbc_comboBoxLocalidade);

		panelProdutos = new JPanel();
		panelProdutos.setBackground(new Color(255, 250, 250));
		panelProdutos.setBounds(10, 132, 719, 120);
		panelProdutos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Escolher Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		contentPane.add(panelProdutos);
		panelProdutos.setLayout(null);

		lblCodigoProduto = new JLabel("Código do Produto:");
		lblCodigoProduto.setBounds(31, 35, 109, 14);
		panelProdutos.add(lblCodigoProduto);

		comboBoxProduto = new JComboBox<>();
		comboBoxProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxProduto.setBounds(150, 32, 105, 20);
		novaVendaController.popularComboBoxProdutos(comboBoxProduto);
		comboBoxProduto.setEditable(false);
		panelProdutos.add(comboBoxProduto);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantidade.setBounds(265, 35, 75, 14);
		panelProdutos.add(lblQuantidade);

		spinnerQtd = new JSpinner();
		spinnerQtd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinnerQtd.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQtd.setValue(1);
		spinnerQtd.setBounds(350, 32, 75, 20);
		panelProdutos.add(spinnerQtd);

		btnInserir = new JButton("Vender");
		btnInserir.setBackground(new Color(0, 255, 0));
		btnInserir.setForeground(new Color(255, 255, 255));
		btnInserir.setFont(new Font("Verdana", Font.BOLD, 14));
		btnInserir.setBounds(450, 25, 108, 35);
		panelProdutos.add(btnInserir);

		lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(31, 78, 77, 14);
		panelProdutos.add(lblDescricao);

		btnExcluir = new JButton("Excluir Venda");
		btnExcluir.setToolTipText("Função não implementada ainda...");
		btnExcluir.setBounds(580, 25, 108, 35);
		btnExcluir.setEnabled(false);
		panelProdutos.add(btnExcluir);

		lblGetDescricao = new JLabel("");
		lblGetDescricao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGetDescricao.setBounds(118, 70, 570, 27);
		panelProdutos.add(lblGetDescricao);

		panelTabelaVenda = new JPanel();
		panelTabelaVenda.setBackground(new Color(255, 250, 250));
		panelTabelaVenda.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vendas",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panelTabelaVenda.setBounds(10, 263, 719, 231);
		panelTabelaVenda.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelTabelaVenda);
		tabelaVenda = new JTable();
		tabelaVenda.setBackground(new Color(248, 248, 255));
		tabelaVenda.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Descrição", "Quantidade", "Preço 1x", "Total (c/ descontos)" }));
		scrollTabelaVenda = new JScrollPane();
		scrollTabelaVenda.setBackground(new Color(255, 250, 250));
		scrollTabelaVenda.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollTabelaVenda.setViewportView(tabelaVenda);
		panelTabelaVenda.add(scrollTabelaVenda);

		panelTotalCompra = new JPanel();
		panelTotalCompra.setBackground(new Color(255, 250, 250));
		panelTotalCompra.setBounds(10, 494, 719, 58);
		contentPane.add(panelTotalCompra);
		panelTotalCompra.setLayout(null);

		lblTotalDaCompra = new JLabel("Total da Compra:");
		lblTotalDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalDaCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalDaCompra.setBounds(0, 11, 200, 38);
		panelTotalCompra.add(lblTotalDaCompra);

		btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(compra -> {
			this.dispose();
			JOptionPane.showMessageDialog(this, "Compra finalizada!\nTotal: " + formata(totalParcial));
			System.exit(0);
		});

		btnFinalizarCompra.setBackground(new Color(0, 128, 0));
		btnFinalizarCompra.setFont(new Font("Verdana", Font.BOLD, 14));
		btnFinalizarCompra.setForeground(new Color(255, 255, 255));
		btnFinalizarCompra.setBounds(524, 9, 185, 40);
		panelTotalCompra.add(btnFinalizarCompra);

		lblValorTotal = new JLabel("R$0,00");
		lblValorTotal.setForeground(new Color(0, 128, 0));
		lblValorTotal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblValorTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorTotal.setBounds(210, 11, 225, 38);
		panelTotalCompra.add(lblValorTotal);

		comboBoxCliente.setSelectedItem(0);
		clienteSelecionado = (Cliente) comboBoxCliente.getSelectedItem();

		comboBoxLocalidade.setSelectedItem(0);
		localidadeSelecionada = (Localidade) comboBoxLocalidade.getSelectedItem();
		comboBoxProduto.setSelectedItem(0);
		produtoSelecionado = (Produto) comboBoxProduto.getSelectedItem();
		lblGetDescricao.setText(produtoSelecionado.getDescricao());

		JLabel lblPdvCadastro = new JLabel("PDV - Cadastro de Vendas");
		lblPdvCadastro.setForeground(SystemColor.textHighlight);
		lblPdvCadastro.setFont(new Font("Verdana", Font.BOLD, 23));
		lblPdvCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPdvCadastro.setBounds(20, 11, 709, 54);
		contentPane.add(lblPdvCadastro);

		lblBugs = new JLabel("<html><u>Bugs a serem corrigidos</u></html>");
		lblBugs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBugs.setHorizontalAlignment(SwingConstants.LEFT);
		lblBugs.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblBugs.setBounds(604, 51, 125, 14);
		contentPane.add(lblBugs);
		initListeners();

		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void initListeners() {
		comboBoxCliente.addActionListener(event -> clienteSelecionado = (Cliente) comboBoxCliente.getSelectedItem());

		comboBoxLocalidade
				.addActionListener(event -> localidadeSelecionada = (Localidade) comboBoxLocalidade.getSelectedItem());

		comboBoxProduto.addItemListener(event -> {
			produtoSelecionado = (Produto) comboBoxProduto.getSelectedItem();
			lblGetDescricao.setText(produtoSelecionado.getDescricao());
		});

		btnInserir.addActionListener(inserir -> {
			produtoSelecionado = (Produto) comboBoxProduto.getSelectedItem();
			int qtd = (int) (spinnerQtd.getValue());

			Venda itemDeVenda = null;
			try {
				itemDeVenda = novaVendaController.cadastrarVenda(clienteSelecionado, localidadeSelecionada,
						produtoSelecionado, qtd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Falha ao cadastrar venda.\nDetalhes: " + e.getLocalizedMessage());
				return;
			}

			if (itemDeVenda == null) {
				JOptionPane.showMessageDialog(this, "Falha ao efetuar venda");
			} else {
				vendas.add(itemDeVenda);
				totalParcial = totalParcial.add(itemDeVenda.getValorTotal());
				lblValorTotal.setText(formata(totalParcial));
				comboBoxLocalidade.setSelectedItem(0);
				atualizarTabela();
			}
		});

		lblBugs.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				JanelaListaBugs listaBugs = new JanelaListaBugs();
				listaBugs.setVisible(true);
			}
		});

	}

	private void atualizarTabela() {
		DefaultTableModel modelo = (DefaultTableModel) tabelaVenda.getModel();
		modelo.setRowCount(0);
		vendas.forEach(item -> {
			modelo.addRow(new Object[] { item.getProduto().getDescricao(), item.getQuantidade(),
					formata(item.getProduto().getPreco()), formata(item.getValorTotal()) });
		});
	}
}
