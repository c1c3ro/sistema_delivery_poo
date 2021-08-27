package application;
import fachada.Delivery;

public final class FachadaHolder {
	
	
	Delivery fachada = null;
	
	private final static FachadaHolder INSTANCE = new FachadaHolder();

	private FachadaHolder() {}
	  

	public static FachadaHolder getInstance() {
	    return INSTANCE;
	}
	
}
