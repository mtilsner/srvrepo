package de.cocktail.srvrepo.predicates;

import de.cocktail.srvrepo.types.TupleDatatype;
import de.cocktail.srvrepo.types.Datatype;
import srvrepo.Predicate;
import java.util.List;
import java.util.ArrayList;

public class InstantiatedPredicate implements Formula {
	
	Predicate predicate;
	public Predicate getPredicate() { return predicate; }
	private void setPredicate(Predicate predicate) { this.predicate = predicate; }
	
	List<Variable> variables;
	public List<Variable> getVariables() { return variables; }
	private void setVariables(List<Variable> variables) throws VariableConflictException {
		if(getPredicate() != null) {
			List<Datatype> types = new ArrayList<Datatype>();
			for(Variable v: variables) { types.add(v.getType()); }
			Datatype type = (types.size() == 1) ? types.get(0) : new TupleDatatype(types);
			if(!getPredicate().getInput().equals(type))
				throw new VariableConflictException("Variables '"+variables+"' do not match type of Predicate '"+getPredicate().getInput()+"'");
		}
		this.variables = variables;
	}
	
	public InstantiatedPredicate(Predicate predicate, List<Variable> variables) throws VariableConflictException {
		setPredicate(predicate);
		setVariables(variables);
	}
	
	public List<Variable> getFreeVariables() { return getVariables(); }
	public List<Variable> getBoundVariables() { return new ArrayList<Variable>(); }
	public void rename(Variable old, Variable replacement) throws VariableNotFreeException, VariableConflictException {
		int i = getFreeVariables().indexOf(old);
		if(i == -1) throw new VariableNotFreeException("Variable '"+old+"' is not free in Formula '"+toString()+"'");
		int j = getFreeVariables().indexOf(replacement);
		if(j != -1 && j != i) throw new VariableConflictException("Variable '"+replacement+"' already exists free in Formula '"+toString()+"'");
		int k = getBoundVariables().indexOf(replacement);
		if(k != -1 && k != i) throw new VariableConflictException("Variable '"+replacement+"' already exists bound in Formula '"+toString()+"'");
		getVariables().set(i, replacement);
	}

	public String toString() {
		String s = getPredicate().getName()+"(";
		for(Variable v: getVariables()) { s+=v.getName()+","; }
		return s.substring(0,s.length()-1)+")";
	}
	public boolean equals(Formula other) {
		return (other instanceof InstantiatedPredicate)
				&& ((InstantiatedPredicate) other).getPredicate().equals(getPredicate())
				&& ((InstantiatedPredicate) other).getVariables().equals(getVariables());
	}
}