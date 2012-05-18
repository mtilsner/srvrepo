package de.cocktail.srvrepo.types;

public class MapDatatype implements Datatype {
	
	private Datatype key;
	public Datatype getKey() { return key; }
	private void setKey(Datatype key) { this.key = key; }
	
	private Datatype value;
	public Datatype getValue() { return value; }
	private void setValue(Datatype value) { this.value = value; }
	
	public MapDatatype(Datatype key, Datatype value) { setKey(key); setValue(value); }
	
	public String toString() { return "["+getKey()+":"+getValue()+"]"; }
	public boolean equals(Datatype other) {
		return (other instanceof MapDatatype) && ((MapDatatype) other).getKey().equals(getKey())
											  && ((MapDatatype) other).getValue().equals(getValue());
	}
	
	public boolean isSubtypeOf(Datatype other) { return false; }
}