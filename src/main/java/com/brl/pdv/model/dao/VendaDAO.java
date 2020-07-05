package com.brl.pdv.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.brl.pdv.model.Cliente;
import com.brl.pdv.model.Desconto;
import com.brl.pdv.model.Localidade;
import com.brl.pdv.model.Produto;
import com.brl.pdv.model.Venda;
import com.brl.pdv.model.dao.conexao.ConnectionFactory;

public class VendaDAO {

	private String sql;
	private PreparedStatement stmt;
	private Connection connection;
	private ProdutoDAO produtoDAO;
	private ClienteDAO clienteDAO;

	public Venda insert(Cliente cliente, Localidade localidade, Produto produto, int quantidade) throws SQLException {
		Venda venda = null;
		try {
			produtoDAO = new ProdutoDAO();
			Produto produto2 = produtoDAO.findById(produto.getCodigo());
			if (produto2 == null) {
				JOptionPane.showMessageDialog(null, "Produto não encontrado");
				return null;
			}

			if (quantidade > produto2.getQuantidade()) {
				JOptionPane.showMessageDialog(null, "Estoque insuficiente");
				return null;
			}

			produto = produtoDAO.update(produto, quantidade);
			BigDecimal total = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
			total = calcularValorTotal(produto, cliente, quantidade, total);

			if (produto.getLocalidade().getCodigo() == localidade.getCodigo()) {
				BigDecimal mult = total.multiply(BigDecimal.valueOf(0.1));
				total = total.subtract(mult);
			}

			sql = "INSERT INTO venda(codcli, codprod, codlocal, qtd_venda, valor_total, data_venda) VALUES (?, ?, ?, ?, ?, ?)";
			connection = ConnectionFactory.getConexao();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getCodigo());
			stmt.setInt(2, produto.getCodigo());
			stmt.setInt(3, localidade.getCodigo());
			stmt.setInt(4, quantidade);
			stmt.setBigDecimal(5, total);
			stmt.setDate(6, Date.valueOf(LocalDate.now()));
			stmt.executeUpdate();
			venda = new Venda(cliente, localidade, produto, quantidade);
			venda.setValorTotal(total);
			clienteDAO = new ClienteDAO();
			clienteDAO.descontarBonus(cliente);
			connection.commit();
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return venda;
	}

	private BigDecimal calcularValorTotal(Produto produto, Cliente cliente, int quantidade, BigDecimal total) {
		if (cliente.getBonus() >= 100) {
			DescontoDAO descontoDAO = new DescontoDAO();
			Desconto desconto = descontoDAO.getDesconto(produto, quantidade);
			if (desconto == null) {
				return total;
			} else {
				BigDecimal result = total.multiply(BigDecimal.valueOf(desconto.getPercentual() * 0.01));
				BigDecimal result2 = total.subtract(result);
				return result2;
			}
		}
		return total;
	}

}
