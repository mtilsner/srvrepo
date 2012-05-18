package de.cocktail.srvrepo.predicates;

public class VariableConflictException extends Exception {	
	public VariableConflictException(String msg) { super(msg); }
	public VariableConflictException(String msg, Throwable cause) { super(msg, cause); }
	public VariableConflictException(Throwable cause) { super(cause); } 
}