package dados;

import negocios.UsuarioAbstrato;

public interface RepositorioUsuarios <Usuario extends UsuarioAbstrato> {
	
	public void adicionar(Usuario usuario);
	
	public void remover(Usuario usuario);
	
	public Usuario consultar(String cpf);
	
	public void atualizar(Usuario usuario);
	
}
