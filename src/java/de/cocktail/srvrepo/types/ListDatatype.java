package de.cocktail.srvrepo.types;

public class ListDatatype implements Datatype {

	private Datatype listType;
	public Datatype getListType() { return listType; }
	private void setListType(Datatype listType) { this.listType = listType; }
	
	public ListDatatype(Datatype listType) { setListType(listType); }
	
	public String toString() { return "List["+getListType()+"]"; }
	public boolean equals(Datatype other) {
		return (other instanceof ListDatatype) && ((ListDatatype) other).getListType().equals(getListType());
	}

	public boolean isSubtypeOf(Datatype other) { return false; }
}