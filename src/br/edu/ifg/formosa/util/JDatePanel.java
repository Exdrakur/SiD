package br.edu.ifg.formosa.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class JDatePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private String[] dias31={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private JComboBox<String> cBDia, cBMes, cBAno;
	private final int ANO_IMPLANTACAO=2015;
	
	public JDatePanel(int pX, int pY) {
		super(null);
		setBackground(Color.WHITE);
		this.setBounds(pX, pY+1, 220, 28);
		
		cBDia = new JComboBox<String>(dias31);
		cBDia.setBounds(0, 0, 50, 28);
		add(cBDia);

		String[] meses={"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
		cBMes = new JComboBox<String>(meses);
		cBMes.setBounds(55, 0, 90, 28);
		add(cBMes);
		
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy");
		int anoAtual = Integer.parseInt(sDF.format(Calendar.getInstance().getTime()));
		cBAno = new JComboBox<String>();
			for (int i=anoAtual; i>=ANO_IMPLANTACAO; i--) {	cBAno.addItem(i+"");	}//2015 � o ano de cria��o e implata��o do programa
		cBAno.setBounds(150, 0, 70, 28);
		add(cBAno);
		
		addAcao(cBMes);
		addAcao(cBAno);
	}
	
	private void addAcao(JComboBox<String> combo){
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selec=cBMes.getSelectedIndex();
				int indexDia = cBDia.getSelectedIndex();
				int totalDias=0;
				//Meses de 31 dias
				//Janeiro - Mar�o - Maio - Julho - Agosto - Outubro - Dezembro
				if(selec==0 || selec==2 || selec==4 || selec==6 || selec==7 || selec==9 || selec==11){
					totalDias=31;
				}
				//Fevereiro
				else if(selec==1){
					int ano = Integer.parseInt(cBAno.getSelectedItem().toString());
					if(ano%4==0){//Ano bissesto
						totalDias=29;
					}else{//Ano normal
						totalDias=28;
					}
				}
				//Meses de 30 dias
				else{
					totalDias=30;
				}
				cBDia.removeAllItems();//Remove todas a op��es
				for(int i=1;i<=totalDias;i++){//Adiciona as op��es de dias
					cBDia.addItem(i+"");
				}
				indexDia=(indexDia<cBDia.getItemCount()?indexDia:cBDia.getItemCount());
				
				try{//Seleciona uma op��o
					cBDia.setSelectedIndex(indexDia);
				}catch(IllegalArgumentException e){
					cBDia.setSelectedIndex(cBDia.getItemCount()-1);
				}
			}
		});
	}
	
	public String getText(){
		return (//Padr�o de data do Postegree: AAAA-MM-DD
			cBAno.getSelectedItem().toString()//Ano
			+"-"
			+(cBMes.getSelectedIndex()+1)//�ndice do m�s mais a corre��o de +1
			+"-"
			+cBDia.getSelectedItem().toString()//Dia
		);
	}
	
	public Date getDateEUA(){
		return Date.valueOf(getText());
	}
	
}
