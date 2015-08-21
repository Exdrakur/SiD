package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

public class LoginRecepcionistaView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final JButton btnConfirmar, btnCancelar;
	private final JLabel lblTextoInfomativo;
	private final JPasswordField pwSenha;
	private JFormattedTextField fTxtCPF;
	
	public LoginRecepcionistaView() {
		super("Cadastro de Visitantes");
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/Sem t\u00EDtulo-1.png")).getImage());
		this.setBounds(300, 250, 300, 205);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Login");
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
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnConfirmar.setBounds(150, 130, 100, 30);
		getContentPane().add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancelar.setBounds(40, 130, 100, 30);
		getContentPane().add(btnCancelar);
		
		lblTextoInfomativo = new JLabel();
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
			fTxtCPF.setText("08007317124");
			
		}catch (ParseException e) {	e.printStackTrace();		}
		
		pwSenha = new JPasswordField("123abc");
		pwSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pwSenha.setBounds(70, 95, 200, 25);
		getContentPane().add(pwSenha);
		
		this.setVisible(true);
	}
	
	public JLabel getLblTextoInfomativo(){	return lblTextoInfomativo;	}
	public JFormattedTextField getFTxtCPF() {	return fTxtCPF;	}
	public JPasswordField getTxtSenha() {	return pwSenha;	}
	public JButton getBtnConfirmar(){	return btnConfirmar;}
	public JButton getBtnCancelar() {	return btnCancelar;	}
}
