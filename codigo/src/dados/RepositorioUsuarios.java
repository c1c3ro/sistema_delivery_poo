package dados;

import negocios.UsuarioAbstrato;

public interface RepositorioUsuarios {
	
	public void adicionar(UsuarioAbstrato usuario);
	
	public void remover(UsuarioAbstrato usuario);
	
	public UsuarioAbstrato conslutar(String cpf);
	
	public void atualizar(UsuarioAbstrato usuario);
	
}
