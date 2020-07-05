package com.brl.pdv.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import com.brl.pdv.model.Localidade;
import com.brl.pdv.model.Produto;
import com.brl.pdv.model.dao.conexao.ConnectionFactory;

public class ProdutoDAO {

	private String sql;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection connection;
	private LocalidadeDAO localidadeDAO;

	public Set<Produto> findAll() {
		Set<Produto> produtos = new HashSet<>();
		try {
			sql = "SELECT * FROM produto";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			Produto produto = null;
			localidadeDAO = new LocalidadeDAO();
			while (rs.next()) {
				int codigo = rs.getInt("codprod");
				String descricao = rs.getString("descricao");
				int quantidade = rs.getInt("qtd_estoque");
				BigDecimal preco = rs.getBigDecimal("preco_unitario");
				produto = new Produto(descricao, quantidade, preco);
				produto.setCodigo(codigo);
				Localidade localidade = localidadeDAO.findById(rs.getInt("codlocal"));
				produto.setLocalidade(localidade);
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			// connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return produtos;
	}

	public Produto update(Produto produto, int quantidade) throws SQLException {
		Produto produtoAtualizado = null;
		try {
			sql = "UPDATE produto SET qtd_estoque = ? WHERE codprod = ?";
			connection = ConnectionFactory.getConexao();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, produto.getQuantidade() - quantidade);
			stmt.setInt(2, produto.getCodigo());
			stmt.executeUpdate();
			produtoAtualizado = new Produto(produto.getDescricao(), produto.getQuantidade(), produto.getPreco());
			produtoAtualizado.setCodigo(produto.getCodigo());
			produtoAtualizado.setQuantidade(produto.getQuantidade() - quantidade);
			produtoAtualizado.setLocalidade(produto.getLocalidade());
			connection.commit();
			rs.close();
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
			connection.rollback();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro ao tentar encerrar conexão com o banco de dados.\nDescrição do erro: "
								+ e.getLocalizedMessage());
			}
		}
		return produtoAtualizado;
	}

	public Produto findById(int codigo) {
		Produto produto = null;
		try {
			sql = "SELECT * FROM produto WHERE codprod = ?";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			localidadeDAO = new LocalidadeDAO();
			if (rs.next()) {
				int codigoProduto = rs.getInt("codprod");
				String descricao = rs.getString("descricao");
				int quantidade = rs.getInt("qtd_estoque");
				BigDecimal preco = rs.getBigDecimal("preco_unitario");
				produto = new Produto(descricao, quantidade, preco);
				produto.setCodigo(codigoProduto);
				Localidade localidade = localidadeDAO.findById(rs.getInt("codlocal"));
				localidade.setCodigo(rs.getInt("codlocal"));
				produto.setLocalidade(localidade);
			}
			rs.close();
			stmt.close();
			// connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return produto;
	}
}
