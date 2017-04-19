package Modelo;

public class NoSePuedeHipotecarException extends RuntimeException {
	
	public NoSePuedeHipotecarException(){
		super();
	}
	public NoSePuedeHipotecarException(String message){
		super(message);
	}
}
