package Modelo;

public class NoSePudoComprarException extends RuntimeException{
	public NoSePudoComprarException(){
		super();
	}
	public NoSePudoComprarException(String message){
		super(message);
	}
}
