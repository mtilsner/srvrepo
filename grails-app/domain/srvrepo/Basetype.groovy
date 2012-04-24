package srvrepo

import de.cocktail.srvrepo.types.Datatype

class Basetype implements Datatype {

	static hasMany = [attributes: Attribute, subtypes: Basetype]
	static belongsTo = [supertype: Basetype]

	String name
	
	public String toString() { return name; }
	public boolean equals(Datatype other) {
		return (other instanceof Basetype) && ((Basetype) other).getName().equals(getName())
	}
	public boolean equals(Basetype other) { return equals((Datatype) other); }
	public boolean isSubtypeOf(Datatype other) {
		if(supertype && (supertype == other || supertype.isSubtypeOf(other)))
			return true
		else
			return false
	}
	
	static constraints = {
		name(unique: true)
	}
}
