package srvrepo.api

import de.cocktail.srvrepo.types.DatatypeHelper
import grails.converters.*
import srvrepo.*

class API_TypesController {

	def list = {
		response.status = 200
		render convertTypeListToMap(Basetype.list()) as JSON
	}

	def read = {
		def t
		try {
			t = Basetype.get(params.id.toLong())
		} catch(NumberFormatException e) {
			t = Basetype.findByName(params.id)
		}
		if(t) {
			response.status = 200
			render convertTypeToMap(t) as JSON
		} else
			response.sendError(404)
	}

/************************************************************************
 ******************** HELPER METHODS FOR RENDERING **********************
 ************************************************************************/
	def static convertTypeReferenceToMap(Basetype type) {
		return type.id
	}
	def static convertTypeToMap(Basetype type) {
		def attributes = [:]
		for(a in type.attributes) {
			attributes[a.name] = DatatypeHelper.disassemble(a.type)
		}
		return [ id: convertTypeReferenceToMap(type),
				 name: type.name,
				 attributes: attributes,
 			   ]
	}
	def static convertTypeListToMap(List<Basetype> types) {
		def buffer = []
		for(t in types)
			buffer << convertTypeReferenceToMap(t)

		return buffer
	}
}
