package srvrepo.gui

import de.cocktail.srvrepo.types.DatatypeHelper
import de.cocktail.srvrepo.predicates.FormulaHelper
import srvrepo.*

class SpecificationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [specificationInstanceList: Specification.list(params), specificationInstanceTotal: Specification.count()]
    }

    def create = {
        def specificationInstance = new Specification()
        specificationInstance.properties = params
        return [specificationInstance: specificationInstance]
    }

    def save = {
		if(params.input && params.input instanceof String) params.input =  DatatypeHelper.assemble(params.input)
		if(params.output && params.output instanceof String) params.output =  DatatypeHelper.assemble(params.output)
		if(params.precondition && params.precondition instanceof String) params.precondition =  FormulaHelper.assemble(params.precondition)
		if(params.postcondition && params.postcondition instanceof String) params.postcondition =  FormulaHelper.assemble(params.postcondition)
        def specificationInstance = new Specification(params)
        if (specificationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'specification.label', default: 'Specification'), specificationInstance.id])}"
            redirect(action: "show", id: specificationInstance.id)
        }
        else {
            render(view: "create", model: [specificationInstance: specificationInstance])
        }
    }

    def show = {
        def specificationInstance = Specification.get(params.id)
        if (!specificationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
            redirect(action: "list")
        }
        else {
            [specificationInstance: specificationInstance]
        }
    }

    def edit = {
        def specificationInstance = Specification.get(params.id)
        if (!specificationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [specificationInstance: specificationInstance]
        }
    }

    def update = {
		if(params.input && params.input instanceof String) params.input =  DatatypeHelper.assemble(params.input)
		if(params.output && params.output instanceof String) params.output =  DatatypeHelper.assemble(params.output)
		if(params.precondition && params.precondition instanceof String) params.precondition =  FormulaHelper.assemble(params.precondition)
		if(params.postcondition && params.postcondition instanceof String) params.postcondition =  FormulaHelper.assemble(params.postcondition)
        def specificationInstance = Specification.get(params.id)
        if (specificationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (specificationInstance.version > version) {
                    
                    specificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'specification.label', default: 'Specification')] as Object[], "Another user has updated this Specification while you were editing")
                    render(view: "edit", model: [specificationInstance: specificationInstance])
                    return
                }
            }
            specificationInstance.properties = params
            if (!specificationInstance.hasErrors() && specificationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'specification.label', default: 'Specification'), specificationInstance.id])}"
                redirect(action: "show", id: specificationInstance.id)
            }
            else {
                render(view: "edit", model: [specificationInstance: specificationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def specificationInstance = Specification.get(params.id)
        if (specificationInstance) {
            try {
                specificationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'specification.label', default: 'Specification'), params.id])}"
            redirect(action: "list")
        }
    }
}
