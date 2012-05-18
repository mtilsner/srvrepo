package de.cocktail.srvrepo.predicates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrCombinator implements Formula {
	
	List<Formula> formulas;
	public List<Formula> getFormulas() { return formulas; }
	private void setFormulas(List<Formula> formulas) { this.formulas = formulas; }
	
	public OrCombinator(List<Formula> formulas) { setFormulas(formulas); }
	
	public List<Variable> getFreeVariables() {
		List<Variable> variables = new ArrayList<Variable>();
		for(Formula f: getFormulas()) {
			for(Variable v: f.getFreeVariables()) {
				if(!variables.contains(v)) variables.add(v);
			}
		}
		return variables;
	}
	public List<Variable> getBoundVariables() {
		List<Variable> variables = new ArrayList<Variable>();
		for(Formula f: getFormulas()) {
			for(Variable v: f.getBoundVariables()) {
				if(!variables.contains(v)) variables.add(v);
			}
		}
		return variables;
	}
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException {
		int i = getFreeVariables().indexOf(old);
		if(i == -1) throw new VariableNotFreeException("Variable '"+old+"' is not free in Formula '"+toString()+"'");
		int j = getFreeVariables().indexOf(replacement);
		if(j != -1 && j != i) throw new VariableConflictException("Variable '"+replacement+"' already exists in Formula '"+toString()+"'");
		int k = getBoundVariables().indexOf(replacement);
		if(k != -1 && k != i) throw new VariableConflictException("Variable '"+replacement+"' already exists in Formula '"+toString()+"'");
		for(Formula f: getFormulas()) {
			try {
				f.rename(old, replacement);
			} catch(Exception e) {}
		}
	}

	public String toString() {
		String s = "("+getFormulas().get(0);
		for(Formula f: getFormulas().subList(1,getFormulas().size())) {
			s += " v "+f;
		}

		return s+")";
	}
	public boolean equals(Formula other) {
		if(!(other instanceof OrCombinator)) return false;
		Set<Formula> theseFormulas = new HashSet<Formula>(getFormulas());
		Set<Formula> otherFormulas = new HashSet<Formula>(((OrCombinator) other).getFormulas());
		return theseFormulas.equals(otherFormulas);
	}
}