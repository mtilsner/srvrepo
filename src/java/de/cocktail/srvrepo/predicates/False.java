package de.cocktail.srvrepo.predicates;

import java.util.ArrayList;
import java.util.List;

public class False implements Formula {
	public List<Variable> getFreeVariables() {
		return new ArrayList<Variable>();
	}
	public List<Variable> getBoundVariables(){
		return new ArrayList<Variable>();
	}
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException {
	}
	
	public String toString() {
		return "false";
	}
	public boolean equals(Formula other) {
		return toString().equals(other.toString());
	}
}