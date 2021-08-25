package Exceptions;

public class ClienteJaExisteException extends Exception {
    
	public ClienteJaExisteException(String errorMessage) {
        
		super(errorMessage);
    
	}
	
}
