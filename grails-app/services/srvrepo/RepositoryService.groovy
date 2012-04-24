package srvrepo

import de.cocktail.srvrepo.ServiceNotFoundException
import de.cocktail.srvrepo.ServiceQuery
import de.cocktail.srvrepo.SpecificationQuery
import de.cocktail.srvrepo.logic.Reasoner
import de.cocktail.srvrepo.predicates.AndCombinator

class RepositoryService {
    static transactional = true

	def authenticationService

    def enable(String name, List<Specification> specifications, URL url, Vendor vendor) {
		def s = new Service(name: name, url: url, vendor: vendor, activated: false)
		for(p in specifications) s.addToSpecifications(p)
		return s.save(flush: true)
    }

	def retrieve(Long id) throws ServiceNotFoundException {
		def s = Service.read(id)
		if(!s) throw new ServiceNotFoundException("Unknown service '${id}'")
		return s
	}
	
	def update(Long id, List<Specification> specifications, URL url, boolean activated) throws ServiceNotFoundException  {
		def s = Service.get(id)
		if(!s) throw new ServiceNotFoundException("Unknown service '${id}'")
		if(specifications) {
			for(spec in Specification.list()) {
				if(s.specifications.contains(spec) && !specifications.contains(spec)) s.removeFromSpecifications(spec)
				if(!s.specifications.contains(spec) && specifications.contains(spec)) s.addToSpecifications(spec)
			}
		}
		if(url) s.url = url
		if(activated) s.activated = activated
		return s.save(flush: true)
	}
	
	def disable(Long id) throws ServiceNotFoundException  {
		def s = Service.get(id)
		if(!s) throw new ServiceNotFoundException("Unknown service '${id}'")
		s.delete(flush: true)
	}
	
	def findServices(ServiceQuery query) {
		return Service.list().findAll { service ->
		  (
			   (!query.name || service.name ==~ query.name)
			&& (!query.specification || service.specifications.contains(query.specification))
			&& (!query.vendor || service.vendor == query.vendor)
			&& service.activated
		  )
		}
	}
	
	def findSpecifications(SpecificationQuery query) {
		return Specification.list().findAll {
		  (
			   (!query.name || it.name ==~ query.name)
			&& (!query.description || it.description ==~ query.description)
			&& (!query.input || query.input == it.input || query.input.isSubtypeOf(it.input))
			&& (!query.output || query.output == it.output || it.output.isSubtypeOf(query.output))
			&& (!query.precondition || Reasoner.implies(query.precondition,it.precondition))
			&& (!query.postcondition || (
					   (!query.precondition && Reasoner.implies(it.postcondition,query.postcondition))
					|| (query.precondition && Reasoner.implies(new AndCombinator([it.precondition,it.postcondition]),query.postcondition))
										)
			   )
		  )
		}		
	}
}
