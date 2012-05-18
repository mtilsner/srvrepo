package srvrepo.gui

import srvrepo.*
import groovyx.net.http.RESTClient

class ServiceController {
	def repositoryService
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [serviceInstanceList: Service.list(params), serviceInstanceTotal: Service.count()]
    }

    def create = {
        def serviceInstance = new Service()
        serviceInstance.properties = params
        return [serviceInstance: serviceInstance]
    }
    def save = {
		def specifications = []
		def vendor = Vendor.get(params.vendor)
		def s = repositoryService.enable(params.name, [], new URL(params.url), vendor)
        if (s?.id) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'service.label', default: 'Service'), s.id])}"
            redirect(action: "show", id: s.id)
        }
        else {
			def serviceInstance = new Service(name: params.name, specifications: specifications, vendor: vendor)
            render(view: "create", model: [serviceInstance: serviceInstance])
        }
    }

    def show = {
        def serviceInstance = Service.get(params.id)
        if (!serviceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
            redirect(action: "list")
        }
        else {
            [serviceInstance: serviceInstance]
        }
    }

    def edit = {
        def serviceInstance = Service.get(params.id)
        if (!serviceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
            redirect(action: "list")
        }
        else {
			if(params.addSpecification)
				return [serviceInstance: serviceInstance, addSpecification: true]
			else
            	return [serviceInstance: serviceInstance]
        }
    }

	def addSpecification = {
		def service = Service.get(params.id)
		service.addToSpecifications(Specification.get(params.specification))
		service.save(flush: true)
		redirect(action: "show", id: params.id)
	}
	def deleteSpecification = {
		def service = Service.get(params.service)
		service.removeFromSpecifications(Specification.get(params.id))
		redirect(action: "show", id: params.service)
	}

    def update = {
        def serviceInstance = Service.get(params.id)
        if (serviceInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (serviceInstance.version > version) {
                    
                    serviceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'service.label', default: 'Service')] as Object[], "Another user has updated this Service while you were editing")
                    render(view: "edit", model: [serviceInstance: serviceInstance])
                    return
                }
            }
            serviceInstance.properties = params
            if (!serviceInstance.hasErrors() && serviceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'service.label', default: 'Service'), serviceInstance.id])}"
                redirect(action: "show", id: serviceInstance.id)
            }
            else {
                render(view: "edit", model: [serviceInstance: serviceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def serviceInstance = Service.get(params.id)
        if (serviceInstance) {
            try {
                serviceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])}"
            redirect(action: "list")
        }
    }
}
