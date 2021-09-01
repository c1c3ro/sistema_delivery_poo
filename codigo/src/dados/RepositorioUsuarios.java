package dados;

import negocios.UsuarioAbstrato;

import Exceptions.OpcaoInvalidaException;

public interface RepositorioUsuarios <Usuario extends UsuarioAbstrato> {
	
	public void adicionar(Usuario usuario);
	
	public void remover(Usuario usuario);
	
	public Usuario consultar(String cpf);
	
	public String atualizar(Usuario usuario, int campo, String novoValor) throws OpcaoInvalidaException;
	
}
