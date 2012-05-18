package de.cocktail.srvrepo;

import de.cocktail.srvrepo.predicates.Formula;
import de.cocktail.srvrepo.types.Datatype;

public class SpecificationQuery {
	
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	private String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	private Datatype input;
	public Datatype getInput() { return input; }
	public void setInput(Datatype input) { this.input = input; }
	
	private Datatype output;
	public Datatype getOutput() { return output; }
	public void setOutput(Datatype output) { this.output = output; }
	
	private Formula precondition;
	public Formula getPrecondition() { return precondition; }
	public void setPrecondition(Formula precondition) { this.precondition = precondition; }
	
	private Formula postcondition;
	public Formula getPostcondition() { return postcondition; }
	public void setPostcondition(Formula postcondition) { this.postcondition = postcondition; }
	
}