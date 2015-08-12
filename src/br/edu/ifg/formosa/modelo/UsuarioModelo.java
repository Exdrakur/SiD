package br.edu.ifg.formosa.modelo;

public class UsuarioModelo {

	private String cpf;
	private String nome;
	private String senha;

	public UsuarioModelo() {}
	
	public UsuarioModelo(String cpf, String senha) {
		this.cpf=cpf;
		this.senha=senha;
	}
	
	public UsuarioModelo(String cpf, String senha, String nome) {
		this.cpf=cpf;
		this.senha=senha;
		this.nome=nome;
	}
	
	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}

}
