package com.brl.pdv.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.brl.pdv.model.Desconto;
import com.brl.pdv.model.Produto;
import com.brl.pdv.model.dao.conexao.ConnectionFactory;

public class DescontoDAO {

	private String sql;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection connection;

	public Desconto getDesconto(Produto produto, int quantidade) {
		Desconto desconto = null;
		try {
			sql = "SELECT codprod, qtd_min, qtd_max, percentual FROM desconto WHERE codprod = ? AND qtd_min <= ? AND qtd_max >= ? LIMIT 1";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, produto.getCodigo());
			stmt.setInt(2, quantidade);
			stmt.setInt(3, quantidade);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int percentual = rs.getInt("percentual");
				int qtd_min = rs.getInt("qtd_min");
				int qtd_max = rs.getInt("qtd_max");
				desconto = new Desconto(percentual, qtd_max, qtd_min, produto);
			}
			rs.close();
			stmt.close();
//			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return desconto;
	}
}
