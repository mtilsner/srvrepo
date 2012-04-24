package srvrepo.gui

import de.cocktail.srvrepo.types.DatatypeHelper
import srvrepo.*

class BasetypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [basetypeInstanceList: Basetype.list(params), basetypeInstanceTotal: Basetype.count()]
    }

    def create = {
        def basetypeInstance = new Basetype()
        basetypeInstance.properties = params
        return [basetypeInstance: basetypeInstance]
    }

    def save = {
        def basetypeInstance = new Basetype(params)
        if (basetypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'basetype.label', default: 'Basetype'), basetypeInstance.id])}"
            redirect(action: "show", id: basetypeInstance.id)
        }
        else {
            render(view: "create", model: [basetypeInstance: basetypeInstance])
        }
    }

    def show = {
        def basetypeInstance = Basetype.get(params.id)
        if (!basetypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
            redirect(action: "list")
        }
        else {
            [basetypeInstance: basetypeInstance]
        }
    }

    def edit = {
        def basetypeInstance = Basetype.get(params.id)
        if (!basetypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
            redirect(action: "list")
        }
        else {
			if(params.addAttribute)
				return [basetypeInstance: basetypeInstance, addAttribute: true]
			else
            	return [basetypeInstance: basetypeInstance]
        }
    }
	def addAttribute = {
		def attributeInstance = new Attribute(name: params.attribute.name,
											  type: DatatypeHelper.assemble(params.attribute.type),
											  basetype: Basetype.get(params.id))
		attributeInstance.save(flush: true)
		redirect(action: "edit", id: params.id)
	}
	def deleteAttribute = {
		def attributeInstance = Attribute.get(params.id)
		def name = attributeInstance.name
		def type = attributeInstance.basetype
        if (attributeInstance) {
            try {
                attributeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'attribute.label', default: 'Attribute'), '"'+name+'"'])}"
                redirect(action: "show", id: type.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'attribute.label', default: 'Attribute'), '"'+name+'"'])}"
                redirect(action: "show", id: type.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'attribute.label', default: 'Attribute'), '"'+name+'"'])}"
            redirect(action: "show", id: type.id)
        }	
	}

    def update = {
        def basetypeInstance = Basetype.get(params.id)
        if (basetypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (basetypeInstance.version > version) {
                    
                    basetypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'basetype.label', default: 'Basetype')] as Object[], "Another user has updated this Basetype while you were editing")
                    render(view: "edit", model: [basetypeInstance: basetypeInstance])
                    return
                }
            }
            basetypeInstance.properties = params
            if (!basetypeInstance.hasErrors() && basetypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'basetype.label', default: 'Basetype'), basetypeInstance.id])}"
                redirect(action: "show", id: basetypeInstance.id)
            }
            else {
                render(view: "edit", model: [basetypeInstance: basetypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def basetypeInstance = Basetype.get(params.id)
        if (basetypeInstance) {
            try {
                basetypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basetype.label', default: 'Basetype'), params.id])}"
            redirect(action: "list")
        }
    }
}
