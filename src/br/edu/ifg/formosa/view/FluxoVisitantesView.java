package br.edu.ifg.formosa.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

public class FluxoVisitantesView extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private final JButton btNao, btSim;
	
	public FluxoVisitantesView(String nome, boolean situacao) {
		super("Confirmar Fluxo", false, true, false, false);
		getContentPane().setBackground(Color.WHITE);
		this.setBounds(240,125,420,150);
		getContentPane().setLayout(null);
		
		String situacaoTxt= situacao?"saída":"entrada";
		JLabel label = new JLabel("Deseja confirmar a "+situacaoTxt+" de");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label.setBounds(10, 10, 385, 30);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("?");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label_1.setBounds(385, 40, 10, 30);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel(nome);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label_2.setBounds(10, 40, 370, 30);
		getContentPane().add(label_2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		
		btNao = new JButton("N\u00E3o");
		btNao.setBounds(80, 80, 90, 30);
		btNao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		getContentPane().add(btNao);
		
		btSim = new JButton("Sim");
		btSim.setBounds(230, 80, 90, 30);
		btSim.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		getContentPane().add(btSim);
		
		setVisible(true);
	}

	public JButton getBtNao() {return btNao;}
	public JButton getBtSim() {return btSim;}
	
}