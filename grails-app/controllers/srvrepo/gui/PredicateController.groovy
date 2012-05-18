package srvrepo.gui

import de.cocktail.srvrepo.types.DatatypeHelper
import srvrepo.*

class PredicateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [predicateInstanceList: Predicate.list(params), predicateInstanceTotal: Predicate.count()]
    }

    def create = {
        def predicateInstance = new Predicate()
        predicateInstance.properties = params
        return [predicateInstance: predicateInstance]
    }

    def save = {
		if(params.input instanceof String) params.input =  DatatypeHelper.assemble(params.input)
        def predicateInstance = new Predicate(params)
        if (predicateInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'predicate.label', default: 'Predicate'), predicateInstance.id])}"
            redirect(action: "show", id: predicateInstance.id)
        }
        else {
            render(view: "create", model: [predicateInstance: predicateInstance])
        }
    }

    def show = {
        def predicateInstance = Predicate.get(params.id)
        if (!predicateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
            redirect(action: "list")
        }
        else {
            [predicateInstance: predicateInstance]
        }
    }

    def edit = {
        def predicateInstance = Predicate.get(params.id)
        if (!predicateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [predicateInstance: predicateInstance]
        }
    }

    def update = {
		if(params.input instanceof String) params.input =  DatatypeHelper.assemble(params.input)
        def predicateInstance = Predicate.get(params.id)
        if (predicateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (predicateInstance.version > version) {
                    
                    predicateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'predicate.label', default: 'Predicate')] as Object[], "Another user has updated this Predicate while you were editing")
                    render(view: "edit", model: [predicateInstance: predicateInstance])
                    return
                }
            }
            predicateInstance.properties = params
            if (!predicateInstance.hasErrors() && predicateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'predicate.label', default: 'Predicate'), predicateInstance.id])}"
                redirect(action: "show", id: predicateInstance.id)
            }
            else {
                render(view: "edit", model: [predicateInstance: predicateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def predicateInstance = Predicate.get(params.id)
        if (predicateInstance) {
            try {
                predicateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'predicate.label', default: 'Predicate'), params.id])}"
            redirect(action: "list")
        }
    }
}
