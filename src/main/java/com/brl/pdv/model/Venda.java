package com.brl.pdv.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {

	private Cliente cliente;
	private Localidade localidade;
	private Produto produto;
	private int quantidade;
	private LocalDate dataVenda;
	private BigDecimal valorTotal;

	public Venda(Cliente cliente, Localidade localidade, Produto produto, int quantidade) {
		this.cliente = cliente;
		this.localidade = localidade;
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataVenda = LocalDate.now();
		this.valorTotal = new BigDecimal(0.0);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
