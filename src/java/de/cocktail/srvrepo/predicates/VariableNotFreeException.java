package de.cocktail.srvrepo.predicates;

public class VariableNotFreeException extends Exception {
	public VariableNotFreeException(String msg) { super(msg); }
	public VariableNotFreeException(String msg, Throwable cause) { super(msg, cause); }
	public VariableNotFreeException(Throwable cause) { super(cause); } 
}