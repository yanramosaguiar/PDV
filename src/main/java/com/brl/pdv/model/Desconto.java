package com.brl.pdv.model;

public class Desconto {

	private int percentual;
	private int qtdMax;
	private int qtdMin;
	private Produto produto;

	public Desconto(int percentual, int qtdMax, int qtdMin, Produto produto) {
		this.percentual = percentual;
		this.qtdMax = qtdMax;
		this.qtdMin = qtdMin;
		this.produto = produto;
	}

	public int getPercentual() {
		return percentual;
	}

	public void setPercentual(int percentual) {
		this.percentual = percentual;
	}

	public int getQtdMax() {
		return qtdMax;
	}

	public void setQtdMax(int qtdMax) {
		this.qtdMax = qtdMax;
	}

	public int getQtdMin() {
		return qtdMin;
	}

	public void setQtdMin(int qtdMin) {
		this.qtdMin = qtdMin;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
