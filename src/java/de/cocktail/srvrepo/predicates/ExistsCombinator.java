package de.cocktail.srvrepo.predicates;

import java.util.ArrayList;
import java.util.List;

public class ExistsCombinator implements Formula {

	Formula formula;
	public Formula getFormula() { return formula; }
	private void setFormula(Formula formula) { this.formula = formula; }
	
	Variable variable;
	public Variable getVariable() { return variable; }
	private void setVariable(Variable variable) { this.variable = variable; }
	
	public ExistsCombinator(Variable variable, Formula formula) { setVariable(variable); setFormula(formula); }
	
	public List<Variable> getFreeVariables() {
		List<Variable> free = new ArrayList<Variable>(getFormula().getFreeVariables());
		free.remove(getVariable());
		return free;
	}
	public List<Variable> getBoundVariables() {
		List<Variable> bound = new ArrayList<Variable>(getFormula().getBoundVariables());
		bound.add(getVariable());
		return bound;
	}
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException {
		if(getVariable().equals(old)) setVariable(replacement);
		getFormula().rename(old, replacement);
	}

	public String toString() {
		return "∃"+getVariable()+".("+getFormula()+")";
	}
	public boolean equals(Formula other) {
		return (other instanceof ExistsCombinator) && ((ExistsCombinator) other).getFormula().equals(getFormula())
										 		   && ((ExistsCombinator) other).getVariable().equals(getVariable());
	}
	
}