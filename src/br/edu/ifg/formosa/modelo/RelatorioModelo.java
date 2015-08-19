package br.edu.ifg.formosa.modelo;



public class RelatorioModelo {

	private String nome, rg, orgaoExp, fone, hrEntrada, hrSaida, data;
	
	public RelatorioModelo(String nome, String rg, String orgaoExp, String fone, String hrEntrada, String hrSaida, String data) {
		this.nome=nome;
		this.rg=rg;
		this.orgaoExp=orgaoExp;
		this.fone=fone;
		//Aqui faz a codificação da data e da hora
		this.hrEntrada=hrEntrada.substring(0,8);
		this.hrSaida=hrSaida.substring(0,8);
		this.data=(data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4));
	}

	public String getNome() {return nome;}
	public String getRg() 	{return rg;}
	public String getOrgaoExp() {return orgaoExp;}
	public String getFone() 	{return fone;}
	public String getHrEntrada() 	{return hrEntrada;}
	public String getHrSaida() 	{return hrSaida;}
	public String getData() 	{return data;}
	
}
