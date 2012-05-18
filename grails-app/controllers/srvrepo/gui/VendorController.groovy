package srvrepo.gui

import srvrepo.*

class VendorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [vendorInstanceList: Vendor.list(params), vendorInstanceTotal: Vendor.count()]
    }

    def create = {
        def vendorInstance = new Vendor()
        vendorInstance.properties = params
        return [vendorInstance: vendorInstance]
    }

    def save = {
        def vendorInstance = new Vendor(params)
        if (vendorInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'vendor.label', default: 'Vendor'), vendorInstance.id])}"
            redirect(action: "show", id: vendorInstance.id)
        }
        else {
            render(view: "create", model: [vendorInstance: vendorInstance])
        }
    }

    def show = {
        def vendorInstance = Vendor.get(params.id)
        if (!vendorInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
            redirect(action: "list")
        }
        else {
            [vendorInstance: vendorInstance]
        }
    }

    def edit = {
        def vendorInstance = Vendor.get(params.id)
        if (!vendorInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [vendorInstance: vendorInstance]
        }
    }

    def update = {
        def vendorInstance = Vendor.get(params.id)
        if (vendorInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (vendorInstance.version > version) {
                    
                    vendorInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vendor.label', default: 'Vendor')] as Object[], "Another user has updated this Vendor while you were editing")
                    render(view: "edit", model: [vendorInstance: vendorInstance])
                    return
                }
            }
            vendorInstance.properties = params
            if (!vendorInstance.hasErrors() && vendorInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vendor.label', default: 'Vendor'), vendorInstance.id])}"
                redirect(action: "show", id: vendorInstance.id)
            }
            else {
                render(view: "edit", model: [vendorInstance: vendorInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def vendorInstance = Vendor.get(params.id)
        if (vendorInstance) {
            try {
                vendorInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vendor.label', default: 'Vendor'), params.id])}"
            redirect(action: "list")
        }
    }
}
