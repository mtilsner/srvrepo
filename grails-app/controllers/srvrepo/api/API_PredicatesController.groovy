package srvrepo.api

import grails.converters.*
import srvrepo.*

class API_PredicatesController {

	def list = {
		response.status = 200
		render convertPredicateListToMap(Predicate.list()) as JSON
	}

	def read = {
		def p 
		try {
			p = Predicate.get(params.id.toLong())
		} catch(NumberFormatException e) {
			p = Predicate.findByName(params.id)
		}
		if(p) {
			response.status = 200
			render convertPredicateToMap(p) as JSON
		} else
			response.sendError(404)
	}

/************************************************************************
 ******************** HELPER METHODS FOR RENDERING **********************
 ************************************************************************/
	def static convertPredicateReferenceToMap(Predicate predicate) {
		return predicate.name
	}
	def static convertPredicateToMap(Predicate predicate) {
		return [ id: convertPredicateReferenceToMap(predicate),
				 description: predicate.description
 			   ]
	}
	def static convertPredicateListToMap(List<Predicate> predicates) {
		def buffer = []
		for(p in predicates)
			buffer << convertPredicateReferenceToMap(p)

		return buffer
	}
}
