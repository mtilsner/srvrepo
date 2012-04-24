package srvrepo

import de.cocktail.srvrepo.types.Datatype
import de.cocktail.srvrepo.predicates.Formula

class Specification {
	static hasMany = [services: Service]
	static belongsTo = Service
	
	String name
	Datatype input
	Datatype output
	Formula precondition
	Formula postcondition
	String description

	static mapping = {
		description(type: 'text')
	}

    static constraints = {
		name(unique: true)
		precondition(nullable: true)
		postcondition(nullable: true)
		input(nullable: true)
		output(nullable: true)
    }
}
