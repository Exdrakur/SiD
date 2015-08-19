package br.edu.ifg.formosa.modelo;

public class FluxoModelo {
	
	private int cpf;
	private String data;
	private String hrEntrada;
	private String hrSaida;
	
	public FluxoModelo(String data, String hrEntrada, String hrSaida){
		//Aqui faz a codificação da data e da hora
		this.data=(data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4));
		this.hrEntrada=hrEntrada.substring(0,8);
		this.hrSaida=hrSaida.substring(0,8);
	}
	
	public int getCpf() {return cpf;}
	public void setCpf(int cpf) {this.cpf = cpf;}
	public String getData() {return data;}
	public void setData(String data) {this.data = data;}
	public String getHrEntrada() {return hrEntrada;}
	public void setHrEntrada(String hrEntrada) {this.hrEntrada = hrEntrada;}
	public String getHrSaida() {return hrSaida;}
	public void setHrSaida(String hrSaida) {this.hrSaida = hrSaida;}
}
