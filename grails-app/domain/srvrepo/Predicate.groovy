package srvrepo

import de.cocktail.srvrepo.types.Datatype

class Predicate {

	String name
	Datatype input
	String description

	public String toString() {
		return name
	}

	static mapping = {
		description(type: 'text')
	}
    static constraints = {
		name(unique: true)
    }
}
