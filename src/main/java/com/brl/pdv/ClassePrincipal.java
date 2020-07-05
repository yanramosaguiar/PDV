package com.brl.pdv;

import java.awt.EventQueue;

import com.brl.pdv.view.JanelaNovaVenda;

public class ClassePrincipal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JanelaNovaVenda janelaInicial = new JanelaNovaVenda();
				janelaInicial.setVisible(true);
			}
		});
	}
}
