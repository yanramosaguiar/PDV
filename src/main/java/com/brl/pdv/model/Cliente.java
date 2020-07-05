package com.brl.pdv.model;

public class Cliente {

	private int codigo;
	private String nome;
	private int bonus;
	private char perfil;
	private char status;

	public Cliente(String nome, char perfil, char status) {
		this.nome = nome;
		this.perfil = perfil;
		this.status = status;
		this.bonus = 0;
	}

	public String getNome() {
		return nome;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public char getPerfil() {
		return perfil;
	}

	public char getStatus() {
		return status;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
