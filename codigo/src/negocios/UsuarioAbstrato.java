package negocios;

import java.io.Serializable;

public abstract class UsuarioAbstrato implements Serializable {
	
	private String nome;
	private String senha;
	private String cpf;
	
	public UsuarioAbstrato(String nome, String senha, String cpf) {
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
	}
	
	public void atualizarNome(String nomeNovo) {
		
		this.nome = nomeNovo;
	
		return;
	}
	
	public void atualizarSenha(String senhaNova) {
		
		this.senha = senhaNova;
		
		return;
	}
	
	public String getCPF() {
		
		return this.cpf;
		
	}
	
	public String getNome() {
		
		return this.nome;
		
	}
	
	public String getSenha() {
		
		return this.senha;
		
	}

}
