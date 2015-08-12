package br.edu.ifg.formosa.modelo;

public class FluxoModelo {
	
	private int cpf;
	private String data;
	private String hrEntrada;
	private String hrSaida;
	
	public FluxoModelo(String data, String hrEntrada, String hrSaida){
		this.data=data;
		this.hrEntrada=hrEntrada;
		this.hrSaida=hrSaida;
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
