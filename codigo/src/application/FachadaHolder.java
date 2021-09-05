package application;
import fachada.Delivery;
import negocios.Cliente;
import negocios.Gerente;
import negocios.Restaurante;

public final class FachadaHolder {
	
	Delivery fachada = null;
	private Cliente clienteLogado;
	private Gerente gerenteLogado;
	private Restaurante restauranteSelecionado;
	
	public Restaurante getRestauranteSelecionado() {
		return restauranteSelecionado;
	}


	public void setRestauranteSelecionado(Restaurante restauranteSelecionado) {
		this.restauranteSelecionado = restauranteSelecionado;
	}


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
	
	public void salvarDados() throws Exception {
		this.fachada.saveData();
	}
	
	public void carregarDados() throws Exception {
		this.fachada = this.fachada.readData();
	}
	
}
