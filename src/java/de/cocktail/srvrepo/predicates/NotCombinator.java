package de.cocktail.srvrepo.predicates;

import java.util.ArrayList;
import java.util.List;

public class NotCombinator implements Formula {
	
	Formula formula;
	public Formula getFormula() { return formula; }
	private void setFormula(Formula formula) { this.formula = formula; }
	
	public NotCombinator(Formula formula) { setFormula(formula); }
	
	public List<Variable> getFreeVariables() {
		return getFormula().getFreeVariables();
	}
	public List<Variable> getBoundVariables() {
		return getFormula().getBoundVariables();
	}
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException {
		getFormula().rename(old, replacement);
	}

	public String toString() {
		return "¬("+getFormula()+")";
	}
	public boolean equals(Formula other) {
		return (other instanceof NotCombinator) && ((NotCombinator) other).getFormula().equals(getFormula());
	}
}