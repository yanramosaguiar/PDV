package com.brl.pdv.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import com.brl.pdv.model.Cliente;
import com.brl.pdv.model.dao.conexao.ConnectionFactory;

public class ClienteDAO {

	private String sql;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection connection;

	public Set<Cliente> findAll() {
		Set<Cliente> clientes = new HashSet<>();
		try {
			sql = "SELECT codcli, nome, status, perfil, bonus FROM cliente";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			Cliente cliente = null;
			while (rs.next()) {
				String nome = rs.getString("nome");
				char status = rs.getString("status").charAt(0);
				char perfil = rs.getString("perfil").charAt(0);
				cliente = new Cliente(nome, perfil, status);
				cliente.setCodigo(rs.getInt("codcli"));
				cliente.setBonus(rs.getInt("bonus"));
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
//			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return clientes;
	}

	public Cliente descontarBonus(Cliente cliente) throws SQLException {
		Cliente clienteAtualizado = null;
		try {
			sql = "UPDATE cliente SET bonus = ? WHERE codcli = ?";
			connection = ConnectionFactory.getConexao();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getBonus() - 100);
			stmt.setInt(2, cliente.getCodigo());
			stmt.executeUpdate();
			clienteAtualizado = new Cliente(cliente.getNome(), cliente.getPerfil(), cliente.getStatus());
			clienteAtualizado.setBonus(cliente.getCodigo() - 100);
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
		return clienteAtualizado;
	}
}
