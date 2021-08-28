package application;
import fachada.Delivery;
import negocios.Cliente;
import negocios.Gerente;

public final class FachadaHolder {
	
	private Cliente clienteLogado;
	private Gerente gerenteLogado;
	Delivery fachada = null;
	
	private final static FachadaHolder INSTANCE = new FachadaHolder();

	private FachadaHolder() {}
	  

	public static FachadaHolder getInstance() {
	    return INSTANCE;
	}
	
	public Cliente getClienteLogado() {
		return clienteLogado;
	}


	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}


	public Gerente getGerenteLogado() {
		return gerenteLogado;
	}


	public void setGerenteLogado(Gerente gerenteLogado) {
		this.gerenteLogado = gerenteLogado;
	}
	
}
