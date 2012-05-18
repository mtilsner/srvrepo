package de.cocktail.srvrepo.types;

import java.util.List;

public class TupleDatatype implements Datatype {
	
	private List<Datatype> types;
	public List<Datatype> getTypes() { return types; }
	private void setTypes(List<Datatype> types) { this.types = types; }
	
	public TupleDatatype(List<Datatype> types) { setTypes(types); }
	
	public String toString() {
		String s = "(";
		for(Datatype t: types) { s+=t+","; }
		return s.substring(0,s.length()-1)+")";
	}
	public boolean equals(Datatype other) {
		if(!(other instanceof TupleDatatype)) return false;
		TupleDatatype o = (TupleDatatype) other;		
		if(o.getTypes().size() != getTypes().size()) return false;
		for(int i=0; i<getTypes().size();i++) {
			if(! getTypes().get(i).equals(o.getTypes().get(i))) return false;
		}
		return true;
		
		/*************************************************************************************
		 * Would be nicer to use this line, but for some reason it doesn't work      ???WHY???
		 *************************************************************************************/
		// return (other instanceof TupleDatatype) && ((TupleDatatype) other).getTypes().equals(getTypes());
	}
	
	public boolean isSubtypeOf(Datatype other) { return false; }
	
}