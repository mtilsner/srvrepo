package srvrepo.api

import de.cocktail.srvrepo.ServiceNotFoundException
import de.cocktail.srvrepo.ServiceQuery
import de.cocktail.srvrepo.predicates.FormulaHelper
import de.cocktail.srvrepo.types.DatatypeHelper
import grails.converters.*
import srvrepo.*

class API_ServiceController {
	def repositoryService
	
	def list = {
		response.status = 200
		render convertServiceListToMap(Service.list()) as JSON
	}
	
	def search = {
		def r = request.JSON
		def query = new ServiceQuery()
		if(r.name) query.name = params.name
		if(r.specification) query.specification = Specification.get(r.specification)
		if(r.vendor) query.vendor = Vendor.get(r.vendor)
		response.status = 200
		render convertServiceListToMap(repositoryService.findServices(query)) as JSON
	}
	
	def create = {
		def r = request.JSON
		def specifications = []
		for(s in r.specifications) specifications << Specification.get(s)
		def vendor = Vendor.get(r.vendor)
		def s = repositoryService.enable(r.name, specifications, new URL(r.url), vendor)
		if(s) {
			response.status = 201
			response.setHeader("Location", "${g.createLinkTo(dir: "services", file: "${s.id}", absolute: true)}")
			render convertServiceReferenceToMap(s)
		} else
			response.sendError(500)
	}
	
	def read = {
		try {
			def s = repositoryService.retrieve(params.id.toLong())
			response.status = 200
			render convertServiceToMap(s) as JSON
		} catch(ServiceNotFoundException e) {
			response.sendError(404)
		}
	}
	
	def update = {
		def r = request.JSON
		def specifications = null
		def url = null
		def activated = null
		if(r.specifications) {
			specifications = []
			for(s in r.specifications) specifications << Specification.get(s)
		}
		if(r.url) url = new URL(r.url)
		if(r.activated) activated = Boolean.parseBoolean(r.activated)
		try {
			if(repositoryService.update(params.id.toLong(), specifications, url, activated))
				render(status: 204)
			else
				response.sendError(500)
		} catch(ServiceNotFoundException e) {
			response.sendError(404)
		}
	}
	
	def delete = {
		try {
			repositoryService.disable(params.id.toLong())
			try {
				def s = repositoryService.retrieve(params.id.toLong())
				response.sendError(500)
			} catch(ServiceNotFoundException e) {
				render(status: 204)
			}
		} catch(ServiceNotFoundException e) {
			response.sendError(404)
		}
	}
	
/************************************************************************
 ******************** HELPER METHODS FOR JSON RENDERING *****************
 ************************************************************************/
	def static convertServiceReferenceToMap(Service service) {
		return service.id
	}
	def static convertServiceToMap(Service service) {
		def qualities = []
		for(q in service.qualities) qualities[] = q.id
		def specifications = API_SpecificationsController.convertSpecificationListToMap(service.specifications)
		
		return [ id: convertServiceReferenceToMap(service),
				 name: service.name,
				 specifications: specifications,
				 url: service.url,
				 vendor: service.vendor.id,
				 qualities: qualities]
	}
	def static convertServiceListToMap(Iterable<Service> services) {
		def buffer = []
		for(s in services)
			buffer << convertServiceReferenceToMap(s)
			
		return buffer
	}
}
