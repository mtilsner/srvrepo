package srvrepo

import de.cocktail.srvrepo.types.*
import de.cocktail.srvrepo.predicates.* 

class CocktailTagLib {
	static namespace = "cocktail"
	
	def specificationLink = { attrs,body ->
		out << g.link(controller:"specification", action:"show", id:attrs.specification.id) { attrs.specification.name }
	}
	def specificationSelector = { attrs,body ->
		def specifications = Specification.list()
		if(attrs.service) {
			specifications = specifications.findAll { spec ->
				!attrs.service.specifications.find { it.id == spec.id }
			}
		}
		out << g.select(name: attrs.name, from:specifications,
						optionKey:"id", optionValue:"name", value:attrs.value?.id)
	}
	
	def serviceLink = { attrs,body ->
		out << g.link(controller:"service", action:"show", id:attrs.service.id) { attrs.service.name }
	}
	
	def formulaLink = { attrs,body ->
		if(attrs.formula instanceof AndCombinator) {
			def formulas = ((AndCombinator) attrs.formula).formulas
			out << "And("
			for(int i=0; i<formulas.size(); i++) {
				out << cocktail.formulaLink(formula: formulas[i])
				if(i < formulas.size()-1) out << ", "
			}
			out << ")"
		} else if(attrs.formula instanceof OrCombinator) {
			def formulas = ((OrCombinator) attrs.formula).formulas
			out << "Or("
			for(int i=0; i<formulas.size(); i++) {
				out << cocktail.formulaLink(formula: formulas[i])
				if(i < formulas.size()-1) out << ", "
			}
			out << ")"	
		} else if(attrs.formula instanceof ExistsCombinator) {
			def formula = ((ExistsCombinator) attrs.formula).formula
			out << "Exists(" + cocktail.formulaLink(formula: formula) + ")"
		} else if(attrs.formula instanceof NotCombinator) {
			def formula = ((NotCombinator) attrs.formula).formula
			out << "Not(" + cocktail.formulaLink(formula: formula) + ")"
		} else if(attrs.formula instanceof InstantiatedPredicate) {
			def formula = (InstantiatedPredicate) attrs.formula
			out << g.link(controller: "predicate", action: "show", id: formula.predicate.id){ formula.predicate.name } + "("
			for(int i=0; i<formula.variables.size(); i++) {
				out << formula.variables[i].name + ":"
				out << cocktail.typeLink(type: formula.variables[i].type)
				if(i<formula.variables.size()-1) out << ","
			}
			out << ")"
		} else if(attrs.formula instanceof True) {
			out << "True"
		} else if(attrs.formula instanceof False) {
			out << "False"
		}
	}	
	def formulaSelector = { attrs,body ->
		out << g.textField(name: attrs.name, value: FormulaHelper.disassemble(attrs.value))
	}
	
	def typeLink = { attrs,body ->
		if(attrs.type instanceof ListDatatype) {
			def itemType = ((ListDatatype) attrs.type).listType
			out << "List(" + cocktail.typeLink(type: itemType) + ")"
		} else if(attrs.type instanceof MapDatatype) {
			def key = ((MapDatatype) attrs.type).key
			def value = ((MapDatatype) attrs.type).value			
			out << "Map(" + cocktail.typeLink(type: key) + ", " + cocktail.typeLink(type: value) + ")"
		} else if(attrs.type instanceof TupleDatatype) {
			def types = ((TupleDatatype) attrs.type).types
			out << "Tuple("
			for(int i=0; i<types.size(); i++) {
				out << cocktail.typeLink(type: types[i])
				if(i < types.size()-1) out << ", "
			}
			out << ")"
		} else if(attrs.type instanceof Basetype){
			def type = (Basetype) attrs.type
			out << g.link(controller: "basetype", action: "show", id: type.id){ type.name }
		}
	}	
	def typeSelector = { attrs,body ->
		out << g.textField(name: attrs.name, value: DatatypeHelper.disassemble(attrs.value))
	}
}
