package com.brl.pdv.model;

import java.math.BigDecimal;

public class Produto {

	private int codigo;
	private String descricao;
	private int quantidade;
	private BigDecimal preco;
	private Localidade localidade;

	public Produto(String descricao, int quantidade, BigDecimal preco) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	@Override
	public String toString() {
		return String.valueOf(this.codigo);
	}
}
