package srvrepo.api

import de.cocktail.srvrepo.SpecificationQuery
import de.cocktail.srvrepo.predicates.FormulaHelper
import de.cocktail.srvrepo.types.DatatypeHelper
import grails.converters.*
import srvrepo.*

class API_SpecificationsController {
	def repositoryService
	
	def list = {
		response.status = 200
		render convertSpecificationListToMap(Specification.list()) as JSON
	}
	
	def search = {
		def r = request.JSON
		def query = new SpecificationQuery()
		if(r.name) query.name = r.name
		if(r.description) query.description = r.description
		if(r.input) query.input = DatatypeHelper.assemble(r.input)
		if(r.output) query.output = DatatypeHelper.assemble(r.output)
		if(r.'pre-condition') query.precondition = FormulaHelper.assemble(r.precondition)
		if(r.'post-condition') query.postcondition = FormulaHelper.assemble(r.postcondition)
		response.status = 200
		render convertSpecificationListToMap(repositoryService.findSpecifications(query)) as JSON
	}

	def read = {
		def s = Specification.get(params.id.toLong())
		if(s) {
			response.status = 200
			render convertSpecificationToMap(s) as JSON
		} else
			response.sendError(404)
	}

/************************************************************************
 ******************** HELPER METHODS FOR RENDERING **********************
 ************************************************************************/
	def static convertSpecificationReferenceToMap(Specification specification) {
		return specification.id
	}
	def static convertSpecificationToMap(Specification specification) {
		return [ id: convertSpecificationReferenceToMap(specification),
				 name: specification.name,
				 input: DatatypeHelper.disassemble(specification.input),
				 output: DatatypeHelper.disassemble(specification.output),
				 'pre-condition': FormulaHelper.disassemble(specification.precondition),
				 'post-condition': FormulaHelper.disassemble(specification.postcondition),
				 description: specification.description ]
	}
	def static convertSpecificationListToMap(Iterable<Specification> specifications) {
		def buffer = []
		for(s in specifications)
			buffer << convertSpecificationReferenceToMap(s)

		return buffer
	}
}
