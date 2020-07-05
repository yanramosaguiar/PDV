package com.brl.pdv.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JanelaListaBugs extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public JanelaListaBugs() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("PDV - PRINCIPAIS BUGS");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 494, 40);
		contentPane.add(lblNewLabel);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(ok -> this.dispose());
		btnOk.setBounds(425, 225, 89, 36);
		contentPane.add(btnOk);

		JTextArea txtrNo = new JTextArea();
		txtrNo.setEditable(false);
		txtrNo.setFont(new Font("Monospaced", Font.ITALIC, 14));
		txtrNo.setForeground(new Color(128, 0, 0));
		txtrNo.setBackground(new Color(255, 250, 250));
		txtrNo.setText(
				"- Não é possível fazer mais de uma compra com o mesmo \r\ncomprador, produto e quantidade;\r\n- O usuário não é impedido de fazer uma venda repetida;\r\n- Não é possivel excluir uma venda\r\n (não deu tempo de implementar :/)\r\n- A ação de vender funciona, porém demora para executar \r\n (na minha máquina acontece isso)");
		txtrNo.setBounds(10, 62, 504, 152);
		contentPane.add(txtrNo);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
