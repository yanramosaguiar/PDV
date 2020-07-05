package com.brl.pdv.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;

import com.brl.pdv.model.Cliente;
import com.brl.pdv.model.Localidade;
import com.brl.pdv.model.Produto;
import com.brl.pdv.model.Venda;
import com.brl.pdv.model.dao.ClienteDAO;
import com.brl.pdv.model.dao.LocalidadeDAO;
import com.brl.pdv.model.dao.ProdutoDAO;
import com.brl.pdv.model.dao.VendaDAO;

public class NovaVendaController {

	private ClienteDAO clienteDAO;
	private LocalidadeDAO localidadeDAO;
	private ProdutoDAO produtoDAO;
	private VendaDAO vendaDAO;

	private Set<Cliente> clientes;
	private List<Localidade> localidades;
	private Set<Produto> produtos;

	public NovaVendaController() {
		clienteDAO = new ClienteDAO();
		localidadeDAO = new LocalidadeDAO();
		produtoDAO = new ProdutoDAO();
		vendaDAO = new VendaDAO();
	}

	public void popularComboBoxClientes(JComboBox<Cliente> comboBoxCliente) {
		clientes = this.clienteDAO.findAll();
		clientes.forEach(cliente -> comboBoxCliente.addItem(cliente));
	}

	public void popularComboBoxLocalidades(JComboBox<Localidade> comboBoxLocalidade) {
		localidades = this.localidadeDAO.findAll();
		localidades.forEach(localidade -> comboBoxLocalidade.addItem(localidade));
	}

	public void popularComboBoxProdutos(JComboBox<Produto> comboBoxProdutos) {
		produtos = this.produtoDAO.findAll();
		comboBoxProdutos.removeAllItems();
		produtos.forEach(produto -> comboBoxProdutos.addItem(produto));
	}

	public Venda cadastrarVenda(Cliente cliente, Localidade localidade, Produto produto, int qtd) throws SQLException {
		return vendaDAO.insert(cliente, localidade, produto, qtd);
	}
}
