package de.cocktail.srvrepo

public class ServiceNotFoundException extends Exception {
	public ServiceNotFoundException(String msg) { super(msg); }
	public ServiceNotFoundException(String msg, Throwable cause) { super(msg, cause); }
	public ServiceNotFoundException(Throwable cause) { super(cause); } 
}