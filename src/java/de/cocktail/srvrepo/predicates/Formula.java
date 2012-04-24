package de.cocktail.srvrepo.predicates;

import java.util.List;

public interface Formula {
	
	public List<Variable> getFreeVariables();
	public List<Variable> getBoundVariables();
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException;
	
	public String toString();
	public boolean equals(Formula other);
}