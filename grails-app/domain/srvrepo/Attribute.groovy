package srvrepo

import de.cocktail.srvrepo.types.Datatype

class Attribute {

	static belongsTo = [basetype: Basetype]

	String name
	Datatype type
	
	static constraints = {
		name(unique: 'basetype')
	}

	public String toString() {
		return name
	}
}
