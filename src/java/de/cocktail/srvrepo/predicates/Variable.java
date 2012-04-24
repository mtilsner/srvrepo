package de.cocktail.srvrepo.predicates;

import de.cocktail.srvrepo.types.Datatype;

public class Variable {

	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	private Datatype type;
	public Datatype getType() { return type; }
	public void setType(Datatype type) { this.type = type; }
	
	public String toString() { return getName()+"["+getType()+"]"; }
	public boolean equals(Variable other) {
		return getName().equals(other.getName()) && getType().equals(other.getType());
	}
	
	public Variable(String name, Datatype type) { setName(name); setType(type); }
}