package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class GerarRelatorioNomeView extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private final JButton btnImprimir, btnCancelar;
	private final JLabel lblTextoInfomativo;
	private final JPasswordField pwSenha;
	private JFormattedTextField fTxtCPF;

	public GerarRelatorioNomeView() {
		super("Visualizar dados", false, true, false, false);
		getContentPane().setBackground(Color.WHITE);
		this.setBounds(300, 120, 300, 205);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Confirmar administrador");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 264, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lbCpf = new JLabel("CPF:");
		lbCpf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbCpf.setBounds(20, 65, 50, 20);
		getContentPane().add(lbCpf);
		
		JLabel blSenha = new JLabel("Senha:");
		blSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		blSenha.setBounds(20, 95, 50, 20);
		getContentPane().add(blSenha);
		
		btnImprimir = new JButton("Confirmar");
		btnImprimir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnImprimir.setBounds(150, 130, 100, 30);
		getContentPane().add(btnImprimir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancelar.setBounds(40, 130, 100, 30);
		getContentPane().add(btnCancelar);
		
		lblTextoInfomativo = new JLabel();//
		lblTextoInfomativo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTextoInfomativo.setForeground(Color.RED);
		lblTextoInfomativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoInfomativo.setBounds(10, 45, 264, 14);
		getContentPane().add(lblTextoInfomativo);
		
		MaskFormatter mskCPF;
		try{
			mskCPF=new MaskFormatter("###.###.###-##");
			fTxtCPF = new JFormattedTextField(mskCPF);
			fTxtCPF.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			fTxtCPF.setBounds(70, 65, 200, 25);
			getContentPane().add(fTxtCPF);
		}catch (ParseException e) {	e.printStackTrace();		}
		
		pwSenha = new JPasswordField();
		pwSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pwSenha.setBounds(70, 95, 200, 25);
		getContentPane().add(pwSenha);		
		
		this.setVisible(true);
	}
	
	public JButton getBtnImprimir() {return btnImprimir;}
	public JButton getBtnCancelar() {return btnCancelar;}
	public void setLblTextoInfomativo(String texto) {this.lblTextoInfomativo.setText(texto);}
	public JPasswordField getPwSenha() {return pwSenha;}
	public JFormattedTextField getfTxtCPF() {return fTxtCPF;}
	
}
