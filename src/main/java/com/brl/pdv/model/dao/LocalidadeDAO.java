package com.brl.pdv.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.brl.pdv.model.Localidade;
import com.brl.pdv.model.dao.conexao.ConnectionFactory;

public class LocalidadeDAO {

	private String sql;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection connection;

	public Localidade findById(int id) {
		Localidade localidade = null;
		try {
			sql = "SELECT * FROM localidade WHERE codlocal = ?";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String telefone = rs.getString("telefone");
				localidade = new Localidade(nome, endereco, telefone);
				localidade.setCodigo(rs.getInt("codlocal"));
			}
			rs.close();
			stmt.close();
//			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return localidade;
	}

	public List<Localidade> findAll() {
		List<Localidade> localidades = new ArrayList<>();
		try {
			sql = "SELECT * FROM localidade";
			connection = ConnectionFactory.getConexao();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			Localidade localidade = null;
			while (rs.next()) {
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String telefone = rs.getString("telefone");
				localidade = new Localidade(nome, endereco, telefone);
				localidade.setCodigo(rs.getInt("codlocal"));
				localidades.add(localidade);
			}
			rs.close();
			stmt.close();
//			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao tentar executar tarefa\nDescrição do erro: " + e.getLocalizedMessage());
		}
		return localidades;
	}
}
