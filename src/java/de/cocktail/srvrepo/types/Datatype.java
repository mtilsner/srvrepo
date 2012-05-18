package de.cocktail.srvrepo.types;

public interface Datatype {
	public boolean equals(Datatype other);
	public String toString();
	public boolean isSubtypeOf(Datatype other);
}