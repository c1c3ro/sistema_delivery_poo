package negocios;

public abstract class UsuarioAbstrato {
	
	private String nome;
	private String senha;
	
	public UsuarioAbstrato(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public void atualizarNome(String nomeNovo) {
		
		this.nome = nomeNovo;
	
		return;
	}
	
	public void atualizarSenha(String senhaNova) {
		
		this.senha = senhaNova;
		
		return;
	}

}
